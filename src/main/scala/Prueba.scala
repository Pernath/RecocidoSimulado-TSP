import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import autsp._

object Prueba {  
  def main(args: Array[String]): Unit = {    
    println("Pequeña prueba...")
    //val conexion = new Conexion("org.sqlite.JDBC","jdbc:sqlite:hi.db")
    //conexion.abre()
    //conexion.printAll()
    //conexion.cierra()
    var l = List(1,2,3,4)
    var j = l.slice(0,l.size)
    println(l)
    println(j)
    println(l.size)

    var c = new Controlador(l)
    println(c.inside(l,3))
  }
}
