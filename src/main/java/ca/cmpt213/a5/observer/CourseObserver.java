package ca.cmpt213.a5.observer;

/**
 * Interface for observers to implement to be able to observe
 * changes to NumberList objects.
 */

public interface CourseObserver {
    void stateChanged();
}
