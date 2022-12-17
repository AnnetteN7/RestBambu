package com.example.restbambu.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.procyectomovil.adapter.CategoryAdapter
import com.project.restbambu.R
import com.project.restbambu.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private lateinit var homeViewModel: HomeViewModel
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

     //   binding.imageView8.setOnClickListener {
       //     findNavController().navigate(R.id.action_nav_home_to_addCategoriaFragment)
     //   }

        //Cargar Datos
        val categoriaAdapter = CategoryAdapter()
        val reciclador = binding.recicladorMenu
        reciclador.adapter = categoriaAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        homeViewModel.getCategorias.observe(viewLifecycleOwner){
                categorias -> categoriaAdapter.setCategorias(categorias)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}