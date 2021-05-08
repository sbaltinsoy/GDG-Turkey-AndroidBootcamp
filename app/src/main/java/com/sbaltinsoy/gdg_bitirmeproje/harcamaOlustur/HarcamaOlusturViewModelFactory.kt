package com.sbaltinsoy.gdg_bitirmeproje.harcamaOlustur

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sbaltinsoy.gdg_bitirmeproje.veritabani.VeriTabaniErisimNesnesi

class HarcamaOlusturViewModelFactory(
    private val veriKaynagi: VeriTabaniErisimNesnesi,
    private val uygulama: Application,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HarcamaOlusturViewModel::class.java)) {
            return HarcamaOlusturViewModel(veriKaynagi, uygulama) as T
        }
        throw IllegalArgumentException("Bilinmeyen ViewModel Sınıfı")
    }
}