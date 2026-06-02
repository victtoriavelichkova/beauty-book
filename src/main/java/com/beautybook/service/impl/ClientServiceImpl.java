package com.beautybook.service.impl;

import com.beautybook.model.dto.ClientRegisterRequest;
import com.beautybook.model.entity.Client;
import com.beautybook.repository.ClientRepository;
import com.beautybook.service.interfaces.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(UUID id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
    }

    @Override
    public Client updateClient(UUID id, Client client) {
        Client existing = getClientById(id);

        existing.setFirstName(client.getFirstName());
        existing.setLastName(client.getLastName());
        existing.setPhone(client.getPhone());

        return clientRepository.save(existing);
    }



    @Override
    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void register(ClientRegisterRequest clientRegisterRequest) {

    }
}