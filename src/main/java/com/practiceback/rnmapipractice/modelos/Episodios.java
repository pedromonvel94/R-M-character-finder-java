package main.java.com.practiceback.rnmapipractice.modelos;

public class Episodios {
    int id;
    String name;
    String airDate;
    String episode;

    public Episodios(int id, String name, String airDate, String episode) {
        this.id = id;
        this.name = name;
        this.airDate = airDate;
        this.episode = episode;
    }

    public Episodios(EpisodiosRnMApi episodiosRnMApi ) {
        this.id = episodiosRnMApi.id();
        this.name = episodiosRnMApi.name();
        this.airDate = episodiosRnMApi.airDate();
        this.episode = episodiosRnMApi.episode();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAirDate() {
        return airDate;
    }

    public String getEpisode() {
        return episode;
    }

    @Override
    public String toString() {
        return "--------------------------------\n" +
                "Name: " + name + "\n" +
                "Air date: " + airDate + "\n" +
                "Episode: " + episode + "\n" +
                "--------------------------------";
    }
}
