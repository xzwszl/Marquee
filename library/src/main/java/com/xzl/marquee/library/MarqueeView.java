package com.xzl.marquee.library;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

/**
 * Created by xzwszl on 17/2/13.
 */

public class MarqueeView extends View {

    private TextPaint mPaint;
    private String mText;
    private int mSpeed;
    private float mCurLeft;
    private float mLastLeft;
    private ValueAnimator mAnimator;
    private float mLength;
    private boolean mIsRunning;
    private int mSpacing;
    private TimeInterpolator mInterpolator;

    public MarqueeView(Context context) {
        this(context, null);
    }

    public MarqueeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarqueeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaint = new TextPaint();
        mPaint.setAntiAlias(true);
        mCurLeft = mLastLeft = 0;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MarqueeView);
        mSpeed = a.getDimensionPixelSize(R.styleable.MarqueeView_speed, 50);
        int textColor = a.getColor(R.styleable.MarqueeView_text_color, Color.BLACK);
        int textSize = a.getDimensionPixelSize(R.styleable.MarqueeView_text_size, 24);
        int shadowColor = a.getColor(R.styleable.MarqueeView_text_shadowColor, Color.TRANSPARENT);
        float radius = a.getFloat(R.styleable.MarqueeView_txt_radius, 0f);
        float dx = a.getFloat(R.styleable.MarqueeView_txt_dx, 0f);
        float dy = a.getFloat(R.styleable.MarqueeView_txt_dy, 0f);
        mSpacing = a.getDimensionPixelOffset(R.styleable.MarqueeView_txt_spacing, 10);
        a.recycle();
        mPaint.setTextSize(textSize);
        mPaint.setColor(textColor);
        mPaint.setShadowLayer(radius, dx, dy, shadowColor);
    }

    private void ensureAnimator() {
        mAnimator = ValueAnimator.ofFloat(0, 1);
        mAnimator.setInterpolator(mInterpolator == null ? new LinearInterpolator() : mInterpolator);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.setRepeatMode(ValueAnimator.RESTART);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float factor = valueAnimator.getAnimatedFraction();
                mCurLeft = mLastLeft - factor * mLength;
                if (mCurLeft < -mLength) {
                    mCurLeft += mLength;
                }
                setTranslationX(mCurLeft);
            }
        });
    }

    //make the view right outside of its parent about the length of the text
    public void setText(String text) {
        mText = text;
        mLength = mPaint.measureText(mText) + mSpacing;
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) getLayoutParams();
        lp.rightMargin = -(int) mLength;
        setLayoutParams(lp);
        invalidate();
    }

    public void setInterpolator(TimeInterpolator interpolator) {
        mInterpolator = interpolator;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (TextUtils.isEmpty(mText) || mLength == 0) return;
        float left = 0;
        while (left < getWidth()) {
            canvas.drawText(mText, left, getPaddingTop() - mPaint.ascent(), mPaint);
            left += mLength;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // only make the view wrap txt
        int height = (int) (mPaint.descent() - mPaint.ascent()) + getPaddingBottom() + getPaddingTop();
        setMeasuredDimension(View.MeasureSpec.getSize(widthMeasureSpec), height);
    }

    public void start() {
        if (mAnimator == null) {
            ensureAnimator();
        }

        if (mIsRunning) return;
        mIsRunning = true;
        mAnimator.setDuration((long) (mLength * 1000 / mSpeed));
        mAnimator.start();
    }

    public void pause() {
        if (mAnimator != null) {
            mAnimator.cancel();
            if (mCurLeft <= -mLength) {
                mCurLeft += mLength;
            }
            mLastLeft = mCurLeft;
            setTranslationX(mCurLeft);
        }
        mIsRunning = false;
    }

    public void stop() {
        if (mAnimator != null) {
            mAnimator.cancel();
            mLastLeft = mCurLeft = 0;
            setTranslationX(0);
            mAnimator = null;
        }
        mIsRunning = false;
    }
}
