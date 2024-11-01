package org.systemeReparti.FuturewithLambdaExample;

import java.util.Random;
import java.util.concurrent.Callable;
public class OrderFetcher implements Callable<Integer> {
    private static Random random;
    public OrderFetcher() {
        random = new Random();
    }
    public Integer call() {
        FetcherCall(300);
        return random.nextInt(10);
    }

    public void FetcherCall (int ms_time) {
        try{
            Thread.sleep(ms_time);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

}
