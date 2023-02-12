package pl.kartven.ems.employee

import org.springframework.stereotype.Service

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
        val employee = employeeRepository.findById(id);
        if (employee.isPresent)
            return Employee.ResponseDto.map(employee.get())
        else
            return null
    }

    fun deleteEmployee(id: Long) {
        employeeRepository.deleteById(id)
    }

    fun updateEmployee(id: Long, employeeDto: Employee.RequestDto) {
        val employeeDb = employeeRepository.findById(id);
        val employee = employeeDb.get()
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