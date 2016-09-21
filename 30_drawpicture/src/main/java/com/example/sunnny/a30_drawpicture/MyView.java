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
        drawRect(100,100,400,200);
        draw();
        drawText();
    }



    public void draw(){
        Path path = new Path();
        //画三角形
        path.moveTo(500,120);
        path.lineTo(1400,120);
        path.lineTo(1200,1400);
        path.close();
        canvas.drawPath(path,paint);
    }
    public void drawRect(float left,float top,float right,float bottom){
        //画矩形
        canvas.drawRect(left,top,right,bottom,paint);
    }
    //画文字
    public void drawText(){
        paint.setTextSize(100);
        Path path = new Path();
        path.moveTo(50,200);
        paint.setStrikeThruText(true);
        Path path_text = new Path();
        //文字画成环形
        path_text.addCircle(200,1000,100,Path.Direction.CCW);
        canvas.drawTextOnPath("Nice job!",path_text,0,10,paint);
    }

}
