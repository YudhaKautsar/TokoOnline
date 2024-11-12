package com.yudha.tokoonline.api.model.localDatabase.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey val id: Int,
    val email: String
)
//    val name: String,
    /*val username: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val city: String,
    val street: String,
    val number: Int,
    val zipcode: String,
    val lat: Double,
    val long: Double,
    val phone: String*/

/*
data class Name(
    val firstname: String,
    val lastname: String
)

data class Address(
    val city: String,
    val street: String,
    val number: Int,
    val zipcode: String,
    val geolocation: Geolocation
)

data class Geolocation(
    val lat: String,
    val long: String
)*/
