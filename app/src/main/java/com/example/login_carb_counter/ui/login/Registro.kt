package com.example.login_carb_counter.ui.login

import android.R.attr.bitmap
import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.icu.util.Calendar
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.login_carb_counter.R
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.util.*
import kotlin.properties.Delegates


class Registro : AppCompatActivity(){

    private lateinit  var nombreRegistro: EditText
    private lateinit  var emailRegistro: EditText
    private lateinit  var fechaNacimientoRegistro: EditText
    private lateinit  var tipoDiabetesRegistro: EditText
    private lateinit  var pesoRegistro: EditText
    private lateinit  var alturaRegistro: EditText
    private lateinit  var grupoSanguineoRegistro:EditText
    private lateinit  var cedulaRegistro: EditText
    private lateinit var  imagenUsuario: ImageButton
    private lateinit var bitmap:Bitmap
    private lateinit var spinnerDiabetesRegistro: Spinner
    private lateinit var spinnerGrupoSanguineoRegistro: Spinner
    private lateinit var grupoSanguineo: String
    private lateinit var tipoDiabetes: String
    private lateinit var view: DatePicker
    private var  year: Int = 0
    private var month: Int = 0
    private var day: Int = 0
    var  calendario: Calendar = Calendar.getInstance()
    var dateYear = calendario.get(Calendar.YEAR)
    var dateMonth = calendario.get(Calendar.MONTH)
    var dateDay = calendario.get(Calendar.DAY_OF_MONTH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.formulario_registro)





        var nombreRegistro= findViewById<EditText>(R.id.editTextTextPersonName)
        var emailRegistro= findViewById<EditText>(R.id.editTextTextEmailAddress)
        var fechaNacimientoRegistro= findViewById<EditText>(R.id.editTextDate)
        var spinnerDiabetesRegistro= findViewById<Spinner>(R.id.tipoDiabetesRegistroSpinner)
        var pesoRegistro= findViewById<EditText>(R.id.editTextNumberDecimal)
        var alturaRegistro= findViewById<EditText>(R.id.editTextNumber)
        var spinnerGrupoSanguineoRegistro= findViewById<Spinner>(R.id.grupoSanguineoRegistroSpinner)
        var cedulaRegistro= findViewById<EditText>(R.id.editTextNumber2)
        var imagenUsuario= findViewById<ImageButton>(R.id.imageButton)

        var botonRegistrar = findViewById<Button>(R.id.button5)




        fechaNacimientoRegistro.setOnClickListener {
            showDialog(1)
        }



        var adapterTipoDiabetes:ArrayAdapter<CharSequence>  = ArrayAdapter.createFromResource(this,R.array.combo_tipo_diabetes, android.R.layout.simple_spinner_item)
        spinnerDiabetesRegistro.adapter = adapterTipoDiabetes

        var adapterGrupoSanguineo:ArrayAdapter<CharSequence>  = ArrayAdapter.createFromResource(this,R.array.combo_grupo_sanguineo, android.R.layout.simple_spinner_item)
        spinnerGrupoSanguineoRegistro.adapter = adapterGrupoSanguineo


        tipoDiabetes = " "
        grupoSanguineo = " "


