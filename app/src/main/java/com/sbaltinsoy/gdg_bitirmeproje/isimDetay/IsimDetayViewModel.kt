package com.sbaltinsoy.gdg_bitirmeproje.isimDetay

import android.app.Application
import androidx.lifecycle.*
import com.sbaltinsoy.gdg_bitirmeproje.isimler.UserName
import com.sbaltinsoy.gdg_bitirmeproje.isimler.UsersDao
import com.sbaltinsoy.gdg_bitirmeproje.veritabani.VeriTabaniErisimNesnesi
import kotlinx.coroutines.launch

class IsimDetayViewModel(
    val veriTabaniErisimNesnesi: UsersDao,
    uygulama: Application
): AndroidViewModel(uygulama){

    private var _veriEkleButonKontrol = MutableLiveData<Boolean?>()
    val veriEkleButonKontrol: LiveData<Boolean?>
        get() = _veriEkleButonKontrol


    fun veriEkle(userName: UserName){
        viewModelScope.launch {
            veriTabaniErisimNesnesi.addUser(userName)
        }
    }

    fun butonTikla(){
        _veriEkleButonKontrol.value = true
    }

    fun veriEkleTamamlandi(){
        _veriEkleButonKontrol.value = null
    }

}