package com.rhino.constraintlayoutdemo;

import android.animation.ValueAnimator;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.LinearInterpolator;

import com.rhino.constraintlayoutdemo.databinding.CircularPositioningBinding;

import java.util.concurrent.TimeUnit;


/**
 * @author LuoLin
 * @since Create on 2018/6/5.
 */
public class CircularPositioningActivity extends AppCompatActivity {

    private CircularPositioningBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.circular_positioning);


        ValueAnimator anim = ValueAnimator.ofInt(0, 359);
        anim.addUpdateListener(valueAnimator -> {
            int val = (Integer) valueAnimator.getAnimatedValue();
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) binding.img.getLayoutParams();
            layoutParams.circleAngle = val;
            binding.img.setLayoutParams(layoutParams);
        });
        anim.setDuration(TimeUnit.SECONDS.toMillis(6));
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatMode(ValueAnimator.RESTART);
        anim.setRepeatCount(ValueAnimator.INFINITE);

        anim.start();

    }
}
