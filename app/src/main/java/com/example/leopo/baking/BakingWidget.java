package com.example.leopo.baking;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.example.leopo.baking.data.Ingredient;
import com.example.leopo.baking.data.Recipe;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidget extends AppWidgetProvider {

    SharedPreferences sharedPreferences;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, Recipe recipe) {



        CharSequence widgetText = context.getString(R.string.appwidget_text);
        if (recipe != null) {
            widgetText = recipe.getName() + "\n" + widgetText + ":\n";
            ArrayList<Ingredient> ingredients = recipe.getIngredients();
            for (int i = 0; i<=ingredients.size()-1; i++) {
                Ingredient ingredient = ingredients.get(i);
                widgetText = widgetText + "- " +ingredient.getQuantity() + " " +
                        ingredient.getMeasure() + " of " + ingredient.getIngredient() + "\n";
            }
        } else {
            widgetText = "Select recipe first";
        }

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        sharedPreferences = context.getSharedPreferences("BakingData", Context.MODE_PRIVATE);
        String recipeString = sharedPreferences.getString("Recipe", null);
        Gson gson = new Gson();
        Recipe recipe = gson.fromJson(recipeString, Recipe.class);
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, recipe);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

