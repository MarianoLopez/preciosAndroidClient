package com.z.mariano.precios.Model

import java.util.*

/**
 * Created by Mariano on 20-ene-18.
 */
class DataClasses {
    data class Product(val id:String?="",val name:String,val price:Double,val date: String?=null)
}