package com.sbaltinsoy.gdg_bitirmeproje.onBoarding.Screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.sbaltinsoy.gdg_bitirmeproje.R
import com.sbaltinsoy.gdg_bitirmeproje.databinding.ThirdScreenFragmentBinding
import kotlinx.android.synthetic.main.third_screen_fragment.view.*

class ThirdScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.third_screen_fragment, container, false)

        view.finis_buton.setOnClickListener {
            onBoardingFinished()
            findNavController().navigate(R.id.action_viewPagerFragment_to_harcamaDurumTakipFragment)

        }

        return view
    }

    private fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Fisnished",true)
        editor.apply()
    }
}