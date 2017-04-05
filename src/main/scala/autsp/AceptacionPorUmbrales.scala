package autsp

import hoc._
import hoc.{Temperatura => Temp}

class AceptacionPorUmbrales(var temperatura: Temp, var lote: Lote, var sActual: Solucion, val cTerminacion: CondicionDeTerminacion, val epsilon: Double, val vZero: Double) extends RecocidoSimulado{

  def run(){
    var p: Double = -1
    while(temperatura.temperatura > epsilon){
      var pprime:Double = 0
      while(Math.abs(p-pprime) > vZero){
        pprime = p
        var tuple = calculaLote()
        p = tuple._1
        sActual = tuple._2
      }
      temperatura.cambio()
    }
  }
}
