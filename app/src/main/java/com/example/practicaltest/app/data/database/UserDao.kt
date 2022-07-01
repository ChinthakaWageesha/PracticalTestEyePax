package com.example.practicaltest.app.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.practicaltest.app.domain.model.DUser

@Dao
interface UserDao {

    @Insert
    fun registerUser(user: DUser)

    @Query("SELECT EXISTS (SELECT * FROM users WHERE email = :email AND password = :password)")
    fun login(email: String, password: String): Boolean

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    fun getUser(email: String, password: String): DUser


}