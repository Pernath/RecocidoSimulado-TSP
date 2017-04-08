package hoc
//import java.util.{ArrayList => List}

/** Trait para modelar un lote.
  * Posterior uso en graficación de soluciones (?)
  */
trait Lote{
  var soluciones: Int = 0
  val carga: Int

  /** Función que agrega elementos al lote
    * Por el momento no guarda nada.
    * 
    */
  def add(){
    if(carga < soluciones)
      return
    soluciones += 1
  }
}
