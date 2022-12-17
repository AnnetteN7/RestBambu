package com.project.restbambu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.restbambu.ui.home.HomeViewModel
import com.project.restbambu.databinding.FragmentDetalleBinding


class DetalleFragment : Fragment() {
    private val args by navArgs<DetalleFragmentArgs>()

    private var _binding: FragmentDetalleBinding? = null
    private  val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentDetalleBinding.inflate(inflater,container,false)
        binding.etNombre.setText(args.categoria.categoryName)
        binding.etNombre22.setText(args.categoria.categoryName)
        binding.etDescripcion.setText(args.categoria.descripcion)
        binding.etPrecio.setText(args.categoria.precio.toString())
        binding.productoImage.setImageResource(args.categoria.ruta)
        binding.btnAgregar.setOnClickListener {
            findNavController().navigate(R.id.action_detalleFragment_to_ordenarFragment)
        }
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_detalle, container, false)
        return binding.root
    }

}

private fun ImageView.setImageResource(ruta: String?) {

}
