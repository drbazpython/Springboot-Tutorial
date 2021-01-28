package com.drbaz.Springboot.datasource
import com.drbaz.Springboot.model.Bank
import com.drbaz.Springboot.model.mongoBank
import org.springframework.http.ResponseEntity

interface BankDataSource {

    fun getAllBanks(): ResponseEntity<List<mongoBank>>{
        TODO( "not yet implemented")
    }
    fun retrieveBanks(): Collection<Bank>{
        TODO( "not yet implemented")
    }

    fun retrieveBank(accountNumber: String) :Bank{
        TODO( "not yet implemented")
    }

    fun createBank(bank: Bank): Bank{
        TODO( "not yet implemented")
    }
    fun updateBank(bank: Bank): Bank{
        TODO("not yet implemented")
    }

    fun deleteBank(accountNumber: String) {
        TODO( "not yet implemented")

    }

}