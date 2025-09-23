package cz.knowit.ai.bank.database.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing {@link Locality} entities
 *
 * @author AI Assistant
 * @since 1.0.0
 */
@Repository
public interface LocalityRepository extends JpaRepository<Locality, Long> {
}