package autsp

import hoc.Solucion

/** Un camino solucion del espacio de soluciones de TSP
  * 
  * @param largo el tamaño del camino
  * @param evaluacion del camino
  */
class Camino(val valor: Array[Int], val fitness: Double) extends Solucion{
}
