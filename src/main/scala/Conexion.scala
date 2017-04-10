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
  var conn: Connection = _
  var resultSet: ResultSet = _


  /** Función para establecer la conexión con la base
    * de datos
    */
  def abre(){
    Class.forName(driver)
    try{
      conn = DriverManager.getConnection(url)
    } catch {
      case e: SQLException =>
        println(e.getMessage)
    }
  }

  /** Función que imprime los nombres de todas las entradas 
    * en la tabla de ciudades
    * 
    */
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

  /** Función para cerrar la conexión con la base de datos
    * 
    */
  def cierra(){
    if (conn != null) {
      conn.close()
      println("Conexión finalizada")
    }
  }

  /** Función para establecer el resultSet dada una consulta en cadena
    * @param q la cadena que contiene la consulta
    * 
    */
  def setResults(q: String) {
    abre()
    try{
      print("Abriendo conexión con "+url+"...")
      val statement = conn.createStatement()
      resultSet = statement.executeQuery(q)
      print(" Éxito.\n")
    } catch {
      case e: SQLException =>
        print(" Error.\n")
        println(e.getMessage)
        return
    }
  }

  /** Función para obtener una 3-tupla con los id's de dos ciudades
    * y la distancia entre ellas. Suponemos que el resultSet tiene
    * los resultados de la consulta sobre la tabla de conexiones
    * @return una tupla con dos id's y un valor doble de distancia
    */
  def getRowFromResults(): (Int,Int,Double) = {
    if(!resultSet.next())
      return null
    var id1 = resultSet.getInt("id_city_1")
    var id2 = resultSet.getInt("id_city_2")
    var d = resultSet.getInt("distance")
    return (id1,id2,d)
  }
}
