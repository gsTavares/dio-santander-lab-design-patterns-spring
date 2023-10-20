package one.digitalinnovation.gof.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.model.Address;
import one.digitalinnovation.gof.model.Client;
import one.digitalinnovation.gof.repository.AddressRepository;
import one.digitalinnovation.gof.repository.ClientRepository;
import one.digitalinnovation.gof.service.ClientService;
import one.digitalinnovation.gof.service.ViaCepService;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).get();
    }

    @Override
    public void insert(Client client) {
        saveClientWithPostalCode(client);
    }

    @Override
    public void update(Long id, Client client) {
        Optional<Client> find = clientRepository.findById(id);
        if (find.isPresent()) {
            saveClientWithPostalCode(client);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Client> find = clientRepository.findById(id);
        if (find.isPresent()) {
            clientRepository.deleteById(id);
        }
    }

    private void saveClientWithPostalCode(Client client) {
        Address address = addressRepository.findById(client.getAddress().getCep()).orElseGet(() -> {
            Address newAddress = viaCepService.findByPostalCode(client.getAddress().getCep());
            addressRepository.saveAndFlush(newAddress);
            return newAddress;
        });
        client.setAddress(address);
        clientRepository.saveAndFlush(client);
    }

}
