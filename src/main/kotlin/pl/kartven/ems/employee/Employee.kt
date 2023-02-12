package pl.kartven.ems.employee

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data
import pl.kartven.ems.util.Auditable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Email

@Entity
class Employee(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    @Email
    var email: String? = null
) : Auditable<String>() {
    @Data
    public open class RequestDto(
        @JsonProperty("first_name")
        var firstName: String? = null,
        @JsonProperty("last_name")
        var lastName: String? = null,
        var email: String? = null
    ) {
        companion object {
            fun map(employeeDto: RequestDto): Employee {
                return Employee(
                    null,
                    employeeDto.firstName,
                    employeeDto.lastName,
                    employeeDto.email
                )
            }
        }
    }

    public class ResponseDto(
        var id: Long? = null,
        @JsonProperty("first_name")
        var firstName: String? = null,
        @JsonProperty("last_name")
        var lastName: String? = null,
        var email: String? = null
    ) {
        companion object {
            fun map(employee: Employee?): ResponseDto {
                if (employee == null)
                    return ResponseDto()
                return ResponseDto(
                    employee.id,
                    employee.firstName,
                    employee.lastName,
                    employee.email
                )
            }
        }
    }
}