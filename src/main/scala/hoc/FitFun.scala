package hoc

trait FitFun{ 
  def eval(s: Array[Int]): Double
  def factible(s: Array[Int]): Boolean
  def desconexiones(s: Array[Int]): Int
}
