package exemples

import org.json.JSONTokener
import org.json.JSONObject
import java.io.FileReader

fun main(args: Array<String>) {

    val r_json = FileReader("Empresa.json")

    val arrel = JSONTokener(r_json).nextValue() as JSONObject
    val empresa = arrel.getJSONObject("empresa")

    //for (e in arrel.getJSONObject("empresa").getJSONArray("empleats"))

    for (e in empresa.getJSONArray("empleats")){
        val empleat = e as JSONObject
        println("" + empleat.get("nom") + " (" + empleat.get("sou") + ")")
    }
}

