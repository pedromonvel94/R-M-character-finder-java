package main.java.com.practiceback.rnmapipractice.principal;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import main.java.com.practiceback.rnmapipractice.modelos.*;
import main.java.com.practiceback.rnmapipractice.utils.JsonFileWriter;
import main.java.com.practiceback.rnmapipractice.utils.SearchFlow;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalBusquedaPersonajes {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .setPrettyPrinting()
                .create();

        SearchFlow flow = new SearchFlow(scanner, gson);

        while (true) {
            System.out.println("Deseas realizar una busqueda? (Y/N)");
            String searchAgreement = scanner.nextLine();

            if (searchAgreement.equalsIgnoreCase("exit")) {
                System.out.println("Saliendo...");
                return;
            }

            if (searchAgreement.equalsIgnoreCase("y")) {
                flow.runOnce();
            } else if (searchAgreement.equalsIgnoreCase("n")) {
                System.out.println("Gracias por usar nuestra busqueda!");
                break;
            } else {
                System.out.println("Opción no válida. Escribe Y o N (o EXIT).");
            }
        }
    }
}

        /*
        List<Personajes> listaPersonajes = new ArrayList<>();
        List<Locaciones> listaLocaciones = new ArrayList<>();
        List<Episodios> listaEpisodios = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .setPrettyPrinting()
                .create();

        while(true){
            System.out.println("Deseas realizar una busqueda? (Y/N)");
            String searchAgreement = scanner.nextLine();
            if (searchAgreement.equalsIgnoreCase("y")){

                Boolean menuElection = false;
                String searchType = "";
                String typeCharacterSearch = "";
                String parameterSearch = "";
                String base = "https://rickandmortyapi.com/api/";
                String path;

                while (!menuElection){
                    System.out.println("Cual tipo de busqueda quieres hacer?" + "\n" +
                            "1. Personaje(P)" + "\n" +
                            "2. Locaciones(L)" + "\n" +
                            "3. Episodios(E)" + "\n" +
                            "Escribe EXIT en cualquier momento para salir.");

                    searchType = scanner.nextLine();

                    if (searchType.equalsIgnoreCase("exit")) {
                        System.out.println("Saliendo...");
                        return;
                    }

                    if(searchType.equalsIgnoreCase("p")||searchType.equals("1")||searchType.equalsIgnoreCase("personaje")){
                        searchType = "character";

                        System.out.println("Deseas buscar por NOMBRE (NM) o por ID (ID)");
                        typeCharacterSearch = scanner.nextLine();

                        switch (typeCharacterSearch.trim().toLowerCase()) {
                            case "id" -> {
                                System.out.println("Ingresa el ID (Opciones del 1 al 826): ");
                                parameterSearch = scanner.nextLine();
                                if (parameterSearch.equalsIgnoreCase("exit")) {
                                    return;
                                }
                                menuElection = true;
                            }
                            case "nm" -> {
                                System.out.println("Ingresa el nombre: ");
                                parameterSearch = scanner.nextLine();
                                if (parameterSearch.equalsIgnoreCase("exit")) {
                                    return;
                                }
                                menuElection = true;
                            }
                            case "exit" -> {
                                System.out.println("Saliendo...");
                                return;
                            }
                            default -> System.out.println("Opción no válida");
                        }

                    } else if(searchType.equalsIgnoreCase("l")||searchType.equals("2") || searchType.equals("locaciones")) {
                        searchType = "location";

                        System.out.println("Ingresa el ID (Opciones del 1 al 126): ");
                        parameterSearch = scanner.nextLine();
                        if (parameterSearch.equalsIgnoreCase("exit")){
                            return;
                        }

                        menuElection = true;
                    } else if(searchType.equalsIgnoreCase("e")||searchType.equals("3") || searchType.equals("episodios")) {
                        searchType = "episode";

                        System.out.println("Deseas buscar por NOMBRE (NM) o por # de episodio (#)");
                        typeCharacterSearch = scanner.nextLine();

                        switch (typeCharacterSearch.trim().toLowerCase()) {
                            case "#" -> {
                                System.out.println("Ingresa el # del episodio (Opciones del 1 al 51): ");
                                parameterSearch = scanner.nextLine();
                                if (parameterSearch.equalsIgnoreCase("exit")) {
                                    return;
                                }
                                menuElection = true;
                            }
                            case "nm" -> {
                                System.out.println("Ingresa el nombre del episodio: ");
                                parameterSearch = scanner.nextLine();
                                if (parameterSearch.equalsIgnoreCase("exit")) {
                                    return;
                                }
                                menuElection = true;
                            }
                            case "exit" -> {
                                System.out.println("Saliendo...");
                                return;
                            }
                            default -> System.out.println("Opción no válida");
                        }
                    } else if(searchType.equalsIgnoreCase("exit")){
                        System.out.println("Saliendo...");
                        break;
                    } else {
                        System.out.println("Opción no válida");
                    }
                }

                if (searchType.equals("character")) {
                    path = typeCharacterSearch.equalsIgnoreCase("id") ? base + "character/" + parameterSearch : base + "character/?name=" + URLEncoder.encode(parameterSearch, "UTF-8");
                } else if (searchType.equals("episode")) {
                    path = typeCharacterSearch.equalsIgnoreCase("#") ? base + "episode/" + parameterSearch : base + "episode/?name=" + URLEncoder.encode(parameterSearch, "UTF-8");
                } else {
                    path = base + "location/" + parameterSearch;
                }

                try{
                    HttpClient client = HttpClient.newBuilder()
                            .version(HttpClient.Version.HTTP_1_1)
                            .followRedirects(HttpClient.Redirect.NORMAL)
                            .build();

                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(path))
                            .header("User-Agent", "JavaHttpClient") // algunos servers se ponen intensos sin UA
                            .GET()
                            .build();

                    HttpResponse<String> response = client
                            .send(request, HttpResponse.BodyHandlers.ofString());

                    int status = response.statusCode();
                    if (status != 200) {
                        System.out.println("Error HTTP: " + status);
                        System.out.println(response.body());
                        continue;
                    }

                    String json = response.body();

                    //System.out.println("STATUS: " + response.statusCode()); //Este me sirve para poder probar cual es el estatus de respuesta que me esta arrojando el servidor.
                    //System.out.println("JSON: \n" + json); //Aqui lo que hago es imprimir el json tal cual me llega para verificar que los valores sean correctos

                    switch (searchType) {
                        case "character" -> {
                            if (typeCharacterSearch.equalsIgnoreCase("id")) {
                                PersonajesRnMApi personajesRnMApi = gson.fromJson(json, PersonajesRnMApi.class);
                                Personajes newCharacter = new Personajes(personajesRnMApi);
                                listaPersonajes.add(newCharacter);
                                System.out.println("Personaje encontrado:\n" + newCharacter);

                            } else {
                                CharacterSearchResponse resp = gson.fromJson(json, CharacterSearchResponse.class);
                                List<PersonajesRnMApi> resultados = resp.results();

                                if (resultados == null || resultados.isEmpty()) {
                                    System.out.println("No se encontraron personajes con ese nombre.");
                                } else {
                                    PersonajesRnMApi primero = resultados.get(0);
                                    Personajes personaje = new Personajes(primero);
                                    listaPersonajes.add(personaje);
                                    System.out.println("Primer resultado:\n" + personaje);
                                }
                            }
                            JsonFileWriter.saveJson("PersonajesRnMApi.json", gson.toJson(listaPersonajes));
                        }
                        case "location" -> {
                            LocacionesRnMApi locacionesRnMApi = gson.fromJson(json, LocacionesRnMApi.class);
                            Locaciones newLocation = new Locaciones(locacionesRnMApi);
                            listaLocaciones.add(newLocation);
                            System.out.println("Locación encontrada:\n" + newLocation);

                            JsonFileWriter.saveJson("LocacionesRnMApi.json", gson.toJson(listaLocaciones));
                        }
                        case "episode" -> {
                            EpisodiosRnMApi episodiosRnMApi = gson.fromJson(json, EpisodiosRnMApi.class);
                            Episodios newEpisode = new Episodios(episodiosRnMApi);
                            listaEpisodios.add(newEpisode);
                            System.out.println("Episodio encontrado:\n" + newEpisode);

                            JsonFileWriter.saveJson("EpisodiosRnMApi.json", gson.toJson(listaEpisodios));
                        }
                        default -> System.out.println("Tipo de búsqueda no soportado: " + searchType);
                    }
                }catch (Exception e){
                    System.out.println("Error: " + e.getMessage());
                    e.printStackTrace();
                    //Ojo que me falta crear la exception especifica para cada uno los diferentes errores pero por ahora lo puedo dejar asi.
                }

            } else if (searchAgreement.equalsIgnoreCase("n")) {
                System.out.println("Gracias por usar nuestra busqueda!");
                break;
            }

        }
    }
}*/