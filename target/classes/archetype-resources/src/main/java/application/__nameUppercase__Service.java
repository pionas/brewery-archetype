package ${package}.application;

import ${package}.domain.${nameUppercase};

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ${nameUppercase}Service {

    List<${nameUppercase}> findAll();

    Optional<${nameUppercase}> findById(UUID id);

    ${nameUppercase} create(${nameUppercase} ${name});

    ${nameUppercase} update(UUID ${name}Id, ${nameUppercase} ${name});

    void delete(UUID ${name}Id);
}
