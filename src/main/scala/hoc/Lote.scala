package hoc

import java.util.{ArrayList => List}
trait Lote{
  var soluciones: List[Solucion]
  val carga: Int

  def add(s: Solucion){
    if(carga < soluciones.size())
      return
    soluciones.add(s)
  }

  def ultima(): Solucion = {
    return soluciones.get(soluciones.size()-1)
  }

}
