package com.sbaltinsoy.gdg_bitirmeproje.onBoarding.Screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.sbaltinsoy.gdg_bitirmeproje.R
import com.sbaltinsoy.gdg_bitirmeproje.databinding.SecondScreenFragmentBinding
import kotlinx.android.synthetic.main.second_screen_fragment.view.*


class SecondScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.second_screen_fragment, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        view.next_buton2.setOnClickListener {
            viewPager?.currentItem = 2
        }

        return view
    }

}