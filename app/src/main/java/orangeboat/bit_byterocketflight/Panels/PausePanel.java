package orangeboat.bit_byterocketflight.Panels;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

import orangeboat.bit_byterocketflight.Display;

/**
 * Created by jawpa on 10/10/2016.
 */
public class PausePanel {
    ArrayList<Bitmap> loader = new ArrayList<>();
    Paint paint;
    Bitmap resumeButton, quitButton, touch, tilt;
    public Rect rectResume, rectQuit, rectToggle;
    private int x, y, qx, qy, buttonX, buttonY;
    private int togglex, toggley, togglewidth, toggleheight;
    public int toggle = 1;
    public PausePanel()
    {
        paint = new Paint();
        paint.setColor(Color.BLACK);
    }
    public void draw(Canvas canvas){
        canvas.drawRect(0,0, Display.displayMetrics.widthPixels, Display.displayMetrics.heightPixels, paint);
        canvas.drawBitmap(resumeButton, x, y, null);
        if(toggle == 2) {
            canvas.drawBitmap(touch,togglex, toggley, null);
        }
        else if( toggle == 1){
            canvas.drawBitmap(tilt, togglex, toggley, null);
        }
    }
    public void load()
    {
        this.resumeButton= loader.get(0);
        this.quitButton = loader.get(1);
        this.touch = loader.get(2);
        this.tilt = loader.get(3);
        buttonX = resumeButton.getWidth();
        buttonY = resumeButton.getHeight();
        togglewidth = tilt.getWidth();
        toggleheight = tilt.getHeight();
        x = (int) (Display.displayMetrics.widthPixels/2);
        y = (int) ( Display.displayMetrics.heightPixels/1.3);
        qx = (int) (Display.displayMetrics.widthPixels/3.3);
        qy = (int) ( Display.displayMetrics.heightPixels/1.3);
        togglex  = (int) ( Display.displayMetrics.widthPixels/9.5);
        toggley = (int) ( Display.displayMetrics.heightPixels/1.35);
        rectResume = new Rect(x,y, (x + buttonX), (y + buttonY));
        rectQuit = new Rect(qx,qy, (qx + buttonX), (qy + buttonY));
        rectToggle = new Rect(togglex, toggley, (togglex+togglewidth), (toggley+toggleheight));
    }
    public void remove(){

    }
    public void imgLoad(Bitmap image) {loader.add(image);}
}
