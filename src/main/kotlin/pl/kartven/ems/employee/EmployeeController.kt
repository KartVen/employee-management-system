package pl.kartven.ems.employee

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import pl.kartven.ems.util.CrudRest
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/employees")
class EmployeeController(
    private val employeeService: EmployeeService
) : CrudRest<Employee.RequestDto, Employee.ResponseDto> {
    override fun getAll(): ResponseEntity<List<Employee.ResponseDto>> {
        return ResponseEntity.ok(employeeService.getAllEmployees())
    }

    override fun get(id: Long): ResponseEntity<Employee.ResponseDto> {
        return ResponseEntity.ok(employeeService.getEmployeeById(id))
    }

    override fun delete(id: Long) {
        employeeService.deleteEmployee(id)
    }

    override fun update(id: Long, employeeDto: Employee.RequestDto) {
        employeeService.updateEmployee(id, employeeDto)
    }

    override fun save(employeeDto: Employee.RequestDto, request: HttpServletRequest): ResponseEntity<Employee.ResponseDto> {
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