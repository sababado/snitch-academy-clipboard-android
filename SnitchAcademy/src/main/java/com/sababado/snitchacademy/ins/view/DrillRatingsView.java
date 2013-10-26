package com.sababado.snitchacademy.ins.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sababado.snitchacademy.ins.R;

/**
 * Created by rszabo on 10/25/13.
 */
public class DrillRatingsView extends LinearLayout {
    private OnRatingChangeListener onRatingChangeListener;
    private String drillName;

    public DrillRatingsView(Context context) {
        super(context);
        View.inflate(context, R.layout.drill_ratings, this);
    }

    public DrillRatingsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.drill_ratings, this);
        init(attrs);
    }

    public DrillRatingsView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        View.inflate(context, R.layout.drill_ratings, this);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.drill);
        drillName = a.getString(R.styleable.drill_title);
        ((TextView)findViewById(R.id.title)).setText(drillName);
        a.recycle();

        //set listeners
        ((RatingBar)findViewById(R.id.first_run)).setOnRatingBarChangeListener(onRatingBarChangeListener);
        ((RatingBar)findViewById(R.id.second_run)).setOnRatingBarChangeListener(onRatingBarChangeListener);
        ((RatingBar)findViewById(R.id.third_run)).setOnRatingBarChangeListener(onRatingBarChangeListener);
    }

    final private RatingBar.OnRatingBarChangeListener onRatingBarChangeListener = new RatingBar.OnRatingBarChangeListener() {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            if(onRatingChangeListener != null) {
                onRatingChangeListener.onRatingChanged(drillName, ratingBar, rating, fromUser);
            }
        }
    };

    public interface OnRatingChangeListener {
        public void onRatingChanged(String drillName, RatingBar ratingBar, float rating, boolean fromUser);
    }

    public void setOnRatingChangeListener(final OnRatingChangeListener l) {
        onRatingChangeListener = l;
    }
}
