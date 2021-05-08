package com.sbaltinsoy.gdg_bitirmeproje.isimler

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sbaltinsoy.gdg_bitirmeproje.veritabani.VeriTabaniErisimNesnesi


// singleton => factory => static
@Database(entities = [UserName::class], version = 1,exportSchema = false)
abstract class IsimVeriTabani: RoomDatabase() {
    abstract val usersDao: UsersDao
    // static obje 1 sefer olusturuluyor
    companion object{
        @Volatile
        private var ORNEK_NESNE: IsimVeriTabani? = null

        fun ornegiGetir(baglam: Context): IsimVeriTabani {
            synchronized(this){
                var ornek = ORNEK_NESNE
                if(ornek == null){
                    ornek = Room.databaseBuilder(
                        baglam.applicationContext,
                        IsimVeriTabani::class.java,
                        "isim_veritabani"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    ORNEK_NESNE = ornek
                }

                return ornek
            }
        }
    }
}