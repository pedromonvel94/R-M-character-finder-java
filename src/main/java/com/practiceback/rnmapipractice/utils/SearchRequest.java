package main.java.com.practiceback.rnmapipractice.utils;

public class SearchRequest {
    private final SearchType searchType;
    private final CharacterQueryMode characterMode;
    private final EpisodeQueryMode episodeMode;
    private final String parameter;

    public SearchRequest(SearchType searchType, CharacterQueryMode characterMode, EpisodeQueryMode episodeMode, String parameter) {
        this.searchType = searchType;
        this.characterMode = characterMode;
        this.episodeMode = episodeMode;
        this.parameter = parameter;
    }

    public SearchType getSearchType() {
        return searchType;
    }

    public CharacterQueryMode getCharacterMode() {
        return characterMode;
    }

    public EpisodeQueryMode getEpisodeMode() {
        return episodeMode;
    }

    public String getParameter() {
        return parameter;
    }
}
