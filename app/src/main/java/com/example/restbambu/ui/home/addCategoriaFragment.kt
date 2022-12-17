package com.example.restbambu.ui.home

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.procyectomovil.model.Category
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.project.restbambu.R
import com.project.restbambu.databinding.FragmentAddCategoriaBinding

// TODO: Rename parameter arguments, choose names that match

class addCategoriaFragment : Fragment() {
    private var _binding: FragmentAddCategoriaBinding? = null
    private val binding get() = _binding!!
    private lateinit var categoriaViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        categoriaViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentAddCategoriaBinding.inflate(inflater, container, false)

        binding.btAddLugar.setOnClickListener {
            addLugar()
            }


        return binding.root
    }



    //Efectivamente hace el registro del lugar en la base de datos
    private fun addLugar() {
        val categoria=binding.etCategoryname.text.toString()
        val precio=binding.etPrecio.text.toString().toInt()
        val descripcion=binding.etDescripcion.text.toString()
        val ruta=binding.etRuta.text.toString()


        if (categoria.isNotEmpty()) {   //Al menos tenemos un nombre
            val category = Category("",categoria,precio,descripcion,ruta)
            categoriaViewModel.saveCategory(category)
            Toast.makeText(requireContext(),getString(R.string.msg_lugar_added),
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addCategoriaFragment_to_nav_home)
        } else {  //No hay info del lugar...
            Toast.makeText(requireContext(),getString(R.string.msg_data),
                Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}