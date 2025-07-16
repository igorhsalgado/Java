package br.com.sonora.models;

public class Favorites {
    public void include(Audio audio){
        if(audio.getTotalRating() >= 9){
            System.out.println(audio.getTitle() + " é considerado sucesso absoluto, e preferido por todos");
        } else {
            System.out.println(audio.getTitle() + " também é um dos que todo mundo está curtindo.");
        }
    }
}
