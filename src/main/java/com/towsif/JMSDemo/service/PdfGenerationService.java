package com.towsif.JMSDemo.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfGenerationService
{
    public byte[] generatePdf(String content) throws IOException, DocumentException
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        document.open();
        document.add(new Paragraph(content));
        document.close();

        return outputStream.toByteArray();

    }
}
