package kth.game.othello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * A handler for different kinds of observers tied to a certain Observable.
 */
class ObserverHandler {

	private final Map<Object, List<Observer>> observerMap = new HashMap<>();
	private final Observable observable;

	ObserverHandler(Observable observable) {
		this.observable = observable;
	}

	/**
	 * Add an observer associated with the given key.
	 * 
	 * @param observerKey
	 *            The key for which this observer should be registered
	 * @param observer
	 *            The observer to be associated with the given key
	 */
	void addObserver(Object observerKey, Observer observer) {
		if (!observerMap.containsKey(observerKey))
			observerMap.put(observerKey, new ArrayList<Observer>());
		observerMap.get(observerKey).add(observer);
	}

	/**
	 * Notify all the observers associated with the given key, and do so with
	 * the given argument. This will run the
	 * {@link Observer#update(Observable, Object)} method with the constructor
	 * {@link Observable} argument and the input {@link Object} arg.
	 * 
	 * @param observerKey
	 *            The key with associated observers
	 * @param arg
	 *            The arg to pass to the observers
	 */
	void notifyObservers(Object observerKey, Object arg) {
		if (!observerMap.containsKey(observerKey))
			return;
		for (Observer observer : observerMap.get(observerKey))
			observer.update(observable, arg);
	}

	/**
	 * Notify all the observers associated with the given key, and do so by
	 * passing a <code>null</code> argument.
	 * 
	 * @param observerKey
	 *            The key with associated observers
	 */
	void notifyObservers(Object observerKey) {
		notifyObservers(observerKey, null);
	}
}
