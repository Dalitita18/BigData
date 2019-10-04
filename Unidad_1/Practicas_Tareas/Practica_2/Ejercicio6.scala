// 6. Crea una mapa mutable llamado nombres que contenga los siguiente
//     "Jose", 20, "Luis", 24, "Ana", 23, "Susana", "27"

val nombres = collection.mutable.Map(("Jose",20),("Luis",24),("Ana",23),("Susana",27))

// 6 a . Imprime todas la llaves del mapa
nombres.keys

// 7 b . Agrega el siguiente valor al mapa("Miguel", 23)
nombres += ("Miguel" -> 23)