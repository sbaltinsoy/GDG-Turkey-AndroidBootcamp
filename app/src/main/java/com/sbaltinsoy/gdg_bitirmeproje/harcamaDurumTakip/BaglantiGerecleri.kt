package com.sbaltinsoy.gdg_bitirmeproje.harcamaDurumTakip

import android.annotation.SuppressLint
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbaltinsoy.gdg_bitirmeproje.*
import com.sbaltinsoy.gdg_bitirmeproje.servis.ParaDataBase
import com.sbaltinsoy.gdg_bitirmeproje.veritabani.*
import kotlinx.coroutines.launch


@SuppressLint("StaticFieldLeak")
lateinit var context: Context
lateinit var viewDurum: ViewModel
lateinit var butonId: LiveData<Int>

fun durumlariAl(context1: Context, viewDurum1: ViewModel, butonId1: LiveData<Int>){
    context = context1
    butonId = butonId1
    viewDurum = viewDurum1
}

@BindingAdapter("harcamaTuru")
fun TextView.harcamaTuruAta1(harcamaDurum: HarcamaDurum?) {
    harcamaDurum?.let {
        text = harcamaDurum.harcamaAciklama
    }
}



@BindingAdapter("harcamaMiktari")
fun TextView.harcamaMiktariAta(eleman: HarcamaDurum?) {
    eleman?.let {
        context?.let {
            viewDurum.viewModelScope.launch {
                val dao = ParaDataBase(context).paraDao()
                println("dolar" + dao.getAllPara()?.dolar)
                val para = dao.getAllPara()
                println("butonIdisi" + butonId.value)
                para?.let {
                    when (butonId.value) {
                        1 -> when (eleman.paraBirimi) {
                            1 -> text =
                                (((eleman.harcananMiktar / para.turklirasi) * para.turklirasi).toInt() .toString() + "TL")

                            2 -> text =
                                (((eleman.harcananMiktar / para.dolar) * para.turklirasi).toInt()
                                    .toString() + "TL")
                            3 -> text =
                                (((eleman.harcananMiktar / para.euro) * para.turklirasi).toInt()
                                    .toString() + "TL")
                            4 -> text =
                                (((eleman.harcananMiktar / para.sterlin) * para.turklirasi).toInt()
                                    .toString() + "TL")
                        }
                        2 -> when (eleman.paraBirimi) {
                            1 -> text =
                                (((eleman.harcananMiktar / para.turklirasi) * para.sterlin).toInt()
                                    .toString() + "ST")
                            2 -> text =
                                (((eleman.harcananMiktar / para.dolar) * para.sterlin).toInt()
                                    .toString() + "ST")
                            3 -> text =
                                (((eleman.harcananMiktar / para.euro) * para.sterlin).toInt()
                                    .toString() + "ST")
                            4 -> text =
                                (((eleman.harcananMiktar / para.sterlin) * para.sterlin).toInt()
                                    .toString() + "ST")
                        }
                        3 -> when (eleman.paraBirimi) {
                            1 -> text =
                                (((eleman.harcananMiktar / para.turklirasi) * para.euro).toInt()
                                    .toString() + "E")
                            2 -> text = (((eleman.harcananMiktar / para.dolar) * para.euro).toInt()
                                .toString() + "E")
                            3 -> text = (((eleman.harcananMiktar / para.euro) * para.euro).toInt()
                                .toString() + "E")
                            4 -> text =
                                (((eleman.harcananMiktar / para.sterlin) * para.euro).toInt()
                                    .toString() + "E")
                        }
                        4 -> when (eleman.paraBirimi) {
                            1 -> text =
                                (((eleman.harcananMiktar / para.turklirasi) * para.dolar).toInt()
                                    .toString() + "$")
                            2 -> text = (((eleman.harcananMiktar / para.dolar) * para.dolar).toInt()
                                .toString() + "$")
                            3 -> text = (((eleman.harcananMiktar / para.euro) * para.dolar).toInt()
                                .toString() + "$")
                            4 -> text =
                                (((eleman.harcananMiktar / para.sterlin) * para.dolar).toInt()
                                    .toString() + "$")
                        }
                    }

                }
            }
        }

    }
}

@BindingAdapter("harcamaIcon")
fun ImageView.setSleepImage(harcamaDurum: HarcamaDurum?) {
    harcamaDurum?.let {
        setImageResource(
            when (harcamaDurum.harcamaTuru) {
                1 -> R.drawable.invoice
                2 -> R.drawable.home
                3 -> R.drawable.shopping24
                else -> R.drawable.ic_plus_24
            }
        )
    }
}

