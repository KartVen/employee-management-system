package pl.kartven.ems.util

import lombok.Getter
import lombok.Setter
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.Date
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass
import javax.persistence.Temporal
import javax.persistence.TemporalType


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
@Getter
@Setter

abstract class Auditable<U : Any> {
    @CreatedBy
    protected var createdBy: U? = null

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected var createdDate: Date? = null

    @LastModifiedBy
    protected var lastModifiedBy: U? = null

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected var lastModifiedDate: Date? = null
}