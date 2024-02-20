package com.example.examen_ib

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import java.lang.Exception

class EFirestoreHelperUniversidadFacultad {
    private val TAG = "Firestore"
    private val db = FirebaseFirestore.getInstance()

    // ------------------- UNIVERSIDAD -------------------
    fun crearUniversidad(id:Int, nombre:String, fecha:String, tipo:String, estudiantes:String, postgrado:String ): Boolean{
        val universidad = hashMapOf(
            "id" to id,
            "nombre" to nombre,
            "fundacion" to fecha,
            "tipo" to tipo,
            "estudiantes" to estudiantes,
            "postgrado" to postgrado
        )

        db.collection("universidades")
            .add(universidad)
            .addOnSuccessListener { documentReference: DocumentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e: Exception ->
                Log.w(TAG, "Error adding document", e)
            }
        return true
    }

    fun listarU(): ArrayList<Universidad>{
        var lista = arrayListOf<Universidad>()
        db.collection("universidades")
            .get()
            .addOnSuccessListener { documents: QuerySnapshot ->
                for (document in documents) {
                    val universidad = document.toObject(Universidad::class.java)
                    lista.add(universidad)
                }
            }
            .addOnFailureListener { e: Exception ->
                Log.w(TAG, "Error getting documents", e)
            }
        return lista
    }

    fun actualizarUniversidad(id1:Int, nombre:String, fechaFundacion:String, tipo:String, estudiantes:String, postgrado:String ):Boolean{
        val universidad = hashMapOf(
            "nombre" to nombre,
            "fundacion" to fechaFundacion,
            "tipo" to tipo,
            "estudiantes" to estudiantes,
            "postgrado" to postgrado
        )
        db.collection("universidades").document(id1.toString())
            .set(universidad)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { e: Exception ->
                Log.w(TAG, "Error updating document", e)
            }
        return true
    }

    fun eliminarU(id:Int):Boolean{
        db.collection("universidades").document(id.toString())
            .delete()
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully deleted!")
            }
            .addOnFailureListener { e: Exception ->
                Log.w(TAG, "Error deleting document", e)
            }
        return true
    }

    // ------------------- FACULTAD -------------------
    fun crearFacultad(id:Int, nombre:String, departamentos:String, presupuesto: String, investigacion: String):Boolean{
        val facultad = hashMapOf(
            "id" to id,
            "nombre" to nombre,
            "departamentos" to departamentos,
            "presupuesto" to presupuesto,
            "investigacion" to investigacion
        )

        db.collection("facultades")
            .add(facultad)
            .addOnSuccessListener { documentReference: DocumentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e: Exception ->
                Log.w(TAG, "Error adding document", e)
            }
        return true
    }

    fun listarFacultades(): ArrayList<Facultad>{
        var lista = arrayListOf<Facultad>()
        db.collection("facultades")
            .get()
            .addOnSuccessListener { documents: QuerySnapshot ->
                for (document in documents) {
                    val facultad = document.toObject(Facultad::class.java)
                    lista.add(facultad)
                }
            }
            .addOnFailureListener { e: Exception ->
                Log.w(TAG, "Error getting documents", e)
            }
        return lista
    }

    fun actualizarFacultad(id:Int, nombre:String, departamentos:String, presupuesto: String, investigacion: String):Boolean{
        val facultad = hashMapOf(
            "nombre" to nombre,
            "departamentos" to departamentos,
            "presupuesto" to presupuesto,
            "investigacion" to investigacion
        )
        db.collection("facultades").document(id.toString())
            .set(facultad)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { e: Exception ->
                Log.w(TAG, "Error updating document", e)
            }
        return true
    }

    fun eliminarFacultad(id:Int):Boolean{
        db.collection("facultades").document(id.toString())
            .delete()
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully deleted!")
            }
            .addOnFailureListener { e: Exception ->
                Log.w(TAG, "Error deleting document", e)
            }
        return true
    }
}