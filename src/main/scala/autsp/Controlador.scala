package autsp

/** 
  * @param size el tamaño del conjunto de ciudades
  * @param l la lista con los índices del subconjunto de ciudades 
  * (en orden)
  */
class Controlador(var lista: List[Int]){
  var tsp: TSPInstance = null

  def genInstance(){
    val matriz =  Array.ofDim[Double](278,278)
    val conexion = new Conexion("org.sqlite.JDBC","jdbc:sqlite:hi.db")
    conexion.setResults("SELECT * FROM connections")

    var maxD = 0.0
    var total = 0.0 //para agregar a la instancia
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
    tsp = new TSPInstance(matriz,maxD,total/lista.size)
  }

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

  def exec(){
    var temperatura = new Temperatura(1000000,0.1)
    var lote = new Lote(500)
    val fun = new FuncionDeCosto(tsp,10)
    var genVer = new GenVer(12,23,fun, lista)
    var maxFail = new MaximoFallidos(1000)
    var epsilon = 0.2
    var vZero = 0.1

    val inicial = genVer.randomSol
    println("INICIAL: "+inicial + " fitness: "+inicial.fitness)
    val autsp = new AceptacionPorUmbrales(temperatura, lote, inicial, maxFail, epsilon, vZero, genVer)
    autsp.run

  }

}
