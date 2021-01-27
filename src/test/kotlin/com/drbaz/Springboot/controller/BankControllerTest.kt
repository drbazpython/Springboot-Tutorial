package com.drbaz.Springboot.controller

import com.drbaz.Springboot.model.Bank
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
  val mockMvc: MockMvc,
  val objectMapper: ObjectMapper
        ) {

    //@Autowired
    //lateinit var mockMvc: MockMvc

    //@Autowired
    //lateinit var objectMapper: ObjectMapper

    @Nested
    @DisplayName("GET /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBanks {
        @Test
        fun `should return all banks `() {
            //when then
            mockMvc.get("/api/banks")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].accountNumber") { value("1234") }

                }

        }
    }

    @Nested
    @DisplayName("GET /api/bank")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBank {
        @Test
        fun `should return the bank with the given accout number`() {
            //given
            val accountNumber = 1234
            //when
            mockMvc.get("/api/banks/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { MediaType.APPLICATION_JSON }
                    jsonPath("$.trust") { value("4.0") }
                }
            //then

        }
    }

    @Test
    fun `should return NOT FOUND if account number does not exist`() {
        //given
        val accountNumber = "does_not_exist"
        //when then
        mockMvc.get("/api/banks/$accountNumber")
            .andDo { print() }
            .andExpect { status { isNotFound() } }

    }

    @Nested
    @DisplayName("POST /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostNewBank {
        @Test
        fun `should add the new bank`() {
            //given
            val newBank = Bank("acc123", 31.4, 2)
            //when
            val performPost = mockMvc.post("/api/banks") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }
            // then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.accountNumber") { value("acc123") }

                }


        }

        @Test
        fun `should return BAD REQUEST if duplicate account`() {
            //given
            val invalidBank = Bank("1234", 4.0, 1)
            //when
            val performPost = mockMvc.post("/api/banks") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }
            //then
            performPost
                .andDo { print() }
                .andExpect { status { isBadRequest() } }

        }
    }

    @Nested
    @DisplayName("PATCH /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PatchExistingBank {
        @Test
        fun `should update an existing bank`() {
            //given
            val updatedBank = Bank("1234", 9.9, 1)
            //when
            val performPatchRequest = mockMvc.patch("/api/banks") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedBank)
            }
            //then
            performPatchRequest
                .andDo { print("START PRINTING") }
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        //compares all fields of object
                        json(objectMapper.writeValueAsString(updatedBank))
                    }
                }
            // make sure changes are persistent
            mockMvc.get("/api/banks/${updatedBank.accountNumber}")
                .andExpect { content { json(objectMapper.writeValueAsString(updatedBank)) } }
        }

        @Test
        fun `should returna BAD REQUEST if no bank with given account number exists`() {
            val invalidBank = Bank("does_not_exist", 1.0, 1)
            //when then
            val performPatchRequest = mockMvc.patch("/api/banks/") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }
            performPatchRequest
                .andDo { print() }
                .andExpect { status { isBadRequest() } }

        }

    }

    @Nested
    @DisplayName("DELETE /api/banks/{accountNumber}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class DeleteExistingBank {
        @Test
        fun `should delete the given bank with given accountNumber `() {
            //given
            val accountNumber = 1234
            //when then
            mockMvc.delete("/api/banks/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isNoContent() }
                }

            mockMvc.get("/api/banks/$accountNumber")
                .andExpect { status { isNotFound() } }

        }

        @Test
        fun `should return BAD REQUEST if no bank with given acountNumber exists`() {
            //given
            val invalidAccountNumber = "DOES_NOT_EXIST"
            //when then
            mockMvc.delete("/api/banks/$invalidAccountNumber")
                .andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                }

        }
    }
}

