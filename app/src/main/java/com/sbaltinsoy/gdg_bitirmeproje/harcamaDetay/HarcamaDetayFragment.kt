package com.sbaltinsoy.gdg_bitirmeproje.harcamaDetay

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.sbaltinsoy.gdg_bitirmeproje.R
import com.sbaltinsoy.gdg_bitirmeproje.databinding.HarcamaDetayFragmentBinding
import com.sbaltinsoy.gdg_bitirmeproje.veritabani.Veritabani

class HarcamaDetayFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val veriBagi: HarcamaDetayFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.harcama_detay_fragment, container, false
        )

        val argumanlar = HarcamaDetayFragmentArgs.fromBundle(requireArguments())
        val uygulama = requireNotNull(this.activity).application

        val veriKaynagi = Veritabani.ornegiGetir(uygulama).veriTabaniErisimNesnesi

        val harcamaDetayGoruntuModelFactory =
            HarcamaDetayViewModelFactory(argumanlar.harcamaKimlik, veriKaynagi)

        val harcamaDetayGoruntuModel = ViewModelProvider(
            this,
             harcamaDetayGoruntuModelFactory
        ).get(HarcamaDetayViewModel::class.java)

        veriBagi.harcamaDetayViewModel = harcamaDetayGoruntuModel

        veriBagi.setLifecycleOwner(this)



        harcamaDetayGoruntuModel.harcamaTakibeYonlendir.observe(viewLifecycleOwner, {
            if (it == true) {
                this.findNavController()
                    .navigate(HarcamaDetayFragmentDirections.actionHarcamaDetayFragmentToHarcamaDurumTakipFragment())

                harcamaDetayGoruntuModel.yonlendirmeTamamlandi()
            }
        })

        return veriBagi.root
    }


}