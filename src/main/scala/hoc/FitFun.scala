package hoc

/** Trait para la función de costo
  * 
  */
trait FitFun{

  /** Función para evaluar una solución
    * @param la solución a evaluar
    * @return el costo de la solución
    */
  def eval(s: Array[Int]): Double

  /** Función para decidir si un camino es factible
    * @param s la solución a verificar
    * @return true si es factible, false en caso contrario
    */
  def factible(s: Array[Int]): Boolean

  /** Función para calcular el número de desconexiones 
    * del camino
    * 
    * @param s a solución a verificar
    * @return el número de aristas inexistentes en el camino
    */
  def desconexiones(s: Array[Int]): Int
}
