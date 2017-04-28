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
  val r = new Random(seedN) /** Inicialización del RNG con la semilla*/
  val s = new Random(seedI) /** Inicialización del RNG con la semilla*/

  def vecino(s: Array[Int], f: Double): Solucion = {
    var idx = r.nextInt(s.length)
    var idx2 = r.nextInt(s.length)
    while(idx == idx2)
      idx2 = r.nextInt(s.length)

    val mayor = if (idx > idx2) idx else idx2
    val menor = (idx+idx2) - mayor

    var distancia = f*(func.tsp.promedio*(s.length-1))
    if(mayor - menor > 1){
      distancia -= func.distancia(s(menor),s(menor+1))
      distancia -= func.distancia(s(mayor),s(mayor-1))

      distancia += func.distancia(s(mayor),s(menor+1))
      distancia += func.distancia(s(mayor-1),s(menor))

    }
    if(mayor < s.length - 1){
      distancia -= func.distancia(s(mayor),s(mayor+1))

      distancia += func.distancia(s(menor), s(mayor+1))
    }
    if(menor > 0){
      distancia -= func.distancia(s(menor),s(menor-1))

      distancia += func.distancia(s(menor-1),s(mayor))
    }
    var temp = s(idx)
    s(idx) = s(idx2)
    s(idx2) = temp
    //return new Camino(s,evalua(s))
    
    return new Camino(s,distancia/(func.tsp.promedio*(s.length-1)))
  }

  /*
  def randomSol(): Solucion = {
    var toTake = new ListBuffer[Int]()
    lista.copyToBuffer(toTake)
    var a = new Array[Int](toTake.size)
    for (i <- 0 to toTake.size-1)
      a(i) = toTake.remove(s.nextInt(toTake.size))    
    return new Camino(a,evalua(a))
  }*/

  def instanceSol(): Solucion = {
    var out = new Array[Int](lista.size)   
    for (i <- 0 to lista.size-1)
      out(i) = lista(i)
    return new Camino(out,evalua(out))
  }

}
