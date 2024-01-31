package com.towsif.JMSDemo.dto;

import java.io.Serializable;

public class ReportRequest implements Serializable
{
    String recipientEmail;

    public ReportRequest(String recipientEmail)
    {
        this.recipientEmail = recipientEmail;
    }

    public String getRecipientEmail()
    {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail)
    {
        this.recipientEmail = recipientEmail;
    }
}
