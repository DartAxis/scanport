import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class PortScan {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final String base = "77.51.14.";
        final int port = 80;
        final int timeout = 200;
        Long start = System.currentTimeMillis();
        final ExecutorService es = Executors.newFixedThreadPool(200);
        final List<Future<Boolean>> futures = new ArrayList<>();
        final List<String> ips = new ArrayList<>();
        for(int i=1;i<255;i++){
            futures.add(ServiceScan.portIsOpen(es,new StringBuffer(base).append(i).toString(), port, timeout));
            ips.add(new StringBuffer(base).append(i).toString());
        }
        es.shutdown();
        //es.awaitTermination(200L, TimeUnit.MILLISECONDS);
        for(int i=0;i<futures.size();i++){
            if(futures.get(i).get()){

                System.out.println(ips.get(i)+" port is open" );
            }
        }
        Long end = System.currentTimeMillis();
        System.out.println((end-start));

    }
}
