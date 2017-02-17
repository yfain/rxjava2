import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;

public class BeerServer {

    static List<Beer> beerStock = new ArrayList<>();

    private static  void loadSeller(){
        beerStock.add(new Beer("Obolon", "Ukraine", 4.00f));
        beerStock.add(new Beer("Stella", "Belgium", 7.75f));
        beerStock.add(new Beer("Sam Adams", "USA", 7.00f));
        beerStock.add(new Beer("Bud Light", "USA", 5.00f));
        beerStock.add(new Beer("Yuengling", "USA", 5.50f));
        beerStock.add(new Beer("Leffe Blonde", "Belgium", 8.75f));
        beerStock.add(new Beer("Chimay Blue", "Belgium", 10.00f));
        beerStock.add(new Beer("Brooklyn Lager", "USA", 8.25f));

    }


    public static Observable<Beer> getData(){

        loadSeller();
        System.out.println("*** Sending beers as Observable stream ***");

        // Create an observable passing subscriber (implements Observer)
        // provided by the client

        return
            Observable.create(subscriber -> {

                int totalBeers = beerStock.size();
                for (int i = 0; i < totalBeers; i++) {

                    // This is synchronous, call, but can be async
                    subscriber.onNext(beerStock.get(i));
                    
                    try {
                        Thread.sleep(500); // Emulating delay in getting data
                    } catch (InterruptedException e) {
                        throw new Exception("Error in getting beer info");
                    }
                }

                subscriber.onComplete();
        });
    }
}
