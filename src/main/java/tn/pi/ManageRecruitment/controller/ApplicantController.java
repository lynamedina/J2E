package tn.pi.ManageRecruitment.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/applicant")
public class ApplicantController {

    @GetMapping
    public String applicantDashboard() {
        return "applicant"; // Correspond à applicant.html
    }
}