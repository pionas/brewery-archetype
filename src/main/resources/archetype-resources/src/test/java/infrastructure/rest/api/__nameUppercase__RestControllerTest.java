package ${package}.infrastructure.rest.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ${package}.application.${nameUppercase}Service;
import ${package}.domain.${nameUppercase};
import ${package}.domain.exception.${nameUppercase}NotFoundException;
import ${package}.infrastructure.rest.api.dto.${nameUppercase}Request;
import ${package}.infrastructure.rest.api.dto.${nameUppercase}Response;
import ${package}.infrastructure.rest.api.dto.${nameUppercase}sResponse;
import ${package}.infrastructure.rest.api.mapper.${nameUppercase}RestMapper;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ${nameUppercase}RestControllerTest extends AbstractMvcTest {

    private static final OffsetDateTime OFFSET_DATE_TIME = OffsetDateTime.of(2025, 1, 23, 12, 7, 0, 0, ZoneOffset.UTC);

    @InjectMocks
    private ${nameUppercase}RestController controller;

    @Mock
    private ${nameUppercase}Service ${name}Service;

    @Spy
    private ${nameUppercase}RestMapper ${name}RestMapper = Mappers.getMapper(${nameUppercase}RestMapper.class);

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter(getObjectMapper()))
                .build();
    }

    @Test
    void get${nameUppercase}s_ShouldReturnEmptyListOf${nameUppercase}s() throws Exception {
        // given

        // when
        mockMvc.perform(get("/api/v1/${name}s"))
                .andExpect(status().isOk());

        // then
        verify(${name}Service).findAll();
    }

    @Test
    void get${nameUppercase}s_ShouldReturnListOf${nameUppercase}s() throws Exception {
        // given
        final var ${name}1 = create${nameUppercase}(UUID.fromString("71737f0e-11eb-4775-b8b4-ce945fdee936"));
        final var ${name}2 = create${nameUppercase}(UUID.fromString("4a5b96de-684a-411b-9616-fddd0b06a382"));
        when(${name}Service.findAll())
                .thenReturn(List.of(${name}1, ${name}2));

        // when
        final var response = mockMvc.perform(get("/api/v1/${name}s"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        // then
        assertNotNull(response);
        final var responseBody = response.getContentAsString();
        assertNotNull(responseBody);
        final var ${name}sResponse = super.mapFromJson(responseBody, ${nameUppercase}sResponse.class);
        assertNotNull(${name}sResponse);
        final var ${name}sResponseList = ${name}sResponse.get${nameUppercase}s();
        assertNotNull(${name}sResponseList);
        assertEquals(2, ${name}sResponseList.size());
        final var ${name}Response1 = ${name}sResponseList.getFirst();
        assertNotNull(${name}Response1);
        assertEquals(${name}Response1.getId(), ${name}1.getId());
        final var ${name}Response2 = ${name}sResponseList.getLast();
        assertNotNull(${name}Response2);
        assertEquals(${name}Response2.getId(), ${name}2.getId());
        verify(${name}Service).findAll();
    }

    @Test
    void get${nameUppercase}_ShouldReturnNotFound() throws Exception {
        // given
        final var ${name}Id = UUID.fromString("71737f0e-11eb-4775-b8b4-ce945fdee936");
        // when
        final var response = mockMvc.perform(get("/api/v1/${name}s/" + ${name}Id))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse();

        // then
        assertNotNull(response);
        final var responseBody = response.getContentAsString();
        assertNotNull(responseBody);
        verify(${name}Service).findById(${name}Id);
    }

    @Test
    void get${nameUppercase}_ShouldReturn() throws Exception {
        // given
        final var ${name}Id = UUID.fromString("71737f0e-11eb-4775-b8b4-ce945fdee936");
        final var ${name} = create${nameUppercase}(${name}Id);
        when(${name}Service.findById(${name}Id)).thenReturn(Optional.of(${name}));

        // when
        final var response = mockMvc.perform(get("/api/v1/${name}s/" + ${name}Id))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        // then
        assertNotNull(response);
        final var responseBody = response.getContentAsString();
        assertNotNull(responseBody);
        final var ${name}Response = super.mapFromJson(responseBody, ${nameUppercase}Response.class);
        assertNotNull(${name}Response);
        assertEquals(${name}Response.getId(), ${name}.getId());
    }

    @Test
    void create${nameUppercase}_ShouldReturnCreated${nameUppercase}() throws Exception {
        // given
        final var ${name} = create${nameUppercase}(UUID.fromString("71737f0e-11eb-4775-b8b4-ce945fdee936"));
        final var ${name}Request = ${nameUppercase}Request.builder()

                .build();
        when(${name}Service.create(any())).thenReturn(${name});

        // when
        final var response = mockMvc.perform(post("/api/v1/${name}s")
                        .content(super.mapToJson(${name}Request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();

        // then
        assertNotNull(response);
        final var responseBody = response.getContentAsString();
        assertNotNull(responseBody);
        final var ${name}Response = super.mapFromJson(responseBody, ${nameUppercase}Response.class);
        assertNotNull(${name}Response);
        assertEquals(${name}Response.getId(), ${name}.getId());
        verify(${name}Service).create(any());
    }

    @Test
    void update${nameUppercase}_ShouldReturnUpdated${nameUppercase}() throws Exception {
        // given
        final var offsetDateTime = OffsetDateTime.of(2025, 1, 23, 12, 7, 10, 0, ZoneOffset.UTC);
        final var original${nameUppercase} = create${nameUppercase}(UUID.fromString("71737f0e-11eb-4775-b8b4-ce945fdee936"));
        final var updateRequest = getUpdateRequest(original${nameUppercase});
        final var expected${nameUppercase} = getExpected${nameUppercase}(original${nameUppercase}, offsetDateTime);
        final var ${name}Request = ${nameUppercase}Request.builder()

                .build();
        when(${name}Service.update(any(), any())).thenReturn(expected${nameUppercase});

        // when
        final var response = mockMvc.perform(put("/api/v1/${name}s/" + original${nameUppercase}.getId())
                        .content(super.mapToJson(${name}Request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        // then
        assertNotNull(response);
        final var responseBody = response.getContentAsString();
        assertNotNull(responseBody);
        final var ${name}Response = super.mapFromJson(responseBody, ${nameUppercase}Response.class);
        assertNotNull(${name}Response);
        assertEquals(${name}Response.getId(), expected${nameUppercase}.getId());
        verify(${name}Service).update(any(), any());
    }

    @Test
    void delete${nameUppercase}_ShouldDeleted${nameUppercase}() throws Exception {
        // given
        final var ${name}Id = UUID.fromString("71737f0e-11eb-4775-b8b4-ce945fdee936");

        // when
        mockMvc.perform(delete("/api/v1/${name}s/" + ${name}Id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

        // then
        verify(${name}Service).delete(${name}Id);
    }

    @Test
    void delete${nameUppercase}_ShouldThrowExceptionWhenTryDeleted${nameUppercase}() throws Exception {
        // given
        final var ${name}Id = UUID.fromString("71737f0e-11eb-4775-b8b4-ce945fdee936");
        doThrow(new ${nameUppercase}NotFoundException("${nameUppercase} Not Found. UUID: " + ${name}Id)).when(${name}Service).delete(${name}Id);

        // when
        mockMvc.perform(delete("/api/v1/${name}s/" + ${name}Id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        // then
        verify(${name}Service).delete(${name}Id);
    }

    private ${nameUppercase} create${nameUppercase}(UUID id) {
        return ${nameUppercase}.builder()
                .id(id)
                .build();
    }

    private ${nameUppercase} getUpdateRequest(${nameUppercase} original${nameUppercase}) {
        return create${nameUppercase}(
                original${nameUppercase}.getId()
        );
    }

    private ${nameUppercase} getExpected${nameUppercase}(${nameUppercase} original${nameUppercase}, OffsetDateTime offsetDateTime) {
        return create${nameUppercase}(
                original${nameUppercase}.getId()
        );
    }
}