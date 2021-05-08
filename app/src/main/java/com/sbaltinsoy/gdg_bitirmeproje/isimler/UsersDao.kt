package com.sbaltinsoy.gdg_bitirmeproje.isimler

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsersDao {
    @Insert
    suspend fun addUser(userName: UserName?)
    @Query("SELECT * FROM users ORDER BY id DESC LIMIT 1")
    fun readUser(): UserName?
    @Query("DELETE FROM users")
    suspend fun deleteUser()
    @Query("SELECT * FROM users ORDER BY id DESC")
    fun getAllUsers(): LiveData<List<UserName>>
}