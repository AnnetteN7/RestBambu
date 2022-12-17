package com.example.procyectomovil.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.procyectomovil.model.Category
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase

class CategoryDao {
    private var codigoUsuario : String
    private var firestore: FirebaseFirestore

    init {
        val usuario = Firebase.auth.currentUser?.email
        codigoUsuario = "$usuario"
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    //CRUD Create Read Update Delete
    fun saveCategory(category: Category){
        val document: DocumentReference
        if(category.id.isEmpty()){
            //Agregar
            document = firestore.
            collection("proyectoFinal").
            document(codigoUsuario).
            collection("misCategorias").
            document()
            category.id = document.id
        }
        else{
            //Modificar
            document = firestore.
            collection("proyectoFinal").
            document(codigoUsuario).
            collection("misCategorias").
            document(category.id)
        }
        document.set(category)
            .addOnCompleteListener {
                Log.d("SaveCategory","Guardado con exito")
            }
            .addOnCanceledListener {
                Log.e("SaveCategory","Error al guardar")
            }
    }

    fun deleteCategory(category: Category){
        if(category.id.isNotEmpty()){
            firestore.
            collection("proyectoFinal").
            document(codigoUsuario).
            collection("misCategorias").
            document(category.id).
            delete()
                .addOnCompleteListener {
                    Log.d("deleteLugar","Eliminado con exito")
                }
                .addOnCanceledListener {
                    Log.e("deleteLugar","Error al eliminar")
                }
        }

    }

    fun getCategorias() : MutableLiveData<List<Category>> {
        val listaCategorias = MutableLiveData<List<Category>>()
        firestore.
        collection("proyectoFinal").
        document("ema@gmail.com").
        collection("misCategorias").
        addSnapshotListener{snapshot, e ->
            if(e != null){
                return@addSnapshotListener
            }
            if(snapshot != null){
                val lista = ArrayList<Category>()
                val categorias = snapshot.documents
                categorias.forEach{
                    val category = it.toObject(Category ::class.java)
                    if(category != null){
                        lista.add(category)
                    }
                }
                listaCategorias.value = lista
            }
        }
        return listaCategorias
    }
}

