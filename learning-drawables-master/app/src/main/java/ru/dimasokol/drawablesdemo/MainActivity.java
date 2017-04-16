package ru.dimasokol.drawablesdemo;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class MainActivity extends Activity {

    // Первый и второй вью, на которых мы будем анимировать фон
    private View mFirstAnimatedView, mSecondAnimatedView, hwCharging;
    // Аниматор фона, общий на двоих
    private ValueAnimator mAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirstAnimatedView = findViewById(R.id.first_animated_view);
        mSecondAnimatedView = findViewById(R.id.second_animated_view);
        hwCharging = findViewById(R.id.animated_charging);


        // Создаём аниматор
        mAnimator = ValueAnimator.ofInt(1000, 10000);
        mAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.setDuration(3500);
        mAnimator.setInterpolator(new DecelerateInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Integer value = (Integer) valueAnimator.getAnimatedValue();
                mFirstAnimatedView.getBackground().setLevel(value);
                mSecondAnimatedView.getBackground().setLevel(value);
                hwCharging.getBackground().setLevel(value);
            }
        });
        mAnimator.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Здесь, перед удалением Activity, зачистим аниматор во избежание утечек памяти
        mAnimator.cancel();
        mAnimator.removeAllUpdateListeners();
        mAnimator = null;
    }
}
