package tn.pi.ManageRecruitment.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recruiter")
public class RecruiterController {

    @GetMapping
    public String recruiterDashboard() {
        return "recruiter"; // Correspond à recruiter.html
    }

}