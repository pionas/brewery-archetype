package ${package}.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ${nameUppercase}Repository {

    List<${nameUppercase}> findAll();

    ${nameUppercase} save(${nameUppercase} ${name});

    Optional<${nameUppercase}> findById(UUID id);

    void deleteById(UUID id);
}
