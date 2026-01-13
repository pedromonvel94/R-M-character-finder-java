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

        while(true){
            System.out.println("Deseas realizar una busqueda? (Y/N)");
            String searchAgreement = scanner.nextLine();
            if (searchAgreement.equalsIgnoreCase("y")){

                Boolean menuElection = false;
                String searchType = "";
                String typeCharacterSearch = "";

                while (!menuElection){
                    System.out.println("Cual tipo de busqueda quieres hacer?" + "\n" +
                            "1. Personaje(P)" + "\n" +
                            "2. Locaciones(L)" + "\n" +
                            "3. Episodios(E)" + "\n" +
                            "O escribe EXIT en cualquier momento para salir.");

                    searchType = scanner.nextLine(); //OJO aqui falta que dependiendo de lo que reciba por consola el searchtype es character, location o episode

                    if(searchType.equalsIgnoreCase("p")||searchType.equals("1")||searchType.equalsIgnoreCase("personaje")){
                        searchType = "character";

                        System.out.println("Deseas buscar por NOMBRE (NM) o por ID (ID)");
                        typeCharacterSearch = scanner.nextLine();

                        menuElection = true;
                    } else if(searchType.equalsIgnoreCase("l")||searchType.equals("2") || searchType.equals("locaciones")) {
                        searchType = "location";
                        menuElection = true;
                    } else if(searchType.equalsIgnoreCase("e")||searchType.equals("3") || searchType.equals("episodios")) {
                        searchType = "episode";
                        menuElection = true;
                    } else if(searchType.equalsIgnoreCase("exit")){
                        System.out.println("Saliendo...");
                        break;
                    } else {
                        System.out.println("Opción no válida");
                    }
                }

                String path = "https://rickandmortyapi.com/api/" + URLEncoder.encode(searchType + "/"); //ojo que aqui me falta donde estan las comillas debe ir la variable que me brinde, ya sea el id, el nombre o lo que vaya a buscar dependiendo de la categoria y el objeto que vaya a usar.

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(path))
                        .build();
            } else if (searchAgreement.equalsIgnoreCase("n")) {
                System.out.println("Gracias por usar nuestra busqueda!");
                break;
            }


        }

    }
}
