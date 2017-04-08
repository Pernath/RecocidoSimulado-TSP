package autsp
import hoc.{GeneradorVerificador => GV}
import hoc.Solucion
import hoc.FitFun
import scala.util.Random
import scala.collection.mutable.ListBuffer

/** Clase para implementar el trait GeneradorVerificador
  * 
  * @param seedN la semilla para el PRNG de la función vecino
  * @param seedI la semilla para el PRNG de la solucion aleatoria
  * @param func la función de costo
  * @param lista la lista de ciudades de la instancia de TSP
  */
class GenVer(val seedN: Int, val seedI: Int, val func: FitFun, val lista:List[Int]) extends GV{
  //Inicialización de los PRNG
  val r = new Random(seedN)
  val s = new Random(seedI)


  def vecino(s: Array[Int]): Solucion = {
    var idx = r.nextInt(s.length)
    var idx2 = r.nextInt(s.length)
    while(idx == idx2)
      idx2 = r.nextInt(s.length)
    var temp = s(idx)
    s(idx) = s(idx2)
    s(idx2) = temp
    return new Camino(s,evalua(s))
  }



  def randomSol(): Solucion = {
    var toTake = new ListBuffer[Int]()
    lista.copyToBuffer(toTake)
    var a = new Array[Int](toTake.size)
    for (i <- 0 to toTake.size-1)
      a(i) = toTake.remove(s.nextInt(toTake.size))    
    return new Camino(a,evalua(a))
  }

  def instanceSol(): Solucion = {
    var out = new Array[Int](lista.size)   
    for (i <- 0 to lista.size-1)
      out(i) = lista(i)
    return new Camino(out,evalua(out))
  }

}
