package main.java.com.practiceback.rnmapipractice.principal;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import main.java.com.practiceback.rnmapipractice.modelos.Personajes;
import main.java.com.practiceback.rnmapipractice.modelos.PersonajesRnMApi;

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
        List<Personajes> listaPersonajes = new ArrayList<>();
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

                String path = "https://rickandmortyapi.com/api/" + searchType + "/" + URLEncoder.encode(parameterSearch, "UTF-8");

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

                    String json = response.body();

                    System.out.println("STATUS: " + response.statusCode()); //Este me sirve para poder probar cual es el estatus de respuesta que me esta arrojando el servidor.
                    System.out.println("JSON: \n" + json); //Aqui lo que hago es imprimir el json tal cual me llega para verificar que los valores sean correctos

                    PersonajesRnMApi personajesRnMApi = gson.fromJson(json, PersonajesRnMApi.class);

                    System.out.println(personajesRnMApi); //Otro test que hago para verificar que el objeto personajesRnMApi si contenga la informacion del personaje que necesito.

                    Personajes newCharacter = new Personajes(personajesRnMApi);
                    listaPersonajes.add(newCharacter);

                    System.out.println("Personaje encontrado: " + "\n" + newCharacter);
                }catch (Exception e){
                    System.out.println("Error: " + e.getMessage());
                    e.printStackTrace();
                    //Ojo que falta crear la exeption espeifica para los diferentes errores.
                }

            } else if (searchAgreement.equalsIgnoreCase("n")) {
                System.out.println("Gracias por usar nuestra busqueda!");
                break;
            }
        }
    }
}
