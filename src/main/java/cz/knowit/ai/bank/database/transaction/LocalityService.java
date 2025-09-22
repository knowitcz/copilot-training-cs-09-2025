package cz.knowit.ai.bank.database.transaction;

import java.util.Optional;

/**
 * Service for accessing the {@link Locality} from datastorage.
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
public interface LocalityService {

    /**
     * Gets locality by ID
     *
     * @param id locality ID
     * @return locality if found
     */
    Optional<Locality> getLocalityById(long id);
}