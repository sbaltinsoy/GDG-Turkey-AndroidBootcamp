package com.sbaltinsoy.gdg_bitirmeproje.harcamaDetay

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sbaltinsoy.gdg_bitirmeproje.harcamaDurumTakip.HarcamaDurumTakipViewModel
import com.sbaltinsoy.gdg_bitirmeproje.veritabani.VeriTabaniErisimNesnesi

class HarcamaDetayViewModelFactory (
    private val duyguId: Long,
    private val veriKaynagi: VeriTabaniErisimNesnesi
) : ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HarcamaDetayViewModel::class.java)) {
            return HarcamaDetayViewModel(duyguId, veriKaynagi) as T
        }
        throw IllegalArgumentException("Bilinmeyen ViewModel Sınıfı")
    }

}