package com.example.restbambu.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.procyectomovil.model.Category
import com.example.restbambu.model.Cart
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase

class CartDao {
    private var codigoUsuario : String
    private var firestore: FirebaseFirestore

    init {
        val usuario = Firebase.auth.currentUser?.email
        codigoUsuario = "$usuario"
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    //CRUD Create Read Update Delete
    fun saveCart(cart: Cart){
        val document: DocumentReference
        if(cart.id!!.isEmpty()){
            //Agregar
            document = firestore.
            collection("proyectoFinal").
            document(codigoUsuario).
            collection("Cart").
            document()
            cart.id = document.id
        }
        else{
            //Modificar
            document = firestore.
            collection("proyectoFinal").
            document(codigoUsuario).
            collection("Cart").
            document(cart.id.toString())
        }
        document.set(cart)
            .addOnCompleteListener {
                Log.d("SaveCategory","Guardado con exito")
            }
            .addOnCanceledListener {
                Log.e("SaveCategory","Error al guardar")
            }
    }

    fun deleteCart(cart: Cart){
        if(cart.id!!.isNotEmpty()){
            firestore.
            collection("proyectoFinal").
            document(codigoUsuario).
            collection("misCart").
            document(cart.id.toString()).
            delete()
                .addOnCompleteListener {
                    Log.d("deleteCart","Eliminado con exito")
                }
                .addOnCanceledListener {
                    Log.e("deleteCart","Error al eliminar")
                }
        }

    }

    fun getCart() : MutableLiveData<List<Cart>> {
        val listaCart = MutableLiveData<List<Cart>>()
        firestore.
        collection("proyectoFinal").
        document("ema@gmail.com").
        collection("misCategorias").
        addSnapshotListener{snapshot, e ->
            if(e != null){
                return@addSnapshotListener
            }
            if(snapshot != null){
                val lista = ArrayList<Cart>()
                val cart = snapshot.documents
                cart.forEach{
                    val cart = it.toObject(Cart ::class.java)
                    if(cart != null){
                        lista.add(cart)
                    }
                }
                listaCart.value = lista
            }
        }
        return listaCart
    }
}