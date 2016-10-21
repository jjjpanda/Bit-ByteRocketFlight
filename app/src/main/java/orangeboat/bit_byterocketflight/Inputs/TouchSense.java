package orangeboat.bit_byterocketflight.Inputs;

import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;

import orangeboat.bit_byterocketflight.Panels.EndPanel;
import orangeboat.bit_byterocketflight.Panels.GamePanel;
import orangeboat.bit_byterocketflight.Panels.PausePanel;
import orangeboat.bit_byterocketflight.Panels.TitlePanel;

/**
 * Created by jawpa on 10/10/2016.
 */
public class TouchSense {
    public boolean switcher = false;
    MotionEvent event;
    int x, y;
    int pointerCount;
    public int touchtriggerOn = 0;
    public TouchSense(MotionEvent event) {
        this.event = event;
        pointerCount = event.getPointerCount();
        if (pointerCount >= 2) {
            x = (int) event.getX(event.getPointerId(event.getActionIndex()));
            y = (int) event.getY(event.getPointerId(event.getActionIndex()));
        } else {
            x = (int) event.getX();
            y = (int) event.getY();
        }
    }

    public void checkTitle(TitlePanel title) {
        int action = MotionEventCompat.getActionMasked(event);
        if (MotionEvent.ACTION_DOWN == action) {
            System.out.println(x + " " + y);
            if (title.rectPlay.contains(x, y)) {
                System.out.println("Play button pressed");
                switcher = true;
            }
            else if(title.rectToggle.contains(x,y)){
                System.out.println("TOGGLED");
                if(title.touchOn){
                    title.touchOn = false;
                    touchtriggerOn = 1;
                }
                else{
                    title.touchOn = true;
                    touchtriggerOn = 2;
                }
            }
        }
    }
    public void toGameToggleInfo(GamePanel gamePanel, PausePanel pausePanel, EndPanel endPanel){
        if(touchtriggerOn == 2) {
            gamePanel.touchOn = 2;
            pausePanel.toggle = 2;
            endPanel.toggle = 2;
        }
        if(touchtriggerOn == 1){
            gamePanel.touchOn = 1;
            pausePanel.toggle = 1;
            endPanel.toggle = 1;
        }
    }
    public void checkEnd(EndPanel endPanel){
        int action = MotionEventCompat.getActionMasked(event);
        if (MotionEvent.ACTION_DOWN == action) {
            System.out.println(x + " " + y);
            if (endPanel.rectRetry.contains(x, y)) {
                switcher = true;
            }
            else if(endPanel.rectToggle.contains(x,y)){
                System.out.println("TOGGLED");
                if(endPanel.toggle == 2){
                    endPanel.toggle =  1;
                    touchtriggerOn = 1;
                }
                else{
                    endPanel.toggle = 2;
                    touchtriggerOn = 2;
                }
            }
        }
    }
    public void checkPause(PausePanel pausePanel){
        int action = MotionEventCompat.getActionMasked(event);
        if (MotionEvent.ACTION_DOWN == action) {
            System.out.println(x + " " + y);
            if (pausePanel.rectResume.contains(x, y)) {
                switcher = true;
            }
            else if(pausePanel.rectToggle.contains(x,y)){
                System.out.println("TOGGLED");
                if(pausePanel.toggle == 2){
                    pausePanel.toggle = 1;
                    touchtriggerOn = 1;
                }
                else{
                    pausePanel.toggle = 2;
                    touchtriggerOn = 2;
                }
            }
        }
    }
    public void checkGame(GamePanel gamePanel) {
        int action = MotionEventCompat.getActionMasked(event);
        // FIRST TOUCH
        if (MotionEvent.ACTION_DOWN == action) {
            if (gamePanel.pause.contains(x, y)) {
                switcher = true;
            }
            gamePanel.downTouch(x, y, event.getPointerId(event.getActionIndex()));
            //  System.out.println(x+ " is the x coordinate " + y + " is the y coordinate");
        }
        if(MotionEvent.ACTION_UP == action)
        {
            gamePanel.upTouch(x, y, event.getPointerId(event.getActionIndex()));
        }

        // SECOND TOUCH
        if(MotionEvent.ACTION_POINTER_DOWN == action)
        {
            if (gamePanel.pause.contains(x, y)) {
                switcher = true;
            }
            gamePanel.downTouch(x, y, event.getPointerId(event.getActionIndex()));
        }
        if(MotionEvent.ACTION_POINTER_UP == action) {
            gamePanel.upTouch(x, y, event.getPointerId(event.getActionIndex()));
        }
        if(MotionEvent.ACTION_MOVE == action)
        {
            int pointerCount = event.getPointerCount();
            for(int i = 0; i < pointerCount; ++i) {

                x = (int)event.getX(i);
                y = (int)event.getY(i);
                gamePanel.downTouch(x,y,event.getPointerId(i));
            }
        }
    }
}

