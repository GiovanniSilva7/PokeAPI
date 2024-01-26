package com.example.myapplication.apiclient;

import com.example.myapplication.modal.Pokemon;
import com.example.myapplication.modal.PokemonDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonApiService {
    @GET("pokemon/{id}")
    Call<PokemonDetails> getPokemonDetailsById(@Path("id") int id);


}
