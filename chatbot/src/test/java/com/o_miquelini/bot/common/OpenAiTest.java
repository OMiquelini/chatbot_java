package com.o_miquelini.bot.common;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.o_miquelini.bot.common.OpenAiUtil;

public class OpenAiTest {

    @Test
    public void testOpenAiUtil() {
        // Add assertions or test logic here
        String query = "-b when was i born?";

        System.out.println("##############Processing message: " + query);

        String response = OpenAiUtil.askOpenAI(query);

        System.out.println("##############Response: " + response);

        assertEquals("You were born on 29th December 1999", response);
        

    }

}
