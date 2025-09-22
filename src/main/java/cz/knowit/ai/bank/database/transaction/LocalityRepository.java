package cz.knowit.ai.bank.database.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for {@link Locality}.
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
@Repository
public interface LocalityRepository extends JpaRepository<Locality, Long> {
}