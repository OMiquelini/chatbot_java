package com.o_miquelini.bot.common;

import okhttp3.*;
import org.json.JSONObject;
import com.o_miquelini.bot.common.PdfUtil;

import java.io.IOException;

public class OpenAiUtil {
    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String OPENAI_API_KEY = ConfigUtil.get("openai.api.key");
    private static final String OPENAI_MODEL = ConfigUtil.get("openai.model");
    private static final String CONTEXT = PdfUtil.extractTextFromPdf();

    public static String askOpenAI(String query) {
        OkHttpClient client = new OkHttpClient();

        JSONObject payload = new JSONObject();
        payload.put("model", OPENAI_MODEL);
        payload.put("messages", new JSONObject[]{
                new JSONObject().put("role", "system").put("content", "Answer based on this document: " + CONTEXT),
                new JSONObject().put("role", "user").put("content", query)
        });

        Request request = new Request.Builder()
                .url(OPENAI_API_URL)
                .post(RequestBody.create(payload.toString(), MediaType.parse("application/json")))
                .addHeader("Authorization", "Bearer " + OPENAI_API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return new JSONObject(response.body().string())
                        .getJSONArray("choices")
                        .getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Error: Unable to process the query.";
    }
}
