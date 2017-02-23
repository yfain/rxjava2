import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

import java.util.concurrent.TimeUnit;

public class ParallelFlowableRange {

    public static void main(String[] args) {

        int numberOfRails = 4; // can query #processors with parallelism()

        ParallelFlowable
            .from(Flowable.range(1, 10), numberOfRails)
            .runOn(Schedulers.computation())
            .map(i -> i * i)
            .filter(i -> i % 3 == 0)
            .sequential()
            .subscribe(System.out::println);
    }
}

    /*Flowable myNumbers = Flowable.range(1, 10);

    ParallelFlowable pFlowable = myNumbers.parallel();
    System.out.println("Parallelism level: " + pFlowable.parallelism());*/