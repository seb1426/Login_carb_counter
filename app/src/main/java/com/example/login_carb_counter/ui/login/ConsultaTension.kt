package com.example.login_carb_counter.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.login_carb_counter.R

class ConsultaTension: AppCompatActivity() {

    private lateinit var initNombreUsuario:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.formulario_consulta_tensiones)

        var botonVolver = findViewById<Button>(R.id.button4)
        var botonRegistrarTension = findViewById<Button>(R.id.button8)

        var textSeleccionarFecha = findViewById<TextView>(R.id.textView8)
        var textSeleccinarTension = findViewById<TextView>(R.id.textView9)

        var checboxSistolica = findViewById<CheckBox>(R.id.checkBox)
        var checboxDiastolica = findViewById<CheckBox>(R.id.checkBox2)

        initNombreUsuario = intent.getStringExtra("nombreUsuario").toString()

    }

    public fun navToMenuAction(view: View)
    {
        val intent: Intent = Intent(this, MenuPrincipal::class.java)
        intent.putExtra("nombreUsuario", initNombreUsuario)
        startActivity(intent)

    }
}