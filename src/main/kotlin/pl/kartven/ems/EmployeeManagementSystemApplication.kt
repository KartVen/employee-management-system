package pl.kartven.ems

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.models.annotations.OpenAPI31
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import java.util.stream.Collectors


@SpringBootApplication
@RestController
@Tag(name = "Base")
@OpenAPIDefinition(info = Info(title = "EMS", description = "Employee Management System", version = "v1"))
class EmployeeManagementSystemApplication constructor(
    private val requestMappingHandlerMapping: RequestMappingHandlerMapping
) {
    @Operation(summary = "Get all API endpoints")
    @GetMapping
    fun index(): ResponseEntity<List<String>>? {
        return ResponseEntity.ok(
            requestMappingHandlerMapping
                .handlerMethods
                .keys
                .stream()
                .map { obj: RequestMappingInfo -> obj.toString() }
                .sorted()
                .collect(Collectors.toList())
        )
    }
}

fun main(args: Array<String>) {
    runApplication<EmployeeManagementSystemApplication>(*args)
}
