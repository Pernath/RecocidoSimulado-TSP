package autsp

/** 
  * @param size el tamaño del conjunto de ciudades
  * @param l la lista con los índices del subconjunto de ciudades 
  * (en orden)
  */
class Controlador(var lista: List[Int]){
  var tsp: TSPInstance = null

  def genMatriz(){
    val matriz =  Array.ofDim[Double](278,278)
    val conexion = new Conexion("org.sqlite.JDBC","jdbc:sqlite:hi.db")
    conexion.setResults("SELECT * FROM connections WHERE ")

    var tuple = conexion.getRowFromResults()
    var maxD = 0.0
    var avg = 0.0 //para agregar a la instancia
    while(tuple != null){
      var t = tuple._3      
      matriz(tuple._1)(tuple._2) = t
      matriz(tuple._2)(tuple._1) = t
      if(t > maxD && inside(lista,tuple._1) && inside(lista,tuple._1))
        maxD = t
      tuple = conexion.getRowFromResults()
    }
    tsp = new TSPInstance(matriz,maxD)
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
    var temperatura = new Temperatura(8.0,0.9)
    var lote = new Lote(49)
    val fun = new FuncionDeCosto(tsp)
    var genVer = new GenVer(fun, lista)
    var maxFail = new MaximoFallidos(49*49)
    var epsilon = 200
    var vZero = 0.5

    val autsp = new AceptacionPorUmbrales(temperatura, lote, genVer.randomSol, maxFail, epsilon, vZero, genVer)
     autsp.run
  }

}
