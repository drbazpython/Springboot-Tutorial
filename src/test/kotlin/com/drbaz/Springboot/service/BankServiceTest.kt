package com.drbaz.Springboot.service

import com.drbaz.Springboot.datasource.BankDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BankServiceTest {
    private val dataSource: BankDataSource = mockk(relaxed = true)
    private val bankService = BankService(dataSource)
    @Test
    fun `should call its data source top retrieve banks`() {
        //given
        //every { dataSource.retrieveBanks()} returns  emptyList()
        //when
        bankService.getBanks()
        //then
        verify(exactly=1) {dataSource.retrieveBanks()}
    }
}