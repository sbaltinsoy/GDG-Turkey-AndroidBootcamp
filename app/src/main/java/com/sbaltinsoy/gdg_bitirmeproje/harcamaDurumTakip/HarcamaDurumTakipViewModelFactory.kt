package com.sbaltinsoy.gdg_bitirmeproje.harcamaDurumTakip

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sbaltinsoy.gdg_bitirmeproje.isimler.UsersDao
import com.sbaltinsoy.gdg_bitirmeproje.veritabani.VeriTabaniErisimNesnesi

class HarcamaDurumTakipViewModelFactory(
    private val veriKaynagi: VeriTabaniErisimNesnesi,
    private val uygulama: Application,
    //private val isimKaynak: UsersDao,
) : ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HarcamaDurumTakipViewModel::class.java)) {
            return HarcamaDurumTakipViewModel(veriKaynagi, uygulama) as T
        }
        throw IllegalArgumentException("Bilinmeyen ViewModel Sınıfı")
    }

}