        spinnerDiabetesRegistro.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                tipoDiabetes = parent.getItemAtPosition(position).toString()
                println("EL TIPO DE DIABETES ES: " + tipoDiabetes)
            }

        }

        spinnerGrupoSanguineoRegistro.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                grupoSanguineo = parent.getItemAtPosition(position).toString()
                println("EL GRUPO SANGUINEO ES: " + grupoSanguineo)

            }

        }

        if(tipoDiabetes=="Seleccione un tipo de diabetes" || grupoSanguineo=="Seleccione su grupo sanguíneo")
        {
            tipoDiabetes= " "
            grupoSanguineo= " "
        }

    }



    var  mDateSetListener:DatePickerDialog.OnDateSetListener = DatePickerDialog.OnDateSetListener() { view,  year,  month,  day ->

       // var year = dateYear
       // var month = dateMonth
       // var day = dateDay
        println(year.toString() + "-" + month.toString() + "-" + day.toString())
        fechaNacimientoRegistro= findViewById<EditText>(R.id.editTextDate)
        fechaNacimientoRegistro.setText(year.toString() + "/" + month.toString() + "/" + day.toString())


    }


    override fun onCreateDialog(id:Int):Dialog
    {
         when (id) {
            1 -> {
                println(dateYear.toString() + dateMonth.toString() + dateDay.toString())
                return DatePickerDialog(this, mDateSetListener, dateYear, dateMonth, dateDay)
            }

        }
        return DatePickerDialog(this, mDateSetListener, dateYear, dateMonth, dateDay)
    }



   fun registrarUsuario(view: View)
  {
      var nombreRegistro= findViewById<EditText>(R.id.editTextTextPersonName)
      var emailRegistro= findViewById<EditText>(R.id.editTextTextEmailAddress)
      var fechaNacimientoRegistro= findViewById<EditText>(R.id.editTextDate)
      var spinnerDiabetesRegistro= findViewById<Spinner>(R.id.tipoDiabetesRegistroSpinner)
      var pesoRegistro= findViewById<EditText>(R.id.editTextNumberDecimal)
      var alturaRegistro= findViewById<EditText>(R.id.editTextNumber)
      var spinnerGrupoSanguineoRegistro= findViewById<Spinner>(R.id.grupoSanguineoRegistroSpinner)
      var cedulaRegistro= findViewById<EditText>(R.id.editTextNumber2)
      var imagenUsuario= findViewById<ImageButton>(R.id.imageButton)

      var botonRegistrar = findViewById<Button>(R.id.button5)

      var adapterTipoDiabetes:ArrayAdapter<CharSequence>  = ArrayAdapter.createFromResource(this,R.array.combo_tipo_diabetes, android.R.layout.simple_spinner_item)
      spinnerDiabetesRegistro.adapter = adapterTipoDiabetes

      var adapterGrupoSanguineo:ArrayAdapter<CharSequence>  = ArrayAdapter.createFromResource(this,R.array.combo_grupo_sanguineo, android.R.layout.simple_spinner_item)
      spinnerGrupoSanguineoRegistro.adapter = adapterGrupoSanguineo

        //this.tipoDiabetes

      var nombre = nombreRegistro.text.toString()
      var correo = emailRegistro.text.toString()
      var fechaNacimiento = fechaNacimientoRegistro.text.toString()
     // var tipoDiabetes: String = (spinnerDiabetesRegistro.findViewById<TextView>(R.id.tipoDiabetesRegistroSpinner)).text.toString()
     // var tipoDiabetes = spinnerDiabetesRegistro.get
      var peso = pesoRegistro.text.toString()
      var altura = alturaRegistro.text.toString()
      //var grupoSanguineo: String = (spinnerGrupoSanguineoRegistro.findViewById<TextView>(R.id.grupoSanguineoRegistroSpinner)).text.toString()
      //var grupoSanguineo = spinnerGrupoSanguineoRegistro.selectedItem.toString()
      var cedula = cedulaRegistro.text.toString()

     // tipoDiabetes = " "
     // grupoSanguineo = " "

/*
      spinnerDiabetesRegistro.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
          override fun onNothingSelected(parent: AdapterView<*>?) {

          }

          override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
               tipoDiabetes = parent.getItemAtPosition(position).toString()
               println("EL TIPO DE DIABETES ES: " + tipoDiabetes)
          }

      }

      spinnerGrupoSanguineoRegistro.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
          override fun onNothingSelected(parent: AdapterView<*>?) {

          }

          override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
               grupoSanguineo = parent.getItemAtPosition(position).toString()
              println("EL GRUPO SANGUINEO ES: " + grupoSanguineo)


          }

      }

      if(tipoDiabetes=="Seleccione un tipo de diabetes" || grupoSanguineo=="Seleccione su grupo sanguíneo")
      {
          tipoDiabetes= " "
          grupoSanguineo= " "
      }
*/
      DescargarImagen(this).execute(nombre, correo, fechaNacimiento, tipoDiabetes, peso, altura, grupoSanguineo, cedula)
      val intent: Intent = Intent(this, LoginActivity::class.java)
      startActivity(intent)
  }


    public fun navToLoginAction(view: View)
    {
        val intent: Intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    public fun navToMenuAction(view:View)
    {
        val intent: Intent = Intent(this, MenuPrincipal::class.java)
        startActivity(intent)

    }




}




class DescargarImagen(private val context:Context): AsyncTask<String, Void, String>()
{

