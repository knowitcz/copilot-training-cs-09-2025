package cz.knowit.ai.bank.database.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for {@link Client}.
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
