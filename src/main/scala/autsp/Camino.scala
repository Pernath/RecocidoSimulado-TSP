package autsp

import hoc.Solucion

/** Un camino solucion del espacio de soluciones de TSP
  * 
  * @param largo el tamaño del camino
  * @param evaluacion del camino
  */
class Camino(val valor: Array[Int]) extends Solucion{
  fitness = FuncionDeCosto.eval(this)
  factible = factibilidad()

  def factibilidad(): Boolean = {
    return true
  }
  
  def vecino(): Solucion = {
    return this
  }
}
