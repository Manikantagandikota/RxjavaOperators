package com.example.rxjavaoperators;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
Button fromarray,concat,distinct,map,take,buffer,reduce,filter;
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
        //take(n) acts exactly opposite to skip. It takes first N emissions of an Observable.
        take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Observable
                        .range(1, 10)
                        .take(4)
                        .subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.d("TAG", "Subscribed");
                            }

                            @Override
                            public void onNext(Integer integer) {
                                Log.d("TAG", "onNext: " + integer);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                Log.d("TAG", "Completed");
                            }
                        });
            }
        });
        buffer.setOnClickListener(new View.OnClickListener() {
            //Buffering operator allows to gather items emitted by an Observable into a list or bundles and emit those bundles instead of items
            @Override
            public void onClick(View view) {
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
                                System.out.println("onNext: ");
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
        });

        reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });

    }
    private void getfromarraydata() {
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

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
      Observable
              .concat(getMaleObservable(), getFemaleObservable())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Observer<User>() {
                  @Override
                  public void onSubscribe(Disposable d) {
                  }

                  @Override
                  public void onNext(User user) {
                      Log.e("TAG", user.getName() + ", " + user.getGender());
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
                        Log.d("TAG", "onNext: " + integer);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    }
