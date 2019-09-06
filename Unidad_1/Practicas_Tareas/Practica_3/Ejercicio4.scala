// Fibonacci version iterativa 2 variables(Complejidad O(n)
import scala.math.sqrt
import scala.math.pow

def fib4(n:Int): Double = {
    var a = 0.00
    var b = 1.00
    for(k <- Range(0,n)){
        b = b + a
        a = b - a
    }
    return a
}

fib4(100)