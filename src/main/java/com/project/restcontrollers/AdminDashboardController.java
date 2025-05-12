package com.project.restcontrollers;
import com.project.services.AdminDashboardService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/admin/dashboard")
public class AdminDashboardController {
    private final AdminDashboardService dashboardService;

    public AdminDashboardController(AdminDashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/stats")
    public Map<String, Long> getStatsSummary() {
        return dashboardService.getStatsSummary();
    }

    @GetMapping("/by-secteur")
    public Map<String, Long> getCandidaturesBySecteur() {
        return dashboardService.getCandidaturesBySecteur();
    }

    @GetMapping("/recruitment-trend")
    public Map<LocalDate, Long> getRecruitmentTrend() {
        return dashboardService.getRecruitmentTrend();
    }
}