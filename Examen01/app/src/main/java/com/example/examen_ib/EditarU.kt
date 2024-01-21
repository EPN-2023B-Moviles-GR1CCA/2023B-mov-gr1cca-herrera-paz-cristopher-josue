package com.example.examen_ib

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class EditarU : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ciclo-vida", "onCreate")
        setContentView(R.layout.activity_editar_universidad)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        val posicionU = intent.getIntExtra("posicionEditar",1)

        var editNombre = findViewById<TextInputEditText>(R.id.txt_nombreUniversidad_editar)
        var editFechaFundacion = findViewById<TextInputEditText>(R.id.txt_fechaNacimiento_editar)
        var editTipo = findViewById<TextInputEditText>(R.id.txt_tipoU_editar)
        var editNumeroEstudiantes = findViewById<TextInputEditText>(R.id.txt_numeroEstudiantes_editar)
        var editPostgrado = findViewById<Switch>(R.id.tienePostgrado_editar)

        UBDD.TablaUniversidad!!.listarU().forEachIndexed{ indice: Int, universidad: Universidad ->
            if(indice == posicionU){
                editNombre.setText(universidad.nombreUniversidad.toString())
                editFechaFundacion.setText(universidad.fechaFundacion.toString())
                editTipo.setText(universidad.tipo.toString())
                editNumeroEstudiantes.setText(universidad.numeroEstudiantes.toString())
                if (universidad.tienePostgrado == true){
                    editPostgrado.isChecked = true
                }
            }
        }

        val btnGuardarCambios = findViewById<Button>(R.id.btn_editarUniversidad)
        btnGuardarCambios.setOnClickListener {
            var nombreUniversidad = editNombre.text.toString()
            var fechaFundacion = editFechaFundacion.text.toString()
            var tipo = editTipo.text.toString()
            var numeroEstudiantes = editNumeroEstudiantes.text.toString().toDouble()
            var postgrado = editPostgrado.isChecked

            UBDD.TablaUniversidad!!.actualizarUniversidad(posicionU, nombreUniversidad,
                fechaFundacion, tipo, numeroEstudiantes.toString(), postgrado.toString())

            val intentEditSucces = Intent(this, HomeUniversidades::class.java)
            startActivity(intentEditSucces)
        }

        val btnCancelarEditar = findViewById<Button>(R.id.btn_cancelar_editar)
        btnCancelarEditar.setOnClickListener{
            val intentEditCancel = Intent(this, HomeUniversidades::class.java)
            startActivity(intentEditCancel)
        }

    }
}