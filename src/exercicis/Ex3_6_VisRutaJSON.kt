package exercicis



import javax.swing.*
import java.awt.*
import com.squareup.moshi.Moshi
import org.w3c.dom.Element
import java.io.File

class FinestraJSON : JFrame() {

    init {
        var llistaRutes = ArrayList<Ruta>()

        val jsonFile = File("Rutes.json").readText()
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(Rutes::class.java)
        val fileRutes = adapter.fromJson(jsonFile)

        for (ruta in fileRutes.rutes){
            llistaRutes.add(ruta)
        }
        // sentències per a omplir llistaRutes

        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setTitle("JSON: Punts d'una ruta")
        setSize(400, 300)
        setLayout(BorderLayout())

        val panell1 = JPanel(FlowLayout())
        val panell2 = JPanel(BorderLayout())
        add(panell1, BorderLayout.NORTH)
        add(panell2, BorderLayout.CENTER)

        var nomsLlistaRutes = arrayListOf<String>()
        for (ruta in llistaRutes){
            nomsLlistaRutes.add(ruta.nom)
        }
        // sentències per a omplir l'ArrayList anterior amb el nom de les rutes

        val combo = JComboBox(nomsLlistaRutes.toArray())
        panell1.add(combo)

        panell2.add(JLabel("Llista de punts de la ruta:"), BorderLayout.NORTH)
        val area = JTextArea()
        panell2.add(area)

        combo.addActionListener {
            for (i in 0 until llistaRutes.size) {
                //Para el elemento seleccionado
                if (combo.selectedIndex == i) {
                    //Vaciamos la textArea para evitar acumulacion de concatenaciones
                    area.text = ""

                    val llistaNomPunt = ArrayList<String>()
                    val llistaLatituds = ArrayList<Double>()
                    val llistaLongituds = ArrayList<Double>()
                    for (punt in llistaRutes.get(i).llistaDePunts){
                        llistaNomPunt.add(punt.nom)
                        llistaLatituds.add(punt.coord.latitud)
                        llistaLongituds.add(punt.coord.longitud)
                    }
                    for (n in 0 until llistaNomPunt.size){
                        area.text += llistaNomPunt.get(n)
                            .plus(" (${llistaLatituds.get(n)}")
                            .plus(", ${llistaLongituds.get(n)})\n")
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
        FinestraJSON().isVisible = true
    }
}

