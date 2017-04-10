package com.bawei.jiajianhai.jiajianhai20170410.draw;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.bawei.jiajianhai.jiajianhai20170410.R;

/**
 * @类的用途:
 * @author:jiajianhai
 * @date:2017/4/10
 */

public class DrawCircle extends View {


    private int innerCircleDiameter;
    private int outerCircleDiameter;
    private int innerColor;
    private int outercolor;
    private int divLength;
    private int divColor;
    private int noSelectedOutColor;
    private int topTextSize;
    private int buttomTextSize;
    private int textColor;
    private double progress;
    private String topText;
    private String buttomText;
    private Rect topF, buttomF;
    private Paint topP, buttomP, outerSP, outerP, divP, innerP;
    private int width = 0;
    private int height = 0;
    private RectF innerF, divF,outerF;
   int  num=0;
    private DrawCircle circleProgressBar;



    public void setProgress(double progress) {
        this.progress = progress;
        postInvalidate();
    }

    public void setTopText(String topText) {
        this.topText = topText;
        topP.getTextBounds(topText, 0, topText.length(), topF);
        postInvalidate();
    }

    public void setButtomText(String buttomText) {
        this.buttomText = buttomText;
        buttomP.getTextBounds(buttomText, 0, buttomText.length(), buttomF);
        postInvalidate();
    }

    public DrawCircle(Context context, AttributeSet attrs,
                               int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DrawCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.MyCircleProgress, 0, 0);
        int n = typedArray.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.MyCircleProgress_innercirclediameter:
                    innerCircleDiameter = typedArray.getDimensionPixelSize(i, 0);
                    break;
                case R.styleable.MyCircleProgress_outercirclediameter:
                    outerCircleDiameter = typedArray.getDimensionPixelSize(i, 0);
                    break;
                case R.styleable.MyCircleProgress_innercolor:
                    innerColor = typedArray.getColor(i, Color.RED);
                    break;
                case R.styleable.MyCircleProgress_outercolor:
                    outercolor = typedArray.getColor(i, Color.RED);
                    break;
                case R.styleable.MyCircleProgress_divlength:
                    divLength = typedArray.getDimensionPixelSize(i, 0);
                    break;
                case R.styleable.MyCircleProgress_divcolor:
                    divColor = typedArray.getColor(i, Color.GRAY);
                    break;
                case R.styleable.MyCircleProgress_noselectedoutcolor:
                    noSelectedOutColor = typedArray.getColor(i, Color.GREEN);
                    break;
                case R.styleable.MyCircleProgress_toptextsize:
                    topTextSize = typedArray.getDimensionPixelSize(i, 0);
                    break;
                case R.styleable.MyCircleProgress_buttomtextsize:
                    buttomTextSize = typedArray.getDimensionPixelSize(i, 0);
                    break;
                case R.styleable.MyCircleProgress_textcolor:
                    textColor = typedArray.getColor(i, Color.WHITE);
                    break;
                case R.styleable.MyCircleProgress_toptext:
                    topText = typedArray.getString(i);
                    break;
                case R.styleable.MyCircleProgress_buttomtext:
                    buttomText = typedArray.getString(i);
                    break;
                case R.styleable.MyCircleProgress_progress:
                    progress = typedArray.getFloat(i, (float) 100.0);
                    break;
                default:
                    break;
            }
        }
        topF = new Rect();
        topP = new Paint();
        topP.setTextSize(topTextSize);
        topP.setColor(textColor);
        topP.getTextBounds(topText, 0, topText.length(), topF);
        buttomF = new Rect();
        buttomP = new Paint();
        buttomP.setTextSize(buttomTextSize);
        buttomP.setColor(textColor);
        buttomP.getTextBounds(buttomText, 0, buttomText.length(), buttomF);
        outerSP = new Paint();
        outerSP.setColor(outercolor);
        outerP = new Paint();
        outerP.setColor(noSelectedOutColor);
        divP = new Paint();
        divP.setColor(divColor);
        innerP = new Paint();
        innerP.setColor(innerColor);
        typedArray.recycle();
        circleProgressBar = (DrawCircle) findViewById(R.id.progress);
        circleProgressBar.setTopText("完成 50%");
        circleProgressBar.setProgress(50);
        circleProgressBar.setButtomText("");




    }

    public DrawCircle(Context context) {
        super(context);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        width = 0;
        height = 0;

        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        switch (specMode) {
            case MeasureSpec.EXACTLY:
                width = getPaddingLeft() + getPaddingRight() + specSize;
                break;
            case MeasureSpec.AT_MOST:
                width = getPaddingLeft() + getPaddingRight() + outerCircleDiameter;
            default:
                break;
        }

        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);

        switch (specMode) {
            case MeasureSpec.EXACTLY:
                height = getPaddingTop() + getPaddingBottom() + specSize;
                break;
            case MeasureSpec.AT_MOST:
                height = getPaddingTop() + getPaddingBottom() + outerCircleDiameter;
                break;
            default:
                break;
        }
        innerF = new RectF((width - innerCircleDiameter) / 2,
                (height - innerCircleDiameter) / 2,
                (width - innerCircleDiameter) / 2 + innerCircleDiameter,
                (height - innerCircleDiameter) / 2 + innerCircleDiameter);
        divF = new RectF((width - innerCircleDiameter) / 2 - divLength,
                (height - innerCircleDiameter) / 2 - divLength,
                (width - innerCircleDiameter) / 2 + innerCircleDiameter
                        + divLength, (height - innerCircleDiameter) / 2
                + innerCircleDiameter + divLength);
        outerF = new RectF((width - outerCircleDiameter) / 2,
                (height - outerCircleDiameter) / 2,
                (width - outerCircleDiameter) / 2 + outerCircleDiameter,
                (height - outerCircleDiameter) / 2 + outerCircleDiameter);
        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawArc(outerF, -90, 360, false, outerP);
        canvas.drawArc(outerF, -90, (float)(progress/100)*360, true, outerSP);
        canvas.drawArc(divF, 0, 360, false, divP);
        canvas.drawArc(innerF, 0, 360, false, innerP);
        canvas.drawText(topText, (width - topF.width()) / 2,
                height / 2 - topF.height() / 4, topP);
        canvas.drawText(buttomText, (width - buttomF.width()) / 2, height / 2
                + buttomF.height(), buttomP);



    }


}
   /* private final Paint paint;
    private final Context context;

    public DrawCircle(Context context) {

        // TODO Auto-generated constructor stub
        this(context, null);
    }

    public DrawCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.paint = new Paint();
        this.paint.setAntiAlias(true); //消除锯齿
        this.paint.setStyle(Paint.Style.STROKE); //绘制空心圆
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        int center = getWidth() / 2;
        int innerCircle = dip2px(context, 83); //设置内圆半径
        int ringWidth = dip2px(context, 25); //设置圆环宽度

        //绘制内圆
        this.paint.setARGB(155, 167, 190, 206);
        this.paint.setStrokeWidth(2);
        canvas.drawCircle(center, center, innerCircle, this.paint);

        //绘制圆环
        this.paint.setARGB(255, 212, 225, 233);
        this.paint.setStrokeWidth(ringWidth);
        canvas.drawCircle(center, center, innerCircle + 1 + ringWidth / 2, this.paint);

        //绘制外圆
        this.paint.setARGB(155, 167, 190, 206);
        this.paint.setStrokeWidth(2);
        canvas.drawCircle(center, center, innerCircle + ringWidth, this.paint);


        super.onDraw(canvas);
    }


    *//**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *//*
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }*/


