package main.java.com.practiceback.rnmapipractice.utils;

import java.util.Scanner;

public class ConsolePrompts {

    private final Scanner scanner;

    public ConsolePrompts(Scanner scanner) {
        this.scanner = scanner;
    }

    public SearchRequest askSearchRequestOrExit() {
        while (true) {
            System.out.println("""
                Cual tipo de busqueda quieres hacer?
                1. Personaje(P)
                2. Locaciones(L)
                3. Episodios(E)
                Escribe EXIT en cualquier momento para salir.
                """);

            String input = readLineOrExit();

            SearchType type = parseSearchType(input);
            if (type == null) {
                System.out.println("Opción no válida");
                continue;
            }

            return switch (type) {
                case CHARACTER -> askCharacterRequest();
                case LOCATION -> askLocationRequest();
                case EPISODE -> askEpisodeRequest();
            };
        }
    }

    private SearchRequest askCharacterRequest() {
        while (true) {
            System.out.println("Deseas buscar por NOMBRE (NM) o por ID (ID)");
            String mode = readLineOrExit().trim().toLowerCase();

            if (mode.equals("id")) {
                String id = askNonEmpty("Ingresa el ID (Opciones del 1 al 826): ");
                return new SearchRequest(SearchType.CHARACTER, CharacterQueryMode.ID, null, id);
            }

            if (mode.equals("nm")) {
                String name = askNonEmpty("Ingresa el nombre: ");
                return new SearchRequest(SearchType.CHARACTER, CharacterQueryMode.NAME, null, name);
            }

            System.out.println("Opción no válida");
        }
    }

    private SearchRequest askLocationRequest() {
        String id = askNonEmpty("Ingresa el ID (Opciones del 1 al 126): ");
        return new SearchRequest(SearchType.LOCATION, null, null, id);
    }

    private SearchRequest askEpisodeRequest() {
        while (true) {
            System.out.println("Deseas buscar por NOMBRE (NM) o por # de episodio (#)");
            String mode = readLineOrExit().trim().toLowerCase();

            if (mode.equals("#")) {
                String num = askNonEmpty("Ingresa el # del episodio (Opciones del 1 al 51): ");
                return new SearchRequest(SearchType.EPISODE, null, EpisodeQueryMode.NUMBER, num);
            }

            if (mode.equals("nm")) {
                String name = askNonEmpty("Ingresa el nombre del episodio: ");
                return new SearchRequest(SearchType.EPISODE, null, EpisodeQueryMode.NAME, name);
            }

            System.out.println("Opción no válida");
        }
    }

    private String askNonEmpty(String prompt) {
        while (true) {
            System.out.println(prompt);
            String value = readLineOrExit().trim();
            if (!value.isBlank()) return value;
            System.out.println("No puede estar vacío.");
        }
    }

    private String readLineOrExit() {
        String line = scanner.nextLine();
        if (line.equalsIgnoreCase("exit")) {
            System.out.println("Saliendo...");
            throw new ExitRequestedException();
        }
        return line;
    }

    private SearchType parseSearchType(String input) {
        input = input.trim().toLowerCase();

        if (input.equals("1") || input.equals("p") || input.equals("personaje")) return SearchType.CHARACTER;
        if (input.equals("2") || input.equals("l") || input.equals("locaciones")) return SearchType.LOCATION;
        if (input.equals("3") || input.equals("e") || input.equals("episodios")) return SearchType.EPISODE;

        return null;
    }

    public static class ExitRequestedException extends RuntimeException {
    }
}

