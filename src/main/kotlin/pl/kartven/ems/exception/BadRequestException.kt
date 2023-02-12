package pl.kartven.ems.exception

class BadRequestException : RuntimeException {
    constructor(message: String?) : super(message)
    constructor() : super()
}