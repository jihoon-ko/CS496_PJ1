package cs496.app.first.cs496_first;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by q on 2017-07-01.
 */

public class CanvasView extends View
{
    private float[][] coor= new float[9][2];
    private final Rect textBounds = new Rect();
    private String[] su = {"1", "2", "3", "4", "5", "6", "7", "8"};
    private int[] zero = {2, 2};
    private int[][] where = new int[3][3];
    private int moveCnt = 0;
    private int xx = -1;
    private int yy = -1;
    //private Context cxt;
    private boolean youwin = false;

    public CanvasView(Context c){
        super(c);
        //cxt = c;
        initView();
    }
    public CanvasView(Context c, AttributeSet attrs){
        super(c, attrs);
        //cxt = c;
        initView();
    }
    public CanvasView(Context c, AttributeSet attrs, int defStyle){
        super(c, attrs, defStyle);
        //cxt = c;
        initView();
    }
    protected void initView(){
        shuffle();
    }
    public void shuffle(){
        int[] place = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        su[0] = "1";su[1] = "2";su[2] = "3";su[3] = "4";su[4] = "5";su[5] = "6";su[6] = "7";su[7] = "8";
        for(int i=0;i<100;i++){
            int one = (int)(Math.random()*8);
            int two = (int)(Math.random()*8);
            int tmp = place[one];
            place[one] = place[two];
            place[two] = tmp;
        }
        for(int i=0;i<9;i++) {
            coor[i][1] = (float) ((place[i] / 3) * (1.0 / 3.0));
            coor[i][0] = (float) ((place[i] % 3) * (1.0 / 3.0));
            where[place[i]/3][place[i]%3] = i;
        }
        moveCnt = 0;
        zero[0] = 2; zero[1] = 2;
        youwin = false;
        invalidate();
    }
    @Override
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
                if(!youwin) pnt.setColor(Color.CYAN);
                else pnt.setColor(Color.MAGENTA);
                //else pnt.setColor(Color.MAGENTA);
                canvas.drawRect((70 + (ww * coor[i][0])), 70 + (ww * coor[i][1]), 50 + gili + (ww * coor[i][0]), 50 + gili + (ww * coor[i][1]), pnt);
                pnt.setColor(Color.BLACK);
                pnt.getTextBounds(su[i], 0, 1, textBounds);
                canvas.drawText(su[i], (60 + gili / 2 + (ww * coor[i][0])), (60 + gili / 2 + (ww * coor[i][1])) - textBounds.exactCenterY(), pnt);
            }
            pnt.setTextSize(130);
            if(youwin) canvas.drawText("You WIN!!!", width/2, width + 90 - textBounds.exactCenterY(), pnt);
            else canvas.drawText(("Move: "+ moveCnt), width/2, width + 90 - textBounds.exactCenterY(), pnt);
        }else{
            pnt.setTextSize(100);
            canvas.drawText("세로만 지원합니다... ㅠㅠ", 50, height/2, pnt);
            //Button startButton = (Button) findViewById(R.id.canvas_button);
            //startButton.setVisibility(View.INVISIBLE);
        }
        if(zero[0] == 2 && zero[1] == 2) {
            boolean finished = true;
            for (int i = 0; i < 3; i++) {
                for(int j=0;j<3;j++){
                    if(i==2 && j==2) break;
                    if(where[i][j] != i*3+j){
                        finished = false;
                        break;
                    }
                }
                if(!finished) break;
            }
            if(finished){
                youwin = true;
                su[0] = "T";su[1] = "H";su[2] = "A";su[3] = "N";su[4] = "K";su[5] = "Y";su[6] = "O";su[7] = "U";
                invalidate();
            }
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (youwin) return true;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            xx = x;
            yy = y;
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (xx != -1 && yy != -1) {
                //System.out.println("UP: "+x+" "+y);
                if (Math.abs(xx - x) > Math.abs(yy - y)) {
                    // left or right
                    if (xx - x > getWidth() * 0.15) {
                        //System.out.println("LEFT");
                        if (zero[1] != 2) {
                            zero[1] += 1;
                            coor[where[zero[0]][zero[1]]][0] = (float) ((zero[1] - 1) / 3.0);
                            where[zero[0]][zero[1] - 1] = where[zero[0]][zero[1]];
                            moveCnt += 1;
                        }
                    } else if (xx - x < getWidth() * -0.15) {
                        //System.out.println("RIGHT");
                        if (zero[1] != 0) {
                            zero[1] -= 1;
                            coor[where[zero[0]][zero[1]]][0] = (float) ((zero[1] + 1) / 3.0);
                            where[zero[0]][zero[1] + 1] = where[zero[0]][zero[1]];
                            moveCnt += 1;
                        }
                    }
                } else {
                    if (yy - y > getWidth() * 0.15) {
                        //System.out.println("UP");
                        if (zero[0] != 2) {
                            zero[0] += 1;
                            coor[where[zero[0]][zero[1]]][1] = (float) ((zero[0] - 1) / 3.0);
                            where[zero[0] - 1][zero[1]] = where[zero[0]][zero[1]];
                            moveCnt += 1;
                        }
                    } else if (yy - y < getWidth() * -0.15) {
                        //System.out.println("DOWN");
                        if (zero[0] != 0) {
                            zero[0] -= 1;
                            coor[where[zero[0]][zero[1]]][1] = (float) ((zero[0] + 1) / 3.0);
                            where[zero[0] + 1][zero[1]] = where[zero[0]][zero[1]];
                            moveCnt += 1;
                        }
                    }
                }
                for(int i=0;i<3;i++){
                    for(int j=0;j<3;j++){
                        if(i == zero[0] && j == zero[1]) continue;
                        coor[where[i][j]][0] = (float)(j / 3.0);
                        coor[where[i][j]][1] = (float)(i / 3.0);
                    }
                }
            }
            xx = -1;
            yy = -1;
            invalidate();
        }
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            int x = (int)event.getX();
            int y = (int)event.getY();
            int width = getWidth();
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(i == zero[0] && j == zero[1]) continue;
                    coor[where[i][j]][0] = (float)(j / 3.0);
                    coor[where[i][j]][1] = (float)(i / 3.0);
                }
            }
            if(xx != -1 && yy != -1) {
                if (Math.abs(xx - x) > Math.abs(yy - y)) {
                    if (xx - x > 0) {
                        //System.out.println("LEFT");
                        if(zero[1] != 2){
                            coor[where[zero[0]][zero[1]+1]][0] = (float)((zero[1]+1) / 3.0) - (Math.min(((float)(xx-x) / (float)(width * 0.3)), (float)1.0) / (float)3.0);
                        }
                    } else if (xx - x < 0) {
                        //System.out.println("RIGHT");
                        if(zero[1] != 0){
                            coor[where[zero[0]][zero[1]-1]][0] = (float)((zero[1]-1) / 3.0) + (Math.min(((float)(x-xx) / (float)(width * 0.3)), (float)1.0) / (float)3.0);
                        }
                    }
                } else {
                    if (yy - y > 0) {
                        if(zero[0] != 2){
                            coor[where[zero[0]+1][zero[1]]][1] = (float)((zero[0]+1) / 3.0) - (Math.min(((float)(yy-y) / (float)(width * 0.3)), (float)1.0) / (float)3.0);
                        }
                    } else if (yy - y < 0) {
                        if(zero[0] != 0){
                            coor[where[zero[0]-1][zero[1]]][1] = (float)((zero[0]-1) / 3.0) + (Math.min(((float)(y-yy) / (float)(width * 0.3)), (float)1.0) / (float)3.0);
                        }
                    }
                }
            }
            invalidate();
        }
        return true;
    }
}