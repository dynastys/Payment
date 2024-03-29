package com.xuanyin.payment.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.xuanyin.easy.DensityUtils;
import com.xuanyin.payment.R;

/**
 * 欢迎页圆形进度条自定义view
 */

public class CountDownView extends View {

    /**
     * 圆圈的颜色
     */
    private int mCircleColor;

    /**
     * 圆圈的宽度
     */
    private int mCircleWith;

    /**
     * 画笔
     */
    private Paint mPaint;

    /**
     * 当前进度
     */
    private int mProgress;

    /**
     * 圆轮进度值文本大小
     */

    private int mTextSize;

    /**
     * 圆轮进度值文本颜色
     */
    private int mTextColor;

    /**
     * 圆轮进度值文本文字
     */
    private String mTextName;

    /**
     * 是否正在运行
     */
    public boolean isRunning = true;


    public CountDownView(Context context) {
        this(context, null);
    }

    public CountDownView(Context context,  AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 必要的初始化，获得一些自定义的值
     *
     * @param context      上下文
     * @param attrs        attrs
     * @param defStyleAttr defStyleAttr
     */
    public CountDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CountdownView, defStyleAttr, 0);
        mTextSize = array.getDimensionPixelSize(R.styleable.CountdownView_TextSize, DensityUtils.sp2px(context, 10));
        mTextColor = array.getColor(R.styleable.CountdownView_TextColor, context.getResources().getColor(R.color.view_textColor));
        mTextName = (String) array.getText(R.styleable.CountdownView_TextName);

        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.CountdownView_circleColor:
                    mCircleColor = array.getColor(attr, context.getResources().getColor(R.color.view_circleColor));
                    break;
                case R.styleable.CountdownView_circleWith:
                    mCircleWith = array.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                default:
                    break;
            }
        }
        array.recycle();

        mPaint = new Paint();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //获取圆心坐标
        int centre = getWidth() / 2;
        //半径
        int radius = centre - mCircleWith / 2;
        //设置圆环宽度
        mPaint.setStrokeWidth(mCircleWith);
        //消除锯齿
        mPaint.setAntiAlias(true);
        //设置空心
        mPaint.setStyle(Paint.Style.STROKE);

        //用于定义的圆弧的形状和大小的界限
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);

        //canvas.drawCircle(centre, centre, radius, mPaint);//换出圆环
        //设置圆环的颜色
        mPaint.setColor(mCircleColor);
        //根据进度画圆弧: 顺时针画圆弧
        canvas.drawArc(oval, -90, mProgress, false, mPaint);
        //根据进度画圆弧 : 逆时针画圆弧
        //canvas.drawArc(oval, -90, -mProgress, false, mPaint);

        //绘制文本，可以根据需求进行更改，例如倒计时几秒
        Paint textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
//        String text = mCountdownTime - (int) (mCurrentProgress / 360f * mCountdownTime) + "";
        mTextName = "跳过";
        textPaint.setTextSize(mTextSize);
        textPaint.setColor(mTextColor);

        //文字居中显示
        Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
        int baseline = (int) ((oval.bottom + oval.top - fontMetrics.bottom - fontMetrics.top) / 2);
        canvas.drawText(mTextName, oval.centerX(), baseline, textPaint);

    }


    /**
     * 播放倒计时动画
     */
    public void play() {
        //绘制线程
        new Thread() {
            @Override
            public void run() {
                while (isRunning) {
                    mProgress++;
                    postInvalidate();
                    try {
                        Thread.sleep(timeMillis / 360);



                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }


    /**
     * 倒计时时间
     */
    private long timeMillis = 3000;

    /**
     * 设置倒计时时间
     */
    public void setTimeMillis(long timeMillis) {
        this.timeMillis = timeMillis;
        invalidate();
    }

    private TextView text;

    public void setTxt(TextView text) {
        this.text = text;
        invalidate();
    }

}
