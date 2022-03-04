Rxjava 
observables representing sources of data
subscribers (or observers) listening to the observables
a set of methods for modifying and composing the data
observables 
Observables are the sources for the data. Usually they start providing data once a subscriber starts listening. An observable may emit any number of items (including zero items). It can terminate either successfully or with an error. 
subscribers
              A observable can have any number of subscribers. If a new item is emitted from the                                           observable, the onNext() method is called on each subscriber. If the observable finishes its data flow successful, the onComplete() method is called on each subscriber. Similar, if the observable finishes its data flow with an error, the onError() method is called on each subscriber.
Types of Operators
•	Creating operators
•	Transforming operators
•	Filtering operators
•	Combining operators
•	Utility operators
•	Conditional operators
•	Mathematically operators
•	Connectable operators

Creating operators

1, Just()
 operator takes a list of arguments and converts the items into Observable items. It takes arguments between one to ten (But the official document says one to nine may be it’s language specific)

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

2, From()
 creates an Observable from set of items using an Iterable, which means each item is emitted one at a time.
3, Range() 
creates an Observable from a sequence of generated integers. The function generates sequence of integers by taking starting number and length.
4, Repeat() 
creates an Observable that emits an item or series of items repeatedly. You can also pass an argument to limit the number of repetitions.
5,Create() 
Creates an Observable from scratch and allows observer method to call programmatically
6,Defer() 
Do not create an Observable until an observer subscribes. Creates a fresh observable for each observer.
7, Empty/Never/Throw 
 Creates an Observable with limited behavior
8, Start 
Creates an Observable to emit the return value of a function.
9,Timer 
Creates an Observable to emit a single item after given delay.


Transforming operators
1, Buffer
 gathers items emitted by an Observable into batches and emit the batch instead of emitting one item at a time
2, FlatMap
 Used in nested observables. Transforms items into Observables. Then flatten the items into single Observable.
3, GroupBy
Divide an Observable into set of Observables organized by key to emit different group of items
4, Map
Apply a function to each emitted item to transform it.
5, Scan
Apply a to each function emitted item, sequentially and then emit the successive value.
6, Window 
Gathers items from Observable into Observable windows periodically and then emit the windows rather than items
Filtering operators
1, Debounce
Emits items only when timeout occurs without emiting another item.
2, Distinct
Emits only unique items.
3, ElementAt
emit only item at n index emitted by an Observable.
4, Filter
Emits only those items which pass the given predicate function.
5, First
Emits the first item or first item which passed the given criteria
6, IgnoreElements
Do not emits any items from Observable but marks completion
7, Last
Emits the last element from Observable
8, Sample
Emits the most recent item with given time interval.

9, Skip
Skips the first n items from an Observable.
10, SkipLast
Skips the last n items from an Observable.
11, Take
takes the first n items from an Observable.
12, TakeLast
takes the last n items from an Observable.
Combining operators

1, And/Then/When
 Combine item sets using Pattern and Plan intermediaries
2, CombineLatest
Combine the latest item emitted by each Observable via a specified function and emit resulted item
3, Join
Combine items emitted by two Observables if emitted during time-frame of second Observable emitted item.
4, Merge
Combines the items emitted of Observables.
5, StartWith
Emit a specified sequence of items before starting to emit the items from the source Observable
6, Switch
Emits the most recent items emitted by Observables.
7, Zip
Combines items of Observables based on function and emits the resulted items.
Utility operators

1, Delay
Register action to handle Observable life-cycle events.
2, Materialize/Dematerialize
Represents item emitted and notification sent.
3, ObserveOn
Specify the scheduler to be observed
4, Serialize
Force Observable to make serialized calls.
5, Subscribe
Operate upon the emissions of items and notifications like complete from an Observab
6, SubscribeOn
Specify the scheduler to be used by an Observable when it is subscribed to.
7, TimeInterval
Convert an Observable to emit indications of the amount of time elapsed between emissions.
8, Timeout
Issues error notification if specified time occurs without emitting any item
9, Timestamp
Attach timestamp to each item emitted.
10, Using
Creates a disposable resource or same lifespan as that of Observable.
Conditional operators
1, All
Evaluates all items emitted to meet given criteria.
2, Amb
Emits all items from the first Observable only given multiple Observables.
3, Contains
Checks if an Observable emits a particular item or not.
4, DefaultIfEmpty
Emits default item if Observable do not emit anything.
5, SequenceEqual
Checks if two Observables emit the same sequence of items.
6, SkipUntil
Discards items emitted by first Observable until a second Observable emits an item.
7, SkipWhile
Discard items emitted by an Observable until a given condition becomes false.
8, TakeUntil
Discards items emitted by an Observable after a second Observable emits an item or terminates.
9, TakeWhile
Discard items emitted by an Observable after a specified condition becomes false.

Mathematically operators

1, Average
Evaluates averages of all items and emit the result.
2, Concat
Emits all items from multiple Observable without interleaving.
3, Count
Counts all items and emit the result.
4, Max
Evaluates max valued item of all items and emit the result.
5, Min
Evaluates min valued item of all items and emit the result.
6, Reduce
Apply a function on each item and return the result.
7, Sum
Evaluates sum of all items and emit the result.
Connectable operators
1, Connect
Instruct a connectable Observable to emit items to its subscribers.
2, Publish
Converts an Observable to connectable Observable.
3, RefCount
Converts a connectable Observable to ordinary Observable.
4, Replay
Ensure same sequence of emitted items to be seen by each subscriber, even after the Observable has begun emitting items and subscribers subscribe later










































