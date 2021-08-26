package ru.geekbrains;

public class Main {

    public static void main(String[] args) {

        Main.doWork();
        Main.doWorkV2();

    }

    public static void doWork() {

        int size = 10000000;
        float[] arr = new float[size];
        long a = System.currentTimeMillis();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Время выполнения первого метода равно: " + (System.currentTimeMillis() - a) + " миллисекунд");

    }

    public static void doWorkV2() {

        int size = 10000000;
        int half = size / 2;
        float[] arr = new float[size];
        float[] arr1 = new float[half];
        float[] arr2 = new float[half];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();

        ThreadTest thread = new ThreadTest(arr1);
        ThreadTest thread01 = new ThreadTest(arr2);

        System.arraycopy(arr,5,arr1,0,half);
        System.arraycopy(arr, 0, arr2, 0, half);

        thread.start();
        try {
            thread.join();
//            System.out.println(System.currentTimeMillis() - a);
        } catch (InterruptedException e) {
            System.out.println("Поток №1 прерван.");
        }
        arr1 = thread.tmp;
        thread01.start();
        try {
            thread01.join();
//            System.out.println(System.currentTimeMillis() - a);
        } catch (InterruptedException e) {
            System.out.println("Поток №2 прерван.");
        }
        arr2 = thread01.tmp;

        System.arraycopy(arr1,0,arr,0,half);
        System.arraycopy(arr2, 0,arr,half,half);


        System.out.println("Время выполнения второго метода равно: " + (System.currentTimeMillis() - a) + " миллисекунд");

    }

}
