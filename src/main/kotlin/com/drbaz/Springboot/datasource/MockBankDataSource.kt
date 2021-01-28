package com.drbaz.Springboot.datasource

import com.drbaz.Springboot.model.Bank
import com.drbaz.Springboot.datasource.BankDataSource
import org.springframework.stereotype.Repository
/*
* These functions overide functions in BankDataSource
 */
@Repository
class MockBankDataSource : BankDataSource
{
    val banks =  mutableListOf(
        Bank(accountNumber = "1234", trust = 4.0, transactionFee = 1),
        Bank(accountNumber = "4567", trust = 2.0, transactionFee = 2),
        Bank(accountNumber = "8910", trust = 3.4, transactionFee = 2),
    )

    override fun retrieveBanks(): Collection<Bank> = banks

    override fun retrieveBank(accountNumber: String): Bank {
        return banks.firstOrNull(){ it.accountNumber == accountNumber}
            ?: throw NoSuchElementException("Could not find a bank with account number $accountNumber")
    }
    override fun createBank(bank:Bank): Bank{
        if (banks.any{ it.accountNumber == bank.accountNumber}){
            throw IllegalArgumentException("Bank with account number ${bank.accountNumber} already exists")
        }
        banks.add(bank)
        return bank
    }
    override fun updateBank(bank:Bank): Bank{
        val currentBank = banks.firstOrNull{ it.accountNumber == bank.accountNumber}
            ?: throw IllegalArgumentException("Bank with account number ${bank.accountNumber} does not exist")

        banks.remove(currentBank)
        banks.add(bank)
        return bank
    }

    override fun deleteBank(accountNumber: String) {
        val currentBank = banks.firstOrNull { it.accountNumber == accountNumber }
            ?: throw IllegalArgumentException("Bank with account number ${accountNumber} does not exist")

        banks.remove(currentBank)
    }

}