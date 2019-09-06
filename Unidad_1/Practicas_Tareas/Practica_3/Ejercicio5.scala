// Fibonacci version iterativa vector (Complejidad O(n)
import scala.math.sqrt
import scala.math.pow

def fib5(n:Int): Double = {
    if( n < 2) {
        return n
    }
    else {
        var vector = new Array[Double](n + 1)
        vector(0) = 0
        vector(1) = 1
        for(k <- Range(2,n + 1)){
            vector(k) = vector(k-1) + vector(k-2)
        }
        return vector(n)
    }
}

fib5(100)