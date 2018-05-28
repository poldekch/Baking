package com.example.leopo.baking;

import android.content.Context;

import com.example.leopo.baking.data.Step;

public interface ClickCallback {
    void onClick(Context context, Step step);
}
