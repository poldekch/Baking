package com.example.leopo.baking.utilities;

import android.content.Context;

import com.example.leopo.baking.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecipesJsonUtils {

    public static ArrayList<Recipe> getRecipesFromJson(Context context, String recipesJsonString) throws JSONException {
        final String ID = "id";
        final String NAME = "name";
        final String INGREDIENTS = "ingredients";
        final String QUANTITY = "quantity";
        final String MEASURE = "measure";
        final String INGREDIENT = "ingredient";
        final String STEPS = "steps";
        final String STEP_ID = "ID";
        final String SHORT_DESCRIPTION = "shortDescription";
        final String DESCRIPTION = "description";
        final String VIDEO_URL = "videoURL";
        final String THUMBNAIL_URL = "thumbnailURL";
        final String SERVINGS = "servings";
        final String IMAGE = "image";

        JSONArray recipes = new JSONArray(recipesJsonString);

        ArrayList<Recipe> parsedRecipesData = new ArrayList<>();
        for (int i=0; i<recipes.length(); i++) {
            JSONObject jsonRecipe = (JSONObject) recipes.get(i);
            Recipe recipe = new Recipe();
            recipe.setId(jsonRecipe.optInt(ID));
            recipe.setName(jsonRecipe.optString(NAME));
            // TODO
            recipe.setServings(jsonRecipe.optInt(SERVINGS));
            recipe.setImage(jsonRecipe.optString(IMAGE));
            parsedRecipesData.add(recipe);
        }

        return parsedRecipesData;
    }
}
