package hoc

/** Trait para modelar una condición de terminación dado un valor
  * de continuación y una función que decide si se cambia dicho valor
  * 
  */
trait CondicionDeTerminacion{
  var continua: Boolean = true //por omision permitimos que continúe

  def progress()
}
