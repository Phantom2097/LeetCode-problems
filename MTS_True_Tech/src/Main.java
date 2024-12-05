//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        int a = sc.nextInt();
//        int b = sc.nextInt();
//        int u = sc.nextInt();
//
//        int totalCost;
//        if (u <= 100) {
//            totalCost = a;
//        } else {
//            totalCost = a + (u - 100) * b;
//        }
//
//        System.out.println(totalCost);
//    }
//}

//import java.util.Scanner;
//import java.io.*;
//public class Main {
//    public static void main (String[] args) throws java.lang.Exception {
//        Scanner sc = new Scanner(System.in); // System.in is a standard input stream
//        String[] line = sc.nextLine().split("");
//        boolean m = false, t = false, s = false;
//        for (String string : line) {
//            if (string.equals("M")) {
//                m = true;
//            } else if (m && string.equals("T")) {
//                t = true;
//            } else if (m && t && string.equals("S")) {
//                s = true;
//                System.out.println(1);
//                break;
//            }
//        }
//        if (!s) System.out.println(0);
//    }
//}


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long totalConnections = 0;

        for (int i = 0; i < n; i++) {
            long a = sc.nextLong();
            totalConnections += a;
        }

        if (totalConnections % 2 == 0) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }

        sc.close();
    }
}
