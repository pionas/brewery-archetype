package ${package}.infrastructure.rest.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ${package}.application.${nameUppercase}Service;
import ${package}.infrastructure.rest.api.dto.${nameUppercase}Request;
import ${package}.infrastructure.rest.api.dto.${nameUppercase}Response;
import ${package}.infrastructure.rest.api.dto.${nameUppercase}sResponse;
import ${package}.infrastructure.rest.api.mapper.${nameUppercase}RestMapper;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/${name}s")
class ${nameUppercase}RestController {

    private final ${nameUppercase}Service ${name}Service;
    private final ${nameUppercase}RestMapper ${name}RestMapper;

    @GetMapping({"", "/"})
    public ResponseEntity<${nameUppercase}sResponse> get${nameUppercase}s() {
        return new ResponseEntity<>(${name}RestMapper.map(${name}Service.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{${name}Id}")
    public ResponseEntity<${nameUppercase}Response> get${nameUppercase}(@PathVariable("${name}Id") UUID ${name}Id) {
        return ${name}Service.findById(${name}Id)
                .map(${name}RestMapper::map)
                .map(${name}Response -> new ResponseEntity<>(${name}Response, HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<${nameUppercase}Response> create${nameUppercase}(@Valid @RequestBody ${nameUppercase}Request ${name}Request) {
        final var ${name}Response = ${name}RestMapper.map(${name}Service.create(${name}RestMapper.map(${name}Request)));

        return new ResponseEntity<>(${name}Response, HttpStatus.CREATED);
    }

    @PutMapping("/{${name}Id}")
    public ResponseEntity<${nameUppercase}Response> update${nameUppercase}(@PathVariable("${name}Id") UUID ${name}Id, @Valid @RequestBody ${nameUppercase}Request ${name}Request) {
        final var ${name}Response = ${name}RestMapper.map(${name}Service.update(${name}Id, ${name}RestMapper.map(${name}Request)));

        return new ResponseEntity<>(${name}Response, HttpStatus.OK);
    }

    @DeleteMapping("/{${name}Id}")
    public ResponseEntity<HttpStatus> delete${nameUppercase}(@PathVariable("${name}Id") UUID ${name}Id) {
        ${name}Service.delete(${name}Id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
