import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

public class HotObservable {

    public static void main(String[] args) throws InterruptedException{

        ConnectableObservable<Long> numbers = (ConnectableObservable<Long>) Observable
                .interval(1, TimeUnit.SECONDS)     // generate numbers
                .publish();                        // make it hot

        numbers.connect();                        // create internal subscribtion
        
        Disposable subscriber1 = numbers
            .subscribe(n ->System.out.println("First subscriber: "+ n ));


        Thread.sleep(3000);

        Disposable subscriber2 = numbers
            .subscribe(n ->System.out.println("  Second subscriber: "+ n ));

        Thread.sleep(5000);
        System.out.println(">>> First subscriber goes for lunch break");
        subscriber1.dispose();

        Thread.sleep(5000);
        System.out.println("<<< First subscriber returned from lunch");
        subscriber1 = numbers.subscribe(n ->System.out.println("First subscriber: "+ n ));

        Thread.sleep(60000); // Just to keep the program running
    }
}


/*
      // An alternative to connect()
      
      Observable<Long> numbers =  Observable
                .interval(1, TimeUnit.SECONDS)     // generate numbers
                .publish().                        // make it hot
                refCount();       // push data as long as someone is subscribed
*/
