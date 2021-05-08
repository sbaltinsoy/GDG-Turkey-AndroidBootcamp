package com.sbaltinsoy.gdg_bitirmeproje.servis

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Para::class], version = 1,exportSchema = false)
abstract class ParaDataBase : RoomDatabase() {
    abstract fun paraDao(): paraDao

    // Singleton

    companion object{

        @Volatile private var instance : ParaDataBase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: databaseOlustur(context).also {
                instance = it
            }
        }

        private fun databaseOlustur(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ParaDataBase::class.java,
            "paradatabase").build()
    }
}