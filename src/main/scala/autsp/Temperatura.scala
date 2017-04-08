package autsp

import hoc.{Temperatura => TTemp}

/** Clase para implementar el cambio de la temperatura
  * @param temperatura el valor inicial de la temperatura
  * @param phi el factor de cambio de la temperatura
  */
class Temperatura(var temperatura: Double, val phi: Double) extends TTemp{

  def cambio(){
    phi match{
      case p if p <= 0 | p >= 1  => println("φ no adecuada")
      case _ => temperatura *= phi
    }
  }





}
