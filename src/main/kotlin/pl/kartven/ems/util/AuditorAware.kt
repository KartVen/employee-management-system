package pl.kartven.ems.util

import org.springframework.data.domain.AuditorAware
import java.util.*

class AuditorAware : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        return Optional.ofNullable("User");
    }
}