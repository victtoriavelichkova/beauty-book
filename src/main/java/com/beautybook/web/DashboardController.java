package com.beautybook.web;

import com.beautybook.repository.AppointmentRepository;
import com.beautybook.repository.ClientRepository;
import com.beautybook.repository.HairdresserRepository;
import com.beautybook.repository.SalonServiceRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class DashboardController {

    private final ClientRepository clientRepository;
    private final HairdresserRepository hairdresserRepository;
    private final SalonServiceRepository salonServiceRepository;
    private final AppointmentRepository appointmentRepository;

    public DashboardController(ClientRepository clientRepository,
                               HairdresserRepository hairdresserRepository,
                               SalonServiceRepository salonServiceRepository,
                               AppointmentRepository appointmentRepository) {
        this.clientRepository = clientRepository;
        this.hairdresserRepository = hairdresserRepository;
        this.salonServiceRepository = salonServiceRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(
            Model model,
            Authentication authentication,
            HttpServletResponse response) {

        model.addAttribute("username", authentication.getName());

        model.addAttribute(
                "isAdmin",
                authentication.getAuthorities()
                        .stream()
                        .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))
        );

        model.addAttribute("clientsCount", clientRepository.count());
        model.addAttribute("hairdressersCount", hairdresserRepository.count());
        model.addAttribute("servicesCount", salonServiceRepository.count());
        model.addAttribute("appointmentsCount", appointmentRepository.count());

        Cookie lastVisitCookie =
                new Cookie(
                        "lastVisit",
                        LocalDateTime.now().toString());

        lastVisitCookie.setMaxAge(60 * 60 * 24 * 30);

        response.addCookie(lastVisitCookie);

        return "dashboard";
    }

    @GetMapping("/profile")
    public String profile(
            Model model,
            Authentication authentication,
            @org.springframework.web.bind.annotation.CookieValue(
                    value = "lastVisit",
                    defaultValue = "")
            String lastVisit) {

        model.addAttribute("username", authentication.getName());

        String formattedLastVisit = "No previous visit";

        if (!lastVisit.isBlank()) {
            try {
                LocalDateTime dateTime = LocalDateTime.parse(lastVisit);

                formattedLastVisit = dateTime.format(
                        java.time.format.DateTimeFormatter.ofPattern(
                                "dd MMM yyyy, HH:mm",
                                java.util.Locale.ENGLISH
                        )
                );
            } catch (Exception ignored) {
            }
        }

        model.addAttribute("lastVisit", formattedLastVisit);

        return "profile";
    }
}