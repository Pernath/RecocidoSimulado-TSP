package autsp

import hoc.Solucion

/** Un camino solucion del espacio de soluciones de TSP
  * 
  * @param largo el tamaño del camino
  * @param evaluacion del camino
  */
class Camino(val valor: Array[Int], val fitfun: FuncionDeCosto) extends Solucion{
  factible = factibilidad()
  fitness = fitfun.eval(this)

  def factibilidad(): Boolean = {
    return true
  }
  
  def vecino(): Solucion = {
    return this
  }

}
