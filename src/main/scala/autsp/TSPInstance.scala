package autsp

/** Clase para manejar una instancia de TSP
  * 
  * 
  */
class TSPInstance(val adyacencias: Array[Array[Double]], val maxDistancias: Double, val promedio: Double){
  def distancia(id1: Int, id2: Int): Double = {
    return adyacencias(id1)(id2)
  }
}
