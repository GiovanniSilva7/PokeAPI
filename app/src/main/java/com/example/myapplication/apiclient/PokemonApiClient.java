package com.example.myapplication.apiclient;


import com.example.myapplication.modal.Pokemon;
import com.example.myapplication.modal.PokemonDetails;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonApiClient {
    private static final String BASE_URL = "https://pokeapi.co/api/v2/";
    private final PokemonApiService apiService;

    public PokemonApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(PokemonApiService.class);
    }

    public Call<PokemonDetails> getPokemonDetailsById(int id) {
        return apiService.getPokemonDetailsById(id);
    }


}