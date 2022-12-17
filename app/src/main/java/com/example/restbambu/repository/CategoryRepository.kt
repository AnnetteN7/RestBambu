package com.example.procyectomovil.repository

import androidx.lifecycle.MutableLiveData
import com.example.procyectomovil.data.CategoryDao
import com.example.procyectomovil.model.Category

class CategoryRepository (private val categoryDao: CategoryDao){

    fun saveCategory(category: Category) {
        categoryDao.saveCategory(category)
    }

    fun deleteCategory(category : Category) {
        categoryDao.deleteCategory(category)
    }

    val getCategorias : MutableLiveData<List<Category>> = categoryDao.getCategorias()
}
