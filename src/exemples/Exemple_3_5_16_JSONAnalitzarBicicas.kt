package exemples

import org.json.JSONTokener
import org.json.JSONObject
import org.json.JSONArray
import java.io.FileReader

fun main(args: Array<String>) {

    val r_json = FileReader("Bicicas.json")

    val arrel = JSONTokener(r_json).nextValue() as JSONArray

    val llistaEstacions = arrel.getJSONObject(0).getJSONArray("ocupacion")

    for (e in llistaEstacions){
        e as JSONObject
        println("" + e.get("id") + ".- " + e.get("punto") + " (" + e.get("ocupados") + "/" + e.get("puestos") + ")")
    }
}

