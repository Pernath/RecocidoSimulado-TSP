package autsp

import hoc._
import hoc.{Temperatura => Temp}
import hoc.{Lote => L}

class AceptacionPorUmbrales(var temperatura: Temp, var lote: L, var sActual: Solucion, val cTerminacion: CondicionDeTerminacion, val epsilon: Double, val vZero: Double, val genVer: GeneradorVerificador) extends RecocidoSimulado{

  def run(){
    var p: Double = 9999999999.0
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
    /*
    println("Mejor solucion: "+ mejorS + " fitness: "+mejorS.fitness)
     
    println("Factibilidad: "+genVer.factible(mejorS))
    println("Desconexiones: "+genVer.desconexiones(mejorS))
     */
  }
}
