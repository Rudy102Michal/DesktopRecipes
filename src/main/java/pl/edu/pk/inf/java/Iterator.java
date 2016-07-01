package pl.edu.pk.inf.java;

/**
 * Created by Operator on 2016-06-15.
 */
public interface Iterator {
    boolean hasNext();
    recipe next();
    recipe current();
}
