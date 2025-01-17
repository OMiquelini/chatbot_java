package com.o_miquelini.bot.twitch;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.o_miquelini.bot.common.ConfigUtil;
import com.o_miquelini.bot.common.OpenAiUtil;

public class TwitchBot {
    private final String oauthToken = ConfigUtil.get("TWITCH_OAUTH_TOKEN");

    public String processMessage(String message) {
        if (message.startsWith("-b")) {
            String query = message.substring(2).trim();
            return OpenAiUtil.askOpenAI(query);
        }
        return null;
    }

    public void start() {
        TwitchClient twitchClient = TwitchClientBuilder.builder()
                .withEnableChat(true)
                .withChatAccount(new com.github.philippheuer.credentialmanager.domain.OAuth2Credential("twitch", oauthToken))
                .build();

        String channelName = ConfigUtil.get("TWITCH_CHANNEL_NAME");
        twitchClient.getChat().joinChannel(channelName);

        twitchClient.getEventManager().onEvent(ChannelMessageEvent.class, event -> {
            String response = processMessage(event.getMessage());
            if (response != null) {
                twitchClient.getChat().sendMessage(event.getChannel().getName(), response);
            }
        });

        System.out.println("Twitch bot started and listening to messages...");
    }
}
