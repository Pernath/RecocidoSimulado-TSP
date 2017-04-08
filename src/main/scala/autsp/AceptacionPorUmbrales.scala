
package autsp

import hoc._
import hoc.{Temperatura => Temp}
import hoc.{Lote => L}

/** Clase para modelar la implementación del recocido simulado
  * @param temperatura un objeto de temperatura con la inicial
  * @param lote un objeto de lote con su carga definida
  * @param sActual la solución aceptada hasta el momento
  * @param cTerminacion la condición de terminación
  * @param epsilon el valor mínimo de la temperatura
  * @param vZero el cero virtual para el equilibrio térmico
  * @param genVer un GeneradorVerificador con la instancia TSP 
  * y la función de costo
  * 
  */
class AceptacionPorUmbrales(var temperatura: Temp, var lote: L, var sActual: Solucion, val cTerminacion: CondicionDeTerminacion, val epsilon: Double, val vZero: Double, val genVer: GeneradorVerificador) extends RecocidoSimulado{
  
  def calculaLote(): Double ={
    var c = 0
    var r = 0.0
    while(c < lote.carga && cTerminacion.continua) {
      var sVecina: Solucion = genVer.vecino(sActual.getValor)
      if(sVecina.fitness<= (sActual.fitness + temperatura.temperatura)){
        //lote.add()
        sActual = sVecina
        c += 1
        r += sVecina.fitness
        if(sActual.fitness < mejorS.fitness){
          mejorS.valor = sActual.getValor
          mejorS.fitness = genVer.evalua(mejorS.valor)
        }
      }else{
        cTerminacion.progress()
      }
    }
    return r/lote.carga
  }

  def run(){
    var p: Double = Double.MaxValue
    while(temperatura.temperatura > epsilon){
      var pprime:Double = 0      
      while(Math.abs(p-pprime) > vZero){
        println(mejorS.fitness)
        pprime = p
        p = calculaLote()
      }
      temperatura.cambio()
    }
  }
}
