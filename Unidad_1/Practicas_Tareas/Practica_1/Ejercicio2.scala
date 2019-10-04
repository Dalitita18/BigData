//2. Desarrollar un algoritmo en scala que me diga si un numero es primo
val num = 42
var a = 0
for(i <- Range(1, num + 1)) {
  if( num % i == 0) {
    a += 1
  }
}
if(a != 2) {
  println(s"El numero ${num} no es primo")
} else {
  println(s"El numero ${num} es primo")
}