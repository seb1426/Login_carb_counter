package com.example.login_carb_counter.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.login_carb_counter.R

class ConsultaGlucometria: AppCompatActivity() {

    private lateinit var initNombreUsuario:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.formulario_consulta_glucometrias)

        var botonVolver = findViewById<Button>(R.id.button)
        var botonRegistrarGlucosa = findViewById<Button>(R.id.button3)

        var textSeleccionarFecha = findViewById<TextView>(R.id.textView6)
        var textSeleccionarGlucosa = findViewById<TextView>(R.id.textView7)

        initNombreUsuario = intent.getStringExtra("nombreUsuario").toString()
    }

    public fun navToMenuAction(view: View)
    {
        val intent: Intent = Intent(this, MenuPrincipal::class.java)
        intent.putExtra("nombreUsuario", initNombreUsuario)
        startActivity(intent)

    }
}