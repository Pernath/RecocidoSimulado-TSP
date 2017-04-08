package autsp

import hoc.{CondicionDeTerminacion => Condicion}

/** Clase para implementar la condición de terminación
  * @param maximo el máximo numero de soluciones fallidas permitidas
  * en un lote.
  */
class MaximoFallidos(val maximo: Int) extends Condicion{
  var nActual = 0 //inicialización en 0 

  /** Función para establecer dar un paso hacia la condición
    * 
    */
  def progress(){
    if(nActual >= maximo)
      continua = false
    else
      nActual += 1
  }
}
