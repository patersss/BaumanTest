package org.example;

import java.util.List;
import java.util.ArrayList;

public class Task5 {

    /* Идея заключается в том, что идём снизу вверх, каждый раз вычисляя наименьшей вес расстояния.
           Сложность как раз и O(n^2), потому что мы проходим по всем значениям треугольника, а по факту
           это немного урезанная квадратная матрица.
    */
    public static void solve(List<List<Integer>> triangle) {
        if (triangle == null || triangle.isEmpty()) {
            return;
        }

        List<Integer> lastRow = new ArrayList<>(triangle.getLast());
        List<String> path = new ArrayList<>();
        path.add(String.valueOf(lastRow.getFirst()));

        for (int i = triangle.size() - 2; i >= 0; i--) {
            List<Integer> curRow = triangle.get(i);

            for (int j = 0; j < curRow.size(); j++) {
                int minPath = Math.min(lastRow.get(j), lastRow.get(j + 1));
                lastRow.set(j, curRow.get(j) + minPath);
            }

            path.addFirst(String.valueOf(curRow.getFirst()));
        }

        StringBuilder pathResult = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            pathResult.append(path.get(i));
            if (i < path.size() - 1) {
                pathResult.append(" → ");
            }
        }
        System.out.println("Минимальный путь: " + pathResult);
        System.out.println("Результат: " + lastRow.getFirst());

    }

    public static void main(String[] args) {
        List<List<Integer>> triangle1 = new ArrayList<>();
        triangle1.add(List.of(2));
        triangle1.add(List.of(3, 4));
        triangle1.add(List.of(6, 5, 7));
        triangle1.add(List.of(4, 1, 8, 3));

        solve(triangle1);
        System.out.println();

        List<List<Integer>> triangle2 = new ArrayList<>();
        triangle2.add(List.of(-1));
        triangle2.add(List.of(2, 3));
        triangle2.add(List.of(1, -1, -3));
        triangle2.add(List.of(4, 2, 1, 3));

        solve(triangle2);
        System.out.println();

        List<List<Integer>> triangle3 = new ArrayList<>();
        triangle3.add(List.of(6));
        triangle3.add(List.of(3, 0));
        triangle3.add(List.of(4, -2, 1));
        triangle3.add(List.of(8, 4, 3, 5));

        solve(triangle3);
    }
}