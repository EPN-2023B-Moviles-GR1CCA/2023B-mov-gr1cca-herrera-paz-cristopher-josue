package com.example.examen_ib

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class ESqliteHelperUniversidadFacultad(
    contexto: Context?,
): SQLiteOpenHelper(contexto, "moviles", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaUniversidad: ArrayList<String> = arrayListOf(
            """
               CREATE TABLE FACULTAD(
               id INTEGER PRIMARY KEY,
               nombre VARCHAR(50),
               departamentos VARCHAR(50),
               presupuesto VARCHAR(50),
               investigacion VARCHAR(50)
               );
             """, """
               CREATE TABLE UNIVERSIDAD(
               id INTEGER PRIMARY KEY,
               nombre VARCHAR(50),
               fundacion VARCHAR(100),
               tipo VARCHAR(50),
               estudiantes VARCHAR(50),
               postgrado VARCHAR(50)
               );
            """
        )
        for (i in scriptSQLCrearTablaUniversidad) {
            db!!.execSQL(i)
        }
        Log.i("creart", "Universidades")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    // ------------------- UNIVERSIDAD -------------------
    fun crearUniversidad(id:Int, nombre:String, fecha:String, tipo:String, estudiantes:String, postgrado:String ): Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("id",id)
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("fundacion", fecha)
        valoresAGuardar.put("tipo", tipo)
        valoresAGuardar.put("estudiantes", estudiantes)
        valoresAGuardar.put("postgrado", postgrado)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "UNIVERSIDAD",
                null,
                valoresAGuardar
            )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true
    }

    fun listarU(): ArrayList<Universidad>{
        var lista = arrayListOf<Universidad>()
        var universidad: Universidad?
        val baseDatosLectura = readableDatabase
        val scriptConsultarUsuario = "SELECT * FROM UNIVERSIDAD"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            null
        )
        if(resultadoConsultaLectura.moveToFirst()){
            do {
                universidad=Universidad(0,"","","",0,false)
                universidad!!.idUniversidad= resultadoConsultaLectura.getInt(0)
                universidad.nombreUniversidad= resultadoConsultaLectura.getString(1)
                universidad.fechaFundacion= resultadoConsultaLectura.getString(2)
                universidad.tipo= resultadoConsultaLectura.getString(3)
                universidad.numeroEstudiantes= resultadoConsultaLectura.getString(4).toInt()
                universidad.tienePostgrado= resultadoConsultaLectura.getString(5).toBoolean()
                lista.add(universidad)
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return lista
    }
    fun actualizarUniversidad(id1:Int, nombre:String, fechaFundacion:String, tipo:String, estudiantes:String, postgrado:String ):Boolean{
        var lista= UBDD.TablaUniversidad!!.listarU()
        val id=lista[id1].idUniversidad.toString()
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("fundacion", fechaFundacion)
        valoresAActualizar.put("tipo", tipo)
        valoresAActualizar.put("estudiantes", estudiantes)
        valoresAActualizar.put("postgrado", postgrado)
        val resultadoActualizacion = conexionEscritura
            .update(
                "UNIVERSIDAD", // Nombre tabla
                valoresAActualizar,  // Valores a actualizar
                "id=?", // Clausula Where
                arrayOf(
                    id.toString()
                ) // Parametros clausula Where
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }

    fun eliminarU(id:Int):Boolean{
        var lista= UBDD.TablaUniversidad!!.listarU()
        val idE=lista[id].idUniversidad.toString()
        val conexion= writableDatabase
        val resultadoEliminacion=conexion
            .delete("UNIVERSIDAD","id=?", arrayOf(idE))
        conexion.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    // ------------------- FACULTAD -------------------
    fun crearFacultad(id:Int, nombre:String, departamentos:String, presupuesto: String, investigacion: String):Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("id",id)
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("departamentos", departamentos)
        valoresAGuardar.put("presupuesto", presupuesto)
        valoresAGuardar.put("investigacion", investigacion)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "FACULTAD",
                null,
                valoresAGuardar
            )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true
    }

    fun listarFacultades(): ArrayList<Facultad>{
        var lista = arrayListOf<Facultad>()
        var facultadXD: Facultad?
        val baseDatosLectura = readableDatabase
        val scriptConsultarUsuario = "SELECT * FROM FACULTAD"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            null
        )
        if(resultadoConsultaLectura.moveToFirst()){
            do {
                facultadXD= Facultad(0,"",0,0.0,false)
                facultadXD!!.idFacultad= resultadoConsultaLectura.getInt(0)
                facultadXD.nombreFacultad= resultadoConsultaLectura.getString(1)
                facultadXD.numeroDepartamentos = resultadoConsultaLectura.getInt(2)
                facultadXD.presupuestoAnual = resultadoConsultaLectura.getDouble(3)
                facultadXD.ofertaInvestigacion = resultadoConsultaLectura.getString(4).toBoolean()
                lista.add(facultadXD)
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return lista
    }

    fun actualizarFacultad(id:Int, nombre:String, departamentos:String, presupuesto: String, investigacion: String):Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("departamentos", departamentos)
        valoresAActualizar.put("presupuesto", presupuesto)
        valoresAActualizar.put("investigacion", investigacion)
        val resultadoActualizacion = conexionEscritura
            .update(
                "FACULTAD", // Nombre tabla
                valoresAActualizar,  // Valores a actualizar
                "id=?", // Clausula Where
                arrayOf(
                    id.toString()
                ) // Parametros clausula Where
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }

    fun eliminarFacultad(id:Int):Boolean{
        val conexion= writableDatabase
        val resultadoEliminacion=conexion
            .delete("FACULTAD","id=?", arrayOf(id.toString()))
        conexion.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }
}