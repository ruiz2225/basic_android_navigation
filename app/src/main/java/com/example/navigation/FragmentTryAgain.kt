package com.example.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.navigation.databinding.FragmentTryAgainBinding

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentTryAgain.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentTryAgain : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentTryAgainBinding>(inflater, R.layout.fragment_try_again, container, false)
        binding.btnTryAgain.setOnClickListener {
            it.findNavController().navigate(R.id.action_fragmentTryAgain_to_fragmentQuestions)
        }
        return binding.root
    }
}