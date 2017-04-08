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
/*    for(i <- 0 to lista.size-1){
      println("\n")
      for(j <- 0 to lista.size-1){
        print(matriz(i)(j)+", ")
      }

    }*/
    //tsp = new TSPInstance(matriz,maxD,total/lista.size)
    tsp = new TSPInstance(matriz,maxD,total/edges)
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

  def exec(s: Int, t:Double, phi: Double, lot: Int, e: Double, v: Double, c: Double){
    //for(i <- 0 to 100){
    var temperatura = new Temperatura(t,phi)
    var lote = new Lote(lot)
    val fun = new FuncionDeCosto(tsp,c)
    //var genVer = new GenVer(3,3,fun, lista)
    var maxFail = new MaximoFallidos(lot*lot)
    var genVer:GenVer = null
    /*if(s == -1)
      genVer = new GenVer(i,i,fun,lista)
    else*/
      genVer = new GenVer(s,s,fun,lista)
    //var inicial = genVer.randomSol
    var inicial = genVer.instanceSol
    for(i <- 0 to 10)
      inicial = genVer.vecino(inicial.getValor)
    //println("INICIAL: "+inicial + " fitness: "+inicial.fitness)
    var autsp = new AceptacionPorUmbrales(temperatura, lote, inicial, maxFail, e, v, genVer)
    autsp.run
    val factible = genVer.factible(autsp.mejorS)
    println("Semilla: "+genVer.seedN)
    println("fitness: "+autsp.mejorS.fitness)
    println("Factibilidad: "+factible)
    println("Desconexiones: "+genVer.desconexiones(autsp.mejorS)+"\n")
    if(factible){
      println(autsp.mejorS)
      //return
    }
//    }
  }
}
