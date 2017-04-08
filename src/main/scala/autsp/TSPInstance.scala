package autsp

/** Clase para manejar una instancia de TSP
  * @param adyacencias 
  * 
  */
class TSPInstance(val adyacencias: Array[Array[Double]], val maxDistancias: Double, val promedio: Double){

  /** Función para obtener la distancia entre dos ciudades,
    * dada la matriz de adyacencias
    * @param id1 el id de la ciudad 1 en la pareja
    * @param id2 el id de la ciudad 2 en la pareja
    * @return la distancia en precisión doble
    */
  def distancia(id1: Int, id2: Int): Double = {
    return adyacencias(id1)(id2)
  }
}
