// Importar clases necesarias
import java.util.*
import kotlin.collections.ArrayList

// Función principal
fun main() {
    println("Hola mundo")

    // Variables inmutables (no se pueden reasignar)
    val nombreInmutable: String = "Cristopher"

    // Variables mutables (se pueden reasignar)
    var nombreMutable: String = "Josue"
    nombreMutable = "Cristopher"

    // Duck Typing
    var ejemploVariable = "Cristopher"
    val edadEjemplo: Int = 12
    ejemploVariable.trim()

    // Variables primitivas
    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'C'
    val mayorEdad: Boolean = true

    // Clases Java
    val fechaNacimiento: Date = Date()

    // Switch
    val estadoCivilWhen = "C"
    when (estadoCivilWhen) {
        "C" -> {
            println("Casado")
        }
        "S" -> {
            println("Soltero")
        }
        else -> {
            println("No sabemos")
        }
    }

    // If-else
    val coqueteo = if (estadoCivilWhen == "S") "Si" else "No"

    // Llamadas a la función calcularSueldo con diferentes parámetros
    calcularSueldo(10.00)
    calcularSueldo(10.00, 15.00)
    calcularSueldo(10.00, 12.00, 20.00)

    calcularSueldo(sueldo = 10.00)
    calcularSueldo(sueldo = 10.00, tasa = 15.00)
    calcularSueldo(sueldo = 10.00, tasa = 12.00, bonoEspecial = 20.00)

    calcularSueldo(sueldo = 10.00, bonoEspecial = 20.00)
    calcularSueldo(10.00, bonoEspecial = 20.00)

    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00)

    // Instanciar la clase Suma
    val sumaUno = Suma(1, 1)
    val sumaDos = Suma(null, 1)
    val sumaTres = Suma(1, null)
    val sumaCuatro = Suma(null, null)

    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()

    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    // Arreglos
    val arregloEstatico: Array<Int> = arrayOf(1, 2, 3)
    println(arregloEstatico)

    val arregloDinamico: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    arregloDinamico.forEach { valorActual ->
        println("valor actual: $valorActual")
    }

    arregloDinamico.forEach { println(it) }

    arregloEstatico.forEachIndexed { indice, valorActual ->
        println("Valor $valorActual Indice: $indice")
    }

    // Map
    val respuestaMap: List<Double> = arregloDinamico.map { valorActual ->
        return@map valorActual.toDouble() + 100.00
    }
    println(respuestaMap)

    val respuestaMapDos = arregloDinamico.map { it + 15 }

    // OR y AND
    val respuestaAny: Boolean = arregloDinamico.any { valorActual ->
        return@any valorActual > 5
    }
    println(respuestaAny)

    val respuestaAll: Boolean = arregloDinamico.all { valorActual ->
        return@all valorActual > 5
    }
    println(respuestaAll)

    // Filter
    val respuestaFilter: List<Int> = arregloDinamico.filter { valorActual ->
        val mayoresACinco: Boolean = valorActual > 5
        return@filter mayoresACinco
    }

    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    // Reduce
    val respuestaReduce: Int = arregloDinamico.reduce { acumulado, valorActual ->
        return@reduce acumulado + valorActual
    }
    println(respuestaReduce)
}

// Clase abstracta NumerosJava
abstract class NumerosJava {
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(uno: Int, dos: Int) {
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }
}

// Clase abstracta Numeros
abstract class Numeros(
    protected val numeroUno: Int,
    protected val numeroDos: Int
) {
    init {
        this.numeroUno
        this.numeroDos
        println("Inicializando")
    }
}

// Clase Suma
class Suma(
    uno: Int,
    dos: Int
) : Numeros(uno, dos) {
    init {
        this.numeroUno
        numeroUno
        this.numeroDos
        numeroDos
    }

    constructor(
        uno: Int?,
        dos: Int
    ) : this(
        if (uno == null) 0 else uno,
        dos
    )

    constructor(
        uno: Int,
        dos: Int?
    ) : this(
        uno,
        if (dos == null) 0 else uno
    )

    constructor(
        uno: Int?,
        dos: Int?
    ) : this(
        if (uno == null) 0 else uno,
        if (dos == null) 0 else uno
    )

    fun sumar(): Int {
        val total = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }

    companion object {
        val pi = 3.14

        fun elevarAlCuadrado(num: Int): Int {
            return num * num
        }

        val historialSumas = arrayListOf<Int>()

        fun agregarHistorial(valorNuevaSuma: Int) {
            historialSumas.add(valorNuevaSuma)
        }
    }
}

// Función para imprimir nombre
fun imprimirNombre(nombre: String) {
    println("Nombre: $nombre")
}

// Función para calcular sueldo
fun calcularSueldo(
    sueldo: Double,
    tasa: Double = 12.00,
    bonoEspecial: Double? = null
): Double {
    if (bonoEspecial == null) {
        return sueldo * (100 / tasa)
    } else {
        return sueldo * (100 / tasa) + bonoEspecial
    }
}