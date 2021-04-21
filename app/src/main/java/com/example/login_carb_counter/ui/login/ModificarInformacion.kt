package com.example.login_carb_counter.ui.login

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.login_carb_counter.R
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class ModificarInformacion: AppCompatActivity(){

    private lateinit var nombreModificar: EditText
    private lateinit var emailModificar: EditText
    private lateinit var fechaNacimientoModificar: EditText
    private lateinit  var tipoDiabetesModificar: EditText
    private lateinit var pesoModificar: EditText
    private lateinit var alturaModificar: EditText
    private lateinit var grupoSanguineoModificar: EditText
    private lateinit  var cedulaModificar: EditText
    private lateinit var botonGuardar:Button
    private lateinit var initNombreUsuario:String
    private lateinit var arregloDatos:ArrayList<String>
    private lateinit var idUsuario:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.formulario_cambio_informacion)


         nombreModificar= findViewById<EditText>(R.id.editar_nombre)
         emailModificar= findViewById<EditText>(R.id.editar_email)
         fechaNacimientoModificar= findViewById<EditText>(R.id.editar_fecha_nacimiento)
         tipoDiabetesModificar= findViewById<EditText>(R.id.editar_tipo_diabetes)
         pesoModificar= findViewById<EditText>(R.id.editar_peso)
         alturaModificar= findViewById<EditText>(R.id.editar_altura)
         grupoSanguineoModificar= findViewById<EditText>(R.id.editar_grrupo_sanguineo)
         cedulaModificar= findViewById<EditText>(R.id.editar_cedula)

        botonGuardar= findViewById<Button>(R.id.boton_actualizar)

        initNombreUsuario = intent.getStringExtra("nombreUsuario").toString()
        val x = ConsultaParcialDeDatos(this).execute(initNombreUsuario)
        arregloDatos = x.get().split("] => ", "    [") as ArrayList<String>

        idUsuario = arregloDatos[2]
        val nombreMostrar = arregloDatos[4]
        val emailMostrar = arregloDatos[6]
        val fechaMostrar = arregloDatos[8]
        val diabetesMostrar =  arregloDatos[10]
        val cedulaMostrar = arregloDatos[12]
        val pesoMostrar = arregloDatos[16]
        val alturaMostrar = arregloDatos[18]
        val grupoSanguineoMostrar =  arregloDatos[20]


        nombreModificar.setText(nombreMostrar.toString())
        emailModificar.setText(emailMostrar.toString())
        fechaNacimientoModificar.setText(fechaMostrar.toString())
        tipoDiabetesModificar.setText(diabetesMostrar.toString())
        pesoModificar.setText(pesoMostrar.toString())
        alturaModificar.setText(alturaMostrar.toString())
        grupoSanguineoModificar.setText(grupoSanguineoMostrar.toString())
        cedulaModificar.setText(cedulaMostrar.toString())





    }





    public fun navToMenuAction(view: View)
    {


        val intent: Intent = Intent(this, MenuPrincipal::class.java)
        intent.putExtra("nombreUsuario", initNombreUsuario)
        startActivity(intent)

    }


 fun guardarCambiosActualizacion(view: View)
{


    var correo = emailModificar.text.toString()
    var peso = pesoModificar.text.toString()
    var altura = alturaModificar.text.toString()

    val validacion= actualizacionFinalDeDatos(this).execute(idUsuario,correo,  peso, altura)

    if(validacion.get() == "La actualizacion fue exitosa!")
    {
        navToMenuAction(view)
    }
}




}



class ConsultaParcialDeDatos(private val context: Context): AsyncTask<String, Void, String>() {

    protected override fun doInBackground(vararg params: String): String {

        var urlPost = "http://carbcounter.freeoda.com/consultarDatos.php"

        println("TOMA LA URL 1")
        lateinit var respuesta: String
        val nombre: String = params[0]
        val data: String = URLEncoder.encode("nombre_completo", "UTF-8") + "=" + URLEncoder.encode(nombre, "UTF-8")
       // val data: String = URLEncoder.encode("nombre_completo", "UTF-8") + "=" + URLEncoder.encode(nombre, "UTF-8") + "&" + URLEncoder.encode("correo", "UTF-8") + "=" + URLEncoder.encode(correo, "UTF-8") + "&" + URLEncoder.encode("fecha_nacimiento", "UTF-8") + "=" + URLEncoder.encode(fechaNacimiento, "UTF-8") + "&" + URLEncoder.encode("tipo_diabetes", "UTF-8") + "=" + URLEncoder.encode(tipoDiabetes, "UTF-8") + "&" + URLEncoder.encode("cedula", "UTF-8") + "=" + URLEncoder.encode(cedula, "UTF-8") + "&" + URLEncoder.encode("peso", "UTF-8") + "=" + URLEncoder.encode(peso, "UTF-8") + "&" + URLEncoder.encode("altura", "UTF-8") + "=" + URLEncoder.encode(altura, "UTF-8") + "&" + URLEncoder.encode("grupo_sanguineo", "UTF-8") + "=" + URLEncoder.encode(grupoSanguineo, "UTF-8")

        val mURL = URL(urlPost)
        println("TOMA LA URL 2")
        with(mURL.openConnection() as HttpURLConnection) {
            //Define metodo
            requestMethod = "POST"
            println("ABRE LA CONEXION")
            val wr = OutputStreamWriter(getOutputStream());
            wr.write(data);
            wr.flush();

            println(requestMethod + "URL : $url")
            println(requestMethod + "Response Code : $responseCode")

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                respuesta = response.toString()
                it.close()
                //imprime respuesta.
                println("POST Response : $response")

            }
        }

        return respuesta

    }

}


class actualizacionFinalDeDatos(private val context:Context): AsyncTask<String, Void, String>() {

    protected override fun doInBackground(vararg params: String): String {


        var urlPost = "http://carbcounter.freeoda.com/actualizarDatos.php"

        val idUsuario: String = params[0]
        val correo: String = params[1]
        val peso: String = params[2]
        val altura: String = params[3]


        lateinit var respuesta: String

        println("EL CORREO ES: " + correo)
        println("EL PESO ES: " + peso)
        println("LA ALTURA ES: " + altura)

        val data: String = URLEncoder.encode("idUsuario", "UTF-8") + "=" + URLEncoder.encode(
                idUsuario,
                "UTF-8"
        )  + "&" +  URLEncoder.encode("correo", "UTF-8") + "=" + URLEncoder.encode(
            correo,
            "UTF-8"
        )  + "&" + URLEncoder.encode("peso", "UTF-8") + "=" + URLEncoder.encode(peso, "UTF-8") + "&" + URLEncoder.encode(
            "altura",
            "UTF-8"
        ) + "=" + URLEncoder.encode(altura, "UTF-8")

        val mURL = URL(urlPost)
        with(mURL.openConnection() as HttpURLConnection) {
            //Define metodo
            requestMethod = "POST"

            val wr = OutputStreamWriter(getOutputStream());
            wr.write(data);
            wr.flush();

            println(requestMethod + "URL : $url")
            println(requestMethod + "Response Code : $responseCode")

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                respuesta = response.toString()
                it.close()
                //imprime respuesta.
                println("POST Response : $response")

            }
        }

        return respuesta

    }

    override fun onPostExecute(resultado: String) {

        Toast.makeText(context, resultado, Toast.LENGTH_LONG).show()
    }

}