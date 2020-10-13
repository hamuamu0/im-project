package com.soul.framework.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.soul.framework.R;

import androidx.annotation.Nullable;

/**
 * 类或接口的描述信息
 *
 * @Author:qubin
 * @Theme:
 * @Data:2020-10-13
 * @Describe:
 */
public class TouchPictureView extends View {

    //背景的bitmap
    private Bitmap bgBitmap = null;
    //背景画笔
    private Paint bgPaint = null;

    //空白的bitmap
    private Bitmap nullBitmap = null;
    //空白画笔
    private Paint nullPaint = null;

    //移动方块
    private Bitmap moveBitmp = null;
    //移动小方块画笔
    private Paint movePaint = null;

    //View的宽高
    private int mWidth;
    private int mHeight;

    //白块宽高
    private int CARD_SIZE = 200;

    //空白方块的横坐标和纵坐标
    private int LINE_W,LINE_H = 0;

    //移动方块位置
    private int moveX = 200;

    //误差值
    private int errorValue = 10;

    private OnViewResultLinstener onViewResultLinstener;

    public void setOnViewResultLinstener(OnViewResultLinstener onViewResultLinstener) {
        this.onViewResultLinstener = onViewResultLinstener;
    }

    public TouchPictureView(Context context) {
        super(context);
        init();
    }



    public TouchPictureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }



    public TouchPictureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    private void init() {
        bgPaint = new Paint();
        nullPaint = new Paint();
        movePaint = new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制背景
        drawBg(canvas);

        //绘制空的白块
        drawNullCard(canvas);

        //绘制移动的方块
        drawMoveCard(canvas);

    }

    /**
     * 绘制移动的方块
     * @param canvas
     */
    private void drawMoveCard(Canvas canvas) {
        //截取空白块位置坐标的Bitmap图像
        moveBitmp = Bitmap.createBitmap(bgBitmap, LINE_W, LINE_H, CARD_SIZE, CARD_SIZE);
        //绘制在View上
        canvas.drawBitmap(moveBitmp,moveX,LINE_H,movePaint);
    }

    /**
     * 绘制空的白块
     * @param canvas
     */
    private void drawNullCard(Canvas canvas) {
        //1.获取图片
        nullBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.img_null_card);

        //2.计算值
        CARD_SIZE = nullBitmap.getWidth();
        LINE_W = mWidth / 3 * 2;
        LINE_H = mHeight / 2 - (CARD_SIZE / 2);

        //3.绘制
        canvas.drawBitmap(nullBitmap,LINE_W,LINE_H,nullPaint);
    }

    /**
     * 绘制背景
     * @param canvas
     */
    private void drawBg(Canvas canvas){
        //1.获取背景图片
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_bg);

        //2.创建一个空的bitmap
        bgBitmap = Bitmap.createBitmap(mWidth,mHeight, Bitmap.Config.ARGB_8888);

        //3.将图片绘制到空的bitmap中
        Canvas bgCanvas = new Canvas(bgBitmap);
        bgCanvas.drawBitmap(bitmap,null,new Rect(0,0,mWidth,mHeight),bgPaint);

        //4.将bgBitmap绘制到View上
        canvas.drawBitmap(bgBitmap,null,new Rect(0,0,mWidth,mHeight),bgPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                //判断按下的坐标是否在方块内部，如果在内部才允许其拖动

                break;

            case MotionEvent.ACTION_MOVE:
                //防止越界
                if(event.getX() > 0 && event.getX() < (mWidth - CARD_SIZE)){
                    moveX = (int)event.getX();
                    invalidate();
                }
                break;

            case MotionEvent.ACTION_UP:
                /**
                 * 抬起之后做验证
                 * moveX = LINE_W 并且存在一定的误差即可成功
                 */
                if (moveX > (LINE_W - errorValue) && moveX < (LINE_W + errorValue)){
                    if (onViewResultLinstener != null){
                        onViewResultLinstener.onResult();

                        //成功之后让坐标重置
                        moveX = 200;
                        invalidate();
                    }
                }

                break;
        }
        return true;
    }

    public interface OnViewResultLinstener{
        void onResult();
    }
}
