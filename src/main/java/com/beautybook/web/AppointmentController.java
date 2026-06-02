package com.beautybook.web;

import com.beautybook.model.binding.AppointmentBindingModel;
import com.beautybook.model.entity.Appointment;
import com.beautybook.service.interfaces.AppointmentService;
import com.beautybook.service.interfaces.ClientService;
import com.beautybook.service.interfaces.HairdresserService;
import com.beautybook.service.interfaces.SalonServiceService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final ClientService clientService;
    private final HairdresserService hairdresserService;
    private final SalonServiceService salonServiceService;


    public AppointmentController(AppointmentService appointmentService, ClientService clientService, HairdresserService hairdresserService, SalonServiceService salonServiceService) {
        this.appointmentService = appointmentService;
        this.clientService = clientService;
        this.hairdresserService = hairdresserService;
        this.salonServiceService = salonServiceService;
    }

    @GetMapping
    public String all(Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "appointments";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("appointment", new AppointmentBindingModel());

        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("hairdressers", hairdresserService.getAll());
        model.addAttribute("services", salonServiceService.getAll());

        return "appointment-create";
    }

    @PostMapping("/create")
    public String create(
            @Valid @ModelAttribute("appointment") AppointmentBindingModel model,
            BindingResult bindingResult,
            Model viewModel) {

        if (bindingResult.hasErrors()) {

            viewModel.addAttribute("clients", clientService.getAllClients());
            viewModel.addAttribute("hairdressers", hairdresserService.getAll());
            viewModel.addAttribute("services", salonServiceService.getAll());

            return "appointment-create";
        }

        Appointment appointment = new Appointment();

        appointment.setAppointmentDateTime(model.getAppointmentDateTime());
        appointment.setClient(clientService.getClientById(model.getClientId()));
        appointment.setHairdresser(hairdresserService.getById(model.getHairdresserId()));
        appointment.setService(salonServiceService.getById(model.getServiceId()));

        appointmentService.createAppointment(appointment);

        return "redirect:/appointments";
    }

    @GetMapping("/cancel/{id}")
    public String cancel(@PathVariable UUID id) {
        appointmentService.cancelAppointment(id);
        return "redirect:/appointments";
    }
}