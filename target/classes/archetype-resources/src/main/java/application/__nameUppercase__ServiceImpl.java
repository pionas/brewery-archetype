package ${package}.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ${package}.domain.${nameUppercase};
import ${package}.domain.${nameUppercase}Repository;
import ${package}.domain.exception.${nameUppercase}NotFoundException;
import ${package}.utils.DateTimeProvider;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ${nameUppercase}ServiceImpl implements ${nameUppercase}Service {

    private final ${nameUppercase}Repository ${name}Repository;
    private final DateTimeProvider dateTimeProvider;

    @Override
    public List<${nameUppercase}> findAll() {
        return ${name}Repository.findAll();
    }

    @Override
    public Optional<${nameUppercase}> findById(UUID id) {
        return ${name}Repository.findById(id);
    }

    @Override
    public ${nameUppercase} create(${nameUppercase} ${name}) {
        return ${name}Repository.save(${name});
    }

    @Override
    public ${nameUppercase} update(UUID ${name}Id, ${nameUppercase} ${name}) {
        final var current${nameUppercase} = get${nameUppercase}ById(${name}Id);
        // TODO
        return ${name}Repository.save(current${nameUppercase});
    }

    @Override
    public void delete(UUID ${name}Id) {
        get${nameUppercase}ById(${name}Id);
        ${name}Repository.deleteById(${name}Id);
    }

    private ${nameUppercase} get${nameUppercase}ById(UUID ${name}Id) {
        return ${name}Repository.findById(${name}Id)
                .orElseThrow(() -> new ${nameUppercase}NotFoundException("${nameUppercase} Not Found. UUID: " + ${name}Id));
    }
}
