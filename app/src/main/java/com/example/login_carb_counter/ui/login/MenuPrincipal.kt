package com.example.login_carb_counter.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.login_carb_counter.R


class MenuPrincipal: AppCompatActivity()  {

    private lateinit var initNombreUsuario:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        var imagenContarCarb = findViewById<ImageView>(R.id.contarCarbView)
        var imageninfoAlimento = findViewById<ImageView>(R.id.infoAlimentoView)
        var imagenGlucometrias = findViewById<ImageView>(R.id.glucometriasView)
        var imagenTension = findViewById<ImageView>(R.id.tensionView)
        var imagenSalir = findViewById<ImageView>(R.id.exitView)
        var imagenPerfil = findViewById<ImageView>(R.id.fotoPerfilView)

        var contarButton = findViewById<Button>(R.id.botonCarbohidratos)
        var infoAlimentoButton = findViewById<Button>(R.id.botonAlimentos)
        var glucometriaButton = findViewById<Button>(R.id.botonGlucometrias)
        var tensionButton = findViewById<Button>(R.id.botonTension)
        var cerrarSesionButton = findViewById<Button>(R.id.botonSalir)
        var quejasText = findViewById<TextView>(R.id.quejasView)
        var nombreUsuario = findViewById<TextView>(R.id.nombreUsuario)

        initNombreUsuario = intent.getStringExtra("nombreUsuario").toString()
        nombreUsuario.setText(initNombreUsuario)


    }

    public fun cerrarSesion(view: View)
    {
        val intent: Intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    public fun conteoCarbohidratos(view: View)
    {
        val intent: Intent = Intent(this, ConteoCarbohidratos::class.java)
        intent.putExtra("nombreUsuario", initNombreUsuario)
        startActivity(intent)
    }

    public fun consultarTension(view: View)
    {
        val intent: Intent = Intent(this, ConsultaTension::class.java)
        intent.putExtra("nombreUsuario", initNombreUsuario)
        startActivity(intent)
    }

    public fun consultarGlucometria(view: View)
    {
        val intent: Intent = Intent(this, ConsultaGlucometria::class.java)
        intent.putExtra("nombreUsuario", initNombreUsuario)
        startActivity(intent)
    }

    public fun infoAlimentos(view: View)
    {
        val intent: Intent = Intent(this, InfoAlimentos::class.java)
        intent.putExtra("nombreUsuario", initNombreUsuario)
        startActivity(intent)
    }

    public fun navToEditar(view: View)
    {
        val intent: Intent = Intent(this, ModificarInformacion::class.java)
        intent.putExtra("nombreUsuario", initNombreUsuario)
        startActivity(intent)
    }
}