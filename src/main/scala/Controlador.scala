import autsp._

/** Clase para ejecutar el sistema de Recocido Simulado
  * @param size el tamaño del conjunto de ciudades
  * @param l la lista con los índices del subconjunto de ciudades 
  * (en orden)
  */
class Controlador(var lista: List[Int]){
  var tsp: TSPInstance = genInstance() //la instancia de tsp que generaremos

  /** Función para generar la instancia de TSP 
    * Que contiene:
    * Matriz de adyacencias
    * Calculo de máxima distancia y distancia promedio
    * @return la instancia de TSP.
    */
  def genInstance(): TSPInstance = {
    val matriz =  Array.ofDim[Double](278,278)
    val conexion = new Conexion("org.sqlite.JDBC","jdbc:sqlite:hi.db")
    conexion.setResults("SELECT * FROM connections")
    var maxD = 0.0
    var total = 0.0 //distancia total
    var edges = 0
    var tuple = conexion.getRowFromResults
    while(tuple != null){
      var t = tuple._3      
      matriz(tuple._1)(tuple._2) = t
      matriz(tuple._2)(tuple._1) = t
      if(inside(lista,tuple._1) && inside(lista,tuple._2)){
        if(maxD < t)
          maxD = t
        total += t
        edges += 1 
      }      
      tuple = conexion.getRowFromResults()
    }
    conexion.cierra()
    println("MAXIMA DISTANCIA: "+maxD)
    println("AVG: "+total/edges)
    return new TSPInstance(matriz,maxD,total/edges)
  }


  /** Función para decidir si algún índice de ciudad se encuentra 
    * en la instancia de TSP que utilizaremos.
    * 
    */
  def inside(l: List[Int],i: Int): Boolean = {
    if(l.isEmpty)
      return false
    if(l.size == 1)
      return i == l(0)
    var idx = l.size/2
    var j = l(idx)
    if (i == j)
      return true
    else if(i < j)
      return inside(l.slice(0,idx),i)
    else
      return inside(l.slice(idx+1,l.size),i)
  }

  /** Función de ejecución de la Aceptación por umbrales
    * 
    * @param s la semilla del RNG
    * @param t la temperatura inicial del sistema
    * @param phi el factor de disminución de temperatura
    * @param lot el tamaño del lote a utilizar
    * @param e valor cota inferior de la temperatura
    * @param v valor de equilibrio térmico 
    * @param c constante de la función de costo
    */
  def exec(s: Int, t:Double, phi: Double, lot: Int, e: Double, v: Double, c: Double){
    var temperatura = new Temperatura(t,phi)
    var lote = new Lote(lot)
    val fun = new FuncionDeCosto(tsp,c)
    var maxFail = new MaximoFallidos(lot*lot)
    var genVer:GenVer = new GenVer(s,s,fun,lista)
    var inicial = genVer.instanceSol
    //algo así como un shuffle
    for(i <- 0 to 10)
      inicial = genVer.vecino(inicial.getValor)
    var autsp = new AceptacionPorUmbrales(temperatura, lote, inicial, maxFail, e, v, genVer)
    autsp.run
    val factible = genVer.factible(autsp.mejorS)
    println("Semilla: "+genVer.seedN)
    println("fitness: "+autsp.mejorS.fitness)
    println("Factibilidad: "+factible)
    println("Desconexiones: "+genVer.desconexiones(autsp.mejorS)+"\n")
    if(factible){
      println(autsp.mejorS)
    }
  }
}
