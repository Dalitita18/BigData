// Fibonacci version divide y venceras (Complejidad O(log2(n))
import scala.math.sqrt
import scala.math.pow

def fib6(n:Int):Double = {
    if (n <= 0 ) {
        return 0
    }
    var i = n - 1
    var auxOne = 0.00
    var auxTwo = 1.00
    var a_b = (auxTwo,auxOne)
    var c_d = (auxOne,auxTwo)
    while( i > 0 ){
        if((i%2) != 0 ) {
            auxOne = ( c_d._2 * a_b._2) + (c_d._1 * a_b._1)
            auxTwo = ( c_d._2*(a_b._2 + a_b._1) + (c_d._1 * a_b._2))
            a_b = (auxOne,auxTwo)
        }
        auxOne = pow(c_d._1,2) + pow(c_d._2,2)
        auxTwo = ( c_d._2 * ((2 * c_d._1) + c_d._2) )
        c_d = (auxOne,auxTwo)
        i = i / 2
    } 
    return ((a_b._1)+ (a_b._2))
}

fib6(100)