package com.sbaltinsoy.gdg_bitirmeproje.harcamaDurumTakip

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.sbaltinsoy.gdg_bitirmeproje.isimler.UserName
import com.sbaltinsoy.gdg_bitirmeproje.isimler.UsersDao
import com.sbaltinsoy.gdg_bitirmeproje.network.CurrencyProperty
import com.sbaltinsoy.gdg_bitirmeproje.network.DovizApi
import com.sbaltinsoy.gdg_bitirmeproje.servis.Para
import com.sbaltinsoy.gdg_bitirmeproje.servis.ParaDataBase
import com.sbaltinsoy.gdg_bitirmeproje.veritabani.HarcamaDurum
import com.sbaltinsoy.gdg_bitirmeproje.veritabani.VeriTabaniErisimNesnesi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HarcamaDurumTakipViewModel(
     val veriKaynagi: VeriTabaniErisimNesnesi,
     private val uygulama: Application,
    // private val isimKaynak: UsersDao,
) : AndroidViewModel(uygulama){
     var harcamalar = veriKaynagi.tumHarcamaVerisiniGetir()
    // var isimler = isimKaynak.getAllUsers()

    lateinit var isim: LiveData<Boolean>
   // var sonIsim = MutableLiveData<UserName?>()
    var sonHarcama = MutableLiveData<HarcamaDurum?>()

     private val _harcamaDetayaYonlendir = MutableLiveData<Long?>()
     val harcamaDetayaYonlendir
          get() = _harcamaDetayaYonlendir

     fun harcamaDetayaYonlendirmeTamamlandi() {
          _harcamaDetayaYonlendir.value = null
     }


     fun harcamaDurumTiklandi(id: Long) {
          _harcamaDetayaYonlendir.value = id
     }

/*
    private suspend fun sonIsimVeriTabanindanAl(): UserName?{
        var userName = isimKaynak.readUser()



            if(userName?.user_name == "Isim" )
                userName = null

        return userName

    }

    private fun sonIsmiIlklendir(){
        viewModelScope.launch {
            sonIsim.value = sonIsimVeriTabanindanAl()
        }
    }
*/
     private suspend fun sonHarcamaVeriTabanindanAl(): HarcamaDurum?{
          var harcama = veriKaynagi.sonHarcamaDurumuGetir()

          if(harcama?.harcamaTuru == -1 )
               harcama = null
          return harcama
     }


     private fun sonHarcamayiIlklendir(){
          viewModelScope.launch {
               sonHarcama.value = sonHarcamaVeriTabanindanAl()
          }
     }

     init{
          //sonIsmiIlklendir()
          sonHarcamayiIlklendir()
          dovizKurunuGuncelle()
     }

     private fun dovizKurunuGuncelle(){
          DovizApi.retrofitService.getProperties().enqueue(
               object : Callback<CurrencyProperty> {
                    override fun onResponse(
                         call: Call<CurrencyProperty>,
                         response: Response<CurrencyProperty>
                    ) {
                         response.body()?.let {
                             sqliteSakla(it)
                         }
                    }
                    override fun onFailure(call: Call<CurrencyProperty>, t: Throwable) {
                         Toast.makeText(
                              context,
                              "Veriler DataBase den Cekildi",
                              Toast.LENGTH_LONG,
                         ).show()
                    }

               })
     }


    private fun sqliteSakla(currencyProperty: CurrencyProperty){
         viewModelScope.launch {
              val dao = ParaDataBase(getApplication()).paraDao()
              dao.deleteAllPara()
              var paraMosho = Para()
              paraMosho.dolar = currencyProperty.rates.USD
              paraMosho.turklirasi = currencyProperty.rates.TRY
              paraMosho.sterlin = currencyProperty.rates.GBP
              dao.ekle(paraMosho)
         }
    }


     private val _euroButonGorunmesi = MutableLiveData<Boolean>()
          val euroButonGorunmesi: LiveData<Boolean>
               get() = _euroButonGorunmesi

     var _dolarButonGorunmesi = MutableLiveData<Boolean>()
     val dolarButonGorunmesi: LiveData<Boolean>
          get() = _dolarButonGorunmesi

     var _sterlinButonGorunmesi = MutableLiveData<Boolean>()
     val sterlinButonGorunmesi: LiveData<Boolean>
          get() = _sterlinButonGorunmesi

     var _tlButonGorunmesi = MutableLiveData<Boolean>()
     val tlButonGorunmesi: LiveData<Boolean>
          get() = _tlButonGorunmesi



    var _butonId = MutableLiveData<Int>(1)
    val butonId: LiveData<Int>
        get() = _butonId

     fun euroButonKontrol(){
          _euroButonGorunmesi.value = false
          _dolarButonGorunmesi.value = true
          _sterlinButonGorunmesi.value = true
          _tlButonGorunmesi.value = true
          _butonId.value = 3
     }

     fun sterlinButonKontrol(){
          _euroButonGorunmesi.value = true
          _dolarButonGorunmesi.value = true
          _sterlinButonGorunmesi.value = false
          _tlButonGorunmesi.value = true
          _butonId.value = 2
     }
     fun tlButonKontrol(){
          _euroButonGorunmesi.value = true
          _dolarButonGorunmesi.value = true
          _sterlinButonGorunmesi.value = true
          _tlButonGorunmesi.value = false
          _butonId.value = 1
     }
     fun dolarButonKontrol(){
          _euroButonGorunmesi.value = true
          _dolarButonGorunmesi.value = false
          _sterlinButonGorunmesi.value = true
          _tlButonGorunmesi.value = true
          _butonId.value = 4
     }


}

