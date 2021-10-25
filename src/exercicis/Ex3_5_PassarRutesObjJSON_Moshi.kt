package exercicis

import com.squareup.moshi.*
import java.io.EOFException
import java.io.File
import java.io.FileInputStream
import java.io.ObjectInputStream

fun main (args: Array<String>){
    val f = ObjectInputStream(FileInputStream("Rutes.obj"))
    val listaRutas = ArrayList<Ruta>()

    try {
        while (true) {
            val objetoRuta = f.readObject() as Ruta
            listaRutas.add(Ruta(objetoRuta.nom,objetoRuta.desnivell,objetoRuta.desnivellAcumulat,objetoRuta.llistaDePunts))
        }
    } catch (eof: EOFException) {
        f.close()
    }

    val rutes = Rutes(listaRutas)

    val moshi = Moshi.Builder().build()
    val adapter = moshi.adapter(Rutes::class.java)
    val json = adapter.toJson(rutes)

    File("Rutes.json").writeText(json)
}

