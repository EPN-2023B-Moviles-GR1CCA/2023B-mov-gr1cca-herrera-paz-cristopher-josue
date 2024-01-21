package com.example.examen_ib

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
class EditarFacultad : AppCompatActivity() {
    var universidadPos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_facultad)
    }
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()
        universidadPos = intent.getIntExtra("posicion-Universidad-Editar", 1)
        val editNombreFacultad = findViewById<TextInputEditText>(R.id.txt_nombreMascota_editar)
        val editNumeroDepartamentos = findViewById<TextInputEditText>(R.id.txt_numeroDepartamentos_editar)
        val editPresupuestoAnual = findViewById<TextInputEditText>(R.id.txt_presupuestoAnual_editar)
        val editOfertaInvestigacion = findViewById<Switch>(R.id.ofertaInvestigacion_editar)
        var idFacultad = intent.getIntExtra("Facultad", 1)

        UBDD.TablaUniversidad!!.listarFacultades().forEachIndexed { indice: Int, facultad: Facultad ->
            if (facultad.idFacultad == idFacultad) {
                // Llenar los campos
                editNombreFacultad.setText(facultad.nombreFacultad)
                editNumeroDepartamentos.setText(facultad.numeroDepartamentos.toString())
                editPresupuestoAnual.setText(facultad.presupuestoAnual.toString())
                editOfertaInvestigacion.isChecked = facultad.ofertaInvestigacion == true
            }
        }
        val btnEditFacultad = findViewById<Button>(R.id.btn_editarFacultad)
        btnEditFacultad.setOnClickListener {
            var nombreFacultad = editNombreFacultad.text.toString()
            var numeroDepartamentos = editNumeroDepartamentos.text.toString()
            var presupuestoAnual = editPresupuestoAnual.text.toString()
            var ofertaInvestigacion = editOfertaInvestigacion.isChecked.toString()

            UBDD.TablaUniversidad!!.actualizarFacultad(
                idFacultad, nombreFacultad, numeroDepartamentos, presupuestoAnual,
                ofertaInvestigacion
            )
            answer()
        }
        val btnCancelFacultad = findViewById<Button>(R.id.btn_cancelar_editar_facultad)
        btnCancelFacultad.setOnClickListener {
            answer()
        }

    }

    fun answer(){
        val intentReturnParameters = Intent()
        intentReturnParameters.putExtra("posicion-Universidad-Editar",universidadPos)
        setResult(
            RESULT_OK,
            intentReturnParameters
        )
        finish()
    }
}