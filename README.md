# Proyecto: Implementación de una Tabla Hash (HashMap)

Este proyecto implementa una Tabla Hash desde cero utilizando Encadenamiento Separado.

## Características
- Manejo de colisiones con listas enlazadas
- Redimensionamiento dinámico (resize)
- Uso de genéricos
- Javadoc completo
- Interfaz `Diccionario`

## Compilación
```sh
javac src/estructuras/*.java test/TestTablaHash.java

java estructuras.TablaHashTest

TablaHash<String, Integer> mapa = new TablaHash<>();

// Insertar elementos
mapa.put("manzana", 5);
mapa.put("pera", 3);

// Actualizar un valor existente
mapa.put("manzana", 10);

// Operaciones de consulta y eliminación
System.out.println("Valor de manzana: " + mapa.get("manzana"));
System.out.println("Existe 'pera'?: " + mapa.containsKey("pera"));
System.out.println("Eliminando 'pera': " + mapa.remove("pera"));
System.out.println("Tamaño actual: " + mapa.size());

//Resultados esperados
Valor de manzana: 10
Existe 'pera'?: true
Eliminando pera: 3
Tamaño actual: 1
