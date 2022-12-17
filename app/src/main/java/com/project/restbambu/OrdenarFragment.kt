package com.project.restbambu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.procyectomovil.adapter.CategoryAdapter
import com.example.procyectomovil.model.Category
import com.example.restbambu.OrdenarViewModel
import com.example.restbambu.adapter.CartAdapter
import com.example.restbambu.ui.home.HomeViewModel
import com.project.restbambu.databinding.FragmentHomeBinding
import com.project.restbambu.databinding.FragmentOrdenarBinding

class OrdenarFragment : Fragment() {
    private var _binding: FragmentOrdenarBinding? = null

    private lateinit var ordenarViewModel: OrdenarViewModel
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ordenarViewModel = ViewModelProvider(this).get(OrdenarViewModel::class.java)
        _binding = FragmentOrdenarBinding.inflate(inflater, container, false)

        binding.btnInicio.setOnClickListener {
            findNavController().navigate(R.id.action_ordenarFragment_to_pagarFragment)
        }

        //Cargar Datos
        val cartAdapter = CartAdapter()
        val reciclador = binding.recicladorcart
        reciclador.adapter = cartAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)

        ordenarViewModel.getCarts.observe(viewLifecycleOwner){
                carts -> cartAdapter.setCarts(carts)
        }

        return binding.root
    }


}