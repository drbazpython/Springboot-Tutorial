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
/*@Controller
* must use @Controller with Thymeleaf
* Hence the TyhmeleafController
*/

@RestController
@RequestMapping("/api/banks")
class BankController(private val service: BankService, private val mongoRepository: MongoRepository<mongoBank, Id>) {
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping("/mongo")
        fun getAllBanks(): ResponseEntity<List<mongoBank>> =service.getAllBanks()

        @GetMapping
        fun getBanks(): Collection<Bank> = service.getBanks()

        //fun getBanks(): String = "works"
        @GetMapping("/{accountNumber}")
        fun getBank(@PathVariable accountNumber: String): Bank = service.getBank(accountNumber)

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        fun addBank(@RequestBody bank: Bank): Bank = service.addBank(bank)

        @PatchMapping
        fun updateBank(@RequestBody bank: Bank): Bank = service.updateBank(bank)

        @DeleteMapping("/{accountNumber}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        // Unit is a void methos
        fun deleteBank(@PathVariable accountNumber: String): Unit = service.deleteBank(accountNumber)
    }
