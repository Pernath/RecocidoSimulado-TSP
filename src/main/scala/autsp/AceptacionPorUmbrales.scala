
package autsp

import hoc._
import hoc.{Temperatura => Temp}
import hoc.{Lote => L}
import java.io._

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
  /*
  var n = 1 /**Conteo de aceptados*/
  var execFile:File = null
  var bw: BufferedWriter = null
  var write = false /** Por defecto no escribiremos la corrida*/
   */

  def calculaLote(): Double ={
    var c = 0
    var r = 0.0
    while(c < lote.carga) {
      var sVecina: Solucion = genVer.vecino(sActual.getValor,sActual.distancia)
      if(sVecina.fitness<= (sActual.fitness + temperatura.temperatura)
        && cTerminacion.continua){
        sActual = sVecina

        /*con fines de documentación
        if(write)
          //bw.write(n.toString +" "+sActual.fitness+"\n")
          bw.write("E: "+sActual.fitness+"\n")
         n += 1 //fin*/
        c += 1
        r += sVecina.fitness
        if(sActual.fitness < mejorS.fitness){
          mejorS.valor = sActual.getValor
          mejorS.fitness = sActual.fitness          
          /*
          if(!write)
            println("Mejora evaluación: "+sActual.fitness)
           */
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
        pprime = p
        p = calculaLote()
      }
      temperatura.cambio()
    }
    /*
    if(write)
      bw.close()
     */
  }

  /*
  def init(){
    if(write){
      execFile = new File("doc/graficas/gnuplot/exec.txt")
      bw = new BufferedWriter(new FileWriter(execFile))
    }
  }
   */
}
