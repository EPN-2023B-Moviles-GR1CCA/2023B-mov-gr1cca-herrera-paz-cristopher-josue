package deber1.models

data class Facultad(
    val nombre: String,
    val descripcion: String,
    val carreras: MutableList<String> = mutableListOf()
)