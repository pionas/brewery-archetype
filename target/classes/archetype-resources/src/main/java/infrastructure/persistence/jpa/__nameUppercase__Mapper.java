package ${package}.infrastructure.persistence.jpa;

import org.mapstruct.Mapper;
import ${package}.domain.${nameUppercase};

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.List;

@Mapper
interface ${nameUppercase}Mapper {

    List<${nameUppercase}> map(Iterable<${nameUppercase}Entity> ${name}Entities);

    ${nameUppercase} map(${nameUppercase}Entity ${name}Entity);

    ${nameUppercase}Entity map(${nameUppercase} ${name});

    default Timestamp map(OffsetDateTime value) {
        return value == null ? null : Timestamp.from(value.toInstant());
    }

    default OffsetDateTime map(Timestamp value) {
        return value == null ? null : value.toInstant()
                .atOffset(OffsetDateTime.now().getOffset());
    }
}
