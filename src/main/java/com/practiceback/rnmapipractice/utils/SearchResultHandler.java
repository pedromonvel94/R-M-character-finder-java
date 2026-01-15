package main.java.com.practiceback.rnmapipractice.utils;

import com.google.gson.Gson;
import main.java.com.practiceback.rnmapipractice.modelos.*;

import java.io.IOException;
import java.util.List;

public class SearchResultHandler {

    private final Gson gson;
    private final SearchLists lists;

    public SearchResultHandler(Gson gson, SearchLists lists) {
        this.gson = gson;
        this.lists = lists;
    }

    public void handle(SearchRequest req, String json) throws IOException {
        switch (req.getSearchType()) {
            case CHARACTER -> handleCharacter(req, json);
            case LOCATION -> handleLocation(json);
            case EPISODE -> handleEpisode(req, json);
        }
    }

    private void handleCharacter(SearchRequest req, String json) throws IOException {
        if (req.getCharacterMode() == CharacterQueryMode.ID) {
            PersonajesRnMApi api = gson.fromJson(json, PersonajesRnMApi.class);
            Personajes personaje = new Personajes(api);
            lists.personajes().add(personaje);
            System.out.println("Personaje encontrado:\n" + personaje);
        } else {
            CharacterSearchResponse resp = gson.fromJson(json, CharacterSearchResponse.class);
            List<PersonajesRnMApi> resultados = resp.results();

            if (resultados == null || resultados.isEmpty()) {
                System.out.println("No se encontraron personajes con ese nombre.");
            } else {
                PersonajesRnMApi primero = resultados.get(0);
                Personajes personaje = new Personajes(primero);
                lists.personajes().add(personaje);
                System.out.println("Primer resultado:\n" + personaje);
            }
        }

        JsonFileWriter.saveJson("PersonajesRnMApi.json", gson.toJson(lists.personajes()));
    }

    private void handleLocation(String json) throws IOException {
        LocacionesRnMApi api = gson.fromJson(json, LocacionesRnMApi.class);
        Locaciones locacion = new Locaciones(api);
        lists.locaciones().add(locacion);

        System.out.println("Locación encontrada:\n" + locacion);
        JsonFileWriter.saveJson("LocacionesRnMApi.json", gson.toJson(lists.locaciones()));
    }

    private void handleEpisode(SearchRequest req, String json) throws IOException {
        if (req.getEpisodeMode() == EpisodeQueryMode.NUMBER) {
            EpisodiosRnMApi api = gson.fromJson(json, EpisodiosRnMApi.class);
            Episodios episodio = new Episodios(api);
            lists.episodios().add(episodio);
            System.out.println("Episodio encontrado:\n" + formatEpisode(episodio));
        } else {
            EpisodeSearchResponse resp = gson.fromJson(json, EpisodeSearchResponse.class);
            List<EpisodiosRnMApi> resultados = resp.results();

            if (resultados == null || resultados.isEmpty()) {
                System.out.println("No se encontraron episodios con ese nombre.");
            } else {
                EpisodiosRnMApi primero = resultados.get(0);
                Episodios episodio = new Episodios(primero);
                lists.episodios().add(episodio);
                System.out.println("Primer resultado:\n" + formatEpisode(episodio));
            }
        }

        JsonFileWriter.saveJson("EpisodiosRnMApi.json", gson.toJson(lists.episodios()));
    }

    private String formatEpisode(Episodios episodio) {
        return "--------------------------------\n" +
                "Name: " + episodio.getName() + "\n" +
                "Air date: " + episodio.getAirDate() + "\n" +
                "Episode: " + episodio.getEpisode() + "\n" +
                "--------------------------------";
    }
}