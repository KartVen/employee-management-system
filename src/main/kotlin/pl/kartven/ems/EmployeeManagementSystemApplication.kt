package pl.kartven.ems

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
class EmployeeManagementSystemApplication constructor(
    private val requestMappingHandlerMapping: RequestMappingHandlerMapping
) {
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
