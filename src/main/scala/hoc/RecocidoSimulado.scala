package hoc

trait RecocidoSimulado{
  var temperatura: Temperatura
  var lote: Lote
  var sActual: Solucion
  val cTerminacion: CondicionDeTerminacion
  val epsilon: Double
  val vZero: Double
  val genVer: GeneradorVerificador


  def calculaLote(): (Double, Solucion) ={
//  def calculaLote(){
    var c = 0
    var r = 0.0
    while(c < lote.carga && cTerminacion.continua) {
      var sVecina: Solucion = genVer.vecino(sActual.getValor)
      //println("Actual: " + sActual.fitness)
      //println("Vecina: " + sVecina.fitness)

      if(sVecina.fitness <= sActual.fitness){
        println("changing")
        lote.add()
        sActual = sVecina
        c += 1
        r += sVecina.fitness
        /*println("Solución actual: "+sActual)
        println("Factibilidad: "+genVer.factible(sActual))*/
      } else
          cTerminacion.progress()
    }
    return (r/lote.soluciones,sActual)
  }

  def run()
  
}
