package com.example.myapplication.modal;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Collections;
import java.util.List;

public class Pokemon implements Parcelable {
    private int id;
    private String name;
    private String imageUrl;  // URL da imagem do Pokémon
    private int weight;
    private int height;
    private List<String> types;  // Tipos do Pokémon

    public Pokemon(int id, String name, String imageUrl, int weight, int height, List<String> types) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.weight = weight;
        this.height = height;
        this.types = types;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
    protected Pokemon(Parcel in) {
        id = in.readInt();
        name = in.readString();
        imageUrl = in.toString();
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(imageUrl);
        dest.writeInt(weight);
        dest.writeInt(height);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}


