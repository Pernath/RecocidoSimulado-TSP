package hoc

trait RecocidoSimulado{
  var temperatura: Temperatura
  var lote: Lote
  var sActual: Solucion
  val cTerminacion: CondicionDeTerminacion
  val epsilon: Double
  val vZero: Double


  def calculaLote(): (Double, Solucion) ={
//  def calculaLote(){
    var c = 0
    var r = 0.0
    while(c < lote.carga && cTerminacion.continua) {
      var sVecina: Solucion = sActual.vecino()
      if(sVecina <= sActual){
        lote.add(sActual)
        sActual = sVecina
        c += 1
        r += sVecina.fitness
      } else
          cTerminacion.progress()
    }
    return (r/lote.soluciones.size(),lote.ultima())
  }

  def run()
  
}
