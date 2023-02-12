package pl.kartven.ems.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(AuditConfig::class)
class AppConfig 