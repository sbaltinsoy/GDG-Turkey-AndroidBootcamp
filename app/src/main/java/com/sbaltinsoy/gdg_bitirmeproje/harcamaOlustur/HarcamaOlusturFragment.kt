package com.sbaltinsoy.gdg_bitirmeproje.harcamaOlustur

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sbaltinsoy.gdg_bitirmeproje.R
import com.sbaltinsoy.gdg_bitirmeproje.databinding.HarcamaOlusturFragmentBinding
import com.sbaltinsoy.gdg_bitirmeproje.veritabani.HarcamaDurum
import com.sbaltinsoy.gdg_bitirmeproje.veritabani.Veritabani
import androidx.lifecycle.*

class HarcamaOlusturFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding: HarcamaOlusturFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.harcama_olustur_fragment, container, false
        )

        val uygulama = requireNotNull(this.activity).application
        val veriKaynagi = Veritabani.ornegiGetir(uygulama).veriTabaniErisimNesnesi

        val goruntuModelFactory = HarcamaOlusturViewModelFactory(veriKaynagi,uygulama)

        val harcamaOlusturViewModel = ViewModelProvider(this, goruntuModelFactory).get(HarcamaOlusturViewModel::class.java)

        binding.harcama = harcamaOlusturViewModel

        binding.addButon.setOnClickListener(){
            val harcamaDurum = HarcamaDurum()

            harcamaDurum.harcananMiktar = Integer.parseInt(binding.inputHarcama.text.toString())
            harcamaDurum.harcamaAciklama = binding.inputAciklama.text.toString()

            when(binding.radioGroupBirim.checkedRadioButtonId){
                binding.radioButtonTl.id -> {
                    harcamaDurum.paraBirimi = 1
                }
                binding.radioButtonDolar.id -> {
                    harcamaDurum.paraBirimi = 2
                }
                binding.radioButtonEuro.id -> {
                    harcamaDurum.paraBirimi = 3
                }
                binding.radioButtonSterlin.id -> {
                    harcamaDurum.paraBirimi = 4
                }
            }
            when(binding.radioGroupTur.checkedRadioButtonId){
                binding.radioButtonFatura.id -> harcamaDurum.harcamaTuru = 1;
                binding.radioButtonKira.id -> harcamaDurum.harcamaTuru = 2;
                binding.radioButtonDiger.id -> harcamaDurum.harcamaTuru = 3;
            }

            harcamaOlusturViewModel.veriEkle(harcamaDurum)

            // findNavController().navigate(R.id.action_harcamaOlusturFragment_to_harcamaDurumTakipFragment)
            var a = harcamaDurum.harcamaAciklama + " " + harcamaDurum.harcamaTuru.toString() + " " + harcamaDurum.kimlikNumarasi.toString() + " "
            a = a + harcamaDurum.harcananMiktar.toString() + " " + harcamaDurum.paraBirimi.toString()
            binding.deneme.text = a

            findNavController().navigate(HarcamaOlusturFragmentDirections.actionHarcamaOlusturFragmentToHarcamaDurumTakipFragment())

        }


        return binding.root
    }



}