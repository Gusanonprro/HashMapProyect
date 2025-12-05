package test;

import estructuras.TablaHash;

public class TablaHashTest {
    public static void main(String[] args) {

        TablaHash<String, Integer> mapa = new TablaHash<>();

        mapa.put("manzana", 5);
        mapa.put("pera", 3);
        mapa.put("manzana", 10);

        System.out.println("Valor de manzana: " + mapa.get("manzana"));  
        System.out.println("Existe 'pera'? " + mapa.containsKey("pera"));
        System.out.println("Eliminar pera: " + mapa.remove("pera"));
        System.out.println("Tamano actual: " + mapa.size());
    }
}
