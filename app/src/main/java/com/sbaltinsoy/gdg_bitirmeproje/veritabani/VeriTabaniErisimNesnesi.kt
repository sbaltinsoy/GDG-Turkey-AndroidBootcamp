package com.sbaltinsoy.gdg_bitirmeproje.veritabani

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sbaltinsoy.gdg_bitirmeproje.isimler.UserName


@Dao
interface VeriTabaniErisimNesnesi {
    @Insert
    suspend fun ekle(harcamaDurum: HarcamaDurum)

    @Query(value = "SELECT * FROM harcama_durum_tablosu ORDER BY kimlikNumarasi DESC")
    fun tumHarcamaVerisiniGetir(): LiveData<List<HarcamaDurum>>

    @Query(value = "SELECT * FROM harcama_durum_tablosu WHERE kimlikNumarasi = :kimlik")
    suspend fun getir(kimlik: Long): HarcamaDurum?

    @Query("SELECT * FROM harcama_durum_tablosu WHERE kimlikNumarasi = :anahtar")
    fun kimlikGetir(anahtar: Long): LiveData<HarcamaDurum?>

    @Query(value = "SELECT * FROM harcama_durum_tablosu ORDER BY kimlikNumarasi DESC LIMIT 1")
    suspend fun sonHarcamaDurumuGetir(): HarcamaDurum?

    @Update
    suspend fun guncelle(harcamaDurum: HarcamaDurum)

    @Delete
    suspend fun sil(harcamaDurum: HarcamaDurum)

    @Query("DELETE FROM harcama_durum_tablosu")
    suspend fun tumVeriyiTemizle()





}