package pl.kartven.ems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import pl.kartven.ems.util.AuditorAware;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
class AuditConfig {
    @Bean
    fun auditorAware(): org.springframework.data.domain.AuditorAware<String> {
        return AuditorAware();
    }
}
