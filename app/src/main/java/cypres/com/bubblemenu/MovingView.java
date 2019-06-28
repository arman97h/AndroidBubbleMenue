package cypres.com.bubblemenu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by  Sigmatix on 6/7/2019.
 */
public class MovingView extends FrameLayout {
    private static final String TAG = MovingView.class.getSimpleName();
    private ImageView centerBubble;
    private ImageView bubble2;
    private ImageView bubble3;
    private ImageView bubble4;
    private ImageView bubble5;
    private ImageView bubble6;
    private ImageView bubble7;
    private ImageView bubble8;
    private ImageView bubble9;
    private int circleRadius;

    private int maxDistance;
    private float minScalePercent;
    private float maxScalePercent;
    private int globalCenterX;
    private int globalCenterY;
    private ArrayList<Object> imageViews;

    public MovingView(@NonNull Context context) {
        super(context);
        init();
    }


    public MovingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MovingView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void setupCircles(int count) {
        imageViews = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.insta));
            imageViews.add(imageView);
//            addView(imageView, circleRadius * 2, circleRadius * 2);

            if (i % 7 <= 2) {
                Log.i(TAG, "Must point " + i + " position " + i/7 + " mnacord  " + i % 7);
            }
        }
    }

    private void init() {
        setupCircles(90);
        circleRadius = 65;
        centerBubble = new ImageView(getContext());
        bubble2 = new ImageView(getContext());
        bubble3 = new ImageView(getContext());
        bubble4 = new ImageView(getContext());
        bubble5 = new ImageView(getContext());
        bubble6 = new ImageView(getContext());
        bubble7 = new ImageView(getContext());
        bubble8 = new ImageView(getContext());
        bubble9 = new ImageView(getContext());
        centerBubble.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.insta));
        bubble2.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.insta));
        bubble3.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.insta));
        bubble4.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.insta));
        bubble5.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.insta));
        bubble6.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.insta));
        bubble7.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.insta));
        bubble8.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.insta));
        bubble9.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.insta));
        addView(centerBubble, circleRadius * 2, circleRadius * 2);
        addView(bubble2, circleRadius * 2, circleRadius * 2);
        addView(bubble3, circleRadius * 2, circleRadius * 2);
        addView(bubble4, circleRadius * 2, circleRadius * 2);
        addView(bubble5, circleRadius * 2, circleRadius * 2);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                globalCenterX = getWidth() / 2;
                globalCenterY = getHeight() / 2;

                maxDistance = (int) (Math.pow(Math.pow(getHeight(), 2) + Math.pow(getWidth(), 2), 0.5) / 2);
                minScalePercent = 0.1f;
                maxScalePercent = 1.2f;

                centerBubble.setX(getWidth() / 2f - circleRadius);
                centerBubble.setY(getHeight() / 2f - circleRadius);
                scaleBubble(centerBubble);

                bubble2.setX(getWidth() / 4f - circleRadius);
                bubble2.setY(getHeight() / 4f - circleRadius);
                scaleBubble(bubble2);

                bubble3.setX(3 * getWidth() / 4f - circleRadius);
                bubble3.setY(getHeight() / 4f - circleRadius);
                scaleBubble(bubble3);

                bubble4.setX(3 * getWidth() / 4f - circleRadius);
                bubble4.setY(3 * getHeight() / 4f - circleRadius);
                scaleBubble(bubble4);

                bubble5.setX(getWidth() / 4f - circleRadius);
                bubble5.setY(3 * getHeight() / 4f - circleRadius);
                scaleBubble(bubble5);
            }
        });
    }

    public void setBubbleCoordinates() {

    }


    public void performAnimation() {
        scaleBubble(centerBubble);
        scaleBubble(bubble2);
        scaleBubble(bubble3);
        scaleBubble(bubble4);
        scaleBubble(bubble5);
    }

    private void scaleBubble(ImageView bubble) {

        int bubbleCenterX = (int) (getX() + bubble.getX() + circleRadius);
        int bubbleCenterY = (int) (getY() + bubble.getY() + circleRadius);

        Log.i("TAG", "performAnimation" + " x= " + bubbleCenterX + " & " + globalCenterX
                + " y= " + bubbleCenterY + " & " + globalCenterY);
        int distance = (int) (Math.pow(Math.pow(bubbleCenterY - globalCenterY, 2) + Math.pow(globalCenterX - bubbleCenterX, 2), 0.5));

        float scale = (minScalePercent - maxScalePercent) * distance / maxDistance + 1.2f;
        bubble.setScaleX(scale);
        bubble.setScaleY(scale);
    }
}
