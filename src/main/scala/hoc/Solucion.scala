package hoc

trait Solucion extends Ordered[Solucion]{
  var fitness: Double = 0
  val valor: Array[Int]
  var factible: Boolean = false

  def vecino(): Solucion

  override def equals(solucion: Any): Boolean = {
    solucion match {
      case solucion: Solucion => solucion.isInstanceOf[Solucion] && (solucion.fitness,solucion.valor) == (this.fitness, this.valor)
      case _ => false
    }
  }

  override def compare(solucion: Solucion): Int = solucion.fitness compare this.fitness
}
