// Solucion mas simple
var scores = List(10,5,20,20,4,5,2,25,1) // Lista de tipo Int
var min = scores(0) // Record minimo se le asigna el valor del indice 0 de la lista scores
var max = scores(0) // Record Maximo se le asigna el valor del indice 0 de la lista scores
var minCount = 0 // Contador de records minimos
var maxCount = 0 // Contador de records maximos

for(i <- Range(0,scores.length)) {
    if (scores(i) < min) { // 
        min = scores(i) // Se le asigna el valor de scores(i) como el minimo record
        minCount = minCount + 1 // Se incremneta el contador
    } 
    if(scores(i) > max){  
        max = scores(i) // Se le asigna el valor de scores(i) como el maximo record
        maxCount = maxCount + 1 // Se incremneta el contador
    }
}
println(s"${maxCount} ${minCount}") // Imprime los scores maximos y minimos registrados


// Solucion mas compleja 
import scala.io.StdIn
var maxPoints = (0,0) // Tupla incializados los minimos y maximos de scores
println("Games :")
var games :Int = readInt()  // Captura el total de juegos
println("Scores :")
var scores = readLine() // Captura el total de puntos separados por un espacio

// Hace un split por espacio a un array de strings a una lista despues convierte la lista a tipo Int 
var scoresList= (scores.split(' ').toList).map(_.toInt).toList 

// Funcion que recibe un valor Int y una Lista de tipo Int
def getRecords(games:Int, scores:List[Int]): String = {
    var min = scores(0) // Record minimo se le asigna el valor del indice 0 de la lista scores
    var max = scores(0) // Record maximo se le asigna el valor del indice 0 de la lista scores
    for(i <- Range(0, scores.length)) {

        if(scores(i) < min) {
            min = scores(i) // Se le asigna el valor de scores(i) como el minimo record
            maxPoints = (maxPoints._1 , maxPoints._2 + 1) // Se incrementan los contadores de nuevos minimos
        }
        if(scores(i) > max){
            max = scores(i) // Se le asigna el valor de scores(i) como el maximo record
            maxPoints = (maxPoints._1 + 1, maxPoints._2) // Se incrementan los contadores de nuevos maximos
        }
    }
    // Retorna cuantas veces los scores maximos y minimos fueron incrementando
    return (s"${maxPoints._1} ${maxPoints._2}") 
}

getRecords(games,scoresList)
// Datos de prueba
// {"Games": 9 , "Scores": 10 5 20 20 4 5 2 25 1 }
// {"Games": 10 , "Scores": 3 4 21 36 10 28 35 5 24 42 }



