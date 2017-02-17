import io.reactivex.Observable;
import io.reactivex.Observer;

public class BeerClientWithFailover {
    public static void main(String[] args) {

        Observable<Beer> beerData = BeerServerWithFailover.getData(); // get data from main server

        beerData
            .onErrorResumeNext(err -> {
                System.out.println("\n!!! Switching to an alternative data source because of : "
                                  + err.getMessage() + "\n");
                return BeerServerWithFailover.getDataFromAnotherServer();})   // get data from alternative server
            .subscribe(
                // Implementing the observer
                beer -> System.out.println(beer),
                (error) -> System.err.println("In Observer.onError(): " + error.getMessage()),
                () -> System.out.println("*** The stream is over ***")
        );
    }
}