package com.o_miquelini.bot.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PdfUtilTest {

    @Test
    void testExtractTextFromPdf() {
        String filePath = "src/test/resources/test.pdf";
        System.out.println("##############Extracting text from PDF: " + filePath);
        String text = PdfUtil.extractTextFromPdf();
        System.out.println("#######Extracted text: " + text);
        assertTrue(text.contains("I was born in 12/29/1999"));
    }
}
