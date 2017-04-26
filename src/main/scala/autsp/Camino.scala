package autsp

import hoc.Solucion

/** Un camino solucion del espacio de soluciones de TSP
  * 
  * @param largo el tamaño del camino
  * @param evaluacion del camino
  */
class Camino(var valor: Array[Int], var fitness: Double, var distancia: Double) extends Solucion{
}
