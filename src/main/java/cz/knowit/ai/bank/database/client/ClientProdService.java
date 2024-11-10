package cz.knowit.ai.bank.database.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Production implementation of {@link ClientService}.
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
@Service
public final class ClientProdService implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientProdService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Optional<Client> getById(long id) {
        return clientRepository.findById(id);
    }

    @Override
    public void save(Client client) {
        clientRepository.save(client);
    }
}
