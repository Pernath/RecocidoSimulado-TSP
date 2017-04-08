package hoc

/** Trait para manejar los valores de la temperatura
  * 
  */
trait Temperatura{
  var temperatura: Double
  val phi: Double

  /** Función para provocar algún cambio en la temperatura
    * 
    */
  def cambio()
}
