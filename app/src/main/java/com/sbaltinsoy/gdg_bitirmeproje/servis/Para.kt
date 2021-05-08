package com.sbaltinsoy.gdg_bitirmeproje.servis


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Para(
    @PrimaryKey(autoGenerate = true)
    var paraId: Long = 0L,
    @ColumnInfo(name = "euro")
    var euro: Double = 1.0,
    @ColumnInfo(name = "dolar")
    var dolar: Double = 1.0,
    @ColumnInfo(name = "turklirasi")
    var turklirasi: Double = 1.0,
    @ColumnInfo(name = "sterlin")
    var sterlin: Double = 1.0,
)