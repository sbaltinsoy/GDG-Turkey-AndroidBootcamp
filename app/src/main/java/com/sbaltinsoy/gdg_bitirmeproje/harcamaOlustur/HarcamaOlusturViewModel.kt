package com.sbaltinsoy.gdg_bitirmeproje.harcamaOlustur

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sbaltinsoy.gdg_bitirmeproje.veritabani.HarcamaDurum
import com.sbaltinsoy.gdg_bitirmeproje.veritabani.VeriTabaniErisimNesnesi
import kotlinx.coroutines.launch

class HarcamaOlusturViewModel(
    val veritabani: VeriTabaniErisimNesnesi,
    uygulama: Application
): AndroidViewModel(uygulama){

    private var _veriEkleButonKontrol = MutableLiveData<Boolean?>()
    val veriEkleButonKontrol: LiveData<Boolean?>
        get() = _veriEkleButonKontrol


    fun veriEkle(harcamaDurum: HarcamaDurum){
        viewModelScope.launch {
            veritabani.ekle(harcamaDurum)
        }
    }

    fun butonTikla(){
        _veriEkleButonKontrol.value = true
    }

    fun veriEkleTamamlandi(){
        _veriEkleButonKontrol.value = null
    }

    init{

    }

}