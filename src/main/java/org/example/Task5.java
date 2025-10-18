package org.example;

import java.util.List;
import java.util.ArrayList;

public class Task5 {

    /* Идея заключается в том, что идём снизу вверх, каждый раз вычисляя наименьшей вес расстояния.
           Сложность как раз и O(n^2), потому что мы проходим по всем значениям треугольника, а по факту
           это немного урезанная квадратная матрица.
    */
    public static int solve(List<List<Integer>> triangle) {
        if (triangle == null || triangle.isEmpty()) {
            return - 1;
        }
        // Используем, чтобы отслеживать пути с наименьшим весом. Можно было бы менять значения на месте
        // в самом треугольнике, но тогда возникли бы проблемы при работе с immutable листами
        List<Integer> lastRow = new ArrayList<>(triangle.getLast());

        for (int i = triangle.size() - 2; i >= 0; i--) {
            List<Integer> curRow = triangle.get(i);

            for (int j = 0; j < curRow.size(); j++) {
                int minPath = Math.min(lastRow.get(j), lastRow.get(j + 1));
                lastRow.set(j, curRow.get(j) + minPath);
            }
        }
        return lastRow.getFirst();

    }

    public static void main(String[] args) {
        List<List<Integer>> triangle1 = new ArrayList<>();
        triangle1.add(List.of(2));
        triangle1.add(List.of(3, 4));
        triangle1.add(List.of(6, 5, 7));
        triangle1.add(List.of(4, 1, 8, 3));


        System.out.println("Результат для 1го тестового треугольника: " + solve(triangle1));

        List<List<Integer>> triangle2 = new ArrayList<>();
        triangle2.add(List.of(-1));
        triangle2.add(List.of(2, 3));
        triangle2.add(List.of(1, -1, -3));
        triangle2.add(List.of(4, 2, 1, 3));

        System.out.println("Результат для 2го тестового треугольника: " + solve(triangle2));

        List<List<Integer>> triangle3 = new ArrayList<>();
        triangle3.add(List.of(6));
        triangle3.add(List.of(3, 0));
        triangle3.add(List.of(4, -2, 1));
        triangle3.add(List.of(8, 4, 3, 5));

        System.out.println("Результат для 3го треугольника: " + solve(triangle3));
    }
}