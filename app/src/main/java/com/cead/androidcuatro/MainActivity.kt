package com.cead.androidcuatro

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    // shared preferences es  recordar un valor y compartirlo con otros metodos y actividades
    // toolbar es la barra de herammientas donde se muestran el titulo de la actividad
    // en el layout elevation es un sombreado debajo de la figura

    var preferencias: SharedPreferences ? = null

    var toolbarColaps :  CollapsingToolbarLayout?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        toolbarColaps = find<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
        toolbarColaps!!.title = "beneficios de usar kotlin"

        var toolBar =  find<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolBar)

        preferencias = PreferenceManager.getDefaultSharedPreferences(this)
        //preferencias =  getSharedPreferences("Preferencias", Context.MODE_PRIVATE)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when(item!!.itemId){


            R.id.menuSalir -> {
                startActivity(intentFor<LoginActivity>().newTask().clearTask())
                true
            }
            R.id.menuOlvidar -> {
                preferencias!!.edit().clear().apply()
                startActivity(intentFor<LoginActivity>().newTask().clearTask())
                true
            }

            else -> false





    }
}
