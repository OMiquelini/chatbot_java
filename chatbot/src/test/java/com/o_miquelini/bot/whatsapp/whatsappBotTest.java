package com.o_miquelini.bot.whatsapp;

import com.o_miquelini.bot.common.OpenAiUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class WhatsAppBotTest {

   /* @Test
    void testMessageProcessing() {
        String testMessage = "-b Define machine learning.";
        String mockResponse = "Machine learning is a subset of AI.";

        // Mock OpenAI utility
        OpenAiUtil mockOpenAiUtil = mock(OpenAiUtil.class);
        when(mockOpenAiUtil.askOpenAI(anyString(), anyString(), anyString()))
                .thenReturn(mockResponse);

        // Simulate WhatsApp message processing
        WhatsAppBot bot = new WhatsAppBot("mock_openai_key");
        bot.start();

        String response = bot.processMessage(testMessage);
        assertEquals(mockResponse, response);
    }*/
}
