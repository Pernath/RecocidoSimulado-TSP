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
    val l = List(1, 5, 9, 12, 16, 22, 23, 29, 30, 31, 39, 48, 52, 56, 58, 62, 65, 66, 70, 75, 80, 84, 86, 90, 92, 94, 95, 101, 107, 117, 119, 122, 133, 135, 143, 144, 146, 147, 150, 158, 159, 160, 166, 167, 176, 178, 179, 185, 186, 188, 190, 191, 194, 198, 200, 203, 207, 209, 213, 215, 216, 220, 221, 224, 227, 232, 233, 235, 238, 241, 244, 248, 250, 254, 264, 266, 274, 276)
    //val l = List(1,2,7,14,26,27,29,31,33)
    val cont = new Controlador(l)
    cont.genInstance()
    var s = args(0).toInt
    var t = args(1).toDouble
    var phi = args(2).toDouble
    var lote = args(3).toInt
    var e = args(4).toDouble
    var v = args(5).toDouble
    var c = args(6).toDouble
    cont.exec(s,t,phi,lote,e,v,c)
  }
}
