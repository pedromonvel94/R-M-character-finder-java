package main.java.com.practiceback.rnmapipractice.utils;

import com.google.gson.Gson;
import main.java.com.practiceback.rnmapipractice.utils.ApiClient.HttpStatusException;
import main.java.com.practiceback.rnmapipractice.utils.ConsolePrompts.ExitRequestedException;

import java.util.Scanner;

public class SearchFlow {

    private final ConsolePrompts prompts;
    private final PathBuilder pathBuilder;
    private final ApiClient apiClient;
    private final SearchResultHandler handler;

    public SearchFlow(Scanner scanner, Gson gson) {
        this.prompts = new ConsolePrompts(scanner);
        this.pathBuilder = new PathBuilder();
        this.apiClient = new ApiClient();
        SearchLists lists = new SearchLists();
        this.handler = new SearchResultHandler(gson, lists);
    }

    public void runOnce() {
        try {
            SearchRequest req = prompts.askSearchRequestOrExit();
            String url = pathBuilder.buildPath(req);

            String json = apiClient.getJson(url);

            handler.handle(req, json);

        } catch (ExitRequestedException e) {
            System.out.println("Saliendo...");
            System.exit(0);

        } catch (HttpStatusException e) {
            System.out.println(e.getMessage());
            System.out.println("Respuesta del server:\n" + e.getResponseBody());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
