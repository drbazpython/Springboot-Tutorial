package com.drbaz.Springboot.datasource
import com.drbaz.Springboot.model.Bank
interface BankDataSource {

    fun retrieveBanks(): Collection<Bank>

}