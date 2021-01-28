package com.drbaz.Springboot.datasource

import com.drbaz.Springboot.BankRepository.BankRepository
import com.drbaz.Springboot.model.Bank
import com.drbaz.Springboot.model.mongoBank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate

/*
*we have two implementations of BankDataSourse ( MockBankDataSource and this one)
*must mark one of these implementations as PRIMARY
 */
@Repository("mongodb")
class MongoDataSource (@Autowired private val mongoRepository: MongoRepository<mongoBank,Id>): BankDataSource{


     override fun  getAllBanks(): ResponseEntity<List<mongoBank>> {
                val banks: List<mongoBank> = mongoRepository.findAll()
                return ResponseEntity.ok(banks)
            }

        override fun retrieveBank(accountNumber: String): Bank {
            TODO("not yet implemented")
        }

        override fun createBank(bank: Bank): Bank {
            TODO("not yet implemented")
        }

        override fun updateBank(bank: Bank): Bank {
            TODO("not yet implemented")
        }

        override fun deleteBank(accountNumber: String) {
            TODO("not yet implemented")

        }
    }
