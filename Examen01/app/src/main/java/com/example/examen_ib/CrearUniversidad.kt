package com.example.examen_ib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Switch
import com.google.android.material.textfield.TextInputEditText

class CrearUniversidad : AppCompatActivity() {

    var id1 = 0
    var id2 = 0

    var nombreUniversidad = ""
    var fechaFundacion = ""
    var tipo = ""
    var numeroEstudiantes = ""
    var tienePostgrado = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_universidad)
    }

    override fun onStart() {
        super.onStart()
        Log.i("cicloVida", "onStart")
        var longListUniversidad = UBDD.TablaUniversidad!!.listarU().lastIndex
        UBDD.TablaUniversidad!!.listarU().forEachIndexed { indice: Int, universidad: Universidad ->
            Log.i("testExamen","${universidad.idUniversidad} -> ${universidad.nombreUniversidad}")
            if( indice == longListUniversidad){
                id2 = universidad.idUniversidad
            }
        }

        id1 = id2+1

        var txtNombre = findViewById<TextInputEditText>(R.id.txt_nombreUniversidad)
        var txtFechaFundacion = findViewById<TextInputEditText>(R.id.txt_fechaFundacion)
        var txtTipo = findViewById<TextInputEditText>(R.id.txt_tipoU)
        var txtEstudiantes = findViewById<TextInputEditText>(R.id.txt_numeroEstudiantes)
        var tienePostgrado = findViewById<Switch>(R.id.tienePostgrado)

        var btnCrearU = findViewById<Button>(R.id.btn_crearUniversidad)

        btnCrearU.setOnClickListener {
            nombreUniversidad = txtNombre.text.toString()
            fechaFundacion = txtFechaFundacion.text.toString()
            tipo = txtTipo.text.toString()
            numeroEstudiantes = txtEstudiantes.text.toString()
            this.tienePostgrado = tienePostgrado.isChecked.toString()

            UBDD.TablaUniversidad!!.crearUniversidad(id1,nombreUniversidad,fechaFundacion,
                tipo, numeroEstudiantes, this.tienePostgrado
            )

            val intentAddSucces = Intent(this, HomeUniversidades::class.java)
            startActivity(intentAddSucces)

        }

        var btnCancelarCrearUniversidad = findViewById<Button>(R.id.btn_cancelar_crear)
        btnCancelarCrearUniversidad.setOnClickListener {
            val intentAddCancel = Intent(this, HomeUniversidades::class.java)
            startActivity(intentAddCancel)
        }

    }
}