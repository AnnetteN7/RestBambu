package com.example.restbambu.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cart (
    var id:String?,
    var nombre:String?= null,
    var ruta:String?= null,
    var precio:Int?= 0,
    var cantidad:Int? = 0,
    var total:Int = 0
    ): Parcelable {
    constructor(): this("", "","",0,0,0)
}