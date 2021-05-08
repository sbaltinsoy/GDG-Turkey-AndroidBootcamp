package com.sbaltinsoy.gdg_bitirmeproje.veritabani

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// singleton => factory => static
@Database(entities = [HarcamaDurum::class], version = 1,exportSchema = false)
abstract class Veritabani: RoomDatabase() {
    abstract val veriTabaniErisimNesnesi: VeriTabaniErisimNesnesi
    // static obje 1 sefer olusturuluyor
    companion object{
        @Volatile
        private var ORNEK_NESNE: Veritabani? = null

        fun ornegiGetir(baglam: Context): Veritabani{
            synchronized(this){
                var ornek = ORNEK_NESNE
                if(ornek == null){
                    ornek = Room.databaseBuilder(
                        baglam.applicationContext,
                        Veritabani::class.java,
                        "harcama_durum_veritabani"
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