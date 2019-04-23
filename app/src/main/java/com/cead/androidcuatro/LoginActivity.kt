package com.cead.androidcuatro

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.util.Patterns
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class LoginActivity : AppCompatActivity() {

    var preferencias: SharedPreferences ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var toolBar =  find<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolBar)


        preferencias = PreferenceManager.getDefaultSharedPreferences(this) //leer preferencias, modo en que se crea el archivo, si no se necesitan multiples preferencias usamos las shared por defecto

        // si se necistan multiples preferencias
       // preferencias = getSharedPreferences("Preferencias", Context.MODE_PRIVATE)



        ponerPreferenciasSiExisten()




        btnLogin.onClick {
            val email = editTextEmail.text.toString()
            val pass = editTextPassword.text.toString()

            if(logeo (email, pass)) {
                startActivity(intentFor<MainActivity>().newTask().clearTask()) // una vez que el usuario ya este logeado y le de atras se cerrara la aplicación

                guardarReferencias(email,pass)
            }
        }
    }


    fun  validarEmail(email: String) : Boolean {
        // return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        return !email.isNullOrEmpty()  && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    fun validarPass(pass: String) : Boolean{
        return pass.length >= 5
    }

    fun logeo(email:String, pass: String) : Boolean{

        if(!validarEmail(email)){
            toast("Email no valido, intenta nuevammentre por favor")
            return false
        }else if(!validarPass(pass)){
            toast("Password no valido, intenta nuevammentre por favor")
            return false
        }else{
            return true
        }
    }

    fun guardarReferencias(email: String, pass: String){
        if(switchRecordar.isChecked){
            preferencias!!.edit()                 //escribir las preferncias
                .putString("email", email)
                .putString("pass", pass)
                .apply()
        }
    }


    fun ponerPreferenciasSiExisten(){
        var email = preferencias!!.getString("email","")
        var pass =  preferencias!!.getString("pass","")


        if(!email.isNullOrEmpty() && !pass.isNullOrEmpty()){
            editTextEmail.setText(email)
            editTextPassword.setText(pass)
            switchRecordar.isChecked = true
        }

    }



}
