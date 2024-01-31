package com.towsif.JMSDemo.service;

import com.towsif.JMSDemo.dto.ReportRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Publisher
{
    private final JmsTemplate jmsTemplate;

    @Value("${jms.queue}")
    private String jmsQueue;

    public Publisher(JmsTemplate jmsTemplate)
    {
        this.jmsTemplate = jmsTemplate;
    }

    @Transactional
    public void sendReportRequest(ReportRequest val)
    {
        System.out.println("producer called");
        jmsTemplate.convertAndSend(jmsQueue, val);
        System.out.println("request sent");
    }
}
