package deber1

import deber1.models.Facultad
import deber1.models.Universidad
import deber1.repositories.RFacultad
import deber1.repositories.RUniversidad

fun main() {
    while (true) {
        println("Seleccione una opción:")
        println("1. Ver universidades")
        println("2. Agregar universidad")
        println("3. Actualizar universidad")
        println("4. Eliminar universidad")
        println("5. Ver facultades por universidad")
        println("6. Agregar facultad")
        println("7. Actualizar facultad")
        println("8. Eliminar facultad")
        println("9. Salir")

        when (readlnOrNull()?.toIntOrNull() ?: 0) {
            1 -> verUniversidades()
            2 -> agregarUniversidad()
            3 -> actualizarUniversidad()
            4 -> eliminarUniversidad()
            5 -> verFacultadesPorUniversidad()
            6 -> agregarFacultad()
            7 -> actualizarFacultad()
            8 -> eliminarFacultad()
            9 -> return
            else -> println("Opción no válida. Por favor, elija una opción válida.")
        }
    }
}

fun verUniversidades() {
    val universidades = RUniversidad.getAll()
    if (universidades.isEmpty()) {
        println("No hay universidades registradas.")
    } else {
        println("Universidades:")
        universidades.forEach { println(it) }
    }
}

fun agregarUniversidad() {
    println("Ingrese el nombre de la universidad:")
    val nombre = readlnOrNull().toString()

    println("Ingrese la ubicación de la universidad:")
    val ubicacion = readlnOrNull().toString()

    val nuevaUniversidad = Universidad(RUniversidad.getAll().size + 1, nombre, ubicacion)
    RUniversidad.create(nuevaUniversidad)

    println("Universidad agregada correctamente:")
    println(nuevaUniversidad)
}

fun actualizarUniversidad() {
    println("Ingrese el ID de la universidad a actualizar:")
    val idUniversidad = readlnOrNull()?.toIntOrNull()
    if (idUniversidad != null) {
        val universidadExistente = RUniversidad.getById(idUniversidad)
        if (universidadExistente != null) {
            println("Ingrese el nuevo nombre de la universidad:")
            val nombre = readlnOrNull().toString()

            println("Ingrese la nueva ubicación de la universidad:")
            val ubicacion = readlnOrNull().toString()

            val universidadActualizada = Universidad(idUniversidad, nombre, ubicacion)
            RUniversidad.update(universidadActualizada)

            println("Universidad actualizada correctamente:")
            println(universidadActualizada)
        } else {
            println("No se encontró ninguna universidad con el ID proporcionado.")
        }
    } else {
        println("ID de universidad no válido.")
    }
}

fun eliminarUniversidad() {
    println("Ingrese el ID de la universidad a eliminar:")
    val idUniversidad = readlnOrNull()?.toIntOrNull()
    if (idUniversidad != null) {
        val universidadExistente = RUniversidad.getById(idUniversidad)
        if (universidadExistente != null) {
            RUniversidad.delete(idUniversidad)
            println("Universidad eliminada correctamente.")
        } else {
            println("No se encontró ninguna universidad con el ID proporcionado.")
        }
    } else {
        println("ID de universidad no válido.")
    }
}

fun verFacultadesPorUniversidad() {
    println("Ingrese el ID de la universidad:")
    val idUniversidad = readlnOrNull()?.toIntOrNull()
    if (idUniversidad != null) {
        val facultades = RFacultad.getByUniversidadId(idUniversidad)
        if (facultades.isEmpty()) {
            println("No hay facultades registradas para esta universidad.")
        } else {
            println("Facultades para la Universidad $idUniversidad:")
            facultades.forEach { println(it) }
        }
    } else {
        println("ID de universidad no válido.")
    }
}

fun agregarFacultad() {
    println("Ingrese el nombre de la facultad:")
    val nombre = readlnOrNull().toString()

    println("Ingrese el ID de la universidad para esta facultad:")
    val idUniversidad = readlnOrNull()?.toIntOrNull()
    if (idUniversidad != null) {
        val nuevaFacultad = Facultad(RFacultad.getAll().size + 1, nombre, idUniversidad)
        RFacultad.create(nuevaFacultad)
        println("Facultad agregada correctamente:")
        println(nuevaFacultad)
    } else {
        println("ID de universidad no válido.")
    }
}

fun actualizarFacultad() {
    println("Ingrese el ID de la facultad a actualizar:")
    val idFacultad = readlnOrNull()?.toIntOrNull()
    if (idFacultad != null) {
        val facultadExistente = RFacultad.getById(idFacultad)
        if (facultadExistente != null) {
            println("Ingrese el nuevo nombre de la facultad:")
            val nombre = readlnOrNull().toString()

            println("Ingrese el nuevo ID de la universidad para esta facultad:")
            val idUniversidad = readlnOrNull()?.toIntOrNull()
            if (idUniversidad != null) {
                val facultadActualizada = Facultad(idFacultad, nombre, idUniversidad)
                RFacultad.update(facultadActualizada)
                println("Facultad actualizada correctamente:")
                println(facultadActualizada)
            } else {
                println("ID de universidad no válido.")
            }
        } else {
            println("No se encontró ninguna facultad con el ID proporcionado.")
        }
    } else {
        println("ID de facultad no válido.")
    }
}

fun eliminarFacultad() {
    println("Ingrese el ID de la facultad a eliminar:")
    val idFacultad = readlnOrNull()?.toIntOrNull()
    if (idFacultad != null) {
        val facultadExistente = RFacultad.getById(idFacultad)
        if (facultadExistente != null) {
            RFacultad.delete(idFacultad)
            println("Facultad eliminada correctamente.")
        } else {
            println("No se encontró ninguna facultad con el ID proporcionado.")
        }
    } else {
        println("ID de facultad no válido.")
    }
}
