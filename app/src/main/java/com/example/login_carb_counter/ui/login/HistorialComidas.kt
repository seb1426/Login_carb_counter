package com.example.login_carb_counter.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.login_carb_counter.R

class HistorialComidas: AppCompatActivity() {

    private lateinit var initNombreUsuario:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.formulario_historial_comidas)


        initNombreUsuario = intent.getStringExtra("nombreUsuario").toString()


    }

    public fun navToContarCarbAction(view: View)
    {
        val intent: Intent = Intent(this, ConteoCarbohidratos::class.java)
        intent.putExtra("nombreUsuario", initNombreUsuario)
        startActivity(intent)

    }
}