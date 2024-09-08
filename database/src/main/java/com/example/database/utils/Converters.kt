package com.example.database.utils

import androidx.room.TypeConverter
import com.example.network.data.Address
import com.example.network.data.Experience
import com.example.network.data.Salary
import com.example.network.data.Vocation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromVocation(vocation: Vocation): String {
        return gson.toJson(vocation)
    }

    @TypeConverter
    fun toVocation(vocationString: String): Vocation {
        val vocationType = object : TypeToken<Vocation>() {}.type
        return gson.fromJson(vocationString, vocationType)
    }

    @TypeConverter
    fun fromAddress(address: Address): String {
        return gson.toJson(address)
    }

    @TypeConverter
    fun toAddress(addressString: String): Address {
        val addressType = object : TypeToken<Address>() {}.type
        return gson.fromJson(addressString, addressType)
    }

    @TypeConverter
    fun fromExperience(experience: Experience): String {
        return gson.toJson(experience)
    }

    @TypeConverter
    fun toExperience(experienceString: String): Experience {
        val experienceType = object : TypeToken<Experience>() {}.type
        return gson.fromJson(experienceString, experienceType)
    }

    @TypeConverter
    fun fromSalary(salary: Salary): String {
        return gson.toJson(salary)
    }

    @TypeConverter
    fun toSalary(salaryString: String): Salary {
        val salaryType = object : TypeToken<Salary>() {}.type
        return gson.fromJson(salaryString, salaryType)
    }
}