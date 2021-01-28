package com.drbaz.Springboot.BankRepository

import com.drbaz.Springboot.model.Bank
import com.drbaz.Springboot.model.mongoBank
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.repository.MongoRepository

interface BankRepository<T, U> : MongoRepository<mongoBank,Id> {
    fun findOneById(id: ObjectId): mongoBank

}