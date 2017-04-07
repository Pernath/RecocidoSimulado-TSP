package autsp

import hoc.FitFun
import hoc.Solucion

/** Función de costo
  *  
  * 
  */

class FuncionDeCosto(val tsp: TSPInstance, val constante: Double) extends FitFun{
  def eval(s: Array[Int]): Double = {
    var suma = 0.0
    for (i <- 0 to s.size-2)
      suma += distancia(s(i),s(i+1))
    //return suma/tsp.promedio*(s.size)
    return suma/(tsp.promedio*(s.size-1))
  }

  def distancia(id1: Int, id2: Int): Double = {
    var d = tsp.distancia(id1,id2)
    if (d > 0)
      return d
    return tsp.maxDistancias*constante
  }

  def factible(s: Array[Int]): Boolean = {
    for(i <- 0 to s.length-2)
      if(tsp.distancia(s(i),s(i+1)) <= 0)
        return false
    return true
  }

  def desconexiones(s: Array[Int]): Int = {
    var out = 0
    for(i <- 0 to s.length-2)
      if(tsp.distancia(s(i),s(i+1)) == 0)
        out += 1
    return out
  }
}
