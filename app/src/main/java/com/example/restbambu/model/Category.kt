package com.example.procyectomovil.model


import android.graphics.Picture
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Category(
    var id : String,
    val categoryName: String? =null,
    val precio: Int?,
    val descripcion: String?,

    val ruta:String? = null

) : Parcelable {
    constructor(): this("", "",0,"","")
}