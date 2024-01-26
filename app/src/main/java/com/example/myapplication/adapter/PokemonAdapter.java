package com.example.myapplication.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.modal.Pokemon;
import com.example.myapplication.R;

import java.util.List;


public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {
    private List<Pokemon> pokemonList;
    private OnItemClickListener clickListener;

    public PokemonAdapter(List<Pokemon> pokemonList, OnItemClickListener clickListener) {
        this.pokemonList = pokemonList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon pokemon = pokemonList.get(position);
        holder.bind(pokemon);
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Pokemon pokemon);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView pokemonNameTextView;
        private TextView pokemonDetailsTextView;

        private ImageView pokemonImageView;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemonNameTextView = itemView.findViewById(R.id.pokemonNameTextView);
            pokemonDetailsTextView = itemView.findViewById(R.id.pokemonDetailsTextView);
            pokemonImageView = itemView.findViewById(R.id.pokemonImageView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && clickListener != null) {
                        clickListener.onItemClick(pokemonList.get(position));
                    }
                }
            });
        }

        public void bind(Pokemon pokemon) {
            pokemonNameTextView.setText(pokemon.getName());
            // Exiba os detalhes do Pokémon
            String details = "Peso: " + pokemon.getWeight() + " kg\n"
                    + "Altura: " + pokemon.getHeight() + " m\n"
                    + "Tipos: " + TextUtils.join(", ", pokemon.getTypes());
            pokemonDetailsTextView.setText(details);
        }
    }
}
