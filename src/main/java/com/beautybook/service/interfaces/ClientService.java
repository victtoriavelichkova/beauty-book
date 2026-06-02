package com.beautybook.service.interfaces;

import com.beautybook.model.dto.ClientRegisterRequest;
import com.beautybook.model.entity.Client;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface ClientService {

    Client createClient(Client client);

    List<Client> getAllClients();

    Client getClientById(UUID id);

    Client updateClient(UUID id, Client client);

    void deleteClient(UUID id);

    void register(@Valid ClientRegisterRequest clientRegisterRequest);
}