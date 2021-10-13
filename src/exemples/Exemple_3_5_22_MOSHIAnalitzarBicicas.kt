package exemples

import com.squareup.moshi.*
import java.io.File

/**
 * Recomendacion: usar Moshi si tenemos la estructura de clases ya creada,
 * de no ser asi optar por org.json sera mas facil
 */
fun main(args: Array<String>) {
    val json = File("Bicicas.json").readText()

    val moshi = Moshi.Builder().build()
    val llistaTipus = Types.newParameterizedType(List::class.java, Estacions::class.java)
    val adapter: JsonAdapter<List<Estacions>> = moshi.adapter(llistaTipus)
    val bicicas = adapter.fromJson(json)

    val estacions = bicicas.get(0).ocupacion
    println("Hi ha " + estacions.size + " estacions:")
    for (e in estacions)
        println("" + e.id + ": " + e.punto + " (" + e.ocupados + "/" + e.puestos + ")")
}

