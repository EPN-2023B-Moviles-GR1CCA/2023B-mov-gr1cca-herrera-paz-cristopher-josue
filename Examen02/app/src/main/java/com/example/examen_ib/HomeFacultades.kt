package com.example.examen_ib

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class HomeFacultades : AppCompatActivity() {

    var idSelectItem = 0
    var UPos = 0
    var itemSelect = 0
    var idUniversidad = 0

    var resultAddFacultad = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null){
                val data = result.data
                UPos = data?.getIntExtra("posicionUniversidad",0)!!
                actualizarListaFacultades()
            }
        }
    }
    var resultEditFacultad = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null){
                val data = result.data
                UPos = data?.getIntExtra("posicionUniversidad",0)!!
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_facultades)
    }


    fun listViewFacultad():ArrayList<Facultad>{
        var listaIDFacultades = arrayListOf<Int>()

        Registers.arregloUniversidadesFacultades.forEachIndexed { indice: Int, pp: UniversidadesFacultades ->
            if(pp.idUniversidad == idUniversidad){
                listaIDFacultades.add(pp.idFacultad)
            }
        }
        var facultadLists = arrayListOf<Facultad>()
        UBDD.TablaUniversidad!!.listarFacultades().forEachIndexed { index : Int, facultad: Facultad ->
            for(i in listaIDFacultades){
                if(i==facultad.idFacultad){
                    facultadLists.add(facultad)
                }
            }
        }

        return facultadLists
    }

    private fun actualizarListaFacultades() {
        val listViewFacultad = findViewById<ListView>(R.id.lv_facultades_lista)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listViewFacultad()
        )
        listViewFacultad.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        UPos = intent.getIntExtra("posicionEditar",1)
        UBDD.TablaUniversidad!!.listarU().forEachIndexed { indice: Int, universidad: Universidad ->
            if(indice==UPos){
                val txtUniversidadName = findViewById<TextView>(R.id.tv_nombreUniversidad)
                txtUniversidadName.setText("Universidad: "+universidad.nombreUniversidad)
                idUniversidad = universidad.idUniversidad
            }
        }

        val listViewFacultad = findViewById<ListView>(R.id.lv_facultades_lista)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listViewFacultad()
        )

        listViewFacultad.adapter = adaptador
        adaptador.notifyDataSetChanged()

        this.registerForContextMenu(listViewFacultad)

        val btnNewFacultad = findViewById<Button>(R.id.btn_crear_new_facultad)
        btnNewFacultad.setOnClickListener {
            abrirActividadAddFacultad(CrearFacultad::class.java)
        }

        val btnBack = findViewById<Button>(R.id.btn_volver_facultad)
        btnBack.setOnClickListener {
            val intentBackFacultad = Intent(this, HomeUniversidades::class.java)
            startActivity(intentBackFacultad)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val facultadListView = findViewById<ListView>(R.id.lv_facultades_lista)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listViewFacultad()
        )
        facultadListView.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_facultades, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        itemSelect = id
        val idR= listViewFacultad()[id].idFacultad
        idSelectItem = idR
        Log.i("context-menu", "ID Facultad ${id}")
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editarFacultad -> {
                Log.i("context-menu", "Edit position: ${idSelectItem}")
                abrirActividadEditFacultad(EditarFacultad::class.java)
                return true
            }
            R.id.mi_eliminarFacultad -> {
                Log.i("context-menu", "Delete position: ${idSelectItem}")
                UBDD.TablaUniversidad!!.eliminarFacultad(idSelectItem)
                actualizarListaFacultades()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirActividadAddFacultad(
        clase: Class<*>
    ) {
        val intentAddNewFacultad = Intent(this, clase)
        intentAddNewFacultad.putExtra("posicionUniversidad",UPos)
        Log.i("positionSend","${UPos}")
        resultAddFacultad.launch(intentAddNewFacultad)
    }

    fun abrirActividadEditFacultad(
        clase: Class<*>
    ) {
        val intentEditFacultad = Intent(this, clase)
        intentEditFacultad.putExtra("Facultad", idSelectItem)
        intentEditFacultad.putExtra("posicionUniversidadEditar", UPos)
        resultEditFacultad.launch(intentEditFacultad)
    }
}