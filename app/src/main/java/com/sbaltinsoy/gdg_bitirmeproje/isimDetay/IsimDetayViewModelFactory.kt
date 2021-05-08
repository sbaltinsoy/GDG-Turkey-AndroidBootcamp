package com.sbaltinsoy.gdg_bitirmeproje.isimDetay

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sbaltinsoy.gdg_bitirmeproje.isimler.UsersDao
import com.sbaltinsoy.gdg_bitirmeproje.veritabani.VeriTabaniErisimNesnesi

class IsimDetayViewModelFactory(
    private val veriKaynagi: UsersDao,
    private val uygulama: Application,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IsimDetayViewModel::class.java)) {
            return IsimDetayViewModel(veriKaynagi, uygulama) as T
        }
        throw IllegalArgumentException("Bilinmeyen ViewModel Sınıfı")
    }
}