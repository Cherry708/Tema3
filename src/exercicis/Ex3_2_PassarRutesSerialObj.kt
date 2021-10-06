package exercicis

import java.io.*


fun main() {
    val fileIn = DataInputStream(FileInputStream("Rutes.dat"))
    val fileOut = ObjectOutputStream(FileOutputStream("Rutes.obj"))
    var ruta: Ruta

    try {
        while (true) {
            val nom = fileIn.readUTF()
            val desnivell = fileIn.readInt()
            val desnivellAcumulat = fileIn.readInt()

            val punts = fileIn.readInt()

            val listaPuntos = mutableListOf<PuntGeo>()
            for (i in 1..punts) {
                val nomPunt = fileIn.readUTF()
                val latitud = fileIn.readDouble()
                val longitud = fileIn.readDouble()
                listaPuntos.add(PuntGeo(nomPunt, Coordenades(latitud, longitud)))
            }

            ruta = Ruta(nom, desnivell, desnivellAcumulat, listaPuntos)

            System.out.println("Asignacion finalizada\n")
            println(ruta.mostrarRuta())
            fileOut.writeObject(ruta)
        }
    } catch (e: EOFException) {
        fileIn.close()
        fileOut.close()
    }
}