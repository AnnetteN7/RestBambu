package com.example.restbambu.repository

import androidx.lifecycle.MutableLiveData
import com.example.procyectomovil.data.CategoryDao
import com.example.procyectomovil.model.Category
import com.example.restbambu.data.CartDao
import com.example.restbambu.model.Cart

class CartRepository (private val cartDao: CartDao){

    fun saveCart(cart: Cart) {
        cartDao.saveCart(cart)
    }

    fun deleteCart(cart: Cart) {
        cartDao.deleteCart(cart)
    }

    val getCart : MutableLiveData<List<Cart>> = cartDao.getCart()
}
