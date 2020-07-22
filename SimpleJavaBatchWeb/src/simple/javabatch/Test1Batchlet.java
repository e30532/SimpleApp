package simple.javabatch;

import java.util.concurrent.TimeUnit;

import javax.batch.api.Batchlet;
import javax.inject.Named;

@Named("Test1Batchlet")
public class Test1Batchlet implements Batchlet {

    @Override
    public String process() throws Exception {
        System.out.println("Test1Batchlet:START");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("Test1Batchlet:END");
        return null;
    }

    @Override
    public void stop() throws Exception {
        // TODO Auto-generated method stub
        
    }

}