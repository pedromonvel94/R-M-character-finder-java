package main.java.com.practiceback.rnmapipractice.modelos;

import com.google.gson.annotations.SerializedName;

public record EpisodiosRnMApi(int id, String name, @SerializedName("air_date") String airDate, String episode) {

}
