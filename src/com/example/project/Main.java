package com.example.project;

import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter K");
        int k = getPositiveNumber(scanner);
        System.out.println("Enter N");
        int n = getPositiveNumber(scanner);

        MyList<Integer> childList = new MyList<>();

        String line = "-".repeat(70) + '\n';
        StringBuilder sb = new StringBuilder();
        sb.append(line).append("Children count\tRemoved\t\t\t\t\t\t\t\t\tLast child\t\n").append(line);

        for (int childCount = 1; childCount <= n; childCount++) {
            for (int number = 1; number <= childCount; number++)
                childList.add(number);
            //run
            sb.append(childCount).append("\t\t\t\t");

            final int[] s = {0};
            final int[] last = {0};
            Consumer<Integer> a = value -> {
                last[0] = value;
                s[0]++;
                sb.append(last[0]);
                sb.append(',');
                if (s[0] == 9) {
                    s[0] = 0;
                    sb.append("\t\t\t\t\t\n\t\t\t\t");
                } else {
                    sb.append(' ');
                }
            };
            childList.myRemoving(a, k);
            sb.append("   ".repeat(10 - s[0])).append("\t\t\t").append(last[0]).append("\t\t\t\n").append(line);
        }
        System.out.println(sb.toString());
    }

    private static int getPositiveNumber(Scanner scanner) {
        int value;
        while ((value = getInt(scanner)) < 0)
            System.out.println("Is negative number");
        return value;
    }

    private static int getInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Error");
            scanner.next();
        }
        return scanner.nextInt();
    }
}