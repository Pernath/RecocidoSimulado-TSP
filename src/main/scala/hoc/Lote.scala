package hoc

import java.util.{ArrayList => List}
trait Lote{
  var soluciones: Int = 0
  val carga: Int

  def add(){
    if(carga < soluciones)
      return
    soluciones += 1
  }
}
