package autsp

/** 
  * @param size el tamaño del conjunto de ciudades
  * @param l la lista con los índices del subconjunto de ciudades
  */
class Controlador(var l: List[Int]){
  var tsp: TSPInstance = null

  def genMatriz(){
    val matriz =  Array.ofDim[Double](277,277)
    val conexion = new Conexion("org.sqlite.JDBC","jdbc:sqlite:hi.db")
    conexion.setResults("SELECT * FROM connections")

    var tuple = conexion.getRowFromResults()
    var maxD = 0.0
    while(tuple != null){
      var t = tuple._3      
      matriz(tuple._1)(tuple._2) = t
      if(t > maxD)
        maxD = t
      tuple = conexion.getRowFromResults()
    }
    tsp = new TSPInstance(matriz,maxD)    
  }

  def randomSolution(){
    //generar solucion random
  }

  def exec(){

  }

}
