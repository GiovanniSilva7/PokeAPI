package com.example.myapplication.dba;

import androidx.annotation.NonNull;

import com.example.myapplication.modal.FavoritePokemon;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

// FirebaseBackend.java
public class FirebaseBackend {
    private DatabaseReference databaseReference;

    public FirebaseBackend() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void favoritePokemon(FavoritePokemon favoritePokemon) {
        DatabaseReference favoritesRef = databaseReference.child("favorites");

        favoritesRef.child(String.valueOf(favoritePokemon.getId())).setValue(favoritePokemon);
    }


    public List<FavoritePokemon> getFavoritePokemon() {
        final List<FavoritePokemon> favoritePokemonList = new ArrayList<>();

        DatabaseReference favoritesRef = databaseReference.child("favorites");

        favoritesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    FavoritePokemon favoritePokemon = snapshot.getValue(FavoritePokemon.class);
                    if (favoritePokemon != null) {
                        favoritePokemonList.add(favoritePokemon);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        return favoritePokemonList;
    }


}
