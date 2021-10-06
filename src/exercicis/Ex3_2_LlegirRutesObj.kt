package exercicis

import java.io.EOFException
import java.io.FileInputStream
import java.io.ObjectInputStream

fun main(){
    val fileIn = ObjectInputStream(FileInputStream("Rutes.obj"))

    try {
        while (true){
            val ruta = fileIn.readObject() as Ruta
            println(ruta.mostrarRuta())
        }
    } catch (e: EOFException){
        fileIn.close()
    }
}