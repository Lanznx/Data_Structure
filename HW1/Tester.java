import java.util.LinkedList;
import java.util.Random;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

public class Tester {

    public static void main(String[] args) {
        ArrayList array = insertArrayList(25);
        Vector vector = insertVector(25);
        LinkedList linkedList = insertLinkedList(25);

        sumArrayList(array);
        sumVector(vector);
        sumLinkedList(linkedList);

    }

    public static ArrayList<Integer> insertArrayList(int k) {
        System.out.println("------------------------- insertArrayList -------------------------");
        ArrayList array = new ArrayList<Integer>();
        long upperBound = (int) Math.pow(2, k);
        try {
            OutputStream os = new FileOutputStream("01_Insert_ArrayList.csv");
            PrintWriter w = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
            long startTime = System.nanoTime(); // nanoseconds
            System.out.println("startTime : " + startTime);
            for (long i = 0; i < upperBound; i++) {
                Random r = new Random();
                int element = r.nextInt(10);
                array.add(element);
                if (i == 0) {
                    w.print("k" + "," + "time" + "\n");
                }
                if (i % 50 == 0) {
                    long middleTime = System.nanoTime();
                    long unitDuration = (middleTime - startTime);
                    w.print(i + "," + unitDuration + "\n");
                }
            }

            long endTime = System.nanoTime();
            long totalDuration = (endTime - startTime);
            System.out.println("totalDuration : " + totalDuration);
            System.out.println("endTime : " + endTime);
            System.out.println("\n");
            w.flush();
            w.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return array;
    }

    public static Vector<Integer> insertVector(int k) {
        System.out.println("------------------------- insertVector -------------------------");
        Vector vector = new Vector<Integer>();
        long upperBound = (int) Math.pow(2, k);
        try {
            OutputStream os = new FileOutputStream("02_Insert_Vector.csv");
            PrintWriter w = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
            long startTime = System.nanoTime(); // nanoseconds
            System.out.println("startTime : " + startTime);
            for (long i = 0; i < upperBound; i++) {
                Random r = new Random();
                int element = r.nextInt(10);
                vector.add(element);
                if (i == 0) {
                    w.print("k" + "," + "time" + "\n");
                }
                if (i % 50 == 0) {
                    long middleTime = System.nanoTime();
                    long unitDuration = (middleTime - startTime);
                    w.print(i + "," + unitDuration + "\n");
                }
            }

            long endTime = System.nanoTime();
            long totalDuration = (endTime - startTime);
            System.out.println("totalDuration : " + totalDuration);
            System.out.println("endTime : " + endTime);
            System.out.println("\n");
            w.flush();
            w.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return vector;
    }

    public static LinkedList<Integer> insertLinkedList(int k) {
        LinkedList linkedList = new LinkedList<Integer>();
        System.out.println("------------------------- insertLinkedList -------------------------");
        long upperBound = (int) Math.pow(2, k);
        try {
            OutputStream os = new FileOutputStream("03_Insert_LinkedList.csv");
            PrintWriter w = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
            long startTime = System.nanoTime(); // nanoseconds
            System.out.println("startTime : " + startTime);
            for (long i = 0; i < upperBound; i++) {
                Random r = new Random();
                int element = r.nextInt(10);
                linkedList.add(element);
                if (i == 0) {
                    w.print("k" + "," + "time" + "\n");
                }
                if (i % 50 == 0) {
                    long middleTime = System.nanoTime();
                    long unitDuration = (middleTime - startTime);
                    w.print(i + "," + unitDuration + "\n");
                }
            }

            long endTime = System.nanoTime();
            long totalDuration = (endTime - startTime);
            System.out.println("totalDuration : " + totalDuration);
            System.out.println("endTime : " + endTime);
            System.out.println("\n");
            w.flush();
            w.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return linkedList;
    }

    public static long sumArrayList(ArrayList array) {
        System.out.println("------------------------- sumArrayList -------------------------");
        long startTime = System.nanoTime(); // nanoseconds
        System.out.println("startTime : " + startTime);
        long sum = 0;
        try {
            OutputStream os = new FileOutputStream("04_Sum_ArrayList.csv");
            PrintWriter w = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
            for (long i = 0; i < array.size(); i++) {
                sum += (int) array.get((int) i);
                if (i == 0) {
                    w.print("k" + "," + "time" + "\n");
                }
                if (i % 50 == 0) {
                    long middleTime = System.nanoTime();
                    long unitDuration = (middleTime - startTime);
                    w.print(i + "," + unitDuration + "\n");
                }
            }
            long endTime = System.nanoTime();
            long totalDuration = (endTime - startTime);
            System.out.println("totalDuration : " + totalDuration);
            System.out.println("endTime : " + endTime);
            System.out.println("\n");
            w.flush();
            w.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return sum;
    }

    public static long sumLinkedList(LinkedList list) {
        System.out.println("------------------------- sumLinkedList -------------------------");
        long startTime = System.nanoTime(); // nanoseconds
        System.out.println("startTime : " + startTime);
        long sum = 0;
        try {
            OutputStream os = new FileOutputStream("06_Sum_LinkedList.csv");
            PrintWriter w = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
            for (long i = 0; i < list.size(); i++) {
                sum += (int) list.get((int) i);
                if (i == 0) {
                    w.print("k" + "," + "time" + "\n");
                }
                if (i % 50 == 0) {
                    long middleTime = System.nanoTime();
                    long unitDuration = (middleTime - startTime);
                    w.print(i + "," + unitDuration + "\n");
                }
            }
            long endTime = System.nanoTime();
            long totalDuration = (endTime - startTime);
            System.out.println("totalDuration : " + totalDuration);
            System.out.println("endTime : " + endTime);
            System.out.println("\n");
            w.flush();
            w.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return sum;
    }

    public static long sumVector(Vector vector) {
        System.out.println("------------------------- sumVector -------------------------");
        long startTime = System.nanoTime(); // nanoseconds
        System.out.println("startTime : " + startTime);
        long sum = 0;
        try {
            OutputStream os = new FileOutputStream("05_Sum_Vector.csv");
            PrintWriter w = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
            for (long i = 0; i < vector.size(); i++) {
                sum += (int) vector.get((int) i);
                if (i == 0) {
                    w.print("k" + "," + "time" + "\n");
                }
                if (i % 50 == 0) {
                    long middleTime = System.nanoTime();
                    long unitDuration = (middleTime - startTime);
                    w.print(i + "," + unitDuration + "\n");
                }
            }
            long endTime = System.nanoTime();
            long totalDuration = (endTime - startTime);
            System.out.println("totalDuration : " + totalDuration);
            System.out.println("endTime : " + endTime);
            System.out.println("\n");
            w.flush();
            w.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return sum;
    }
}