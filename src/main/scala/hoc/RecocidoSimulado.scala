package hoc

trait RecocidoSimulado{
  var temperatura: Temperatura
  var lote: Lote
  var sActual: Solucion
  var mejorS: Solucion = genVer.randomSol
  val cTerminacion: CondicionDeTerminacion
  val epsilon: Double
  val vZero: Double
  val genVer: GeneradorVerificador


  def calculaLote(): (Double, Solucion) ={

    var c = 0
    var r = 0.0
    while(c < lote.carga && cTerminacion.continua) {
      var sVecina: Solucion = genVer.vecino(sActual.getValor)

      if(sVecina.fitness <= sActual.fitness + temperatura.temperatura){
        lote.add()
        sActual = sVecina
        if(sActual.fitness < mejorS.fitness){
          mejorS.valor = sActual.getValor
          mejorS.fitness = genVer.evalua(mejorS.valor)
        }
        c += 1
        r += sVecina.fitness
      } else
          cTerminacion.progress()
    }
    return (r/lote.soluciones,sActual)
  }

  def run()
  
}
