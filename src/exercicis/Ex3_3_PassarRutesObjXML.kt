package exercicis

import java.io.EOFException
import java.io.FileInputStream
import java.io.ObjectInputStream
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

fun main(){
    val fileInput = ObjectInputStream(FileInputStream ("Rutes.obj"))

    val doc = DocumentBuilderFactory.newInstance ().newDocumentBuilder().newDocument()
    val arrel = doc.createElement ("rutes")
    doc.appendChild(arrel)

    try {
        while (true) {

            val ruta = fileInput.readObject () as Ruta
            val elementRuta = doc.createElement ("ruta")

            val nom = doc.createElement("nom")
            nom.appendChild(doc.createTextNode(ruta.nom))
            elementRuta.appendChild(nom)

            val desnivell = doc.createElement ("desnivell")
            desnivell.appendChild(doc.createTextNode(ruta.desnivell.toString()  )) // forma llarga: afegim un fill que és un node de text
            elementRuta.appendChild(desnivell)

            val desnivellAcumulat = doc.createElement("desnivellAcumulat")
            desnivellAcumulat.appendChild(doc.createTextNode(ruta.desnivellAcumulat.toString())) // forma curta: amb setTextContent() li posem contingut
            elementRuta.appendChild(desnivellAcumulat)

            val elementPunts = doc.createElement("punts")
            elementRuta.appendChild(elementPunts)
            for (punt in ruta.llistaDePunts){
                val elementPunt = doc.createElement("punt")
                elementPunt.setAttribute("num",(ruta.llistaDePunts.indexOf(punt)+1).toString())

                val nomPunt = doc.createElement("nom")
                nomPunt.appendChild(doc.createTextNode(punt.nom))
                elementPunt.appendChild(nomPunt)

                val latitud = doc.createElement("latitud")
                latitud.appendChild(doc.createTextNode(punt.coord.latitud.toString()))
                elementPunt.appendChild(latitud)

                val longitud = doc.createElement("longitud")
                longitud.appendChild(doc.createTextNode(punt.coord.longitud.toString()))
                elementPunt.appendChild(longitud)

                elementPunts.appendChild(elementPunt)
            }

            arrel.appendChild(elementRuta)
        }

    } catch (eof: EOFException) {
        fileInput.close();
    }
    val trans = TransformerFactory.newInstance().newTransformer()
    trans.setOutputProperty(OutputKeys.INDENT, "yes")
    trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2")

    trans.transform(DOMSource(doc), StreamResult("Rutes.xml"))
}