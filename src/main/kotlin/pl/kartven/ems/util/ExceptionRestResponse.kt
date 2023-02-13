package pl.kartven.ems.util

import java.util.*

class ExceptionRestResponse(
    var timestamp: Date?,
    var status: Int,
    var message: String?,
    var description: String?
)