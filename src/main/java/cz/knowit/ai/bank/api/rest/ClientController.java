package cz.knowit.ai.bank.api.rest;

import cz.knowit.ai.bank.database.client.Client;
import cz.knowit.ai.bank.database.client.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for operations with {@link Client} entity
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/bank/v1")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Gets a client by its id
     *
     * @param id id of the client to get
     */
    @Operation(summary = "Get client by ID", description = "Returns client details for the given client ID")
    @GetMapping("/client/{id}")
    public ResponseEntity<Client> getClient(
            @Parameter(description = "ID of the client to retrieve", required = true) @PathVariable(name = "id") long id) {
        return clientService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
