package cz.knowit.ai.bank.database.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Production implementation of {@link LocalityService}.
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
@Service
public final class LocalityProdService implements LocalityService {

    private final LocalityRepository localityRepository;

    @Autowired
    public LocalityProdService(LocalityRepository localityRepository) {
        this.localityRepository = localityRepository;
    }

    @Override
    public Optional<Locality> getLocalityById(long id) {
        return localityRepository.findById(id);
    }
}