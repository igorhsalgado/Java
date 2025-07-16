package br.com.sonora.main;

import br.com.sonora.models.Favorites;
import br.com.sonora.models.Music;
import br.com.sonora.models.Podcast;

public class Main {
    public static void main(String[] args) {
        Music mySong = new Music();
        mySong.setTitle("Artista Genérico");
        mySong.setArtist("Veigh");

        for (int i = 0; i < 1000; i++) {
            mySong.reproduce();
        }

        for (int i = 0; i < 50; i++) {
            mySong.like();
        }

        Podcast myPodcast = new Podcast();
        myPodcast.setTitle("BolhaDev");
        myPodcast.setHost("Marcos Mendes");

        for (int i = 0; i < 5000; i++) {
            myPodcast.reproduce();
        }

        for (int i = 0; i < 1000; i++) {
            myPodcast.like();
        }

        Favorites favorites = new Favorites();
        favorites.include(mySong);
        favorites.include(myPodcast);
    }
}