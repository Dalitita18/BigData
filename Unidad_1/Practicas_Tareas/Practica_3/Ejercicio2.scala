// Fibonacci version formula explicativa (Complejidad O(log2(n))
import scala.math.sqrt
import scala.math.pow

def fib2(n:Int): Double = {
    if (n < 2) {
        return n
    }
    else {
        var a = ((1 + sqrt(5))/2)
        var j = ((pow(a,n) - (pow((1-a),n))) / sqrt(5))
        return j
    }
}

fib2(100)