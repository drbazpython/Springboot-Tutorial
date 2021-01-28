package com.drbaz.Springboot.model
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.MongoId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection="Banks")
data class mongoBank(
    @Id
    val id: ObjectId = ObjectId.get(),
    val accountNumber: String,
    val trust: Double,
    val transactionFee: Int
)
