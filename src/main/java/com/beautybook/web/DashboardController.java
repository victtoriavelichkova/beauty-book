package com.beautybook.web;

import com.beautybook.repository.AppointmentRepository;
import com.beautybook.repository.ClientRepository;
import com.beautybook.repository.HairdresserRepository;
import com.beautybook.repository.SalonServiceRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String dashboard(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("clientsCount", clientRepository.count());
        model.addAttribute("hairdressersCount", hairdresserRepository.count());
        model.addAttribute("servicesCount", salonServiceRepository.count());
        model.addAttribute("appointmentsCount", appointmentRepository.count());

        return "dashboard";
    }

    @GetMapping("/profile")
    public String profile(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());

        return "profile";
    }
}