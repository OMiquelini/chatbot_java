package com.o_miquelini;

import com.o_miquelini.bot.twitch.TwitchBot;
import com.o_miquelini.bot.youtube.YouTubeBot;
import com.o_miquelini.bot.whatsapp.WhatsAppBot;

public class Main {
    public static void main(String[] args) {
        int botType = Integer.parseInt(args[0]);

        try {
            if ((botType & 1) != 0) {
                new TwitchBot().start();
            }
            if ((botType & 2) != 0) {
                new YouTubeBot().start();
            }
            if ((botType & 4) != 0) {
                //new WhatsAppBot().start(); TODO: implement whatsapp bot
            }
            if (botType == 0) {
                System.out.println("No bot will run.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
