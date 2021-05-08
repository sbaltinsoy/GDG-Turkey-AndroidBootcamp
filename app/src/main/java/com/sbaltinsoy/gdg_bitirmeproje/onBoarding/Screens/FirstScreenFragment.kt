package com.sbaltinsoy.gdg_bitirmeproje.onBoarding.Screens

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.sbaltinsoy.gdg_bitirmeproje.R
import com.sbaltinsoy.gdg_bitirmeproje.SplashScreen.SplashScreenFragmentDirections
import com.sbaltinsoy.gdg_bitirmeproje.databinding.FirstScreenFragmentBinding
import kotlinx.android.synthetic.main.first_screen_fragment.view.*

class FirstScreenFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.first_screen_fragment, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        view.next_buton.setOnClickListener {
            viewPager?.currentItem = 1
        }

        return view
    }


}