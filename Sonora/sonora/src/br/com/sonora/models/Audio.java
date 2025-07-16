package br.com.sonora.models;

public abstract class Audio {
    private String title;
    private int totalReproductions;
    private int totalLikes;
    private int totalRating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalReproductions() {
        return totalReproductions;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public void like(){
        this.totalLikes++;
    }

    public void reproduce(){
        this.totalReproductions++;
    }
}
