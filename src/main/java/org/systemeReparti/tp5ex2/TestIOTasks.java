package org.systemeReparti.tp5ex2;

import java.util.Random;

public class TestIOTasks {
    public static Integer random() {
        return new Random().nextInt(10);
    }
    public static void main(String[] args) throws InterruptedException {
        long end; long begin; long time=0; long sum=0;
        begin = System.currentTimeMillis();
        for (int j = 0; j < 1000; j++) {
            try {
                Thread.sleep(5); //(wait for 5 ms)
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sum=sum+random();}
        System.out.println(sum);
        end = System.currentTimeMillis(); time = time + (end - begin);
        System.out.println("Elapsed Time: " + time + " Milliseconds");}
}