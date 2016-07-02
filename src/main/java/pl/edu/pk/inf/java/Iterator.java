package pl.edu.pk.inf.java;


import DataClasses.Recipe;
/**
 * Created by Operator on 2016-06-15.
 */
public interface Iterator {
    boolean hasNext();
    Recipe next();
    Recipe current();
}
