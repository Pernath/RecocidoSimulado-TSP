package hoc

/** Trait (interfaz simil) para modelar el recocido simulado
  * 
  */
trait RecocidoSimulado{
  var temperatura: Temperatura 
  var lote: Lote
  var sActual: Solucion
  var mejorS: Solucion = genVer.instanceSol
  val cTerminacion: CondicionDeTerminacion
  val epsilon: Double
  val vZero: Double
  val genVer: GeneradorVerificador


  /** Método para calcular el lote en una ejecución del Recocido
    * @return una tupla con la solución actual y el promedio de las 
    * soluciones aceptadas
    */
  def calculaLote(): Double

  /** Método para realizar una ejecución del Recocido Simulado
    * 
    */
  def run()
  
}
