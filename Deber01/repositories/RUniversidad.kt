package deber1.repositories

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import deber1.models.Universidad
import java.io.File
import java.io.IOException

object RUniversidad {
    private val gson = Gson()
    private val file = File("src/main/resources/universidades.json")
    private val universidades: MutableList<Universidad> = loadData()

    private fun loadData(): MutableList<Universidad> {
        return try {
            if (file.exists()) {
                val jsonString = file.readText()
                gson.fromJson(jsonString, object : TypeToken<MutableList<Universidad>>() {}.type)
            } else {
                mutableListOf()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            mutableListOf()
        }
    }

    fun getAll(): List<Universidad> {
        return universidades.toList()
    }

    fun getById(id: Int): Universidad? {
        return universidades.find { it.id == id }
    }

    fun create(universidad: Universidad) {
        universidades.add(universidad)
        saveData()
    }

    fun update(updatedUniversidad: Universidad) {
        val index = universidades.indexOfFirst { it.id == updatedUniversidad.id }
        if (index != -1) {
            universidades[index] = updatedUniversidad
            saveData()
        }
    }

    fun delete(id: Int) {
        universidades.removeAll { it.id == id }
        saveData()
    }

    private fun saveData() {
        val jsonString = gson.toJson(universidades)
        file.writeText(jsonString)
    }
}
