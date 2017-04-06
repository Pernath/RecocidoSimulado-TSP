package autsp

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.ResultSet

/** Para manejar la conexión con base de datos
  * 
  * @param driver el controlador del SMBD
  * @param url la ruta relativa del archivo de base de datos
  */
class Conexion(driver: String, url: String) {
  var conn: Connection = null
  var resultSet: ResultSet = null
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
    abre()
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
    cierra()
  }

  def cierra(){
    if (conn != null) {
      conn.close()
      println("Conexión finalizada")
    }
  }

  def setResults(q: String) {
    abre()
    val statement = conn.createStatement()
    resultSet = statement.executeQuery(q)
    //cierra()
  }

  def getRowFromResults(): (Int,Int,Double) = {
    if(!resultSet.next())
      return null
    var id1 = resultSet.getInt("id_city_1")
    var id2 = resultSet.getInt("id_city_2")
    var d = resultSet.getInt("distance")
    return (id1,id2,d)
  }
}
