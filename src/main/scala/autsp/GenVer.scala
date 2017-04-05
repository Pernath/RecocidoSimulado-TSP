package autsp
import hoc.{GeneradorVerificador => GV}
import hoc.Solucion
import hoc.FitFun

class GenVer(val func: FitFun, val lista:List[Int]) extends GV{
  def vecino(s: Array[Int]): Solucion = {
    var f = evalua(s)
    return new Camino(s,f)
  }

  //debe salir ya evaluada
  def randomSol(): Solucion = {
    return null
  }

}
