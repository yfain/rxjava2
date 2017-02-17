import io.reactivex.*;
import io.reactivex.subscribers.ResourceSubscriber;

public class BeerClientFlowable {

    public static void main(String[] args) {

        Observable<Beer> beerData = BeerServer.getData(); // No streaming just yet


        ResourceSubscriber<Beer> beerSubscriber = new ResourceSubscriber<Beer>() {
            @Override
            public void onNext(Beer beer) {
                System.out.println("Got "+ beer);
            }

            @Override
            public void onError(Throwable throwable) {
                System.err.println("In Observer.onError(): " + throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("*** The stream is over ***");
            }
        };

        // Converting an Observable to Flowable
        beerData
             .toFlowable(BackpressureStrategy.BUFFER)
             .subscribe(beerSubscriber);   // Streaming starts here


        // If the subscriber is less than 21 year old, cancel subscription
         beerSubscriber.dispose();
    }
}