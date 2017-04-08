import org.scalatest.FlatSpec

class FirstSpec extends FlatSpec {
  // tests go here...
  /** Para verificar la conexion a la base de datos
    * 
    */
  "abre()" should "open database" in {
    val conexion = new Conexion("org.sqlite.JDBC","jdbc:sqlite:/hi.db")
    conexion.abre()
    assert(conexion.conn == null)
    conexion.cierra()
  }
}


