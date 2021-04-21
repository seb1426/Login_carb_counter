package com.example.login_carb_counter.ui.modelo

class claseAuxiliarConteo {

    private var arregloAlimento: ArrayList<String> = ArrayList()
    private var arregloPorcion: ArrayList<String> = ArrayList()

    fun asignarAlimentos(arregloalimento:ArrayList<String>, arregloporcion:ArrayList<String>)
    {
        var posicionFinal = arregloalimento.size
        println(posicionFinal)
        this.arregloAlimento.add(arregloalimento[posicionFinal-1])
        this.arregloPorcion.add(arregloporcion[posicionFinal-1])

        println("ARREGLO TEMPORAL ALIMENTO" + this.arregloAlimento)
        println("ARREGLO TEMPORAL PORCION" + this.arregloPorcion)
    }

    fun getArregloAlimento():ArrayList<String>
    {
        return arregloAlimento
    }

    fun getArregloPorcion():ArrayList<String>
    {
        return arregloPorcion
    }

}