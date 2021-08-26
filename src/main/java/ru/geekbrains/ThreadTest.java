package ru.geekbrains;

public class ThreadTest extends Thread{

    float[] tmp;

    public ThreadTest(float[] arr) {
        this.tmp = arr;
    }

    @Override
    public void run() {
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = (float) (tmp[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}
