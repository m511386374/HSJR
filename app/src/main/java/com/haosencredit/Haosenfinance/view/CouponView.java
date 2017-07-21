package com.haosencredit.Haosenfinance.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.LinearLayout;

import com.haosencredit.Haosenfinance.R;


/**
 * Created by 81118 on 2016/7/27.
 */
public class CouponView extends LinearLayout {

    private float radius = 10;
    private float gap = 10;
    private int radiusBackgroundColor = Color.WHITE;

    private float hside = 10,vside;

    private Paint paint ;
    private int hNums = 20;
    private int vNums = 10;

    private boolean showHorizontal = true;
    private boolean showVertical = true;


    private int l,r,t,b;

    public CouponView(Context context) {
        this(context, null);
    }

    public CouponView(Context context, AttributeSet attrs) {

        this(context, attrs, 0);

    }

    public CouponView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        Log.i("lxc","33333333");

        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CouponView,defStyleAttr,0);
        //需要把dip单位转为px


        radius = ta.getDimensionPixelSize(R.styleable.CouponView_gap,(int)
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,4,getResources().getDisplayMetrics()));

        gap = ta.getDimensionPixelSize(R.styleable.CouponView_radius,(int)
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,4,getResources().getDisplayMetrics()));
        radiusBackgroundColor = ta.getColor(R.styleable.CouponView_radiusBackgroundColor,Color.WHITE);
        showHorizontal = ta.getBoolean(R.styleable.CouponView_showHorizontal,false);
        showVertical = ta.getBoolean(R.styleable.CouponView_showVertical,false);


        ta.recycle();

        paint = new Paint();
        paint.setColor(radiusBackgroundColor);



    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CouponView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        Log.i("lxc","4444444444");

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CouponView);
        //需要把dip单位转为px
        radius =ta.getDimensionPixelSize(R.styleable.CouponView_gap,(int)
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,4,getResources().getDisplayMetrics()));
        gap = ta.getDimensionPixelSize(R.styleable.CouponView_radius,(int)
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,4,getResources().getDisplayMetrics()));
        radiusBackgroundColor = ta.getColor(R.styleable.CouponView_radiusBackgroundColor,Color.WHITE);

        showHorizontal = ta.getBoolean(R.styleable.CouponView_showHorizontal,false);
        showVertical = ta.getBoolean(R.styleable.CouponView_showVertical,false);

        ta.recycle();

        paint = new Paint();
        paint.setColor(radiusBackgroundColor);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Resources r = getResources();
        float x = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, r.getDisplayMetrics());
        float y = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, r.getDisplayMetrics());

        if(showVertical) {
            for (int i = 0; i < vNums + 1; i++) {
                canvas.drawCircle(x, vside / 2 + radius + i * (2 * radius + gap)+y, radius, paint);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        hside = getWidth()%(2*radius + gap)-2*radius;
        vside = getHeight()%(2*radius + gap)-2*radius;
        hNums = (int)((getWidth()-0)/(2*radius + gap));
        vNums = (int)((getHeight()-0)/(2*radius + gap));



        this.l= l;
        this.t= t;
        this.r= r;
        this.b= b;


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}