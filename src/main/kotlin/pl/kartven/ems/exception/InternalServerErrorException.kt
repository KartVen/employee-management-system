package pl.kartven.ems.exception

class InternalServerErrorException : RuntimeException {
    constructor(message: String?) : super(message)
    constructor() : super()
}