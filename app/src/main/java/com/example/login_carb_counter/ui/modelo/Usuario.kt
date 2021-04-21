package com.example.login_carb_counter.ui.modelo

import java.util.*

class Usuario(var nombre:String , val email: String, var tipoDiabetes: Int, var peso: Double, var altura:Double, var grupoSanguineo:String, var cedula:Int) {

    var fechaNacimiento = Calendar.getInstance()

    constructor(nombre:String,  email: String,  tipoDiabetes: Int,  peso: Double,  altura:Double,  grupoSanguineo:String,  cedula:Int, fechaNacimiento:Date): this(nombre,  email,  tipoDiabetes,  peso,  altura,  grupoSanguineo,  cedula)
    {


    }

    fun getDateAsString(): String{
        var year = fechaNacimiento.get(Calendar.YEAR).toString()
        var month = fechaNacimiento.get(Calendar.MONTH).toString()
        var day = fechaNacimiento.get(Calendar.DAY_OF_MONTH).toString()
        return "$year/$month/$day"
    }


}