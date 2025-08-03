public class Main {
    public static void main(String[] args) {
        LineProbing<Integer, String> table = new LineProbing<>(10);

        table.insert(1, "Оля");
        table.insert(11, "Сергій");
        table.insert(21, "Максим");

        System.out.println("Знайдено ключ 11: " + table.search(11));

        table.delete(11);

        System.out.println("\nУсі дані після видалення:");
        table.printAll();
    }
}