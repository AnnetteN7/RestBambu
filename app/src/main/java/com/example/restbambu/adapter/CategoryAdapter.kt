package com.example.procyectomovil.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewpager2.adapter.FragmentViewHolder
import com.bumptech.glide.Glide
import com.project.restbambu.databinding.FragmentFilaCategoriaBinding


import com.example.procyectomovil.model.Category
import com.example.restbambu.ui.home.HomeFragmentDirections
import com.project.restbambu.databinding.FragmentProductBinding
import java.net.URL


class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){
    private var listaCategorias = emptyList<Category>()

    inner class CategoryViewHolder(private val itemBinding:FragmentProductBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun dibujar(category:Category){
            itemBinding.tvNomProducto.text = category.categoryName
            itemBinding.tvPrecioProducto.text = category.precio.toString()
            if(category.ruta?.isNotEmpty() ==true){
                Glide.with(itemBinding.root.context)
                    .load(category.ruta)
                    .into(itemBinding.imagePro)
            }
            //Evento edit
         itemBinding.vistaProducto.setOnClickListener{
                val accion = HomeFragmentDirections
                    .actionNavHomeToDetalleFragment(category)
               itemView.findNavController().navigate(accion)

            }
        }
    }
        fun setCategorias(categorias: List<Category>) {
            listaCategorias = categorias
            notifyDataSetChanged()
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
            val itemBinding = FragmentProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return CategoryViewHolder(itemBinding)
        }

        override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
            val category = listaCategorias[position]
            holder.dibujar(category)
        }

        override fun getItemCount(): Int {
            return listaCategorias.size
        }

        }




