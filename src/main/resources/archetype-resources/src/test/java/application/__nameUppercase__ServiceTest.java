package ${package}.application;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ${package}.domain.${nameUppercase};
import ${package}.domain.${nameUppercase}Repository;
import ${package}.domain.exception.${nameUppercase}NotFoundException;
import ${package}.utils.DateTimeProvider;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ${nameUppercase}ServiceTest {

    private static final OffsetDateTime OFFSET_DATE_TIME = OffsetDateTime.of(2025, 1, 23, 12, 7, 0, 0, ZoneOffset.UTC);

    private final ${nameUppercase}Repository ${name}Repository = Mockito.mock(${nameUppercase}Repository.class);
    private final DateTimeProvider dateTimeProvider = Mockito.mock(DateTimeProvider.class);

    private final ${nameUppercase}Service ${name}Service = new ${nameUppercase}ServiceImpl(${name}Repository, dateTimeProvider);


    @Test
    void findAll_ShouldReturnListOf${nameUppercase}s() {
        // given
        final var ${name}s = List.of(
                create${nameUppercase}(UUID.fromString("1b4e28ba-2fa1-4d3b-a3f5-ef19b5a7633b"), OFFSET_DATE_TIME),
                create${nameUppercase}(UUID.fromString("2c4f2ed6-bd1d-4f9d-82c6-6b975b5cf5b3"), OFFSET_DATE_TIME),
                create${nameUppercase}(UUID.fromString("3a8e0e2f-587d-4b3c-b1c9-27f5d6c3627a"), OFFSET_DATE_TIME),
                create${nameUppercase}(UUID.fromString("4c9e7a3b-84e7-4f8e-95e2-cd2f1d56e6b7"), OFFSET_DATE_TIME),
                create${nameUppercase}(UUID.fromString("5d3f8e7c-9f2b-42e1-908d-cf3d1e678e9b"), OFFSET_DATE_TIME),
                create${nameUppercase}(UUID.fromString("6e8f9d4c-7c8a-45d1-8b4c-ed3f5a7b6e9d"), OFFSET_DATE_TIME),
                create${nameUppercase}(UUID.fromString("7f1b3c2d-8e9f-41b2-94c8-ef3d7a6b5c9f"), OFFSET_DATE_TIME),
                create${nameUppercase}(UUID.fromString("8a2d4e6f-9b3c-4e2f-b7d1-2c3f5a8e7b6d"), OFFSET_DATE_TIME),
                create${nameUppercase}(UUID.fromString("9c3e5d7a-b8f2-41c3-82e9-f2b1d6e5c4f7"), OFFSET_DATE_TIME),
                create${nameUppercase}(UUID.fromString("0d1e2f3b-5a7c-4d1f-8e9b-2f3d6a8b7c5f"), OFFSET_DATE_TIME)
        );
        when(${name}Repository.findAll()).thenReturn(${name}s);

        // when
        final var result = ${name}Service.findAll();

        // then
        assertEquals(10, result.size());
        assertEquals(${name}s, result);
        verify(${name}Repository, times(1)).findAll();
    }

    @Test
    void findById_ShouldReturn${nameUppercase}_When${nameUppercase}Exists() {
        // given
        final var ${name} = create${nameUppercase}(UUID.fromString("71737f0e-11eb-4775-b8b4-ce945fdee936"), OFFSET_DATE_TIME);
        when(${name}Repository.findById(${name}.getId())).thenReturn(Optional.of(${name}));

        // when
        final var result = ${name}Service.findById(${name}.getId());

        // then
        assertTrue(result.isPresent());
        assertEquals(${name}, result.get());
        verify(${name}Repository, times(1)).findById(${name}.getId());
    }

    @Test
    void findById_ShouldThrowException_When${nameUppercase}NotFound() {
        // given
        final var ${name} = create${nameUppercase}(UUID.fromString("71737f0e-11eb-4775-b8b4-ce945fdee936"), OFFSET_DATE_TIME);
        when(${name}Repository.findById(${name}.getId())).thenReturn(Optional.empty());

        // when
        final var ${name}Optional = ${name}Service.findById(${name}.getId());

        // then
        assertTrue(${name}Optional.isEmpty());
        verify(${name}Repository, times(1)).findById(${name}.getId());
    }

    @Test
    void create_ShouldSaveAndReturn${nameUppercase}() {
        // given
        final var ${name} = create${nameUppercase}(UUID.fromString("71737f0e-11eb-4775-b8b4-ce945fdee936"), OFFSET_DATE_TIME);
        when(${name}Repository.save(${name})).thenReturn(${name});

        // when
        final var result = ${name}Service.create(${name});

        // then
        assertEquals(${name}, result);
        verify(${name}Repository, times(1)).save(${name});
    }

    @Test
    void update_ShouldUpdateAndReturn${nameUppercase}() {
        // given
        OffsetDateTime offsetDateTime = OffsetDateTime.of(2025, 1, 23, 12, 7, 10, 0, ZoneOffset.UTC);
        final var original${nameUppercase} = create${nameUppercase}(UUID.fromString("71737f0e-11eb-4775-b8b4-ce945fdee936"), OFFSET_DATE_TIME);
        final var updateRequest = getUpdateRequest(original${nameUppercase});
        final var expected${nameUppercase} = getExpected${nameUppercase}(updateRequest, offsetDateTime);

        when(${name}Repository.findById(original${nameUppercase}.getId())).thenReturn(Optional.of(original${nameUppercase}));
        when(${name}Repository.save(any(${nameUppercase}.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(dateTimeProvider.now()).thenReturn(offsetDateTime);

        // when
        final var result = ${name}Service.update(original${nameUppercase}.getId(), updateRequest);

        // then
        assertEquals(expected${nameUppercase}, result);
        assertEquals(expected${nameUppercase}.getId(), result.getId());
        verify(${name}Repository, times(1)).findById(original${nameUppercase}.getId());
        verify(${name}Repository, times(1)).save(original${nameUppercase});
    }


    @Test
    void delete_ShouldDelete${nameUppercase}_When${nameUppercase}Exists() {
        // given
        final var ${name} = create${nameUppercase}(UUID.fromString("71737f0e-11eb-4775-b8b4-ce945fdee936"), OFFSET_DATE_TIME);
        when(${name}Repository.findById(${name}.getId())).thenReturn(Optional.of(${name}));
        doNothing().when(${name}Repository).deleteById(${name}.getId());

        // when
        assertDoesNotThrow(() -> ${name}Service.delete(${name}.getId()));

        // then
        verify(${name}Repository, times(1)).findById(${name}.getId());
        verify(${name}Repository, times(1)).deleteById(${name}.getId());
    }

    @Test
    void delete_ShouldThrowException_When${nameUppercase}NotFound() {
        // given
        final var ${name} = create${nameUppercase}(UUID.fromString("71737f0e-11eb-4775-b8b4-ce945fdee936"), OFFSET_DATE_TIME);
        when(${name}Repository.findById(${name}.getId())).thenReturn(Optional.empty());

        // when
        assertThrows(${nameUppercase}NotFoundException.class, () -> ${name}Service.delete(${name}.getId()));

        // then
        verify(${name}Repository, times(1)).findById(${name}.getId());
        verify(${name}Repository, never()).deleteById(any());
    }

    private ${nameUppercase} getUpdateRequest(${nameUppercase} original${nameUppercase}) {
        return create${nameUppercase}(
                original${nameUppercase}.getId()
        );
    }

    private ${nameUppercase} getExpected${nameUppercase}(${nameUppercase} original${nameUppercase}, OffsetDateTime offsetDateTime) {
        return create${nameUppercase}(
                original${nameUppercase}.getId(),
                offsetDateTime
        );
    }

    private ${nameUppercase} create${nameUppercase}(UUID id, OffsetDateTime offsetDateTime) {
        return ${nameUppercase}.builder()
                .id(id)
                .build();
    }
}