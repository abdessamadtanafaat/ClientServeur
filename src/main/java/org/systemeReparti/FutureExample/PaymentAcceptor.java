package org.systemeReparti.FutureExample;

import java.util.concurrent.Callable;

public class PaymentAcceptor implements Callable<String> {
private Boolean InventoryPresent;
public PaymentAcceptor(Boolean InventoryPresent){
        this.InventoryPresent = InventoryPresent;
        }
        @Override
public String call(){
        PaymentCall(200);
        if(this.InventoryPresent)
        return"Payment Accepted";
        else return"Payment Not Accepted";
        }

        public void PaymentCall(int ms_time) {
        try {
            Thread.sleep(ms_time);
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }
        }
}
