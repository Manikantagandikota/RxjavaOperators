package com.example.rxjavaoperators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.CompletableObserver;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
Button fromarray,concat,distinct,map,take,buffer,reduce,filter,Switchmap,takewhile,skipwhile,sample,ignoreElements,join;
private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fromarray= findViewById(R.id.fromarray);
        distinct= findViewById(R.id.distinct);
        concat= findViewById(R.id.concat);
        map= findViewById(R.id.map);
        take= findViewById(R.id.take);
        buffer= findViewById(R.id.buffer);
        reduce= findViewById(R.id.reduce);
        filter= findViewById(R.id.filter);
        Switchmap= findViewById(R.id.Switchmap);
        takewhile= findViewById(R.id.takewhile);
        skipwhile= findViewById(R.id.skipwhile);
        sample= findViewById(R.id.sample);
        ignoreElements= findViewById(R.id.ignoreElements);
        join= findViewById(R.id.join);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getjoin();
            }
        });

        ignoreElements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getigonre();
            }
        });

        sample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getsample();
            }
        });

        skipwhile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getskipwhile();
            }
        });

        takewhile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gettakewhile();
            }
        });

        Switchmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getswitchmapdata();
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Observable
                        .just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                        .filter(new Predicate<Integer>() {
                            @Override
                            public boolean test(Integer integer) throws Exception {
                                return integer % 2 == 0;
                            }
                        })
                        .subscribe(new DisposableObserver<Integer>() {
                            @Override
                            public void onNext(Integer integer) {
                                Log.e("TAG", "Even: " + integer);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });

        fromarray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getfromarraydata();
            }
        });
        concat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getconcatdata();
            }
        });
        distinct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getdistinctdata();
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getmapdata();
            }
        });
        take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gettakedata();
            }
        });
        buffer.setOnClickListener(new View.OnClickListener() {
            //Buffering operator allows to gather items emitted by an Observable into a list or bundles and emit those bundles instead of items
            @Override
            public void onClick(View view) {
                getbufferdata();
            }
        });

        reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getreducedata();
            }
        });

    }

    private void getskipwhile() {
        Observable
                .create(emitter -> {
                    for(int i=0; i<= 6; i++) {
                        Thread.sleep(1000);
                        emitter.onNext(i);
                    }
                    emitter.onComplete();
                })
                .skipWhile(new Predicate<Object>() {
                    @Override
                    public boolean test(Object o) throws Exception {
                        return (((Integer)o < 2));
                    }
                })
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("onNext: " + o);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void getfromarraydata() {
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
/*The Observer emits the array onNext(Integer[] integers) so you will always have 1 emission irrespective of length of the array.
    exp o/p: number 1 */
        Observable.fromArray(numbers)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        Log.d("fromarray", "Number: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        Log.d("fromarray", "All numbers emitted!");
                    }
                });

    }
    private void getconcatdata(){

      /*  Concat operator combines output of two or more Observables into a single Observable. Concat operator always maintains the sequential execution
         without interleaving the emissions

         exp o/p:
         Obama, male
         Lucy, female
         */
      Observable
              .concat(getMaleObservable(), getFemaleObservable())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Observer<User>() {
                  @Override
                  public void onSubscribe(Disposable d) {
                  }

                  @Override
                  public void onNext(User user) {
                      Log.e("concat", user.getName() + ", " + user.getGender());
                  }

                  @Override
                  public void onError(Throwable e) {

                  }

                  @Override
                  public void onComplete() {

                  }
              });


  }
    private Observable<User> getFemaleObservable() {
        String[] names = new String[]{"Lucy", "Scarlett", "April"};

        final List<User> users = new ArrayList<>();
        for (String name : names) {
            User user = new User();
            user.setName(name);
            user.setGender("female");

            users.add(user);
        }
        return Observable.create(new ObservableOnSubscribe<User>() {
                    @Override
                    public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                        for (User user : users) {
                            if (!emitter.isDisposed()) {
                                Thread.sleep(1000);
                                emitter.onNext(user);
                            }
                        }

                        if (!emitter.isDisposed()) {
                            emitter.onComplete();
                        }
                    }
                }).subscribeOn(Schedulers.io());
    }
    private Observable<User> getMaleObservable() {
        String[] names = new String[]{"Mark", "John", "Trump", "Obama"};

        final List<User> users = new ArrayList<>();

        for (String name : names) {
            User user = new User();
            user.setName(name);
            user.setGender("male");

            users.add(user);
        }
        return Observable.create(new ObservableOnSubscribe<User>() {
                    @Override
                    public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                        for (User user : users) {
                            if (!emitter.isDisposed()) {
                                Thread.sleep(500);
                                emitter.onNext(user);
                            }
                        }

                        if (!emitter.isDisposed()) {
                            emitter.onComplete();
                        }
                    }
                }).subscribeOn(Schedulers.io());
    }
    private void getdistinctdata(){

    /*    Distinct operator filters out items emitted by an Observable by avoiding duplicate items in the list.
        Below, we have list of integers with duplicates. Using distinct(), emission of duplicates can be avoided
        exp o/p: 10,100
        */

        Observable<Integer> numbersObservable = Observable.just(10,10, 15, 20, 100, 200, 100, 300, 20, 100);

        numbersObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .distinct()
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d("distinct", "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getmapdata(){
      /*  Map operator transform each item emitted by an Observable and emits the modified item.
        Let’s say we have an Observable that makes a network call (assume the network call is made) and emits the User objects with name and gender.
         But in our requirement we need an email address to be present for each user, which is missing in the network response.
         exp o/p:
onNext: MARK, male, mark@rxjava.wtf

         */


        getUsersObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<User, User>() {
                    @Override
                    public User apply(User user) throws Exception {
                        // modifying user object by adding email address
                        // turning user name to uppercase
                        user.setEmail(String.format("%s@rxjava.wtf", user.getName()));
                        user.setName(user.getName().toUpperCase());
                        return user;
                    }
                })
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(User user) {
                        Log.e("TAG", "onNext: " + user.getName() + ", " + user.getGender() + ", "+ user.getEmail());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("TAG", "All users emitted!");
                    }
                });
    }

    /**
     * Assume this method is making a network call and fetching Users
     * an Observable that emits list of users
     * each User has name and email, but missing email id
     */
    private Observable<User> getUsersObservable() {
        String[] names = new String[]{"mark", "john", "trump", "obama"};

        final List<User> users = new ArrayList<>();
        for (String name : names) {
            User user = new User();
            user.setName(name);
            user.setGender("male");

            users.add(user);
        }
        return Observable
                .create(new ObservableOnSubscribe<User>() {
                    @Override
                    public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                        for (User user : users) {
                            if (!emitter.isDisposed()) {
                                emitter.onNext(user);
                            }
                        }

                        if (!emitter.isDisposed()) {
                            emitter.onComplete();
                        }
                    }
                }).subscribeOn(Schedulers.io());
    }
     private void getbufferdata(){

        /* Buffer gathers items emitted by an Observable into batches and emit the batch instead of emitting one item at a time.
         Below, we have an Observable that emits integers from 1-9. When buffer(3) is used, it emits 3 integers at a time.
o/p:onNext
Item: 1
Item: 2
Item: 3
onNext
         */
         Observable<Integer> observable = Observable.just(1, 2, 3, 4,
                 5, 6, 7, 8, 9);

         observable.subscribeOn(Schedulers.io())
                 .delay(2, TimeUnit.SECONDS, Schedulers.io())
                 .buffer(3)
                 .subscribe(new Observer<List<Integer>>() {
                     @Override
                     public void onSubscribe(Disposable d) {
                         System.out.println("Subscribed");
                     }
                     @Override
                     public void onNext(List<Integer> integers) {
                         System.out.println("buffer onNext: ");
                         for (Integer value : integers) {
                             System.out.println(value);
                         }
                     }
                     @Override
                     public void onError(Throwable e) {
                         System.out.println("Error");
                     }

                     @Override
                     public void onComplete() {
                         System.out.println("Done! ");
                     }
                 });
         try {
             Thread.sleep(3000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }

     private void gettakedata(){
      /*   take(n) acts exactly opposite to skip. It takes first N emissions of an Observable.
         In the below example, take(4) takes first 4 emissions i.e 1, 2, 3, 4 and skips the remaining
exp o/p
Subscribed
onNext: 1
onNext: 2
onNext: 3
onNext: 4
Completed
         */

         Observable.range(1, 10)
                 .take(4)
                 .subscribe(new Observer<Integer>() {
                     @Override
                     public void onSubscribe(Disposable d) {
                         Log.d("take ", "Subscribed");
                     }

                     @Override
                     public void onNext(Integer integer) {
                         Log.d("take ", "onNext: " + integer);
                     }

                     @Override
                     public void onError(Throwable e) {

                     }

                     @Override
                     public void onComplete() {
                         Log.d("take", "Completed");
                     }
                 });
     }
     private void getreducedata(){
        /* Reduce applies a function on each item and emits the final result. First, it applies a function to first item,
         takes the result and feeds back to same function on second item.
         This process continuous until the last emission.
         Once all the items are over, it emits the final result.
         that emits numbers from 1 to 10. The reduce() operator calculates the sum of all the numbers and emits the final result.
o/p:
Sum of numbers from 1 - 10 is: 55

         */
         Observable
                 .range(1, 10)
                 .reduce(new BiFunction<Integer, Integer, Integer>() {
                     @Override
                     public Integer apply(Integer number, Integer sum) throws Exception {
                         return sum + number;
                     }
                 })
                 .subscribe(new MaybeObserver<Integer>() {
                     @Override
                     public void onSubscribe(Disposable d) {
                         disposable = d;
                     }

                     @Override
                     public void onSuccess(Integer integer) {
                         Log.e("TAG", "Sum of numbers from 1 - 10 is: " + integer);
                     }

                     @Override
                     public void onError(Throwable e) {
                         Log.e("TAG", "onError: " + e.getMessage());
                     }

                     @Override
                     public void onComplete() {
                         Log.e("TAG", "onComplete");
                     }
                 });
     }
    private void getswitchmapdata() {
     /*
        SwithMap() on the other hand is completely a different operator from FlatMap and ConcatMap.
        SwitchMap always return the latest Observable and emits the items from it.*/

        Observable<Integer> integerObservable =
                Observable.fromArray(new Integer[]{1, 2, 3, 4, 5, 6});


        // it always emits 6 as it un-subscribes the before observer
        integerObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .switchMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Exception {
                        return Observable.just(integer).delay(1, TimeUnit.SECONDS);
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("switchmap", "onSubscribe");
                        disposable = d;
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d("switchmap", "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d("switchmap", "All users emitted!");
                    }
                });
    }

    private void gettakewhile(){
        Observable
                .create(emitter -> {
                    for(int i=0; i<= 6; i++) {
                        Thread.sleep(1000);
                        emitter.onNext(i);
                    }
                    emitter.onComplete();
                })
                .takeWhile(new Predicate<Object>() {
                    @Override
                    public boolean test(Object o) {
                        return (((Integer)o < 2));
                    }
                })
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("onNext: " + o);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getsample(){
        Observable timedObservable = Observable
                .just(1, 2, 3, 4, 5, 6)
                .zipWith(Observable.interval(
                        0, 1, TimeUnit.SECONDS), (item, time) -> item);

        timedObservable
                .sample(2, TimeUnit.SECONDS)
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("onNext: " + o);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private void getigonre(){
        Observable.range(1, 10)
                .ignoreElements()
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("onSubscribed");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Completed");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
private void getjoin(){
    /*
     * We create two Observables: left & right which emits a value every 100 milliseconds.
     * We join left Observable to the right Observable.
     * The two integers emitted from both the Observables are added
     * & the result is printed.
     *
     * */
    Observable<Long> left = Observable
            .interval(100, TimeUnit.MILLISECONDS);

    Observable<Long> right = Observable
            .interval(100, TimeUnit.MILLISECONDS);

    left.join(right,
            aLong -> Observable.timer(0, TimeUnit.SECONDS),
            aLong -> Observable.timer(0, TimeUnit.SECONDS),
            (l, r) -> {
                System.out.println("Left result: " + l + " Right Result: " + r);
                return l + r;
            })
            .subscribe(new Observer<Long>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Long aLong) {
                    System.out.println("onNext: " + aLong);
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });

    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(disposable!=null) {
            disposable.dispose();
        }
    }

}
