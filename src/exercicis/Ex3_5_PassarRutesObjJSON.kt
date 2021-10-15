package exercicis

import org.json.JSONArray
import org.json.JSONObject
import java.io.*

fun main(){
    val fileIn = ObjectInputStream(FileInputStream("Rutes.obj"))
    val fileOut = FileWriter(File("RutesPrueba.JSON"))

    /*
    Objeto, array, objeto, array, objeto, objeto
    */

    //Objeto
    val arrel = JSONObject()

    //Array
    val rutes = JSONArray()
    arrel.put("rutes", rutes)

    try{
        while (true){
            val rutaObj = fileIn.readObject() as Ruta
            val ruta = JSONObject()
            //Los objetos tienen clave(nombre) y valor
            ruta.put("nom", rutaObj.nom)
            ruta.put("desnivell",rutaObj.desnivell)
            ruta.put("desnivellAcumulat",rutaObj.desnivellAcumulat)
            val llistaPunts = JSONArray()

            for (puntRuta in rutaObj.llistaDePunts){
                /*
                Los puntos son objetos que contienen miembros,
                estos son nombre, y coordenada, que tambien
                es un objeto que contiene dos miembros.
                Estos objetos punto son asignados a la lista mediante put.
                A su vez, la lista de puntos es un miembro de ruta,
                por lo que al asignarla a ruta le daremos una clave
                 */
                //Objeto punto
                val punt = JSONObject()

                //Miembro nombre
                val nomPunt = puntRuta.nom

                //Asignamos miembro al objeto punto
                punt.put("nom",nomPunt)

                //Asignamos punto a lista de puntos
                llistaPunts.put(punt)

                //Obejto coordenada, miembro de punto
                val coord = JSONObject()

                //Miembros del objeto coordenada
                coord.put("latitud",puntRuta.coord.latitud)
                coord.put("longitud",puntRuta.coord.longitud)

                //Asignamos coordenada a punto
                punt.put("coord",coord)

            }
            //Los array solo toman objetos

            //Asiganmos la ruta a la lista de rutas
            rutes.put(ruta)
            //Asignamos la lista de puntos a la ruta
            ruta.put("llistaDePunts",llistaPunts)
        }
    } catch (e: EOFException){
        fileOut.write(arrel.toString(4))
        fileOut.close()
        fileIn.close()
    }
}