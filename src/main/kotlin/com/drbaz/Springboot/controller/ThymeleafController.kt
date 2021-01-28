package com.drbaz.Springboot.controller
import com.drbaz.Springboot.BankRepository.BankRepository
import com.drbaz.Springboot.model.Bank
import com.drbaz.Springboot.model.mongoBank
import com.drbaz.Springboot.service.BankService
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class ThymeleafController(private val service: BankService, private val mongoRepository: MongoRepository<mongoBank, Id>) {
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @RequestMapping("/home")
    fun home(model: Model): String {
        model.addAttribute("banks", mongoRepository.findAll())
        return "home"
    }

    @RequestMapping("/index")
    fun index(): String {
        return "index"
    }

    @GetMapping("/mongo")
    fun getAllBanks(): ResponseEntity<List<mongoBank>> = service.getAllBanks()

}