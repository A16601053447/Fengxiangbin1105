package fengxiangbin.bwei.com.fengxiangbin1105;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

/**
 * Created by 命运的尘 on 2018/11/5.
 */

public class DiskView extends View implements View.OnClickListener{
    //初始化数据
    private ClickInterface mClickInterface;

    private RotateAnimation mRotateAnimation;
    private int[] colors = new int[]{Color.parseColor("#00ee00"),Color.parseColor("#ff3483"),Color.parseColor("#ff8247"),Color.parseColor("#ffd39b"),Color.parseColor("#ffd700"),Color.parseColor("#8ee5ee")};
    private Paint mPaint;
    private Paint starPaint;
    private int mWidth;
    private int mPadding;
    private Boolean isStart = false;
    private RectF mRectF;
    private String str = "start";
    private String[] contents = new String[]{"一等奖","二等奖","三等奖","四等奖","参与奖","谢谢参与",};
    public DiskView(Context context) {
        this(context,null);
    }

    public DiskView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DiskView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化画笔
        initPaint();
        //画圆
        initData();
        setOnClickListener(this);
    }
    //初始化画笔
    private void initPaint() {

        starPaint = new Paint();
        starPaint.setStyle(Paint.Style.STROKE);
        starPaint.setAntiAlias(true);
        starPaint.setColor(Color.WHITE);
        starPaint.setStrokeWidth(5);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(3);

    }

    private void initData() {
        mRotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateAnimation.setRepeatCount(-1);
        mRotateAnimation.setFillAfter(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mPadding = 5;
        //画圆
        initRect();
    }

    private void initRect() {
        mRectF = new RectF(0,0,mWidth,mWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(mWidth/2,mWidth/2,mWidth-mPadding,mPaint);
        //绘制里面的6个椭圆
        mPaint.setStyle(Paint.Style.FILL);
        initArc(canvas);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(mWidth/2,mWidth/2,50,mPaint);

        //绘制里面的圆心
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(24);
        Rect rect = new Rect();
        mPaint.getTextBounds(str,0,str.length(),rect);
        int textWidth = rect.width();
        int textHight = rect.height();
        canvas.drawText(str,mWidth/2-25+25-textWidth/2,mWidth/2+textHight/2,mPaint);


    }

    private void initArc(Canvas canvas) {
        for(int i=0;i<colors.length;i++){
            mPaint.setColor(colors[i]);
            canvas.drawArc(mRectF,(i-1)*60+60,60,true,mPaint);
        }
        for(int i=0;i<colors.length;i++){
            mPaint.setColor(Color.BLACK);
            Path mpath = new Path();
            mpath.addArc(mRectF,(i-1)*60+60,60);
            canvas.drawTextOnPath(contents[i],mpath,60,60,mPaint);
        }
    }
        public void setCustomOnClickListener(ClickInterface clickListener){
                this.mClickInterface=clickListener;


        }




    private void stopAnmi() {
        clearAnimation();
    }

    @Override
    public void onClick(View v) {
        if (!isStart){
            isStart=true;
            mRotateAnimation.setDuration(1000);
            //不会停顿
            mRotateAnimation.setInterpolator(new LinearInterpolator());
            startAnimation(mRotateAnimation);
        }else{
            isStart=false;
            stopAnmi();
        }
    }
}
