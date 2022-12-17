package com.example.restbambu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.procyectomovil.model.Category
import com.example.restbambu.model.Cart
import com.example.restbambu.ui.home.HomeFragmentDirections
import com.project.restbambu.OrdenarFragmentDirections
import com.project.restbambu.databinding.FragmentOrdenBinding
import com.project.restbambu.databinding.FragmentProductBinding

class CartAdapter: RecyclerView.Adapter<CartAdapter.CartViewHolder>(){
    private var listaCart = emptyList<Cart>()

    inner class CartViewHolder(private val itemBinding: FragmentOrdenBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun dibujar(cart: Cart){
            itemBinding.tvNombreProducto.text = "Ramen"
            //itemBinding.tvPrecio.text = cart.precio.toString()
            itemBinding.txtCantidad.text = cart.cantidad.toString()
            if(cart.ruta?.isNotEmpty() ==true){
                Glide.with(itemBinding.root.context)
                    .load(cart.ruta)
                    .into(itemBinding.imageView10)
            }
            //Evento edit
            //itemBinding.vistaMiOrden.setOnClickListener{
             //   val accion = OrdenarFragmentDirections.actionOrdenarFragmentToPagarFragment()
             //   itemView.findNavController().navigate(accion)

           // }
        }
    }
    fun setCarts(carts: List<Cart>) {
        listaCart = carts
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemBinding = FragmentOrdenBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CartViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cart = listaCart[position]
        holder.dibujar(cart)
    }

    override fun getItemCount(): Int {
        return listaCart.size
    }

}