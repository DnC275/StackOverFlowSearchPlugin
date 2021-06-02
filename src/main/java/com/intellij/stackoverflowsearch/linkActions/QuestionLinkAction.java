package com.intellij.stackoverflowsearch.linkActions;

import com.google.gson.*;
import com.intellij.openapi.ui.Messages;
import com.intellij.stackoverflowsearch.response.subobjects.Owner;

import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;

public class QuestionLinkAction extends LinkAction {
    private Owner owner;
    private boolean isAnswered;
    private int viewCount;
    private int acceptedAnswerId;
    private int answerCount;
    private int score;
    private int lastActivityDate;
    private int creationDate;
    private int lastEditDate;
    private int questionId;
    private String title;

    public QuestionLinkAction(String title) {
        super(title);
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setAnswered(boolean isAnswered) {
        this.isAnswered = isAnswered;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public void setAcceptedAnswerId(int acceptedAnswerId) {
        this.acceptedAnswerId = acceptedAnswerId;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLastActivityDate(int lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public void setCreationDate(int creationDate) {
        this.creationDate = creationDate;
    }

    public void setLastEditDate(int lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void setLink(URL link) {
        this.link = link;
    }

    public static GsonBuilder getGsonBuilder() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(QuestionLinkAction.class, new JsonDeserializer<QuestionLinkAction>() {
            @Override
            public QuestionLinkAction deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                String title;
                if (jsonObject.has("title"))
                    title = jsonObject.get("title").getAsString();
                else
                    title = "";
                QuestionLinkAction question = new QuestionLinkAction(title);

                if (jsonObject.has("is_answered")){
                    question.setAnswered(jsonObject.get("is_answered").getAsBoolean());
                }

                if (jsonObject.has("view_count")){
                    question.setViewCount(jsonObject.get("view_count").getAsInt());
                }

                if (jsonObject.has("accepted_answer_id")){
                    question.setAcceptedAnswerId(jsonObject.get("accepted_answer_id").getAsInt());
                }

                if (jsonObject.has("answer_count")){
                    question.setAnswerCount(jsonObject.get("answer_count").getAsInt());
                }

                if (jsonObject.has("score")){
                    question.setScore(jsonObject.get("score").getAsInt());
                }

                if (jsonObject.has("link")){
                    try {
                        question.setLink(new URL(jsonObject.get("link").getAsString()));
                    } catch (MalformedURLException e) {
                        Messages.showMessageDialog("Something wrong with link", "Really sorry", Messages.getErrorIcon());
                    }
                }

                //TODO others parameters

                return question;
            }
        });
        return builder;
    }
}
