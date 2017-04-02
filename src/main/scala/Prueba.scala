import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import autsp._

object Prueba {  
  def main(args: Array[String]): Unit = {    
    println("Pequeña prueba...")
    val conexion = new Conexion("org.sqlite.JDBC","jdbc:sqlite:hi.db")
    conexion.abre()
    conexion.printAll()
    conexion.cierra()
  }
}
