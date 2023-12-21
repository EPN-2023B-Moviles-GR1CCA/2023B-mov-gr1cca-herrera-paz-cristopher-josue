package deber1.models

data class Universidad(
    val nombre: String,
    val ubicacion: String,
    val facultades: MutableList<Facultad> = mutableListOf()
)
