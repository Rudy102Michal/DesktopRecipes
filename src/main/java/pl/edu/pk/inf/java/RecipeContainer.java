package pl.edu.pk.inf.java;

/**
 * Created by Operator on 2016-06-15.
 */

/**
  ta klasa jest klasą kontenera. Będą w niej przechowywane wszystkie przepisy w trakcie trwania programu. Jest tutaj lista przepisów, iterator
 oraz metoda do dodawania przepisów do tej listy. Poglądowa pętla wykorzystywania iteratora jest teraz a minie.
 Po tej liście będziemy przechodzić szukając przepisów i robiąc wszystkie inne rzeczy. Udało mi sie wcisnąć dwa wzorce projektowe.



 **/
public class RecipeContainer implements Container{
    static int count = 0;
    int index = 0;
    private recipe[] RecipeList;
    private static RecipeContainer instance = new RecipeContainer();

    private RecipeContainer(){
        RecipeList = new recipe[512];
    }

    public static RecipeContainer GetInstance(){
        return instance;
    }
    public Iterator getIterator(){
        return new RecipeIterator();
    }

    public void addRecipe(recipe a){
        RecipeList[count++] = a;
    }

    private class RecipeIterator implements Iterator{



        public boolean hasNext() {
            if(index < count){
                return true;
            }
            return false;
        }


        public recipe next() {

            if(this.hasNext()){
                return RecipeList[index++];
            }
            return null;
        }

        public recipe current(){
            return RecipeList[index];
        }
    }
}
