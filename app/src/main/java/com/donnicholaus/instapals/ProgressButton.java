package com.donnicholaus.instapals;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ProgressButton {

    private CardView cardView;
    private ProgressBar progressBar;
    private TextView textView;
    private ConstraintLayout constraintLayout;

    Animation fade_in;
    Animation fade_out;

    ProgressButton(Context c, View view) {
        cardView = view.findViewById(R.id.cardview_layout);
        progressBar = view.findViewById(R.id.progressBar);
        textView = view.findViewById(R.id.progBtn);
        constraintLayout = view.findViewById(R.id.constraint_layout);
    }

    void buttonActivated (){
        progressBar.setVisibility(View.VISIBLE);
        textView.setText("Please wait...");

    }

    void buttonDeactivated () {
        cardView.setBackgroundColor(cardView.getResources().getColor(R.color.colorAccent));
        progressBar.setVisibility(View.GONE);
        textView.setText("Done");
    }
}
