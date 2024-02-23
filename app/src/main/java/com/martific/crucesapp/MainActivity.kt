package com.martific.crucesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.martific.crucesapp.dbManager.DatosPrueba

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.bttConsultar)

        button.setOnClickListener {
           val edittxt = findViewById<EditText>(R.id.editText)
            val inputVal: Long = edittxt.text.toString().toLong()

            val Datos = DatosPrueba()
            val cruce = Datos.crucePrueba1.numCruce
            if (inputVal == cruce)
            {
                    val intent = Intent(this, CruceActivity::class.java)
                    startActivity(intent)
                }

        }

    }



}