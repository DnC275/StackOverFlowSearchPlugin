package com.intellij.stackoverflowsearch.request;

import com.google.gson.*;
import com.intellij.stackoverflowsearch.linkActions.LinkAction;
import com.intellij.stackoverflowsearch.linkActions.QuestionLinkAction;
import com.intellij.stackoverflowsearch.exceptions.ConnectionException;
import com.intellij.stackoverflowsearch.exceptions.PluginException;
import com.intellij.stackoverflowsearch.exceptions.URLException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class SearchRequest implements Request {
    private final String link;

    public SearchRequest(String link){
        this.link = link;
    }

    public List<LinkAction> perform() throws PluginException {
        URL url = null;
        try {
            url = new URL(link);
        }
        catch (MalformedURLException e){
            throw new URLException(e);
        }
        HttpURLConnection connection;
        BufferedInputStream bufferedInputStream;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            String contentEncoding = connection.getContentEncoding();
            if (connection.getContentEncoding().equals("gzip")) {
                bufferedInputStream = new BufferedInputStream(new GZIPInputStream(connection.getInputStream()));
            }
            else{
                bufferedInputStream = new BufferedInputStream(connection.getInputStream());
            }
        }
        catch (IOException exception) {
            throw new ConnectionException(exception);
        }

        List<LinkAction> questionActions = new ArrayList<>();
        JsonElement jsonElement = JsonParser.parseReader(new InputStreamReader(bufferedInputStream, StandardCharsets.UTF_8));
        if (jsonElement.isJsonObject()){
            JsonObject jsonObject = jsonElement.getAsJsonObject();
//            QuestionLinkAction question = new QuestionLinkAction();
            if (jsonObject.has("items")) {
                JsonArray jsonArray = jsonObject.get("items").getAsJsonArray();
                Gson gson = QuestionLinkAction.getGsonBuilder().create();
                for (JsonElement e : jsonArray) {
                    QuestionLinkAction question = gson.fromJson(e, QuestionLinkAction.class);
                    questionActions.add(question);
                }
            }
//            System.out.println("OK");
        }
        return questionActions;
    }
}
