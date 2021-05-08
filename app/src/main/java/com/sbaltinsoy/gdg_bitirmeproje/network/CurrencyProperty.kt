package com.sbaltinsoy.gdg_bitirmeproje.network

import androidx.room.PrimaryKey
import com.sbaltinsoy.gdg_bitirmeproje.network.Rates

data class CurrencyProperty(
    val base: String,
    val date: String,
    val rates: Rates
){

}