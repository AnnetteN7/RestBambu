package com.example.restbambu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.procyectomovil.data.CategoryDao
import com.example.procyectomovil.model.Category
import com.example.procyectomovil.repository.CategoryRepository
import com.example.restbambu.data.CartDao
import com.example.restbambu.model.Cart
import com.example.restbambu.repository.CartRepository

class OrdenarViewModel (application: Application): AndroidViewModel(application) {
    val getCarts: MutableLiveData<List<Cart>>
    private val repository: CartRepository = CartRepository(CartDao())

    init {
        getCarts = repository.getCart
    }

    fun saveCart(cart: Cart) {
        repository.saveCart(cart)
    }

    fun deleteCart(cart: Cart) {
        repository.deleteCart(cart)
    }
}