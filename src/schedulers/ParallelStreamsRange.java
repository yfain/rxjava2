package schedulers;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;

public class ParallelStreamsRange {

    public static void main(String[] args) {

        Observable.range(1, 10000)

                .flatMap(n->Observable.just(n)
                        .subscribeOn(Schedulers.newThread()))
                .map(n->n*2)
                .observeOn(Schedulers.computation())
                .subscribe(n ->System.out.println("Got "+ n +
                        " on " + Thread.currentThread().getName()));
        
        // Just to keep the program running
        try {
            Thread.sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
   }
}
