package com.app.idnbin.MainScreen.Chat.Support.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BotReply {

    @SerializedName("responses")
    @Expose
    private List<BotResponse> responses = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public BotReply() {
    }

    /**
     *
     * @param responses
     */
    public BotReply(List<BotResponse> responses) {
        super();
        this.responses = responses;
    }

    public List<BotResponse> getResponses() {
        return responses;
    }

    public void setResponses(List<BotResponse> responses) {
        this.responses = responses;
    }

}