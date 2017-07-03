package cs496.app.first.cs496_first;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import static cs496.app.first.cs496_first.C.chueck;
import static cs496.app.first.cs496_first.C.coor;
import static cs496.app.first.cs496_first.C.moveCnt;
import static cs496.app.first.cs496_first.C.mv;
import static cs496.app.first.cs496_first.C.mvdirec;
import static cs496.app.first.cs496_first.C.recent;
import static cs496.app.first.cs496_first.C.simulating;
import static cs496.app.first.cs496_first.C.start_first;
import static cs496.app.first.cs496_first.C.su;
import static cs496.app.first.cs496_first.C.where;
import static cs496.app.first.cs496_first.C.xx;
import static cs496.app.first.cs496_first.C.youwin;
import static cs496.app.first.cs496_first.C.yy;
import static cs496.app.first.cs496_first.C.zero;

/**
 * Created by q on 2017-07-01.
 */

public class CanvasView extends View {
    private final Rect textBounds = new Rect();

    private int[] suu = new int[9];
    private int[] fact = {40320, 5040, 720, 120, 24, 6, 2, 1, 1};
    //private int[] chueck = new int[370000];
    int inf = 2100000000;

    int getPerm() {
        int[] chk = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        int ans = 0;
        for (int i = 0; i < 8; i++) {
            int cnt = 0;
            for (int j = 0; j < suu[i]; j++) {
                cnt += 1 - chk[j];
            }
            chk[suu[i]] = 1;
            ans += cnt * fact[i];
        }
        return ans;
    }

    int setPerm(int target) {
        int[] chk = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        int ans = 9;
        for (int i = 0; i < 9; i++) {
            int need = target / fact[i];
            for (int j = 0; ; j++) {
                if (chk[j] == 0) need--;
                if (need == -1) {
                    chk[j] = 1;
                    suu[i] = j;
                    if (j == 0) ans = i;
                    break;
                }
            }
            target %= fact[i];
        }
        return ans;
    }

    protected void initView() {
        if(start_first){
            start_first = false;
            shuffle();
        }
    }

    public CanvasView(Context c) {
        super(c);
        //cxt = c;
        initView();
    }

    public CanvasView(Context c, AttributeSet attrs) {
        super(c, attrs);
        //cxt = c;
        initView();
    }

    public CanvasView(Context c, AttributeSet attrs, int defStyle) {
        super(c, attrs, defStyle);
        //cxt = c;
        initView();
    }

