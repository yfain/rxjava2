import io.reactivex.Observable;
import io.reactivex.Observer;

import io.reactivex.disposables.Disposable;

public class BeerClient {

    public static void main(String[] args) {

        Observable<Beer> beerData = BeerServer.getData(); // No streaming just yet
                  
        Observer beerObserver = new Observer<Beer>() {

            public void onSubscribe(Disposable d) {
                System.out.println( " !!! Someone just subscribed to the bear stream!!! ");

                // If the subscriber is less than 21 year old, cancel subscription
                // d.dispose();
            }

            public void onNext(Beer beer) {
                System.out.println(beer);
            }

            public void onError(Throwable throwable) {
                System.err.println("In Observer.onError(): " + throwable.getMessage());
            }

            public void onComplete() {
                System.out.println("*** The stream is over ***");
            }
        };
        
        beerData
            .subscribe(beerObserver);       // Streaming starts here
    }
}