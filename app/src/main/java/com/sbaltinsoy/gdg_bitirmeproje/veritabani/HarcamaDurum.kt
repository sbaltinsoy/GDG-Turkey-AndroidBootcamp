package com.sbaltinsoy.gdg_bitirmeproje.veritabani


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "harcama_durum_tablosu")
data class HarcamaDurum (
    @PrimaryKey(autoGenerate = true)
    var kimlikNumarasi: Long = 0L,
    @ColumnInfo(name = "harcama_turu")
    var harcamaTuru: Int = -1,
    @ColumnInfo(name = "harcanan_miktar")
    var harcananMiktar: Int = -1,
    @ColumnInfo(name = "durum")
    var paraBirimi: Int = -1,
    @ColumnInfo(name = "harcama_aciklama")
    var harcamaAciklama: String = "-"
)
