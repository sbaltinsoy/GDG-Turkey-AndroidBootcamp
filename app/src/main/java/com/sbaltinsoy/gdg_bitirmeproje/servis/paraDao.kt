package com.sbaltinsoy.gdg_bitirmeproje.servis

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface paraDao {
    @Insert
    suspend fun ekle(para: Para)

    @Query("SELECT * FROM Para")
    suspend fun getAllPara() : Para?

    @Query("DELETE FROM Para")
    suspend fun deleteAllPara()
}