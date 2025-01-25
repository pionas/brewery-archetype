package ${package}.infrastructure.rest.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ${nameUppercase}sResponse {

    private List<${nameUppercase}Response> ${name}s;
}
