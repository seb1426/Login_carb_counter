package com.example.login_carb_counter.data

import android.os.AsyncTask
import android.util.Log
import com.example.login_carb_counter.data.model.LoggedInUser
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource: AsyncTask<String, Void, String>() {

     fun login(username: String, password: String): Result<LoggedInUser> {
        println("ESTE ES EL USUARIO" + username)
        println("ESTE ES EL PASSWORD" + password)

        try {
            lateinit var respuesta: String
            var urlPost = "http://carbcounter.freeoda.com/login.php"
            val data: String = URLEncoder.encode("correo", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" + URLEncoder.encode("cedula", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8")
            //val data: String = "correo" +  "=" + username + "&" + "cedula" + "=" + password

            val mURL = URL(urlPost)
            println(mURL)
            with(mURL.openConnection() as HttpURLConnection) {
                println("ABRE LA CONEXION")

                //Define metodo
                requestMethod = "POST"

                val wr = OutputStreamWriter(getOutputStream());
                println("HACE EL OPSW")
                wr.write(data);
                println("ESCRIBE")
                wr.flush();
                println("HACE EL FLUSH")

                println(requestMethod + "URL : $url")
                println(requestMethod + "Response Code : $responseCode")


                BufferedReader(InputStreamReader(inputStream)).use {
                    println("ENTRA AL BUFFERED READER")

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


            // TODO: handle loggedInUser authentication
            val usuario = LoggedInUser(java.util.UUID.randomUUID().toString(), username)
            return Result.Success(usuario)

        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }

    override fun doInBackground(vararg params: String): String {

            lateinit var respuesta: String
            var urlPost = "http://carbcounter.freeoda.com/login.php"
            val username: String = params[0]
            val password: String = params[1]


            val data: String = URLEncoder.encode("correo", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" + URLEncoder.encode("cedula", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8")
            //val data: String = "correo" +  "=" + username + "&" + "cedula" + "=" + password

            val mURL = URL(urlPost)
            println(mURL)
            with(mURL.openConnection() as HttpURLConnection) {
                println("ABRE LA CONEXION")

                //Define metodo
                requestMethod = "POST"

                val wr = OutputStreamWriter(getOutputStream());
                println("HACE EL OPSW")
                wr.write(data);
                println("ESCRIBE")
                wr.flush();
                println("HACE EL FLUSH")

                println(requestMethod + "URL : $url")
                println(requestMethod + "Response Code : $responseCode")


                BufferedReader(InputStreamReader(inputStream)).use {
                    println("ENTRA AL BUFFERED READER")

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


            // TODO: handle loggedInUser authentication
            val usuario = LoggedInUser(java.util.UUID.randomUUID().toString(), username)
            return respuesta


    }
}