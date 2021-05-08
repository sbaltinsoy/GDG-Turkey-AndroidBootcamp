package com.sbaltinsoy.gdg_bitirmeproje.harcamaDurumTakip

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sbaltinsoy.gdg_bitirmeproje.databinding.HarcamaGoruntuDuzeniBinding
import com.sbaltinsoy.gdg_bitirmeproje.veritabani.HarcamaDurum
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

private val adapterScope = CoroutineScope(Dispatchers.Default)




class HarcamaGoruntuFarkGeriBildirimi : DiffUtil.ItemCallback<HarcamaDurum>() {
    override fun areItemsTheSame(eski: HarcamaDurum, yeni: HarcamaDurum): Boolean {
        return eski.kimlikNumarasi == yeni.kimlikNumarasi
    }

    override fun areContentsTheSame(eski: HarcamaDurum, yeni: HarcamaDurum): Boolean {
        return eski == yeni
    }
}

class TiklamaTakipcisi(val tiklamaTakipcisi: (harcamaId: Long) -> Unit) {
    fun tiklandi(harcama: HarcamaDurum) = tiklamaTakipcisi(harcama.kimlikNumarasi)
}

class HarcamaGoruntuAdaptoru(val tiklamaTakipcisi: TiklamaTakipcisi,var butonId: LiveData<Int>,var context: Context,var viewDurum: HarcamaDurumTakipViewModel) :
    ListAdapter<HarcamaDurum, HarcamaGoruntuAdaptoru.GoruntuOlusturucu>(HarcamaGoruntuFarkGeriBildirimi()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoruntuOlusturucu {
        return GoruntuOlusturucu.from(parent)
    }

    override fun onBindViewHolder(tutucu: GoruntuOlusturucu, veriSirasi: Int) {
        val eleman = getItem(veriSirasi)
        durumlariAl(context,viewDurum,butonId)
        tutucu.bagla(eleman,tiklamaTakipcisi)

    }

    class GoruntuOlusturucu private constructor(private val binding: HarcamaGoruntuDuzeniBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bagla(eleman: HarcamaDurum, tiklamaTakipcisi: TiklamaTakipcisi) {
            binding.harcama = eleman
            binding.executePendingBindings()
            binding.tiklamaTakibi = tiklamaTakipcisi
        }

        companion object {
            fun from(parent: ViewGroup): GoruntuOlusturucu {
                val layoutInflater = LayoutInflater.from(parent.context)

                val baglanti =
                    HarcamaGoruntuDuzeniBinding.inflate(layoutInflater, parent, false)

                return GoruntuOlusturucu(baglanti)
            }
        }
    }





}




