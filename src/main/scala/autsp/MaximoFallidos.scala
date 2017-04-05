package autsp

import hoc.{CondicionDeTerminacion => Condicion}

class MaximoFallidos(val maximo: Int) extends Condicion{
  var nActual = 0

  def progress(){
    if(nActual == maximo)
      continua = false
    else
      nActual += 1
  }
}
