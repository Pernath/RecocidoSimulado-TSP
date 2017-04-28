package hoc

/** Trait para modelar las soluciones del sistema
  * 
  * 
  */
trait Solucion extends Ordered[Solucion]{
  var fitness: Double
  var valor: Array[Int]

  /** Para evitar paso por referencia en las llamadas al valor,
    * se utiliza un objeto distinto con la misma información
    * del arreglo de enteros que representa el camino
    */
  def getValor(): Array[Int] = {
    return valor.clone
  }

  /** Para facilidad de lectura al momento de imprimir las soluciones
    * 
    */
  override def toString(): String = {
    var s: String = "["
    for(i <- 0 to valor.length-2)
      s += valor(i)+", "
    return s+valor(valor.length-1)+"]"
  }

  /** Al extender Ordered es necesario hacer override de equals()
    * 
    */
  override def equals(solucion: Any): Boolean = {
    solucion match {
      case solucion: Solucion => solucion.isInstanceOf[Solucion] && (solucion.fitness,solucion.valor) == (this.fitness, this.valor)
      case _ => false
    }
  }

  /** Al extender Ordered es necesario hacer override de compare()
    * Con la finalidad de comparar soluciones
    */
  override def compare(solucion: Solucion): Int = solucion.fitness compare this.fitness
}
