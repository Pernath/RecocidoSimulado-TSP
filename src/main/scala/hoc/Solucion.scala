package hoc

trait Solucion extends Ordered[Solucion]{
  val fitness: Double
  val valor: Array[Int]

  override def equals(solucion: Any): Boolean = {
    solucion match {
      case solucion: Solucion => solucion.isInstanceOf[Solucion] && (solucion.fitness,solucion.valor) == (this.fitness, this.valor)
      case _ => false
    }
  }

  override def compare(solucion: Solucion): Int = solucion.fitness compare this.fitness
}
