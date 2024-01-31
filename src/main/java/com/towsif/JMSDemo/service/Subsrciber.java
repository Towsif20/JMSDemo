package com.towsif.JMSDemo.service;

import com.towsif.JMSDemo.dto.ReportRequest;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class Subsrciber
{
    private final PdfGenerationService pdfGenerationService;

    private final JavaMailSender javaMailSender;

    public Subsrciber(PdfGenerationService pdfGenerationService, JavaMailSender javaMailSender)
    {
        this.pdfGenerationService = pdfGenerationService;
        this.javaMailSender = javaMailSender;
    }

    @JmsListener(destination = "${jms.queue}")
    public void processReportRequest(ReportRequest reportRequest) throws Exception
    {
        byte[] reportBytes = pdfGenerationService.generatePdf("Sample Content");

        sendEmailWithAttachment(reportRequest.getRecipientEmail(), "Report Subject", "Report Body", reportBytes);
    }

    private void sendEmailWithAttachment(String to, String subject, String text, byte[] attachment) throws Exception
    {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text);

        mimeMessageHelper.addAttachment("report.pdf", new ByteArrayResource(attachment), "application/pdf");

        javaMailSender.send(message);

        System.out.println("email sent");
    }
}
