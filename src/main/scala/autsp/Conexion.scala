package autsp

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

/** Para manejar la conexión con base de datos
  * 
  * @param driver el controlador del SMBD
  * @param url la ruta relativa del archivo de base de datos
  */
class Conexion(driver: String, url: String) {
  var conn: Connection = null
  //  val driver: String = "org.sqlite.JDBC"
  //val url: String = "jdbc:sqlite:hi.db"

  def abre(){
    Class.forName(driver)
    try{
      conn = DriverManager.getConnection(url)
    } catch {
      case e: SQLException => println(e.getMessage)
    }
  }

  def printAll(){
    if (conn == null){
      println("La conexión no ha sido establecida")
      return
    }
    val statement = conn.createStatement()
    val resultSet = statement.executeQuery("SELECT name FROM cities")
    var counter = 0
    while ( resultSet.next() ) {
      val name = resultSet.getString("name")
      println("City name "+counter+": " + name)
      counter += 1
    }
  }

  def cierra(){
    if (conn != null) {
      conn.close()
      println("Conexión finalizada")
    }
  }

}
