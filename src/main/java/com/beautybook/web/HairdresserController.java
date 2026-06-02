package com.beautybook.web;

import com.beautybook.model.binding.HairdresserBindingModel;
import com.beautybook.model.entity.Hairdresser;
import com.beautybook.service.interfaces.HairdresserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/hairdressers")
public class HairdresserController {

    private final HairdresserService hairdresserService;

    public HairdresserController(HairdresserService hairdresserService) {
        this.hairdresserService = hairdresserService;
    }

    @GetMapping
    public String all(Model model) {
        model.addAttribute("hairdressers", hairdresserService.getAll());
        return "hairdressers";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("hairdresserBindingModel", new HairdresserBindingModel());
        return "hairdresser-create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("hairdresserBindingModel") HairdresserBindingModel hairdresserBindingModel,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "hairdresser-create";
        }

        Hairdresser hairdresser = new Hairdresser();

        hairdresser.setFirstName(hairdresserBindingModel.getFirstName());
        hairdresser.setLastName(hairdresserBindingModel.getLastName());
        hairdresser.setSpecialization(hairdresserBindingModel.getSpecialization());
        hairdresser.setActive(hairdresserBindingModel.isActive());

        hairdresserService.create(hairdresser);

        return "redirect:/hairdressers";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable UUID id, Model model) {

        Hairdresser hairdresser = hairdresserService.getById(id);

        HairdresserBindingModel hairdresserBindingModel =
                new HairdresserBindingModel();

        hairdresserBindingModel.setFirstName(hairdresser.getFirstName());
        hairdresserBindingModel.setLastName(hairdresser.getLastName());
        hairdresserBindingModel.setSpecialization(hairdresser.getSpecialization());
        hairdresserBindingModel.setActive(hairdresser.isActive());

        model.addAttribute("hairdresserId", id);
        model.addAttribute("hairdresserBindingModel",
                hairdresserBindingModel);

        return "hairdresser-edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable UUID id,
                       @Valid @ModelAttribute("hairdresserBindingModel")
                       HairdresserBindingModel hairdresserBindingModel,
                       BindingResult bindingResult,
                       Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("hairdresserId", id);
            return "hairdresser-edit";
        }

        Hairdresser hairdresser = new Hairdresser();

        hairdresser.setFirstName(hairdresserBindingModel.getFirstName());
        hairdresser.setLastName(hairdresserBindingModel.getLastName());
        hairdresser.setSpecialization(
                hairdresserBindingModel.getSpecialization());
        hairdresser.setActive(
                hairdresserBindingModel.isActive());

        hairdresserService.update(id, hairdresser);

        return "redirect:/hairdressers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable UUID id) {
        hairdresserService.delete(id);
        return "redirect:/hairdressers";
    }
}