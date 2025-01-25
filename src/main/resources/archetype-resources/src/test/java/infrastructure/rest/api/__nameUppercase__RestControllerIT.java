package ${package}.infrastructure.rest.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.jdbc.JdbcTestUtils;
import ${package}.infrastructure.rest.api.dto.${nameUppercase}Response;
import ${package}.infrastructure.rest.api.dto.${nameUppercase}sResponse;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ${nameUppercase}RestControllerIT extends AbstractIT {

    private final String MODEL_API_URL = "/api/v1/${name}s";

    @AfterEach
    void clearDatabase(@Autowired JdbcTemplate jdbcTemplate) {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "${name}s");
    }

    @Test
    void get${nameUppercase}s_ShouldReturnEmptyListOf${nameUppercase}s() {
        // given

        // when
        final var response = restTemplate.getForEntity(MODEL_API_URL, ${nameUppercase}sResponse.class);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        final var responseBody = response.getBody();
        assertNotNull(responseBody);
        final var ${name}sResponse = responseBody.get${nameUppercase}s();
        assertNotNull(${name}sResponse);
        assertTrue(${name}sResponse.isEmpty());
    }

    @Test
    @Sql({"/db/${name}s.sql"})
    void get${nameUppercase}s_ShouldReturnListOf${nameUppercase}s() {
        // given

        // when
        final var response = restTemplate.getForEntity(MODEL_API_URL, ${nameUppercase}sResponse.class);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        final var responseBody = response.getBody();
        assertNotNull(responseBody);
        final var ${name}sResponse = responseBody.get${nameUppercase}s();
        assertNotNull(${name}sResponse);
        assertFalse(${name}sResponse.isEmpty());
    }

    @Test
    @Sql({"/db/${name}s.sql"})
    void get${nameUppercase}_ShouldReturn${nameUppercase}() {
        // given
        final var ${name}Id = UUID.fromString("1b4e28ba-2fa1-4d3b-a3f5-ef19b5a7633b");

        // when
        final var response = restTemplate.getForEntity(MODEL_API_URL + "/{${name}Id}", ${nameUppercase}Response.class, ${name}Id);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        final var responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(${name}Id, responseBody.getId());
    }

    @Test
    void delete${nameUppercase}_ShouldThrowException() {
        // given
        final var ${name}Id = UUID.fromString("1b4e28ba-2fa1-4d3b-a3f5-ef19b5a7633b");

        // when
        final var response = restTemplate.exchange(
                MODEL_API_URL + "/{${name}Id}",
                HttpMethod.DELETE,
                null,
                Map.class,
                ${name}Id
        );

        // then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @Sql({"/db/${name}s.sql"})
    void delete${nameUppercase}_ShouldDelete() {
        // given
        final var ${name}Id = UUID.fromString("1b4e28ba-2fa1-4d3b-a3f5-ef19b5a7633b");

        // when
        final var response = restTemplate.exchange(
                MODEL_API_URL + "/{${name}Id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                ${name}Id
        );

        // then
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        final var responseVerify = restTemplate.getForEntity(MODEL_API_URL + "/{${name}Id}", ${nameUppercase}Response.class, ${name}Id);
        assertEquals(HttpStatus.OK, responseVerify.getStatusCode());
    }

}