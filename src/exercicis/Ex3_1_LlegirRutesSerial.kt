package exercicis

import java.io.FileInputStream
import java.io.DataInputStream

fun main() {
    val f = DataInputStream(FileInputStream("Rutes.dat"))
    /*DataInputStream, available():
    devuelve los bytes restantes o disponibles en el fichero
     */
    while (f.available() > 0) {
        System.out.println("Ruta: " + f.readUTF())
        System.out.println("Desnivell: " + f.readInt())
        System.out.println("Desnivell acumulat: " + f.readInt())

        val punts = f.readInt()
        System.out.println("Te $punts punts")
        for (i in 1..punts){
            print("Punt ${i}: ")
            print(f.readUTF()+" ")
            print("(${f.readDouble()}, ${f.readDouble()})")
            println()
        }
        System.out.println()
    }
    f.close()
}