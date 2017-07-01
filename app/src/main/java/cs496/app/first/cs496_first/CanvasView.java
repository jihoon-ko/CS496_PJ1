package cs496.app.first.cs496_first;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
/**
 * Created by q on 2017-07-01.
 */

public class CanvasView extends View
{
    private float[][] coor= new float[9][2];
    private final Rect textBounds = new Rect();
    private String[] su = {"1", "2", "3", "4", "5", "6", "7", "8"};
    private int moveCnt = 0;

    public CanvasView(Context c){
        super(c);
        initView();
    }
    public CanvasView(Context c, AttributeSet attrs){
        super(c, attrs);
        initView();
    }
    public CanvasView(Context c, AttributeSet attrs, int defStyle){
        super(c, attrs, defStyle);
        initView();
    }
    protected void initView(){
        shuffle();
    }
    public void shuffle(){
        int[] place = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        for(int i=0;i<100;i++){
            int one = (int)(Math.random()*8);
            int two = (int)(Math.random()*8);
            int tmp = place[one];
            place[one] = place[two];
            place[two] = tmp;
        }
        for(int i=0;i<9;i++){
            coor[i][1] = (float)((place[i]/3) * (1.0/3.0));
            coor[i][0] = (float)((place[i]%3) * (1.0/3.0));
        }
        moveCnt = 0;
        invalidate();
    }
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        Paint pnt = new Paint();
        pnt.setColor(Color.BLACK);
        int width = getWidth();
        int height = getHeight();
        if(width < height) {
            //Button startButton = (Button) findViewById(R.id.canvas_button);
            //startButton.setVisibility(View.VISIBLE);
            int gili = (width / 3) - 40;
            int ww = width - 120;
            canvas.drawRect(30, 30, width - 30, width - 30, pnt);
            pnt.setTextAlign(Paint.Align.CENTER);
            pnt.setTextSize(gili - 100);
            for (int i = 0; i < 8; i++) {
                //if (i % 2 == 0)
                pnt.setColor(Color.CYAN);
                //else pnt.setColor(Color.MAGENTA);
                canvas.drawRect((90 + (ww * coor[i][0])), 90 + (ww * coor[i][1]), 30 + gili + (ww * coor[i][0]), 30 + gili + (ww * coor[i][1]), pnt);
                pnt.setColor(Color.BLACK);
                pnt.getTextBounds(su[i], 0, 1, textBounds);
                canvas.drawText(su[i], (60 + gili / 2 + (ww * coor[i][0])), (60 + gili / 2 + (ww * coor[i][1])) - textBounds.exactCenterY(), pnt);
            }
            pnt.setTextSize(130);
            canvas.drawText(("Move: "+ moveCnt), width/2, width + 90 - textBounds.exactCenterY(), pnt);
        }else{
            pnt.setTextSize(100);
            canvas.drawText("세로만 지원합니다... ㅠㅠ", 50, height/2, pnt);
            //Button startButton = (Button) findViewById(R.id.canvas_button);
            //startButton.setVisibility(View.INVISIBLE);
        }
    }
}