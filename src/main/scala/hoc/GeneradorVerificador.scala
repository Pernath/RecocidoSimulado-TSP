package hoc

trait GeneradorVerificador{
  val func: FitFun
  val lista: List[Int] //tamaño de las soluciones

  def evalua(s: Array[Int]): Double = {
    return func.eval(s)    
  }

  def vecino(s: Array[Int]): Solucion

  //debe salir ya evaluada
  def randomSol(): Solucion

  def factible(s:Solucion): Boolean = {
    return func.factible(s.valor)
  }

  def desconexiones(s: Solucion): Int = {
    return func.desconexiones(s.valor)
  }



}
