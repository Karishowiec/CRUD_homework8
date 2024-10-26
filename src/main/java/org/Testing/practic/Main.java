package org.Testing.practic;

import org.Testing.practic.client.Client;
import org.Testing.practic.client.ClientService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        List<Client> clients = clientService.listAll();
        System.out.println(clients);
        clientService.setName(2,"Oleg");
        Client byId = clientService.getById(2);
        System.out.println(byId);
        clientService.deleteById(3);
        System.out.println(clientService.listAll());
        clientService.create("Daria");
        System.out.println(clientService.listAll());

    }
}