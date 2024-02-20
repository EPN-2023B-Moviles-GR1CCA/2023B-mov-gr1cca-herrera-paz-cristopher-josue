package com.example.examen_ib
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class HomeUniversidades :  AppCompatActivity() {
    var idSelectItem = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_universidad)
        Log.i("ciclo-vida", "onCreate")

    }
    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        val listViewU = findViewById<ListView>(R.id.lv_universidades_lista)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            UBDD.TablaUniversidad!!.listarU()
        )
        listViewU.adapter = adaptador
        adaptador.notifyDataSetChanged()

        this.registerForContextMenu(listViewU)

        val btnAnadirU = findViewById<Button>(R.id.btn_crear_new_universidad)
        btnAnadirU.setOnClickListener {
            val intentAddU = Intent(this, CrearUniversidad::class.java)
            startActivity(intentAddU)
        }

    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            // Guardar las variables
            // primitivos
            putInt("idItemSeleccionado",idSelectItem)
            putParcelableArrayList("arregloU",UBDD.TablaUniversidad!!.listarU())
            putParcelableArrayList("arregloPP",Registers.arregloUniversidadesFacultades)

        }
        super.onSaveInstanceState(outState)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        idSelectItem = savedInstanceState.getInt("idItemSeleccionado")

        Registers.arregloUniversidadesFacultades = savedInstanceState.getParcelableArrayList<UniversidadesFacultades>("arregloPP")!!
        if (idSelectItem == null){
            idSelectItem = 0
        }
        val listViewU = findViewById<ListView>(R.id.lv_universidades_lista)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            UBDD.TablaUniversidad!!.listarU()
        )
        listViewU.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idSelectItem = id
        Log.i("context-menu", "ID ${id}")
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                Log.i("context-menu", "Edit position: ${idSelectItem}")
                abrirActividadConParametros(EditarU::class.java)
                return true
            }
            R.id.mi_eliminar -> {
                Log.i("context-menu", "Delete position: ${idSelectItem}")
                UBDD.TablaUniversidad!!.eliminarU(idSelectItem)
                val listViewU = findViewById<ListView>(R.id.lv_universidades_lista)
                val adaptador = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    UBDD.TablaUniversidad!!.listarU()
                )
                listViewU.adapter = adaptador
                adaptador.notifyDataSetChanged()
                return true
            }
            R.id.mi_facultades -> {
                Log.i("context-menu", "Facultades: ${idSelectItem}")
                abrirActividadConParametros(HomeFacultades::class.java)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
    fun abrirActividadConParametros(
        clase: Class<*>
    ) {
        val intentEditarU = Intent(this, clase)
        intentEditarU.putExtra("posicionEditar", idSelectItem)
        startActivity(intentEditarU)
    }
}