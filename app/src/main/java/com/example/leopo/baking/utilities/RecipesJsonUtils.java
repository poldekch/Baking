package com.example.leopo.baking.utilities;

import android.content.Context;

import com.example.leopo.baking.data.Ingredient;
import com.example.leopo.baking.data.Recipe;
import com.example.leopo.baking.data.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecipesJsonUtils {

    public static ArrayList<Recipe> getRecipesFromJson(String recipesJsonString) throws JSONException {
        final String ID = "id";
        final String NAME = "name";
        final String INGREDIENTS = "ingredients";
        final String QUANTITY = "quantity";
        final String MEASURE = "measure";
        final String INGREDIENT = "ingredient";
        final String STEPS = "steps";
        final String STEP_ID = "id";
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

            ArrayList<Ingredient> ingredients = new ArrayList<>();
            JSONArray jsonIngredients = jsonRecipe.optJSONArray(INGREDIENTS);
            for (int j=0; j<jsonIngredients.length(); j++) {
                JSONObject jsonIngredient = (JSONObject) jsonIngredients.get(j);
                Ingredient objIngredient = new Ingredient();
                objIngredient.setQuantity(jsonIngredient.optString(QUANTITY));
                objIngredient.setMeasure(jsonIngredient.optString(MEASURE));
                objIngredient.setIngredient(jsonIngredient.optString(INGREDIENT));
                ingredients.add(objIngredient);
            }
            recipe.setIngredients(ingredients);

            ArrayList<Step> steps = new ArrayList<>();
            JSONArray jsonSteps = jsonRecipe.optJSONArray(STEPS);
            for (int j=0; j<jsonSteps.length(); j++) {
                JSONObject jsonStep = (JSONObject) jsonSteps.get(j);
                Step step = new Step();
                step.setId(jsonStep.optInt(STEP_ID));
                step.setShortDescription(jsonStep.optString(SHORT_DESCRIPTION));
                step.setDescription(jsonStep.optString(DESCRIPTION));
                step.setVideoURL(jsonStep.optString(VIDEO_URL));
                step.setThumbnailURL(jsonStep.optString(THUMBNAIL_URL));
                steps.add(step);
            }
            recipe.setSteps(steps);

            recipe.setServings(jsonRecipe.optInt(SERVINGS));
            recipe.setImage(jsonRecipe.optString(IMAGE));
            parsedRecipesData.add(recipe);
        }

        return parsedRecipesData;
    }
}
