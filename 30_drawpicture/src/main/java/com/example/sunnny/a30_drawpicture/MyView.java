package com.example.sunnny.a30_drawpicture;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sunnny on 2016/9/8.
 */
public class MyView extends View {
    private Paint paint;
    private Canvas canvas;
    public MyView(Context context,AttributeSet attributeSet) {

        super(context,attributeSet);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画布
        this.canvas = canvas;
        canvas.drawColor(Color.GREEN);
        //画笔
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(5);
        drawRect(100,100,200,200);
        draw();
        drawText();
    }
    //画文字
    public void drawText(){
        paint.setTextSize(100);
        Path path = new Path();
        path.moveTo(50,300);
        paint.setStrikeThruText(true);
        Path text_path = new Path();
        text_path.addCircle(200,300,80,Path.Direction.CCW);
        canvas.drawTextOnPath("Nice!",text_path,0,10,paint);
    }

    public void drawRect(float left,float top,float right,float bottom){
        //画矩形
        canvas.drawRect(left,top,right,bottom,paint);
    }

    public void draw(){
        Path path = new Path();
        //画三角形
        path.moveTo(500,0);
        path.lineTo(1000,0);
        path.lineTo(1200,1000);
        path.close();
        canvas.drawPath(path,paint);
    }
}
