package pl.edu.pk.inf.java.MainContainer;


import pl.edu.pk.inf.java.DataClasses.Recipe;
/**
 * Created by Operator on 2016-06-15.
 */
public interface Iterator {
    boolean hasNext();
    Recipe next();
    Recipe current();
}
