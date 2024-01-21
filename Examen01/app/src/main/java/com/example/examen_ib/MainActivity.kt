package com.example.examen_ib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        UBDD.TablaUniversidad = ESqliteHelperUniversidadFacultad (this)
        Registers.arregloUniversidadesFacultades
        val btnStart = findViewById<Button>(R.id.btn_examStart)
        btnStart.setOnClickListener{
            val intent = Intent(this, HomeUniversidades::class.java)
            startActivity(intent)
        }

    }
}