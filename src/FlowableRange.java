import io.reactivex.Flowable;

import io.reactivex.subscribers.DisposableSubscriber;

import java.util.concurrent.TimeUnit;

public class FlowableRange {

    static DisposableSubscriber<Integer> subscriber;

    public static void main(String[] args) {

       subscriber = new DisposableSubscriber<Integer>() {

            public void onStart() {
                request(5);

              while (true){     // Emulate some processing
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                request(1);
              }
            }

            public void onNext(Integer t) {
                System.out.println("processing "+ t);

                if (t==8) {
                    subscriber.dispose();
                }
            }

            public void onError(Throwable thr) {
                System.err.println("In onError(): " + thr.getMessage());
            }

            public void onComplete() {
                System.out.println("Done");
            }
        };

        Flowable.range(1, 10)
                .delay(1, TimeUnit.SECONDS)
                .subscribe(subscriber);
    }
}