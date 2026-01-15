package main.java.com.practiceback.rnmapipractice.utils;

public enum SearchType {
    CHARACTER("character"),
    LOCATION("location"),
    EPISODE("episode");

    private final String apiPath;

    SearchType(String apiPath) {
        this.apiPath = apiPath;
    }

    public String apiPath() {
        return apiPath;
    }
}