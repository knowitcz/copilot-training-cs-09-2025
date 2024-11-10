package cz.knowit.ai.bank.configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for Swagger security
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer")
@Configuration
public class SwaggerConfiguration {
}