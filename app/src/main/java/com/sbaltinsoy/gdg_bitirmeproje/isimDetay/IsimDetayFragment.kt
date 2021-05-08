package com.sbaltinsoy.gdg_bitirmeproje.isimDetay

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sbaltinsoy.gdg_bitirmeproje.R
import com.sbaltinsoy.gdg_bitirmeproje.databinding.IsimDetayFragmentBinding
import com.sbaltinsoy.gdg_bitirmeproje.isimler.IsimVeriTabani
import com.sbaltinsoy.gdg_bitirmeproje.isimler.UserName

@Suppress("UNREACHABLE_CODE")
class IsimDetayFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding: IsimDetayFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.isim_detay_fragment,container,false)
        return binding.root

        val uygulama = requireNotNull(this.activity).application
        val veriKaynagi = IsimVeriTabani.ornegiGetir(uygulama).usersDao

        val goruntuModelFactory = IsimDetayViewModelFactory(veriKaynagi,uygulama)

        val isimDetayViewModel = ViewModelProvider(this, goruntuModelFactory).get(
            IsimDetayViewModel::class.java)

        binding.isim = isimDetayViewModel


        binding.kaydetButton.setOnClickListener(){
            val userName = UserName()
               userName.user_name = binding.textIsim.text.toString()

            when(binding.radioGroupCinsiyet.checkedRadioButtonId){
                binding.radioButtonErkek.id -> {
                    userName.user_name = userName.user_name + "Bey"
                }
                binding.radioButtonKadin.id -> {
                    userName.user_name = userName.user_name + "Hanım"
                }
                binding.radioButtonBelirtme.id ->{
                    userName.user_name = userName.user_name
                }
            }

            isimDetayViewModel.veriEkle(userName)

            this.findNavController().navigate(R.id.action_isimDetayFragment_to_harcamaDurumTakipFragment)

        }

    }


}