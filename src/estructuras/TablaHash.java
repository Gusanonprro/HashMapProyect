package estructuras;

import java.util.LinkedList;

/**
 * Implementación de una Tabla Hash con Encadenamiento Separado.
 * Soporta tipos genéricos y redimensionamiento dinámico.
 *
 * El factor de carga máximo es 0.75, tras lo cual se duplica
 * la capacidad automáticamente.
 *
 * @param <K> Tipo de claves
 * @param <V> Tipo de valores
 * @author Gustavo 
 * @version 1.0
 */
public class TablaHash<K, V> implements Diccionario<K, V> {

    /**
     * Clase interna nodo que almacena un par clave-valor.
     */
    private class Nodo<K, V> {
        K key;
        V value;

        /**
         * Constructor del nodo clave-valor.
         * @param key Clave
         * @param value Valor
         */
        public Nodo(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Nodo<K, V>>[] tabla;
    private int size;
    private int capacidad;
    private static final double FACTOR_CARGA_MAX = 0.75;

    /**
     * Constructor por defecto.
     * Inicializa la tabla con capacidad 11.
     */
    @SuppressWarnings("unchecked")
    public TablaHash() {
        this.capacidad = 11;
        this.tabla = new LinkedList[capacidad];
        this.size = 0;

        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new LinkedList<>();
        }
    }

    /**
     * Calcula el índice hash para una clave.
     * Maneja hashCodes negativos.
     *
     * @param key Clave
     * @return índice válido
     */
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacidad;
    }

    /**
     * Inserta un par clave-valor.
     * Actualiza si la clave ya existe.
     *
     * @param key Clave única
     * @param value Valor
     * @throws NullPointerException si key es null
     */
    @Override
    public void put(K key, V value) {
        if (key == null)
            throw new NullPointerException("La clave no puede ser null.");

        int indice = hash(key);
        LinkedList<Nodo<K, V>> lista = tabla[indice];

        for (Nodo<K, V> nodo : lista) {
            if (nodo.key.equals(key)) {
                nodo.value = value; // actualizar
                return;
            }
        }

        lista.add(new Nodo<>(key, value));
        size++;

        if ((double) size / capacidad >= FACTOR_CARGA_MAX) {
            resize();
        }
    }

    /**
     * Recupera el valor asociado a una clave.
     *
     * @param key clave a buscar
     * @return valor o null si no existe
     */
    @Override
    public V get(K key) {
        int indice = hash(key);
        LinkedList<Nodo<K, V>> lista = tabla[indice];

        for (Nodo<K, V> nodo : lista) {
            if (nodo.key.equals(key)) {
                return nodo.value;
            }
        }

        return null;
    }

    /**
     * Elimina una clave de la tabla.
     *
     * @param key clave a eliminar
     * @return valor eliminado o null si no existe
     */
    @Override
    public V remove(K key) {
        int indice = hash(key);
        LinkedList<Nodo<K, V>> lista = tabla[indice];

        for (int i = 0; i < lista.size(); i++) {
            Nodo<K, V> nodo = lista.get(i);
            if (nodo.key.equals(key)) {
                lista.remove(i);
                size--;
                return nodo.value;
            }
        }
        return null;
    }

    /**
     * Verifica si la clave existe.
     *
     * @param key clave a verificar
     * @return true si existe
     */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Retorna la cantidad de elementos.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Duplica la capacidad y reubica los elementos.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        LinkedList<Nodo<K, V>>[] tablaVieja = tabla;

        capacidad *= 2;
        tabla = new LinkedList[capacidad];

        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new LinkedList<>();
        }

        size = 0;

        for (LinkedList<Nodo<K, V>> bucket : tablaVieja) {
            for (Nodo<K, V> nodo : bucket) {
                put(nodo.key, nodo.value);
            }
        }
    }
}

