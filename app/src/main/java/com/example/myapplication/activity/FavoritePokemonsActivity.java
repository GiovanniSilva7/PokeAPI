package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.PokemonAdapter;
import com.example.myapplication.apiclient.PokemonApiClient;
import com.example.myapplication.dba.FirebaseBackend;
import com.example.myapplication.modal.FavoritePokemon;
import com.example.myapplication.modal.Pokemon;
import com.example.myapplication.modal.PokemonDetails;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

// FavoritePokemonsActivity.java
public class FavoritePokemonsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PokemonAdapter pokemonAdapter;

    private PokemonApiClient pokemonApiClient;

    private FirebaseBackend firebaseBackend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_pokemons);

        recyclerView = findViewById(R.id.favoriteRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseBackend = new FirebaseBackend();

        // Carregue a lista de Pokémon favoritos
        try {
            loadFavoritePokemonList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadFavoritePokemonList() throws IOException {
        // Obtenha a lista de Pokémon favoritos do Firebase
        List<FavoritePokemon> favoritePokemonList = firebaseBackend.getFavoritePokemon();

        // Configure o adaptador e vincule-o ao RecyclerView
        pokemonAdapter = new PokemonAdapter(convertToPokemonList(favoritePokemonList), new PokemonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Pokemon pokemon) {
               // Intent intent = new Intent("favorite", FavoritePokemonsActivity.class);
                //startActivity(intent);
            }
        });

        recyclerView.setAdapter(pokemonAdapter);
    }

    private List<Pokemon> convertToPokemonList(List<FavoritePokemon> favoritePokemonList) {
        List<Pokemon> pokemonList = new ArrayList<>();

        for (FavoritePokemon favoritePokemon : favoritePokemonList) {
            try {


                // Obtenha os detalhes completos do Pokémon usando o PokemonApiClient
                PokemonDetails pokemonDetails = pokemonApiClient.getPokemonDetailsById(favoritePokemon.getId()).execute().body();

                // Crie um objeto Pokemon com os detalhes obtidos
                Pokemon pokemon = new Pokemon(
                        pokemonDetails.getId(),
                        pokemonDetails.getName(),
                        pokemonDetails.getImageUrl(),
                        pokemonDetails.getWeight(),
                        pokemonDetails.getHeight(),
                        extractTypes(pokemonDetails.getTypes())  // Método para extrair tipos
                );

                pokemonList.add(pokemon);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return pokemonList;
    }


    private List<String> extractTypes(List<Type> types) {
        List<String> typeNames = new ArrayList<>();
        for (Type type : types) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                typeNames.add(type.getTypeName());
            }
        }
        return typeNames;
    }
}

