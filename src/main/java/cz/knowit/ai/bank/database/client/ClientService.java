package cz.knowit.ai.bank.database.client;

import java.util.Optional;

/**
 * Service for accessing the {@link Client} from datastorage.
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
public interface ClientService {

    /**
     * Gets a client by its id
     *
     * @param id id of the client to get
     * @return client with the given id
     */
    Optional<Client> getById(long id);

    /**
     * Saves a client
     *
     * @param client client to save
     */
    void save(Client client);
}
