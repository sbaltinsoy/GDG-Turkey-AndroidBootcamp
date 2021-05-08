package com.sbaltinsoy.gdg_bitirmeproje.harcamaDurumTakip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sbaltinsoy.gdg_bitirmeproje.R
import com.sbaltinsoy.gdg_bitirmeproje.databinding.HarcamaDurumTakipFragmentBinding
import com.sbaltinsoy.gdg_bitirmeproje.isimler.IsimVeriTabani
import com.sbaltinsoy.gdg_bitirmeproje.veritabani.Veritabani


class HarcamaDurumTakipFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: HarcamaDurumTakipFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.harcama_durum_takip_fragment,container,false
        )

        val uygulama = requireNotNull(this.activity).application
        val veriKaynagi = Veritabani.ornegiGetir(uygulama).veriTabaniErisimNesnesi
       //val isimKaynak = IsimVeriTabani.ornegiGetir(uygulama).usersDao

        val goruntuModelFactory = HarcamaDurumTakipViewModelFactory(veriKaynagi, uygulama)

        val harcamaDurumTakipViewModel =
                ViewModelProvider(this, goruntuModelFactory).get(HarcamaDurumTakipViewModel::class.java)

        //binding.setLifecycleOwner(this)

        binding.harcamaDurumTakipViewModel = harcamaDurumTakipViewModel

        binding.extendedFab.setOnClickListener(){
            findNavController().navigate(HarcamaDurumTakipFragmentDirections.actionHarcamaDurumTakipFragmentToHarcamaOlusturFragment())
        }
        //binding.textIsim.text = isimKaynak.readUser()?.user_name.toString()
        val adaptor = HarcamaGoruntuAdaptoru(TiklamaTakipcisi { duyguId ->
            harcamaDurumTakipViewModel.harcamaDurumTiklandi(duyguId)
        }, harcamaDurumTakipViewModel.butonId, requireContext(),harcamaDurumTakipViewModel)
        binding.harcamaListesi.adapter = adaptor

        harcamaDurumTakipViewModel.dolarButonGorunmesi.observe(viewLifecycleOwner, {
            binding.dolarButon.isEnabled = it == true
            adaptor.notifyDataSetChanged()
        })

        harcamaDurumTakipViewModel.euroButonGorunmesi.observe(viewLifecycleOwner, {
            binding.euroButon.isEnabled = it == true
            adaptor.notifyDataSetChanged()
        })

        harcamaDurumTakipViewModel.sterlinButonGorunmesi.observe(viewLifecycleOwner, {
            binding.sterlinButon.isEnabled = it == true
            adaptor.notifyDataSetChanged()
        })

        harcamaDurumTakipViewModel.tlButonGorunmesi.observe(viewLifecycleOwner, {
            binding.tlButon.isEnabled = it == true
            adaptor.notifyDataSetChanged()
        })

        harcamaDurumTakipViewModel.harcamaDetayaYonlendir.observe(viewLifecycleOwner, {harcamaId ->
            harcamaId?.let {
                this.findNavController().navigate(HarcamaDurumTakipFragmentDirections.actionHarcamaDurumTakipFragmentToHarcamaDetayFragment(harcamaId))
                harcamaDurumTakipViewModel.harcamaDetayaYonlendirmeTamamlandi()
            }
        })

        binding.cardViewHome.setOnClickListener{
            //this.findNavController().navigate()
            this.findNavController().navigate(R.id.action_harcamaDurumTakipFragment_to_isimDetayFragment)
        }
/*
        harcamaDurumTakipViewModel.isimler.observe(viewLifecycleOwner,{
                harcamaDurumTakipViewModel.viewModelScope.launch() {
                    var userName = UserName()
                    userName.user_name = isimKaynak.readUser().toString()
                    binding.textIsim.text = userName.user_name
            }
        })
*/
        harcamaDurumTakipViewModel.harcamalar.observe(viewLifecycleOwner,{
            it.let { harcamalar ->
                  adaptor.notifyDataSetChanged()
                  adaptor.submitList(harcamalar)
            }
        })

        val manager = GridLayoutManager(activity, 1,GridLayoutManager.VERTICAL,false)
        binding.harcamaListesi.layoutManager = manager


        return binding.root
    }





}
