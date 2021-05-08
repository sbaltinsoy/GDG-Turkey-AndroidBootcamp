package com.sbaltinsoy.gdg_bitirmeproje.harcamaDetay

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbaltinsoy.gdg_bitirmeproje.veritabani.HarcamaDurum
import com.sbaltinsoy.gdg_bitirmeproje.veritabani.VeriTabaniErisimNesnesi
import com.sbaltinsoy.gdg_bitirmeproje.veritabani.Veritabani
import kotlinx.coroutines.launch

class HarcamaDetayViewModel(
    private val harcamaId: Long = 0L,
    val veritabani: VeriTabaniErisimNesnesi
) : ViewModel() {
    private val _harcamaDurumTakibeYonlendir = MutableLiveData<Boolean?>()

    val harcamaTakibeYonlendir: LiveData<Boolean?>
        get() = _harcamaDurumTakibeYonlendir


    val harcamaDurum: LiveData<HarcamaDurum?>

    init {
        harcamaDurum = veritabani.kimlikGetir(harcamaId)
    }

    fun yonlendirmeTamamlandi() {
        _harcamaDurumTakibeYonlendir.value = null
    }

    fun harcamaDurumuSil() {
        viewModelScope.launch {
            val harcama = veritabani.getir(harcamaId) ?: return@launch
            veritabani.sil(harcama)

            _harcamaDurumTakibeYonlendir.value = true
        }
    }

    fun yonlendirmeTiklandi() {
        _harcamaDurumTakibeYonlendir.value = true
    }

}