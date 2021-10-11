package exercicis

import javax.swing.*
import java.awt.*
import org.w3c.dom.Document
import org.w3c.dom.Element
import javax.xml.parsers.DocumentBuilderFactory

class Finestra : JFrame() {

    init {
        var doc: Document
        doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("Rutes.xml")
        val arrel = doc.documentElement
        //getElements devuelve una lista de elementos desde el seleccionado
        val llistaElementsRuta = arrel.getElementsByTagName("ruta")
        // sentències per a omplir doc

        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setTitle("Punts d'una ruta")
        setSize(400, 300)
        setLayout(BorderLayout())

        val panell1 = JPanel(FlowLayout())
        val panell2 = JPanel(BorderLayout())
        add(panell1, BorderLayout.NORTH)
        add(panell2, BorderLayout.CENTER)

        val llistaRutes = arrayListOf<String>()
        //Por cada elemento ruta de la lista de elementos ruta
        for (i in 0 until llistaElementsRuta.length) {
            //asignamos cada elemento ruta a una variable
            val elementRuta = llistaElementsRuta.item(i) as Element
            //añadimos a la lista de nombres el nombre de esta ruta mediante la variable
            //recuperamos el nombre de la ruta devolviendo el primer elemento de una lista con etiqueta nom
            llistaRutes.add(elementRuta.getElementsByTagName("nom").item(0).textContent)
        }
        // sentències per a omplir l'ArrayList anterior amb el nom de les rutes

        val combo = JComboBox(llistaRutes.toArray())
        panell1.add(combo)

        panell2.add(JLabel("Llista de punts de la ruta:"), BorderLayout.NORTH)
        val area = JTextArea()
        panell2.add(area)

        combo.addActionListener {
            //Recorremos cada ruta de la lista de rutas
            for (i in 0 until llistaRutes.size) {
                //Para el elemento seleccionado
                if (combo.selectedIndex == i) {
                    //Vaciamos la textArea para evitar acumulacion de concatenaciones
                    area.text = ""

                    //asignamos una ruta a elementoRuta
                    val elementRuta = llistaElementsRuta.item(i) as Element
                    //asignamos a listaElemenosPuntos la lista devuelta de etiquetas punts
                    val llistaElementsPunts = elementRuta.getElementsByTagName("punts")

                    //asigmos a elementoPunts el unico punto en la ruta item(0)
                    val elementPunts = llistaElementsPunts.item(0) as Element
                    //Obtenemos una lista de los puntos en ese punto
                    val llistaElementsPunt = elementPunts.getElementsByTagName("punt")

                    //Por cada elemento de la lista de punto en puntos
                    for (x in 0 until llistaElementsPunt.length) {
                        //obtenemos el punto x, x es la iteracion del bucle
                        val elementPunt = llistaElementsPunt.item(x) as Element

                        //obtenemos una lista con los elementos nombre,latitud y longitud
                        //de cada punto, su longitud sera 1, solo hay uno por cada punto
                        val llistaElementsNoms = elementPunt.getElementsByTagName("nom")

                        val llistaElementsLatitud = elementPunt.getElementsByTagName("latitud")

                        val llistaElementsLongitud = elementPunt.getElementsByTagName("longitud")

                        //asignamos al texto del area el contenido de los elementos de cada punto recorrido en x
                        area.text += llistaElementsNoms.item(0).textContent
                            .plus(
                                "(${llistaElementsLatitud.item(0).textContent}, " +
                                        "${llistaElementsLongitud.item(0).textContent})\n"
                            )
                    }
                }
            }
            // accions quan s'ha seleccionat un element del combobox,
            // i que han de consistir en omplir el JTextArea

        }
    }
}

fun main(args: Array<String>) {
    EventQueue.invokeLater {
        Finestra().isVisible = true
    }
}

