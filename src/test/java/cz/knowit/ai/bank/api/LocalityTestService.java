package cz.knowit.ai.bank.api;

import cz.knowit.ai.bank.database.transaction.Locality;
import cz.knowit.ai.bank.database.transaction.LocalityService;

import java.util.Optional;

/**
 * This is a test implementation of the {@link LocalityService} interface.
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
final class LocalityTestService implements LocalityService {

    @Override
    public Optional<Locality> getLocalityById(long id) {
        // Create a simple test locality
        Locality locality = new Locality();
        locality.setId(id);
        locality.setName("Test Locality " + id);
        locality.setAddress("Test Address " + id);
        locality.setCity("Test City");
        return Optional.of(locality);
    }
}