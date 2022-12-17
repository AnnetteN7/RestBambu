package com.example.restbambu.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.procyectomovil.data.CategoryDao
import com.example.procyectomovil.model.Category
import com.example.procyectomovil.repository.CategoryRepository

class HomeViewModel  (application: Application): AndroidViewModel(application) {

    val getCategorias: MutableLiveData<List<Category>>
    private val repository: CategoryRepository = CategoryRepository(CategoryDao())

    init {
        getCategorias = repository.getCategorias
    }

    fun saveCategory(category: Category) {
        repository.saveCategory(category)
    }

    fun deleteCategory(category: Category) {
        repository.deleteCategory(category)
    }
}