package autsp

import hoc.{Temperatura => TTemp}

class Temperatura(var temperatura: Double, val phi: Double) extends TTemp{

  def cambio(){
    phi match{
      case p if p <= 0 | p >= 1  => println("φ no adecuada")
      case _ => set(temperatura*phi)
    }
  }





}
