package com.towsif.JMSDemo.controller;

import com.towsif.JMSDemo.service.Publisher;
import com.towsif.JMSDemo.dto.ReportRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController
{
    private final Publisher publisher;

    public HomeController(Publisher publisher)
    {
        this.publisher = publisher;
    }

    @GetMapping
    public String home()
    {
        return "home";
    }

    @PostMapping
    public String report()
    {
        ReportRequest request = new ReportRequest("demo@mailtrap.com");

        publisher.sendReportRequest(request);

        return "redirect:/";
    }
}
