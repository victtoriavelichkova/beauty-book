package com.beautybook.web;

import com.beautybook.model.binding.ClientBindingModel;
import com.beautybook.model.entity.Client;
import com.beautybook.service.interfaces.ClientService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String allClients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "clients";
    }

    @GetMapping("/create")
    public String createForm(Model model) {

        model.addAttribute("clientBindingModel",
                new ClientBindingModel());

        return "client-create";
    }

    @PostMapping("/create")
    public String create(
            @Valid @ModelAttribute("clientBindingModel") ClientBindingModel clientBindingModel,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "client-create";
        }

        Client client = new Client();

        client.setFirstName(clientBindingModel.getFirstName());
        client.setLastName(clientBindingModel.getLastName());
        client.setPhone(clientBindingModel.getPhone());

        clientService.createClient(client);

        return "redirect:/clients";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable UUID id, Model model) {

        Client client = clientService.getClientById(id);

        ClientBindingModel clientBindingModel = new ClientBindingModel();

        clientBindingModel.setFirstName(client.getFirstName());
        clientBindingModel.setLastName(client.getLastName());
        clientBindingModel.setPhone(client.getPhone());

        model.addAttribute("clientId", id);
        model.addAttribute("clientBindingModel", clientBindingModel);

        return "client-edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable UUID id,
                       @Valid @ModelAttribute("clientBindingModel") ClientBindingModel clientBindingModel,
                       BindingResult bindingResult,
                       Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("clientId", id);
            return "client-edit";
        }

        Client client = new Client();

        client.setFirstName(clientBindingModel.getFirstName());
        client.setLastName(clientBindingModel.getLastName());
        client.setPhone(clientBindingModel.getPhone());

        clientService.updateClient(id, client);

        return "redirect:/clients";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable UUID id) {
        clientService.deleteClient(id);
        return "redirect:/clients";
    }
}