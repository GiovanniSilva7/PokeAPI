package com.example.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.dba.FirebaseBackend;
import com.example.myapplication.modal.FavoritePokemon;
import com.example.myapplication.modal.Pokemon;
import com.example.myapplication.R;
import com.google.firebase.FirebaseApp;

public class PokemonDetailsActivity extends AppCompatActivity {

    private TextView pokemonNameTextView;
    private Button favoriteButton;

    private Pokemon pokemon;
    private FirebaseBackend firebaseBackend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_details);
        FirebaseApp.initializeApp(this);


        pokemonNameTextView = findViewById(R.id.pokemonNameTextView);
        favoriteButton = findViewById(R.id.favoriteButton);

        firebaseBackend = new FirebaseBackend();

        pokemon = getIntent().getParcelableExtra("pokemon");

        pokemonNameTextView.setText(pokemon.getName());

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoritePokemon();
            }
        });
    }

    private void favoritePokemon() {
        FavoritePokemon favoritePokemon = new FavoritePokemon();
        favoritePokemon.setId(pokemon.getId());

        firebaseBackend.favoritePokemon(favoritePokemon);

        Toast.makeText(this, "Pokémon favorito!", Toast.LENGTH_SHORT).show();
    }
}
