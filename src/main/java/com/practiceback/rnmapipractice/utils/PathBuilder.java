package main.java.com.practiceback.rnmapipractice.utils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class PathBuilder {
    private static final String BASE = "https://rickandmortyapi.com/api/";

    public String buildPath(SearchRequest req) {
        return switch (req.getSearchType()) {
            case CHARACTER -> buildCharacterPath(req);
            case LOCATION -> BASE + "location/" + req.getParameter();
            case EPISODE -> buildEpisodePath(req);
        };
    }

    private String buildCharacterPath(SearchRequest req) {
        if (req.getCharacterMode() == CharacterQueryMode.ID) {
            return BASE + "character/" + req.getParameter();
        }
        String encoded = URLEncoder.encode(req.getParameter(), StandardCharsets.UTF_8);
        return BASE + "character/?name=" + encoded;
    }

    private String buildEpisodePath(SearchRequest req) {
        if (req.getEpisodeMode() == EpisodeQueryMode.NUMBER) {
            return BASE + "episode/" + req.getParameter();
        }
        String encoded = URLEncoder.encode(req.getParameter(), StandardCharsets.UTF_8);
        return BASE + "episode/?name=" + encoded;
    }
}