    protected override fun doInBackground(vararg params: String): String {


        var urlPost = "http://carbcounter.freeoda.com/registroUsuario.php"
        val nombre: String = params[0]
        val correo: String = params[1]
        val fechaNacimiento: String = params[2]
        val tipoDiabetes: String = params[3]
        val peso: String = params[4]
        val altura: String = params[5]
        val grupoSanguineo: String = params[6]
        val cedula: String = params[7]

        lateinit var respuesta: String

        println("EL NOMBRE ES: " + nombre)


        val data: String = URLEncoder.encode("nombre_completo", "UTF-8") + "=" + URLEncoder.encode(nombre, "UTF-8") + "&" + URLEncoder.encode("correo", "UTF-8") + "=" + URLEncoder.encode(correo, "UTF-8") + "&" + URLEncoder.encode("fecha_nacimiento", "UTF-8") + "=" + URLEncoder.encode(fechaNacimiento, "UTF-8") + "&" + URLEncoder.encode("tipo_diabetes", "UTF-8") + "=" + URLEncoder.encode(tipoDiabetes, "UTF-8") + "&" + URLEncoder.encode("cedula", "UTF-8") + "=" + URLEncoder.encode(cedula, "UTF-8") + "&" + URLEncoder.encode("peso", "UTF-8") + "=" + URLEncoder.encode(peso, "UTF-8") + "&" + URLEncoder.encode("altura", "UTF-8") + "=" + URLEncoder.encode(altura, "UTF-8") + "&" + URLEncoder.encode("grupo_sanguineo", "UTF-8") + "=" + URLEncoder.encode(grupoSanguineo, "UTF-8")
        println(data)
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


                /*
                var registrar_url: String = "http://carbcounter.freeoda.com/registroUsuario.php"
                lateinit var resultado: String


                try {
                    val connection = URL(registrar_url).openConnection() as HttpURLConnection
                    connection.requestMethod = "POST"
                    connection.doOutput = true
                    val outputStream = connection.outputStream as OutputStream
                    val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream, StandardCharsets.UTF_8))

                    val nombre: String = params[0]
                    val correo: String = params[1]
                    val fechaNacimiento: String = params[2]
                    val tipoDiabetes: String = params[3]
                    val peso: String = params[4]
                    val altura: String = params[5]
                    val grupoSanguineo: String = params[6]
                    val cedula: String = params[7]

                    println("EL NOMBRE ES: " + nombre)

                    val data: String = URLEncoder.encode("nombre_completo", "UTF-8") + "=" + URLEncoder.encode(nombre, "UTF-8") + "&" + URLEncoder.encode("correo", "UTF-8") + "=" + URLEncoder.encode(correo, "UTF-8") + "&" + URLEncoder.encode("fecha_nacimiento", "UTF-8") + "=" + URLEncoder.encode(fechaNacimiento, "UTF-8") + "&" + URLEncoder.encode("tipo_diabetes", "UTF-8") + "=" + URLEncoder.encode(tipoDiabetes, "UTF-8") + "&" + URLEncoder.encode("cedula", "UTF-8") + "=" + URLEncoder.encode(cedula, "UTF-8") + "&" + URLEncoder.encode("peso", "UTF-8") + "=" + URLEncoder.encode(peso, "UTF-8") + "&" + URLEncoder.encode("altura", "UTF-8") + "=" + URLEncoder.encode(altura, "UTF-8") + "&" + URLEncoder.encode("grupo_sanguineo", "UTF-8") + "=" + URLEncoder.encode(grupoSanguineo, "UTF-8")

                    bufferedWriter.write(data)
                    bufferedWriter.flush()
                    bufferedWriter.close()
                    outputStream.close()

                    val inputStream = connection.inputStream
                    val bufferReader = BufferedReader(InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    val stringBuilder: StringBuilder = StringBuilder()

                    val line: String = bufferReader.readLine()

                        while(line!=null) {
                            if(line == "<br />")
                            {
                            }
                            else {
                                stringBuilder.append(line)
                            }
                        }

                     resultado = stringBuilder.toString()

                    bufferReader.close()
                    inputStream.close()
                    connection.disconnect()


                } catch (e: MalformedURLException) {
                    Log.d("MiAPP", "Se ha utilizado una URL con formato incorrecto.")
                     resultado = "se ha producido un error"
                } catch (e: IOException) {
                    println("ESTE ES EL ERROR" + e)
                    Log.d("MiAPP", "Error inesperado!, posibles problemas de conexion de red")
                     resultado = "se ha producido un error, comprueba tu conexión a Internet"
                }

                return resultado
            }

        */

    override fun onPostExecute(resultado: String) {

        Toast.makeText(context, resultado, Toast.LENGTH_LONG).show()
    }




}