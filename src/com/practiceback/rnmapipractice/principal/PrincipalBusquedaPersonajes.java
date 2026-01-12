package com.practiceback.rnmapipractice.principal;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Scanner;

public class PrincipalBusquedaPersonajes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        System.out.println("Cual tipo de busqueda quieres hacer?" + "\n" +
                "1. Personaje" + "\n" +
                "2. Locaciones" + "\n" +
                "3. Episodios");

        String searchType = scanner.nextLine(); //OJO aqui falta que dependiendo de lo que reciba por consola el seartype es character, location o episode

        String path = "https://rickandmortyapi.com/api/" + URLEncoder.encode(searchType + ""); //ojo que aqui me falta donde estan las comillas debe ir la variable que me brinde, ya sea el id, el nombre o lo que vaya a buscar dependiendo de la categoria y el objeto que vaya a usar.

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(path))
                .build();



    }
}
