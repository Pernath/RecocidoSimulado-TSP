import java.io.{FileNotFoundException, IOException}
import scala.io.Source
import autsp._

object Prueba {
  val help = "Modo de empleo: <run> <archivo>"
  val bienvenida = "\n\n| Heurísticas de optimización combinatoria  |\n|\t\tProyecto 1\t\t    |\n|\t     Semestre 2017-2\t\t    |\n|\tFacultad de Ciencias UNAM\t    |\n| Autor: Carlos Gerardo Acosta Hernández    |\n\n"
  val fileHelp = ""
  var tupleConf: (List[Int], List[Double]) = null

  def conf(filename: String) {
    try{
      val file = Source.fromFile(filename)
      var tuple = pars(file)
      tupleConf = tuple
    }
    catch{
      case ex: FileNotFoundException =>
        println("No se encuentra el archivo de configuración "+filename)
      case iox: IOException =>
        println("No se puede leer el archivo "+filename)
      case df: Exception =>
        println("Error en el formato del archivo.")
    }
  }

  def pars(file: Source): (List[Int], List[Double]) = {
    val iter = file.getLines()
    val tsp = iter.next().split(",").map(_.toInt).toList
    val params = iter.next().split(",").map(_.toDouble).toList
    return (tsp,params)
  }

  def main(args: Array[String]): Unit = {      

    if(args.length < 1){
      println(bienvenida+help)
      return
    }   

    conf(args(0))//establecemos los datos del archivo de config

    if(tupleConf == null || tupleConf._2.size < 7){
      println(tupleConf == null)
      println("Error en el archivo de configuración."+fileHelp)
      return
    }
    val cont = new Controlador(tupleConf._1, args.length > 1 && args(1) == "w")    
    var l = tupleConf._2
    var s = l(0).toInt
    var t = l(1)
    var phi = l(2)
    var lote = l(3).toInt
    var e = l(4)
    var v = l(5)
    var c = l(6)
    cont.exec(s,t,phi,lote,e,v,c)    
  }
}
