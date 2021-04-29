package com.example;

import com.example.lists.DoubleLinkedList;
import com.example.lists.SingleLinkedRingList;
import com.example.project.MyList;

public class Test {
    public static void testDoubleLinkedList() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

        for (int i = 0; i < 7; i++) {
            list.add(i);
            System.out.print(i + " ");
        }
        for (int i = 0; i < 7; i++) {
            int a = i + 3;
            list.add(list.size() - 1, a);
            System.out.print(a + " ");
        }
        for (int i = 0; i < 7; i++) {
            int a = i + 4;
            list.add(0, a);
            System.out.print(a + " ");
        }
        System.out.println();

        list.forEach(integer -> System.out.print(integer + " "));
        System.out.println();

        list.remove(0);
        list.remove(Integer.valueOf(7));
        list.forEach(integer -> System.out.print(integer + " "));
        System.out.println();
    }
    public static void testSingleLinkedRingList() {
        SingleLinkedRingList<Integer> list = new SingleLinkedRingList<>();

        for (int i = 0; i < 7; i++) {
            list.add(i);
            System.out.print(i + " ");
        }
        for (int i = 0; i < 7; i++) {
            int a = i + 3;
            list.add(a, list.size() - 1);
            System.out.print(a + " ");
        }
        for (int i = 0; i < 7; i++) {
            int a = i + 4;
            list.add(a, 0);
            System.out.print(a + " ");
        }
        System.out.println();

        list.forEach(integer -> System.out.print(integer + " "));
        System.out.println();

        list.remove(0);
        list.remove(Integer.valueOf(7));
        list.forEach(integer -> System.out.print(integer + " "));
        System.out.println();
    }
    public static void testRingList() {
        MyList<Integer> list = new MyList<>();

        for (int i = 0; i < 7; i++) {
            list.add(i);
            System.out.print(i + " ");
        }
        for (int i = 0; i < 7; i++) {
            int a = i + 3;
            list.add(a, list.size() - 1);
            System.out.print(a + " ");
        }
        for (int i = 0; i < 7; i++) {
            int a = i + 4;
            list.add(a, 0);
            System.out.print(a + " ");
        }
        System.out.println();

        list.forEach(integer -> System.out.print(integer + " "));
        System.out.println();

        list.remove(0);
        list.remove(Integer.valueOf(7));
        list.forEach(integer -> System.out.print(integer + " "));
        System.out.println();
    }

    public static void laba() {
        double[] array = new double[50];

        for (int i = 0; i < array.length; i++) {
            array[i] = Math.random() * 10;
            System.out.println("Index = " + i + "\t" + array[i]);
        }

        int indexV = -1;
        double minEpsilon = 1.0D;
        for (int i = 0; i < array.length; i++) {
            double v = array[i];

            double floating = v - Math.floor(v);
            double epsilon = (floating < 0.5D) ? floating : (1 - floating);
            if (epsilon < minEpsilon) {
                minEpsilon = epsilon;
                indexV = i;
            }
        }

        if (indexV > -1)
            System.out.println("Index = " + indexV + ", Value = " + array[indexV] + ", Epsilon = " + minEpsilon);
        else
            System.out.println("NotFound");
    }

    public static void main(String[] args) {
        testDoubleLinkedList();
        System.out.println();
        testSingleLinkedRingList();
        System.out.println();
        testRingList();
    }
}