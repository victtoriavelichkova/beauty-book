package com.beautybook.web;

import com.beautybook.model.binding.SalonServiceBindingModel;
import com.beautybook.model.entity.SalonService;
import com.beautybook.service.interfaces.SalonServiceService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/services")
public class SalonServiceController {

    private final SalonServiceService salonServiceService;

    public SalonServiceController(SalonServiceService salonServiceService) {
        this.salonServiceService = salonServiceService;
    }

    @GetMapping
    public String all(Model model) {
        model.addAttribute("services", salonServiceService.getAll());
        return "services";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("serviceBindingModel",
                new SalonServiceBindingModel());

        return "service-create";
    }

    @PostMapping("/create")
    public String create(
            @Valid @ModelAttribute("serviceBindingModel")
            SalonServiceBindingModel serviceBindingModel,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "service-create";
        }

        SalonService service = new SalonService();

        service.setName(serviceBindingModel.getName());
        service.setDurationMinutes(serviceBindingModel.getDurationMinutes());
        service.setPrice(serviceBindingModel.getPrice());

        salonServiceService.create(service);

        return "redirect:/services";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable UUID id, Model model) {

        SalonService service = salonServiceService.getById(id);

        SalonServiceBindingModel serviceBindingModel =
                new SalonServiceBindingModel();

        serviceBindingModel.setName(service.getName());
        serviceBindingModel.setDurationMinutes(service.getDurationMinutes());
        serviceBindingModel.setPrice(service.getPrice());

        model.addAttribute("serviceId", id);
        model.addAttribute("serviceBindingModel",
                serviceBindingModel);

        return "service-edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable UUID id,
                       @Valid @ModelAttribute("serviceBindingModel")
                       SalonServiceBindingModel serviceBindingModel,
                       BindingResult bindingResult,
                       Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("serviceId", id);
            return "service-edit";
        }

        SalonService service = new SalonService();

        service.setName(serviceBindingModel.getName());
        service.setDurationMinutes(
                serviceBindingModel.getDurationMinutes());
        service.setPrice(serviceBindingModel.getPrice());

        salonServiceService.update(id, service);

        return "redirect:/services";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable UUID id) {
        salonServiceService.delete(id);
        return "redirect:/services";
    }

    @GetMapping("/pricelist")
    public String pricelist(Model model) {

        model.addAttribute("services", salonServiceService.getAll());

        return "pricelist";
    }

}