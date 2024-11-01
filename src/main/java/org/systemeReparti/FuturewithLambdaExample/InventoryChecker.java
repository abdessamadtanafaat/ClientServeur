package org.systemeReparti.FuturewithLambdaExample;

import java.util.concurrent.Callable;

public class InventoryChecker implements Callable<Boolean> {
    private Integer  Order;
    public InventoryChecker(Integer Order) {
        this.Order = Order;
    }
    @Override
    public Boolean call() {
        checkerCall(150);
        if (this.Order%2 == 0) {
            return Boolean.TRUE;
        }else {
            return Boolean.FALSE;
        }
    }
    public void checkerCall (int ms_time){
        try{
            Thread.sleep(ms_time);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
