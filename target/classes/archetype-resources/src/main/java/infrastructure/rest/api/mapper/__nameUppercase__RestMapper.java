package ${package}.infrastructure.rest.api.mapper;

import org.mapstruct.Mapper;
import ${package}.domain.${nameUppercase};
import ${package}.infrastructure.rest.api.dto.${nameUppercase}Request;
import ${package}.infrastructure.rest.api.dto.${nameUppercase}Response;
import ${package}.infrastructure.rest.api.dto.${nameUppercase}sResponse;

import java.util.List;

@Mapper
public interface ${nameUppercase}RestMapper {

    ${nameUppercase} map(${nameUppercase}Request ${name}Request);

    ${nameUppercase}Response map(${nameUppercase} ${name});

    List<${nameUppercase}Response> map${nameUppercase}s(List<${nameUppercase}> all);

    default ${nameUppercase}sResponse map(List<${nameUppercase}> all) {
        return ${nameUppercase}sResponse.builder()
                .${name}s(map${nameUppercase}s(all))
                .build();
    }
}
