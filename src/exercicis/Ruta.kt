package exercicis

import java.io.Serializable

class Ruta (var nom: String,
            var desnivell: Int,
            var desnivellAcumulat: Int,
            var llistaDePunts: MutableList<PuntGeo>): Serializable {

    companion object {
        private const val serialVersionUID: Long = 1
    }

    fun addPunt(p: PuntGeo){
        llistaDePunts.add(p)
    }

    fun getPunt(i: Int): PuntGeo{
        return llistaDePunts.get(i)
    }

    fun getPuntNom(i: Int): String {
        return llistaDePunts.get(i).nom
    }

    fun getPuntLatitud(i: Int): Double {
        return llistaDePunts.get(i).coord.latitud
    }

    fun getPuntLongitud(i: Int): Double {
        return llistaDePunts.get(i).coord.longitud
    }

    fun size(): Int {
        return llistaDePunts.size
    }

    fun mostrarRuta():String {
        val ruta = "Ruta: $nom\nDesnivell: $desnivell\nDesnivell acumulat: $desnivellAcumulat\n" +
                "Te ${size()} punts\n"
        var punts = ""
        for (i in 0..llistaDePunts.size-1){
            punts += "Punt ${i+1}: ${llistaDePunts.get(i).nom} (${getPuntLatitud(i)}, ${getPuntLongitud(i)})\n"
        }
        return ruta.plus(punts)
        // Aquest és el mètode que heu d'implementar vosaltres
    }
}

class PuntGeo(
    var nom:String,
    var coord:Coordenades
):Serializable

class Coordenades (
    var latitud:Double,
    var longitud:Double
):Serializable

