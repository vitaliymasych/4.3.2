public class LineProbing<K, V> {
    private static class Node<K, V> {
        K key;
        V value;
        boolean deleted;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.deleted = false;
        }
    }

    private Node<K, V>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public LineProbing(int capacity) {
        table = new Node[capacity];
        size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    // Додавання пари ключ-значення
    public void insert(K key, V value) {
        int index = hash(key);
        int start = index;

        do {
            if (table[index] == null || table[index].deleted || table[index].key.equals(key)) {
                table[index] = new Node<>(key, value);
                size++;
                return;
            }
            index = (index + 1) % table.length;
        } while (index != start);

        System.out.println("Таблиця заповнена. Не вдалося вставити.");
    }

    // Пошук за ключем
    public V search(K key) {
        int index = hash(key);
        int start = index;

        do {
            Node<K, V> node = table[index];
            if (node == null) return null;
            if (!node.deleted && node.key.equals(key)) {
                return node.value;
            }
            index = (index + 1) % table.length;
        } while (index != start);

        return null;
    }

    // Видалення за ключем (логічне)
    public void delete(K key) {
        int index = hash(key);
        int start = index;

        do {
            Node<K, V> node = table[index];
            if (node == null) return;
            if (!node.deleted && node.key.equals(key)) {
                node.deleted = true;
                size--;
                return;
            }
            index = (index + 1) % table.length;
        } while (index != start);
    }

    // Виведення всіх елементів
    public void printAll() {
        for (int i = 0; i < table.length; i++) {
            Node<K, V> node = table[i];
            if (node != null && !node.deleted) {
                System.out.println("[" + i + "] " + node.key + " => " + node.value);
            }
        }
    }

    public int getSize() {
        return size;
    }
}
