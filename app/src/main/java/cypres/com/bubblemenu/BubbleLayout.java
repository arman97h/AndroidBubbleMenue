package cypres.com.bubblemenu;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by  Sigmatix on 6/7/2019.
 */
public class BubbleLayout extends FrameLayout {
    private MovingView movingLayout;

    public BubbleLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public BubbleLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BubbleLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init() {
        movingLayout = new MovingView(getContext());
        addView(movingLayout, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


        movingLayout.setOnTouchListener(new OnTouchListener() {
            float dX, dY;

            @Override
            public boolean onTouch(View view, MotionEvent event) {

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        dX = view.getX() - event.getRawX();
                        dY = view.getY() - event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:

                        movingLayout.animate()
                                .x(event.getRawX() + dX)
                                .y(event.getRawY() + dY)
                                .setDuration(0)
                                .start();
                        movingLayout.performAnimation();
                        break;
                    case MotionEvent.ACTION_UP:
                        movingLayout.animate()
                                .x(0)
                                .y(0)
                                .setDuration(300)
                                .setInterpolator(new FastOutSlowInInterpolator())
                                .setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                    @Override
                                    public void onAnimationUpdate(ValueAnimator animation) {
                                        movingLayout.performAnimation();
                                    }
                                })
                                .start();
                    default:
                        return false;
                }
                return true;
            }

        });
    }
}
