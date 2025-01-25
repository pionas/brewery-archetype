package ${package}.infrastructure.persistence.jpa;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ${package}.domain.${nameUppercase};
import ${package}.domain.${nameUppercase}Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
class Jpa${nameUppercase}Repository implements ${nameUppercase}Repository {

    private final SpringJpa${nameUppercase}Repository springJpa${nameUppercase}Repository;
    private final ${nameUppercase}Mapper ${name}Mapper;

    @Override
    public List<${nameUppercase}> findAll() {
        return ${name}Mapper.map(springJpa${nameUppercase}Repository.findAll());
    }

    @Override
    public ${nameUppercase} save(${nameUppercase} ${name}) {
        return ${name}Mapper.map(springJpa${nameUppercase}Repository.save(${name}Mapper.map(${name})));
    }

    @Override
    public Optional<${nameUppercase}> findById(UUID id) {
        return springJpa${nameUppercase}Repository.findById(id)
                .map(${name}Mapper::map);
    }

    @Override
    public void deleteById(UUID id) {
        springJpa${nameUppercase}Repository.deleteById(id);
    }
}
