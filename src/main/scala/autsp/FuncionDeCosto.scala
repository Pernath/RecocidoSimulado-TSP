package autsp

import hoc.FitFun
import hoc.Solucion

/** Función de costo
  *  
  * 
  */

class FuncionDeCosto(val tsp: TSPInstance) extends FitFun{
  def eval(s: Solucion): Double = {
    return 0.0
  }
}
