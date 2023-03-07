package com.example.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.navigation.databinding.FragmentWonBinding

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentWon.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentWon : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentWonBinding>(inflater, R.layout.fragment_won, container, false)
        binding.btnPlayAgain.setOnClickListener {
            it.findNavController().navigate(R.id.action_fragmentWon_to_fragmentQuestions)
        }
        return binding.root
    }
}