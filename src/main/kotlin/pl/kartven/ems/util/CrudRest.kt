package pl.kartven.ems.util

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

interface CrudRest<REQ: Any, RES: Any> {
    @GetMapping
    fun getAll(): ResponseEntity<List<RES>>

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): ResponseEntity<RES>

    @PostMapping
    fun save(@RequestBody objectDto: REQ, request: HttpServletRequest): ResponseEntity<RES>

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody objectDto: REQ)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long)
}