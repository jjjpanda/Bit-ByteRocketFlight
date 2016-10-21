package orangeboat.bit_byterocketflight.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import orangeboat.bit_byterocketflight.Inputs.Sensor;
import orangeboat.bit_byterocketflight.Threads.Animation;

/**
 * Created by jawpa on 10/10/2016.
 */
public class Rocket extends Entity{
    int dx, sensitivity;
    Bitmap rocket;
    Bitmap death;
    Bitmap [] deathFrames = new Bitmap[8];
    public Animation rocketDeath;
    Paint p2 = new Paint();
    int midline;
    int limit;
    int signal = 0; // 0-middle, 1 left, 2 is right
    public Rocket(Bitmap rocket ,int x, int y, int limit, Bitmap death)
    {
        super(rocket, x, y,1);
        this.limit = limit;
        this.rocket = rocket;
        this.death = death;
        midline = limit/2 - rocket.getWidth()/2;
        this.rocketDeath = new Animation();
        p2.setColor(Color.WHITE);
        sensitivity = 20;
    }
    public void load()
    {
        int width = death.getWidth()/8;
        int height = death.getHeight();
        for(int i = 0; i < deathFrames.length;i++)
        {
            deathFrames[i] = Bitmap.createBitmap(death,i*width,0,width,height);
        }
        rocketDeath.setFrames(deathFrames);
        rocketDeath.setDelay(175);
    }
    public void update()
    {
        dx = (int)(Sensor.lastX* sensitivity);
        if(dx<2 && dx>-2) {
            dx = 0;
        }
        if((x > 0 && dx > 0) || (dx < 0 && x < limit-rocket.getWidth()) ) {
            //adds edges of the screen
            x -= dx;
        }
        super.update();
    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(rocket, x, y, null);
    }
    public void updateDeath(){
       if(!rocketDeath.playedOnce()) {
            rocketDeath.update();
           return;
       }
    }
    public void drawDeath(Canvas canvas)
    {
        canvas.drawBitmap(rocketDeath.getImage(), x, y, null);
    }
    public void touchUpdate(){
        if(signal == 2) {dx = (int)  -2.6f   *20;}
        if(signal == 1) {dx = (int)  2.6f   *20;}
        if(signal == 0) { dx = 0; }
        if((x > 0 && (signal == 1) || ((signal == 2) && x < limit-rocket.getWidth()))){
            x -= dx;
        }
        super.update();
        //hitbox.set(hitbox.left + boatForwardImg.getWidth() / 16, hitbox.top,
        //        hitbox.right - boatForwardImg.getWidth() / 16, hitbox.bottom - boatForwardImg.getHeight() / 4);
    }
    public void moveRight(){ signal = 2;}
    public void moveLeft(){ signal = 1;}
    public void straighten(){signal = 0;}
}
