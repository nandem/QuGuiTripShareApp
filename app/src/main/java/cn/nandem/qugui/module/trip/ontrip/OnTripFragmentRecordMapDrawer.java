package cn.nandem.qugui.module.trip.ontrip;

import android.content.Context;
import android.graphics.*;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import cn.nandem.qugui.R;

/**
 * @author Nandem on 17-4-8.
 */

public class OnTripFragmentRecordMapDrawer extends View
{
    public OnTripFragmentRecordMapDrawer(Context context)
    {
        super(context);
    }

    public OnTripFragmentRecordMapDrawer(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public OnTripFragmentRecordMapDrawer(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public OnTripFragmentRecordMapDrawer(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    Paint p = new Paint();
    Path path2=new Path();
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        p.setColor(Color.BLACK);// 设置红色
        p.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了
        canvas.drawCircle(120, 100, 15, p);// 大圆
        canvas.drawCircle(820, 350, 15, p);// 大圆
        canvas.drawCircle(580, 200, 15, p);// 大圆
        canvas.drawCircle(490, 800, 15, p);// 大圆
        canvas.drawCircle(720, 1060, 15, p);// 大圆
        canvas.drawCircle(450, 480, 15, p);// 大圆

        p.reset();
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.BLACK);
        p.setStrokeWidth(8);
        p.setAntiAlias(true);
        path2.moveTo(120, 100);//设置Path的起点
        path2.lineTo(580, 200);//设置Path的起点
        path2.lineTo(820, 350);//设置Path的起点
        path2.lineTo(450, 480);//设置Path的起点
        path2.lineTo(490, 800);//设置Path的起点
        path2.lineTo(720, 1060);//设置Path的起点
        canvas.drawPath(path2, p);
        p.reset();
        p.setAntiAlias(true);
        p.setTextSize(100);
        canvas.drawText("成都", 50, this.getHeight() - 50, p);
    }
}
