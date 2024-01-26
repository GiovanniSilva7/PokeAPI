package com.example.myapplication.modal;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Type;
import java.util.List;

public class PokemonDetails implements Parcelable {
    private int id;
    private String name;
    private String imageUrl;
    private int weight;
    private int height;
    private List<Type> types;



    public PokemonDetails(Parcel in) {
        id = in.readInt();
        name = in.readString();
        imageUrl = in.toString();
    }
    public static final Parcelable.Creator<PokemonDetails> CREATOR = new Parcelable.Creator<PokemonDetails>() {
        @Override
        public PokemonDetails createFromParcel(Parcel in) {
            return new PokemonDetails(in);
        }

        @Override
        public PokemonDetails[] newArray(int size) {
            return new PokemonDetails[size];
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

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }
}
