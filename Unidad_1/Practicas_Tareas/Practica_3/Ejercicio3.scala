// Fibonacci version iterativa (Complejidad O(n)
import scala.math.sqrt
import scala.math.pow

def fib3(n:Int): Double = {
    var a = 0.00
    var b = 1.00
    var c = 0.00
    for(k <- Range(0,n)){
        c = (b + a)
        a = b
        b = c
    }
    return a
} 

fib3(100)