package pl.kartven.ems.exception

class NotFoundException : RuntimeException {
    constructor(message: String?) : super(message)
    constructor() : super()
}