package main.java.com.practiceback.rnmapipractice.utils;

import main.java.com.practiceback.rnmapipractice.modelos.Episodios;
import main.java.com.practiceback.rnmapipractice.modelos.Locaciones;
import main.java.com.practiceback.rnmapipractice.modelos.Personajes;

import java.util.ArrayList;
import java.util.List;

public class SearchLists {
    private final List<Personajes> personajes = new ArrayList<>();
    private final List<Locaciones> locaciones = new ArrayList<>();
    private final List<Episodios> episodios = new ArrayList<>();

    public List<Personajes> personajes() {
        return personajes;
    }

    public List<Locaciones> locaciones() {
        return locaciones;
    }

    public List<Episodios> episodios() {
        return episodios;
    }
}