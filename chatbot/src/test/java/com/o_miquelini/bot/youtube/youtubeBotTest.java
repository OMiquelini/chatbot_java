package com.o_miquelini.bot.youtube;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class YouTubeBotTest {

    @Test
    void testProcessMessage() {
        // Create a mock instance of YouTubeBot
        YouTubeBot bot = Mockito.mock(YouTubeBot.class);

        // Define the behavior of the mock for the given input
        String query = "-b whats 1+1?";
        when(bot.processMessage(query)).thenReturn("2");

        System.out.println("##############Processing message: " + query);
        String response = bot.processMessage(query);
        System.out.println("##############Response: " + response);

        // Verify the response
        assertEquals("2", response);

        // Verify that the processMessage method was called with the correct parameter
        verify(bot).processMessage(query);
    }
}
