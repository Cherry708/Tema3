package exercicis

import org.json.JSONArray
import org.json.JSONObject
import org.w3c.dom.Element
import java.io.File
import java.io.FileWriter
import javax.xml.parsers.DocumentBuilderFactory

fun main(){

    /*
    Bucle For para los vehiculos y bucles for para los elementos de cada
    vehiculo que varian en numero, por ejemplo for de getElementsByTagName("extra"),
    es el mismo caso para las fotos, por la longitud de la lista devuelta por getElements
    realizamos la asignacion de un objeto.
     */

    //XML
    val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("cotxes.xml")
    val arrel = doc.documentElement  // apuntarà a l'element arrel
    val listaVehiculosXml = arrel.getElementsByTagName("vehiculo")


    val fileOut = FileWriter(File("cotxesPrueba.json"))

    //JSON
    val elemento = listaVehiculosXml.item(0) as Element


    val arrelJson = JSONObject()
    val oferta = JSONObject()

    arrelJson.put("oferta",oferta)

    val vehiculos = JSONArray()
    oferta.put("vehiculos",vehiculos)

    for (i in 0 until listaVehiculosXml.length) {
        val vehiculo = JSONObject()

        vehiculo.put("marca", elemento.getElementsByTagName("marca").item(0).getChildNodes().item(0).getNodeValue())

        val modelo = JSONObject()
        modelo.put("color", (elemento.getElementsByTagName("modelo").item(0) as Element).getAttribute("color"))
        modelo.put("nombe_modelo", elemento.getElementsByTagName("modelo").item(0).nodeValue)

        vehiculo.put("modelo", modelo)

        val motor = JSONObject()
        motor.put("combustible", "gasolina")
        motor.put("nombre_motor", "duratorc 1.4")

        vehiculo.put("motor", motor)

        vehiculo.put("matricula", "1234AAA")
        vehiculo.put("kilometros", "12500")
        vehiculo.put("precio_inicial", "12000")
        vehiculo.put("precio_oferta", "10000")

        val elementsExtra = elemento.getElementsByTagName("extra")
        val extra = JSONArray()

        for (i in 0 until elementsExtra.length) {
            val objetoExtra = JSONObject()
            //por aqui, recuperar atributo como arriba, linea 45
            objetoExtra.put("valor", elementsExtra.item(i))
            objetoExtra.put("nombre_extra", elemento.getElementsByTagName("extra").item(i).nodeValue)
            extra.put(objetoExtra)

            vehiculo.put("extra", extra)
        }

        val foto = JSONArray()
        foto.put("11325.jpg")
        foto.put("11326.jpg")

        vehiculo.put("foto", foto)

        vehiculos.put(vehiculo)
    }

    fileOut.write(arrelJson.toString(4))
    fileOut.close()

/*
    for (i in 0 until llista.length) {
        val el = llista.item(i) as Element
        println(el.getNodeName() + " " + (i + 1))
        println("Marca: " + el.getElementsByTagName("marca").item(0).getChildNodes().item(0).getNodeValue())
        println("Matrícula: " + el.getElementsByTagName("matricula").item(0).getFirstChild().getNodeValue())
        println("Motor: " + el.getElementsByTagName("motor").item(0).getTextContent())
        println("Combustible: " + el.getElementsByTagName("motor").item(0).getAttributes().item(0).getNodeValue())
        val m = el . getElementsByTagName ("motor").item(0) as Element
        println("Combustible: " + m.getAttribute("combustible"))
        println()
    }
    println(arrel.getTextContent())

 */
}