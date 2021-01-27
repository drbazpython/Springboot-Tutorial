package com.drbaz.Springboot.datasource

import com.drbaz.Springboot.model.Bank
import com.drbaz.Springboot.datasource.BankDataSource
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource : BankDataSource
{
    val banks =  listOf(
        Bank(accountNumber = "1234", trust = 4.0, transactionFee = 1),
        Bank(accountNumber = "4567", trust = 2.0, transactionFee = 2),
        Bank(accountNumber = "8910", trust = 3.4, transactionFee = 2),
    )

    override fun retrieveBanks(): Collection<Bank> = banks

}