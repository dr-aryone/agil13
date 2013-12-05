package kth.game.othello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

class ObserverHandler {

	private final Map<Object, List<Observer>> observerMap = new HashMap<>();
	private final Observable observable;

	ObserverHandler(Observable observable) {
		this.observable = observable;
	}

	void addObserver(Object observerKey, Observer observer) {
		if (!observerMap.containsKey(observerKey))
			observerMap.put(observerKey, new ArrayList<Observer>());
		observerMap.get(observerKey).add(observer);
	}

	void notifyObservers(Object observerKey, Object arg) {
		if (!observerMap.containsKey(observerKey))
			return;
		for (Observer observer : observerMap.get(observerKey))
			observer.update(observable, arg);
	}

	void notifyObservers(Object gameFinishedObserverKey) {
		notifyObservers(gameFinishedObserverKey, null);
	}
}
