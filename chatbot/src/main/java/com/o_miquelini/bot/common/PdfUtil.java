package com.o_miquelini.bot.common;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class PdfUtil {
    // Using a more flexible approach to load resources
    public static String extractTextFromPdf() {
        try {
            // Load PDF from the resources folder
            URL resource = PdfUtil.class.getClassLoader().getResource("test.pdf");

            if (resource == null) {
                return "Error: File not found in resources.";
            }

            File file = Paths.get(resource.toURI()).toFile();

            try (PDDocument document = PDDocument.load(file)) {
                PDFTextStripper pdfStripper = new PDFTextStripper();
                return pdfStripper.getText(document);
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return "Error: Unable to read the PDF.";
        }
    }
}
