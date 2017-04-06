package autsp
import hoc.{GeneradorVerificador => GV}
import hoc.Solucion
import hoc.FitFun
import scala.util.Random
import scala.collection.mutable.ListBuffer

class GenVer(val seedN: Int, val seedI: Int, val func: FitFun, val lista:List[Int]) extends GV{
  val r = new Random(seedI)
  val s = new Random(seedN)

  def vecino(s: Array[Int]): Solucion = {
    var idx = r.nextInt(s.length)
    var temp = s(idx)
    if (idx == s.length-1){
      s(idx) = s(0)
      s(0) = temp
    }
    else {
      s(idx) = s(idx+1)
      s(idx+1) = temp
    }      
    return new Camino(s,evalua(s))
  }

  //debe salir ya evaluada
  def randomSol(): Solucion = {
    var toTake = new ListBuffer[Int]()
    lista.copyToBuffer(toTake)
    var a = new Array[Int](toTake.size)
    for (i <- 0 to a.length-1)
      a(i) = toTake.remove(s.nextInt(toTake.size))    
    return new Camino(a,evalua(a))
  }

}
