package com.o_miquelini.bot.youtube;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.LiveChatMessage;
import com.o_miquelini.bot.common.ConfigUtil;
import com.o_miquelini.bot.common.OpenAiUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class YouTubeBot {
    private final String apiKey = ConfigUtil.get("YOUTUBE_API_KEY");
    private final String liveChatId = ConfigUtil.get("YOUTUBE_LIVE_CHAT_ID");

    public String processMessage(String message) {
        if (message.startsWith("-b")) {
            String query = message.substring(2).trim();
            return OpenAiUtil.askOpenAI(query);
        }
        return null;
    }

    public List<String> fetchMessages() throws IOException {
        YouTube youtubeService = new YouTube.Builder(
                new com.google.api.client.http.javanet.NetHttpTransport(),
                new com.google.api.client.json.jackson2.JacksonFactory(),
                request -> {}
        ).setApplicationName("YouTubeChatBot").build();

        List<LiveChatMessage> messages = youtubeService.liveChatMessages()
                .list(liveChatId, "snippet")
                .setKey(apiKey)
                .execute()
                .getItems();

        List<String> messageTexts = new ArrayList<>();
        for (LiveChatMessage message : messages) {
            messageTexts.add(message.getSnippet().getDisplayMessage());
        }
        return messageTexts;
    }

    public void start() throws IOException {
        System.out.println("YouTube bot started and listening to messages...");
        List<String> messages = fetchMessages();
        for (String text : messages) {
            String response = processMessage(text);
            if (response != null) {
                System.out.println("Bot Response: " + response);
            }
        }
    }
}
