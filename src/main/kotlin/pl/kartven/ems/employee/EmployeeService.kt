package pl.kartven.ems.employee

import org.springframework.stereotype.Service
import pl.kartven.ems.exception.NotFoundException

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository
) {
    fun getAllEmployees(): List<Employee.ResponseDto> {
        val employees = employeeRepository.findAll();
        return employees.stream()
            .map { employee -> Employee.ResponseDto.map(employee) }
            .toList()
    }

    fun getEmployeeById(id: Long): Employee.ResponseDto? {
        val employee = employeeRepository.findById(id).orElseThrow { NotFoundException() }
        return Employee.ResponseDto.map(employee)
    }

    fun deleteEmployee(id: Long) {
        employeeRepository.deleteById(id)
    }

    fun updateEmployee(id: Long, employeeDto: Employee.RequestDto) {
        val employee = employeeRepository.findById(id).orElseThrow { NotFoundException() }
        employee.firstName = employeeDto.firstName
        employee.lastName = employeeDto.lastName
        employee.email = employeeDto.email
        employeeRepository.save(employee)
    }

    fun insertEmployee(employeeDto: Employee.RequestDto): Long? {
        val employee = employeeRepository.save(
            Employee.RequestDto.map(employeeDto)
        )
        return employee.id;
    }

}