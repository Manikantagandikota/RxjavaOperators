# RxjavaOperators
Rxjava 
observables representing sources of data
subscribers (or observers) listening to the observables
a set of methods for modifying and composing the data
observables 
Observables are the sources for the data. Usually they start providing data once a subscriber starts listening. An observable may emit any number of items (including zero items). It can terminate either successfully or with an error. 
subscribers
              A observable can have any number of subscribers. If a new item is emitted from the                                           observable, the onNext() method is called on each subscriber. If the observable finishes its data flow successful, the onComplete() method is called on each subscriber. Similar, if the observable finishes its data flow with an error, the onError() method is called on each subscriber.
Types of Operators
All the operators are categorized depending on the kind of work it do. Some operators are used to Create Observables. The operators like create, just, fromArray, range creates an Observable.
Some operators such as debounce, filter, skip, last are used to filter the data emitted by an Observable. The operators like buffer, map, flatMap, switchMap, compose creates an Observable by transform the data emitted by another Observable.

1, Just() operator takes a list of arguments and converts the items into Observable items. It takes arguments between one to ten (But the official document says one to nine may be it’s language specific)

Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
 
                    }
 
                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: " + integer);
                    }
 
                    @Override
                    public void onError(Throwable e) {
 
                    }
 
                    @Override
                    public void onComplete() {
 
                    }
                });

2,Unlike just, From() creates an Observable from set of items using an Iterable, which means each item is emitted one at a time.
3, Range() creates an Observable from a sequence of generated integers. The function generates sequence of integers by taking starting number and length.
4, Repeat() creates an Observable that emits an item or series of items repeatedly. You can also pass an argument to limit the number of repetitions.
5, Buffer gathers items emitted by an Observable into batches and emit the batch instead of emitting one item at a time
6, Debounce operators emits items only when a specified timespan is passed. This operator is very useful when the Observable is rapidly emitting items but you are only interested in receiving them in timely manner.
7, filter() allows the Observable to emit the only values those passes a test.

8, Skip(n) operator skips the emission of first N items emitted by an Observable

9, skipLast(n) skips the last N emissions from an Observable. In the same example, skipLast(4) skips the emission of 7-10 and emits only 1, 2, 3, 4, 5, 6

10, take(n) acts exactly opposite to skip. It takes first N emissions of an Observable.

11, takeLast(n) emits last N items from an Observable.

12, Distinct operator filters out items emitted by an Observable by avoiding duplicate items in the list.

13, Max() operator finds the maximum valued item in the Observable sequence and emits that value.

14, Min() operator emits the minimum valued item in the Observable data set

15, Sum Calculates the sum of all the items emitted by an Observable and emits only the Sum value

16, Avg Calculates the average of all the items emitted by an Observable and emits only the Average value.

17, Counts number of items emitted by an Observable and emits only the count value
18, Reduce applies a function on each item and emits the final result. First, it applies a function to first item, takes the result and feeds back to same function on second item. This process continuous until the last emission. Once all the items are over, it emits the final result.
19, Concat operator combines output of two or more Observables into a single Observable. Concat operator always maintains the sequential execution without interleaving the emissions.
20, Merge also merges multiple Observables into a single Observable but it won’t maintain the sequential execution.
21, Map operator transform each item emitted by an Observable and emits the modified item.

22, FlatMap To better understand FlatMap, consider a scenario where you have a network call to fetch Users with name and gender. Then you have another network that gives you address of each user

23, ConcatMap() maintains the order of items and waits for the current Observable to complete its job before emitting the next one

24, SwithMap() on the other hand is completely a different operator from FlatMap and ConcatMap. SwitchMap always return the latest Observable and emits the items from it









