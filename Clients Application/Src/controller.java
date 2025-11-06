package com.deltacapita.clientsapi.controller;

import com.deltacapita.clientsapi.model.Client;
import com.deltacapita.clientsapi.repository.ClientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientRepository repo;

    public ClientController(ClientRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Client> getAllClients() {
        return repo.findAll();
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return repo.save(client);
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable String id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable String id) {
        repo.deleteById(id);
    }
}
