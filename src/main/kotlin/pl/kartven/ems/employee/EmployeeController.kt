package pl.kartven.ems.employee

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import pl.kartven.ems.util.CrudRest
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/employees")
@Tag(name = "Employee")
class EmployeeController(
    private val employeeService: EmployeeService
) : CrudRest<Employee.RequestDto, Employee.ResponseDto> {
    @Operation(summary = "Get all employees")
    override fun getAll(): ResponseEntity<List<Employee.ResponseDto>> {
        return ResponseEntity.ok(employeeService.getAllEmployees())
    }

    @Operation(summary = "Get a employee by its id")
    override fun get(id: Long): ResponseEntity<Employee.ResponseDto> {
        return ResponseEntity.ok(employeeService.getEmployeeById(id))
    }

    @Operation(summary = "Remove existing employee by its id")
    override fun delete(id: Long) {
        employeeService.deleteEmployee(id)
    }

    @Operation(summary = "Update existing employee by its id")
    override fun update(id: Long, employeeDto: Employee.RequestDto) {
        employeeService.updateEmployee(id, employeeDto)
    }

    @Operation(summary = "Create a new employee")
    override fun save(
        employeeDto: Employee.RequestDto,
        request: HttpServletRequest
    ): ResponseEntity<Employee.ResponseDto> {
        val id: Long? = employeeService.insertEmployee(employeeDto)
        return ResponseEntity.created(
            ServletUriComponentsBuilder
                .fromUriString(request.requestURI)
                .pathSegment(id.toString())
                .build()
                .toUri()
        ).build()
    }

}