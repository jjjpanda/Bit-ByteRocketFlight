package orangeboat.bit_byterocketflight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import orangeboat.bit_byterocketflight.Inputs.IMGSense;
import orangeboat.bit_byterocketflight.Inputs.SFXSense;
import orangeboat.bit_byterocketflight.Inputs.Sensor;
import orangeboat.bit_byterocketflight.Inputs.TouchSense;
import orangeboat.bit_byterocketflight.Panels.EndPanel;
import orangeboat.bit_byterocketflight.Panels.GamePanel;
import orangeboat.bit_byterocketflight.Panels.PausePanel;
import orangeboat.bit_byterocketflight.Panels.TitlePanel;
import orangeboat.bit_byterocketflight.Threads.MainThread;

/**
 * Created by jawpa on 10/10/2016.
 */
public class Display extends SurfaceView implements SurfaceHolder.Callback{
    TouchSense touch;
    MainThread mainThread;
    SurfaceHolder contextHolder;
    Paint paint;
    public static DisplayMetrics displayMetrics;
    GamePanel gamePanel;
    TitlePanel titlePanel;
    EndPanel endPanel;
    PausePanel pausePanel;
    Sensor sensor;
    IMGSense imageLoader;
    SFXSense sfx;
    int panelSwitch = 0;
    int widthOfPhone;
    int heightofPhone;
    int dx = 50;
    int dx2 = -50;
   public Display(Context context, DisplayMetrics m, Sensor sensor){
        super(context);
        getHolder().addCallback(this);
        contextHolder=getHolder();
        mainThread=new MainThread(getHolder(),this);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        displayMetrics = m;
        this.sensor = sensor;
        widthOfPhone = displayMetrics.widthPixels;
        heightofPhone = displayMetrics.heightPixels;
       gamePanel = new GamePanel(widthOfPhone, heightofPhone);
       titlePanel = new TitlePanel();
       endPanel = new EndPanel();
       pausePanel = new PausePanel();
       imageLoader = new IMGSense(getResources(), m, gamePanel,titlePanel,endPanel, pausePanel);
       sfx = new SFXSense(this.getContext(), gamePanel);
   }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        titlePanel.load();
        gamePanel.load();
        endPanel.load();
        pausePanel.load();
        Thread.State state = mainThread.getState();
        if(state == Thread.State.TERMINATED) {
            newThread();
        }
        //once surface is created, we can safely start gameloop
        mainThread.setRunning(true);
        mainThread.start();
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                mainThread.setRunning(false);
                mainThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }
    public void update()
    {
        if(panelSwitch == 0)
            titlePanel.update();
        else if(panelSwitch == 1) {
            gamePanel.update();
        }
        if(gamePanel.gameEnded){
            panelSwitch = 2;
        }
    }
    public boolean onTouchEvent(MotionEvent event) {
        touch = new TouchSense(event);
        if (panelSwitch == 0) {
            touch.checkTitle(titlePanel);
            touch.toGameToggleInfo(gamePanel,pausePanel,endPanel);
            if (touch.switcher) {
                panelSwitch = 1;
                touch.switcher = false;
            }
        }
        if (panelSwitch == 1) {
            touch.checkGame(gamePanel);
            touch.toGameToggleInfo(gamePanel, pausePanel, endPanel);
            if (touch.switcher) {
                panelSwitch = 3;
                touch.switcher = false;
            }
        }
        if (panelSwitch == 2) {
            gamePanel.load();
            touch.checkEnd(endPanel);
            touch.toGameToggleInfo(gamePanel, pausePanel, endPanel);
            if (touch.switcher) {
                panelSwitch = 1;
                touch.switcher = false;
            }
        }
        if(panelSwitch == 3) {
            touch.checkPause(pausePanel);
            touch.toGameToggleInfo(gamePanel,pausePanel,endPanel);
            if (touch.switcher) {
                panelSwitch = 1;
                touch.switcher = false;
            }
        }
        return true;
    }
    @Override
    public void draw(Canvas canvas)
    {
        /*paint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels, paint);
        paint.setColor(Color.RED);
        canvas.drawRect(rect,paint);
        paint.setTextSize(200f);*/
        paint.setColor(Color.WHITE);
        paint.setTextSize(50f);
        if(panelSwitch == 0)
        {
            titlePanel.draw(canvas);
        }
        else if(panelSwitch == 1) {
            gamePanel.draw(canvas);
        }
        else if (panelSwitch == 2) {
            endPanel.draw(canvas);
        }
        else if(panelSwitch == 3){
            pausePanel.draw(canvas);
        }
        canvas.drawText("" + gamePanel.score, Display.displayMetrics.widthPixels/2, Display.displayMetrics.heightPixels-paint.getTextSize(), paint);
        canvas.drawText("High Score:" + gamePanel.highScore, 0, Display.displayMetrics.heightPixels - paint.getTextSize(), paint);
    }
    public void newThread() {
        mainThread = new MainThread(contextHolder, this);
    }


}

