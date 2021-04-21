package com.example.login_carb_counter.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils.split
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.core.content.ContextCompat.startActivity

import com.example.login_carb_counter.R
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var arregloDatos: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)




        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val loading = findViewById<ProgressBar>(R.id.loading)

        username.setSelection(0)
        password.setSelection(0)

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
                .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
           // finish()
            val intent: Intent = Intent(this, MenuPrincipal::class.java)
            startActivity(intent)
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                        username.text.toString(),
                        password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                                username.text.toString(),
                                password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                var correo =  username.text.toString()
                var contra = password.text.toString()
               //loginViewModel.login(username.text.toString(), password.text.toString())
               val x = DescargarImagen2(this@LoginActivity).execute(correo, contra)
                arregloDatos = x.get().split(") \"", "\"") as ArrayList<String>
                println("LOS DATOS SON: " + arregloDatos[3])
                println("LOS DATOS SON: " + arregloDatos[5])

                if(arregloDatos[5] == "Bienvenido a Carb Counter!")
                {
                    Toast.makeText(context, "Bienvenido a Carb Counter!", Toast.LENGTH_LONG).show()
                    navToMenuPrincipalAction()
                }
                else
                {
                  username.setText("")
                  password.setText("")
                  loading.visibility = View.GONE


                }


            }

        }



    }

    private fun navToMenuPrincipalAction() {
        val intent: Intent = Intent(this, MenuPrincipal::class.java)
        intent.putExtra("nombreUsuario", arregloDatos[3])
        startActivity(intent)
    }
    private fun navToLoginAction() {
        val intent: Intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
                applicationContext,
                "$welcome $displayName",
                Toast.LENGTH_LONG
        ).show()
    }



    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }


    public fun navToRegistroAction(view:View)
    {
        val intent: Intent = Intent(this, Registro::class.java)
        startActivity(intent)
    }






}


class DescargarImagen2(private val context: Context): AsyncTask<String, Void, String>() {



    public override fun doInBackground(vararg params: String): String {


        var urlPost = "http://carbcounter.freeoda.com/login.php"
        val correo: String = params[0]
        val cedula: String = params[1]

        lateinit var respuesta: String

        println("EL NOMBRE ES: " + correo)

        val data: String = URLEncoder.encode("correo", "UTF-8") + "=" + URLEncoder.encode(correo, "UTF-8") + "&" + URLEncoder.encode("cedula", "UTF-8") + "=" + URLEncoder.encode(cedula, "UTF-8")

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
    override fun onPostExecute(resultado: String) {

        Toast.makeText(context, resultado, Toast.LENGTH_LONG).show()
    }

*/

}




/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}

