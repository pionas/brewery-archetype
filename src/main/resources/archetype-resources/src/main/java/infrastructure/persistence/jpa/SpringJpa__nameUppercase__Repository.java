package ${package}.infrastructure.persistence.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface SpringJpa${nameUppercase}Repository extends CrudRepository<${nameUppercase}Entity, UUID> {
}
