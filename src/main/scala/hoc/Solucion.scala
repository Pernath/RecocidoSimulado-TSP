package hoc

trait Solucion extends Ordered[Solucion]{
  var fitness: Double
  var valor: Array[Int]

  def getValor(): Array[Int] = {
    return valor.clone
  }

  override def toString(): String = {
    var s: String = "["
    for(i <- 0 to valor.length-2)
      s += valor(i)+", "
    return s+valor(valor.length-1)+"]"
  }

  override def equals(solucion: Any): Boolean = {
    solucion match {
      case solucion: Solucion => solucion.isInstanceOf[Solucion] && (solucion.fitness,solucion.valor) == (this.fitness, this.valor)
      case _ => false
    }
  }

  override def compare(solucion: Solucion): Int = solucion.fitness compare this.fitness
}
