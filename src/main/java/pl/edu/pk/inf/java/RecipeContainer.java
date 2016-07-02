package pl.edu.pk.inf.java;

/**
 * Created by Operator on 2016-06-15.
 */

import DataClasses.Recipe;
import DataClasses.*;
import java.util.ArrayList;

/**
  ta klasa jest klasą kontenera. Będą w niej przechowywane wszystkie przepisy w trakcie trwania programu. Jest tutaj lista przepisów, iterator
 oraz metoda do dodawania przepisów do tej listy. Poglądowa pętla wykorzystywania iteratora jest teraz a minie.
 Po tej liście będziemy przechodzić szukając przepisów i robiąc wszystkie inne rzeczy. Udało mi sie wcisnąć dwa wzorce projektowe.
 **/
public class RecipeContainer implements Container{
    private static int count = 0;
    private Recipe [] RecipeList;
    private static RecipeContainer instance = new RecipeContainer();

    private RecipeContainer(){
        RecipeList = new Recipe[512];
    }

    public static RecipeContainer GetInstance(){
        return instance;
    }
    public Iterator getIterator(){
        return new RecipeIterator();
    }

    public void addRecipe(Recipe a){
        RecipeList[count++] = a;
    }

    public ArrayList<Recipe> findRecipe(String recipeName){

        int i;
        ArrayList<Recipe> localRecipeList = new ArrayList<>();

        for(i = 0; i < count;i++){
            if (RecipeList[i].recipeName.equals(recipeName)) localRecipeList.add(RecipeList[i]);
        }
        return localRecipeList;
    }

    public ArrayList<Recipe> findRecipe(ArrayList<DataClasses.DefaultTags> recipeTags){

        int i;
        ArrayList<Recipe> localRecipeList = new ArrayList<>();
        int tagListSize;
        int j;

        for(i = 0; i < count; i++){
            tagListSize = RecipeList[i].recipeTags.size();
            for (j = 0; j < tagListSize; j++){
                if (recipeTags.contains(RecipeList[i].recipeTags.get(j)))
                    if (j == tagListSize-1) localRecipeList.add(RecipeList[i]);
                    else;
                else break;
            }
        }
        return localRecipeList;
    }

    public ArrayList<Recipe> findRecipe(ArrayList<DataClasses.DefaultTags> recipeTags, String recipeName){

        ArrayList<Recipe> localRecipeListString;
        ArrayList<Recipe> localRecipeListTags;
        ArrayList<Recipe> localRecipeList = new ArrayList<>();
        localRecipeListString = findRecipe(recipeName);
        localRecipeListTags = findRecipe(recipeTags);

        int stringListLength = localRecipeListString.size();

        for (int i = 0; i < stringListLength; i++){
            if (localRecipeListTags.contains(localRecipeListString.get(i)))
                localRecipeList.add(localRecipeListString.get(i));
        }
        return localRecipeList;
    }

    private class RecipeIterator implements Iterator{

        int index = 0;

        public boolean hasNext() {
            if(index < count){
                return true;
            }
            return false;
        }


        public Recipe next() {

            if(this.hasNext()){
                return RecipeList[index++];
            }
            return null;
        }

        public Recipe current(){
            return RecipeList[index];
        }
    }
}
