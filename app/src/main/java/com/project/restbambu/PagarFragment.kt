package com.project.restbambu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.restbambu.OrdenarViewModel
import com.project.restbambu.databinding.FragmentOrdenarBinding
import com.project.restbambu.databinding.FragmentPagarBinding


class PagarFragment : Fragment() {
    private var _binding: FragmentPagarBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPagarBinding.inflate(inflater, container, false)
        binding.btnSinpe.setOnClickListener {
            findNavController().navigate(R.id.action_pagarFragment_to_pagarSinpeFragment)
        }
        binding.btnPagarRes.setOnClickListener {
            findNavController().navigate(R.id.action_pagarFragment_to_pagarRestFragment)
        }

        return binding.root
    }

}