    public boolean swapFunc(int xx, int yy, int wanted){
        int tmp;
        boolean ans;
        tmp = suu[xx]; suu[xx] = suu[yy]; suu[yy] = tmp;
        ans = (chueck[getPerm()] == wanted);
        //System.out.println(chueck[getPerm()]);
        tmp = suu[xx]; suu[xx] = suu[yy]; suu[yy] = tmp;
        return ans;
    }
    public void moveLeft(){
        zero[1] += 1;
        coor[where[zero[0]][zero[1]]][0] = (float) ((zero[1] - 1) / 3.0);
        where[zero[0]][zero[1] - 1] = where[zero[0]][zero[1]];
        moveCnt += 1;
    }
    public void moveRight(){
        zero[1] -= 1;
        coor[where[zero[0]][zero[1]]][0] = (float) ((zero[1] + 1) / 3.0);
        where[zero[0]][zero[1] + 1] = where[zero[0]][zero[1]];
        moveCnt += 1;
    }
    public void moveUp(){
        zero[0] += 1;
        coor[where[zero[0]][zero[1]]][1] = (float) ((zero[0] - 1) / 3.0);
        where[zero[0] - 1][zero[1]] = where[zero[0]][zero[1]];
        moveCnt += 1;
    }
    public void moveDown() {
        zero[0] -= 1;
        coor[where[zero[0]][zero[1]]][1] = (float) ((zero[0] + 1) / 3.0);
        where[zero[0] + 1][zero[1]] = where[zero[0]][zero[1]];
        moveCnt += 1;
    }
    /*
    public void singleAutoMove(){
        if(youwin) return;
        int cnt = 0;
        xx=-1; yy=-1;
        for (int i = 0; i < 9; i++) {
            suu[i] = (where[i / 3][i % 3] + 1) % 9;
        }
        int zzero = zero[0] * 3 + zero[1];
        suu[zzero] = 0;
        System.out.println(suu[0] + " " + suu[1] + " " + suu[2]);
        System.out.println(suu[3] + " " + suu[4] + " " + suu[5]);
        System.out.println(suu[6] + " " + suu[7] + " " + suu[8]);
        int now_perm = getPerm();
        System.out.println("NOW: " + chueck[now_perm]);
        if(chueck[now_perm] == 0) return;
        if (zero[0] > 0) {
            System.out.print("DOWN: ");
            if (swapFunc(zzero, zzero - 3, chueck[now_perm] - 1)) {
                moveDown();
                ValueAnimator va = ValueAnimator.ofFloat((float) ((zero[0]) / 3.0), (float) ((zero[0] + 1) / 3.0));
                int mDuration = 200;
                va.setDuration(mDuration);
                va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
                    public void onAnimationUpdate(ValueAnimator animation){
                        coor[where[zero[0]][zero[1]]][1] = (float)animation.getAnimatedValue();
                        coor[where[zero[0]][zero[1]]][0] = (float)(zero[1] / 3.0);
                        invalidate();
                    }
                });
                va.start();
                return;
            }
        }
        if (zero[0] < 2) {
            System.out.print("UP: ");
            if (swapFunc(zzero, zzero + 3, chueck[now_perm] - 1)) {
                // UP
                moveUp();
                ValueAnimator va = ValueAnimator.ofFloat((float) ((zero[0]) / 3.0), (float) ((zero[0] - 1) / 3.0));
                int mDuration = 200;
                va.setDuration(mDuration);
                va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
                    public void onAnimationUpdate(ValueAnimator animation){
                        coor[where[zero[0]][zero[1]]][1] = (float)animation.getAnimatedValue();
                        coor[where[zero[0]][zero[1]]][0] = (float)(zero[1] / 3.0);
                        invalidate();
                    }
                });
                va.start();
                return;
            }
        }
        if (zero[1] > 0) {
            System.out.print("RIGHT: ");
            if (swapFunc(zzero, zzero - 1, chueck[now_perm] - 1)) {
                // RIGHT
                moveRight();
                ValueAnimator va = ValueAnimator.ofFloat((float) ((zero[1]) / 3.0), (float) ((zero[1] + 1) / 3.0));
                int mDuration = 200;
                va.setDuration(mDuration);
                va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
                    public void onAnimationUpdate(ValueAnimator animation){
                        coor[where[zero[0]][zero[1]]][0] = (float)animation.getAnimatedValue();
                        coor[where[zero[0]][zero[1]]][1] = (float)(zero[0] / 3.0);
                        invalidate();
                    }
                });
                va.start();
                return;
            }
        }
        if (zero[1] < 2) {
            System.out.print("LEFT: ");
            if (swapFunc(zzero, zzero + 1, chueck[now_perm] - 1)) {
                // LEFT
                moveLeft();
                ValueAnimator va = ValueAnimator.ofFloat((float) ((zero[1]) / 3.0), (float) ((zero[1] - 1) / 3.0));
                int mDuration = 200;
                va.setDuration(mDuration);
                va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
                    public void onAnimationUpdate(ValueAnimator animation){
                        coor[where[zero[0]][zero[1]]][0] = (float)animation.getAnimatedValue();
                        coor[where[zero[0]][zero[1]]][1] = (float)(zero[0] / 3.0);
                        invalidate();
                    }
                });
                va.start();
                return;
            }
        }
    }
    */
    public void showAns() {
        //singleAutoMove();
        xx = -1;
        yy = -1;
        recent = 0;
        for (int i = 0; i < 9; i++) {
            suu[i] = (where[i / 3][i % 3] + 1) % 9;
        }
        int zzero = zero[0] * 3 + zero[1];
        suu[zzero] = 0;
        final int now_perm = getPerm();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(zzero == i*3+j) continue;
                mv[0][where[i][j]][0] = (float)(j / 3.0);
                mv[0][where[i][j]][1] = (float)(i / 3.0);
            }
        }
        for(int i=0;i<chueck[now_perm];i++){
            if(youwin) return;
            System.out.println(suu[0]+" "+suu[1]+" "+suu[2]);
            System.out.println(suu[3]+" "+suu[4]+" "+suu[5]);
            System.out.println(suu[6]+" "+suu[7]+" "+suu[8]);
            for(int j=0;j<8;j++) System.out.print("("+mv[i][j][0]+","+mv[i][j][1]+") ");
            System.out.println("");
            int new_perm = getPerm();
            if ((zzero/3) > 0) {
                if (swapFunc(zzero, zzero - 3, chueck[new_perm] - 1)) {
                    // DOWN
                    int target = (suu[zzero-3]+8)%9;
                    for(int j=0;j<8;j++){
                        if(j != target){
                            mv[i+1][j][0] = mv[i][j][0];
                            mv[i+1][j][1] = mv[i][j][1];
                        }else{
                            mv[i+1][j][0] = mv[i][j][0];
                            mv[i+1][j][1] = mv[i][j][1] + (float)(1.0 / 3.0);
                        }
                    }
                    mvdirec[i] = 0;
                    int tmp = suu[zzero]; suu[zzero] = suu[zzero-3]; suu[zzero-3] = tmp;
                    zzero -= 3;
                    continue;
                }
            }
            if ((zzero/3) < 2) {
                if (swapFunc(zzero, zzero + 3, chueck[new_perm] - 1)) {
                    // UP
                    int target = (suu[zzero+3]+8)%9;
                    for(int j=0;j<8;j++){
                        if(j != target){
                            mv[i+1][j][0] = mv[i][j][0];
                            mv[i+1][j][1] = mv[i][j][1];
                        }else{
                            mv[i+1][j][0] = mv[i][j][0];
                            mv[i+1][j][1] = mv[i][j][1] - (float)(1.0 / 3.0);
                        }
                    }
                    mvdirec[i] = 1;
                    int tmp = suu[zzero]; suu[zzero] = suu[zzero+3]; suu[zzero+3] = tmp;
                    zzero += 3;
                    continue;
                }
            }
            if ((zzero%3) > 0) {
                if (swapFunc(zzero, zzero - 1, chueck[new_perm] - 1)) {
                    // RIGHT
                    int target = (suu[zzero-1]+8)%9;
                    for(int j=0;j<8;j++){
                        if(j != target){
                            mv[i+1][j][0] = mv[i][j][0];
                            mv[i+1][j][1] = mv[i][j][1];
                        }else{
                            mv[i+1][j][0] = mv[i][j][0] + (float)(1.0 / 3.0);
                            mv[i+1][j][1] = mv[i][j][1];
                        }
                    }
                    mvdirec[i] = 2;
                    int tmp = suu[zzero]; suu[zzero] = suu[zzero-1]; suu[zzero-1] = tmp;
                    zzero -= 1;
                    continue;
                }
            }
            if ((zzero%3) < 2) {
                if (swapFunc(zzero, zzero + 1, chueck[new_perm] - 1)) {
                    // LEFT
                    int target = (suu[zzero+1]+8)%9;
                    for(int j=0;j<8;j++){
                        if(j != target){
                            mv[i+1][j][0] = mv[i][j][0];
                            mv[i+1][j][1] = mv[i][j][1];
                        }else{
                            mv[i+1][j][0] = mv[i][j][0] - (float)(1.0 / 3.0);
                            mv[i+1][j][1] = mv[i][j][1];
                        }
                    }
                    mvdirec[i] = 3;
                    int tmp = suu[zzero]; suu[zzero] = suu[zzero+1]; suu[zzero+1] = tmp;
                    zzero += 1;
                    continue;
                }
            }
        }
        for(int j=0;j<8;j++) System.out.print("("+mv[chueck[now_perm]][j][0]+","+mv[chueck[now_perm]][j][1]+") ");
        System.out.println("");


        ValueAnimator va = ValueAnimator.ofFloat((float)0, (float)(chueck[now_perm] + 0.1));
        int mDuration = chueck[now_perm]*1000;
        va.setDuration(mDuration);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
            public void onAnimationUpdate(ValueAnimator animation){
                float val = (float)animation.getAnimatedValue();
                int where = (int)(val);
                while(recent < where){
                    if(mvdirec[recent] == 0){
                        moveDown();
                    }else if(mvdirec[recent] == 1){
                        moveUp();
                    }else if(mvdirec[recent] == 2){
                        moveRight();
                    }else{
                        moveLeft();
                    }
                    recent++;
                }
                if(recent < where){
                    System.out.println(recent + " " + where);
                    recent = where;
                }
                if(where == chueck[now_perm]){
                    for(int i=0;i<8;i++){
                        coor[i][0] = mv[where][i][0];
                        coor[i][1] = mv[where][i][1];
                    }
                }else{
                    float ll = val-where;
                    float rr = 1-ll;
                    for(int i=0;i<8;i++){
                        coor[i][0] = rr * mv[where][i][0] + ll * mv[where+1][i][0];
                        coor[i][1] = rr * mv[where][i][1] + ll * mv[where+1][i][1];
                    }
                }
                invalidate();
            }
        });
        va.start();


        TimerTask timertask = new TimerTask() {
            @Override
            public void run(){
                simulating = false;
            }
        };
        Timer timer = new Timer();
        timer.schedule(timertask, 1000 * chueck[now_perm] + 50);

    }

    public void shuffle(){
        if(simulating) return;
        invalidate();
        int[] place = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        su[0] = "1";su[1] = "2";su[2] = "3";su[3] = "4";su[4] = "5";su[5] = "6";su[6] = "7";su[7] = "8";
        while(true) {
            for (int i = 0; i < 100; i++) {
                int one = (int) (Math.random() * 8);
                int two = (int) (Math.random() * 8);
                int tmp = place[one];
                place[one] = place[two];
                place[two] = tmp;
            }
            for (int i = 0; i < 9; i++) {
                where[place[i] / 3][place[i] % 3] = i;
                suu[place[i]] = (i+1)%9;
            }
            /*
            System.out.println(suu[0]+" "+suu[1]+" "+suu[2]);
            System.out.println(suu[3]+" "+suu[4]+" "+suu[5]);
            System.out.println(suu[6]+" "+suu[7]+" "+suu[8]);
            */
            if(chueck[getPerm()] < inf){
                for (int i = 0; i < 9; i++) {
                    coor[i][1] = (float) ((place[i] / 3) * (1.0 / 3.0));
                    coor[i][0] = (float) ((place[i] % 3) * (1.0 / 3.0));
                }
                break;
            }
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
            if(true) {
                for (int i = 0; i < 8; i++) {
                    //if (i % 2 == 0)
                    if (!youwin) pnt.setColor(Color.CYAN);
                    else pnt.setColor(Color.MAGENTA);
                    //else pnt.setColor(Color.MAGENTA);
                    canvas.drawRect((70 + (ww * coor[i][0])), 70 + (ww * coor[i][1]), 50 + gili + (ww * coor[i][0]), 50 + gili + (ww * coor[i][1]), pnt);
                    pnt.setColor(Color.BLACK);
                    pnt.getTextBounds(su[i], 0, 1, textBounds);
                    canvas.drawText(su[i], (60 + gili / 2 + (ww * coor[i][0])), (60 + gili / 2 + (ww * coor[i][1])) - textBounds.exactCenterY(), pnt);
                }
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
        if(simulating) return true;
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
                            moveLeft();
                        }
                    } else if (xx - x < getWidth() * -0.15) {
                        //System.out.println("RIGHT");
                        if (zero[1] != 0) {
                            moveRight();
                        }
                    }
                } else {
                    if (yy - y > getWidth() * 0.15) {
                        //System.out.println("UP");
                        if (zero[0] != 2) {
                            moveUp();
                        }
                    } else if (yy - y < getWidth() * -0.15) {
                        //System.out.println("DOWN");
                        if (zero[0] != 0) {
                            moveDown();
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