package hoc

/** Trait para la generación y evaluación de soluciones
 * También verifica las desconexiones y la factibilidad
 */
trait GeneradorVerificador{
  val func: FitFun
  val lista: List[Int] //tamaño de las soluciones

  def evalua(s: Array[Int]): Double = {
    return func.eval(s)    
  }

  /** Función que devuelve uno de los vecinos de una solución
    * 
    * @param s la solución de la que queremos un vecino
    * @return la solución vecina de s
    */
  def vecino(s: Array[Int], f: Double): Solucion


  /** Función que devuelve una solución aleatoria
    * 
    * @return la solucion aleatoria
    *
  def randomSol(): Solucion
    */
  /** Función que devuelve una solución igual a la instancia TSP
    * 
    * @return la solucion instancia
    */
  def instanceSol(): Solucion

  /** Función que determina si una solución es factible
    * 
    * @param s la solución a determinar su factibilidad
    * @return false si no es factible, true en otro caso
    */
  def factible(s:Solucion): Boolean = {
    return func.factible(s.valor)
  }

  /** Función que determina el número de desconexiones de una solucion
    * 
    * @param s la solución a determinar sus desconexiones
    * @return un entero positivo que representa las aristas inexistentes
    */
  def desconexiones(s: Solucion): Int = {
    return func.desconexiones(s.valor)
  }

}
