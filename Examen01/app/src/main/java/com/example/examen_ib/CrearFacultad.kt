package com.example.examen_ib

import android.annotation.SuppressLint
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
    //var idSelectedFacultad = 0
    var uPos = 0
    var idSelectedUniversidad = 0

    var nombreFacultad = ""
    var numeroDepartamentos = ""
    var presupuestoAnual = ""
    var ofertaInvestigacion = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_facultad)
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")
        uPos = intent.getIntExtra("posicion Universidad",1)

        UBDD.TablaUniversidad!!.listarU().forEachIndexed { indice: Int, universidad: Universidad ->
            if(indice==uPos){
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

        var txtNombre = findViewById<TextInputEditText>(R.id.txt_nombreFacultad)
        var txtDepartamentos = findViewById<TextInputEditText>(R.id.txt_numeroDepartamentos)
        var txtPresupuesto = findViewById<TextInputEditText>(R.id.txt_presupuestoAnual)
        var ofertaInvestigacion = findViewById<Switch>(R.id.ofertaInvestigacion)

        var btnAddFacultad = findViewById<Button>(R.id.btn_crearFacultad)

        btnAddFacultad.setOnClickListener {
            nombreFacultad = txtNombre.text.toString()
            numeroDepartamentos = txtDepartamentos.text.toString()
            presupuestoAnual = txtPresupuesto.text.toString()
            this.ofertaInvestigacion = ofertaInvestigacion.isChecked.toString()

            UBDD.TablaUniversidad!!.crearFacultad(nextIdFacultad, nombreFacultad,numeroDepartamentos,presupuestoAnual,this.ofertaInvestigacion)
            Registers.arregloUniversidadesFacultades.add(UniversidadesFacultades(nextIdUF, idSelectedUniversidad, nextIdFacultad))

            answer()
        }

        // ------------ o ------------

        var btnCancelarCrearFacultad = findViewById<Button>(R.id.btn_cancelar_crear_facultad)
        btnCancelarCrearFacultad.setOnClickListener {
            answer()
        }
    }

    private fun answer(){
        val intentReturnParameters = Intent()
        intentReturnParameters.putExtra("posicion Universidad",uPos)
        setResult(
            RESULT_OK,
            intentReturnParameters
        )
        finish()
    }


}