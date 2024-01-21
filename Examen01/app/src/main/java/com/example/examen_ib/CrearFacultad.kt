package com.example.examen_ib

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class CrearFacultad : AppCompatActivity() {
    var nextIdFacultad = 0
    var lastIdFacultad = 0

    var nextIdUF = 0
    var lastIdUF = 0
    var idSelectedFacultad = 0
    var UPos = 0
    var idSelectedUniversidad = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("ciclo-vida","onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_facultad)
    }

    override fun onStart() {
        super.onStart()

        Log.i("ciclo-vida","onStart")
        UPos = intent.getIntExtra("posicion Universidad",-1)
        Log.i("posU","${UPos}")

        UBDD.TablaUniversidad!!.listarU().forEachIndexed { indice: Int, universidad: Universidad ->
            if(indice==UPos){
                idSelectedUniversidad = universidad.idUniversidad
            }
        }

        var longListFacultad = UBDD.TablaUniversidad!!.listarFacultades().lastIndex

        UBDD.TablaUniversidad!!.listarFacultades().forEachIndexed { indice: Int, facultad: Facultad ->
            Log.i("testExamen","${facultad.idFacultad} -> ${facultad.nombreFacultad}")
            if(indice == longListFacultad){
                lastIdFacultad = facultad.idFacultad
            }
        }
        nextIdFacultad = lastIdFacultad+1

        var longPP = Registers.arregloUniversidadesFacultades.lastIndex
        Registers.arregloUniversidadesFacultades.forEachIndexed { indice: Int, universidadesFacultades: UniversidadesFacultades ->
            if(indice==longPP)
                lastIdUF = universidadesFacultades.idUniversidadesFacultades
        }
        nextIdUF = lastIdUF+1


        // ------------ o ------------

        var txtNombre = findViewById<TextInputEditText>(R.id.txt_nombreFacultad)
        var txtDepartamentos = findViewById<TextInputEditText>(R.id.txt_numeroDepartamentos)
        var txtPresupuesto = findViewById<TextInputEditText>(R.id.txt_presupuestoAnual)
        var ofertaInvestigacion = findViewById<Switch>(R.id.ofertaInvestigacion)

        var btnAddFacultad = findViewById<Button>(R.id.btn_crearFacultad)
        btnAddFacultad.setOnClickListener {
            var nombreFacultad = txtNombre.text.toString()
            var numeroDepartamentos = txtDepartamentos.text.toString()
            var presupuestoAnual = txtPresupuesto.text.toString()
            val ofertaInvestigacion = ofertaInvestigacion.isChecked.toString()

            Registers.arregloUniversidadesFacultades.add(UniversidadesFacultades(nextIdUF, idSelectedUniversidad, nextIdFacultad))

            UBDD.TablaUniversidad!!.crearFacultad(nextIdFacultad, nombreFacultad,numeroDepartamentos,presupuestoAnual,ofertaInvestigacion)

            answer()
        }

        // ------------ o ------------

        var btnCancelarCrearFacultad = findViewById<Button>(R.id.btn_cancelar_crear_facultad)
        btnCancelarCrearFacultad.setOnClickListener {
            answer()
        }


    }

    fun answer(){
        val intentReturnParameters = Intent()
        intentReturnParameters.putExtra("posicion Universidad",UPos)
        setResult(
            RESULT_OK,
            intentReturnParameters
        )
        finish()
    }


}