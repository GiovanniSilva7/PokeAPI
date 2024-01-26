package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.apiclient.PokemonApiClient;
import com.example.myapplication.activity.PokemonDetailsActivity;
import com.example.myapplication.modal.Pokemon;
import com.example.myapplication.modal.PokemonDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText pokemonIdEditText;
    private Button searchButton;

    private PokemonApiClient pokemonApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pokemonIdEditText = findViewById(R.id.pokemonIdEditText);
        searchButton = findViewById(R.id.searchButton);

        pokemonApiClient = new PokemonApiClient();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPokemon();
            }
        });
    }

    private void searchPokemon() {
        String pokemonIdString = pokemonIdEditText.getText().toString().trim();

        if (!pokemonIdString.isEmpty()) {
            try {
                int id = Integer.parseInt(pokemonIdString);

                pokemonApiClient.getPokemonDetailsById(id).enqueue(new Callback<PokemonDetails>() {
                    @Override
                    public void onResponse(Call<PokemonDetails> call, Response<PokemonDetails> response) {
                        if (response.isSuccessful()) {
                            PokemonDetails pokemonDetails = response.body();

                            Intent intent = new Intent(MainActivity.this, PokemonDetailsActivity.class);
                            intent.putExtra("pokemon", pokemonDetails);
                            startActivity(intent);
                        } else {
                        }
                    }

                    @Override
                    public void onFailure(Call<PokemonDetails> call, Throwable t) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Erro", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
            } catch (NumberFormatException e) {
                // Tratar caso a conversão para inteiro falhe
                Toast.makeText(this, "Digite um número válido para o ID do Pokémon.", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Tratar caso o campo esteja vazio
            Toast.makeText(this, "Digite um ID do Pokémon.", Toast.LENGTH_SHORT).show();
        }
    }
}

