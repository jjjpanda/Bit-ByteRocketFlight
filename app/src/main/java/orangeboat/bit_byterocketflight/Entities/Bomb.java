package orangeboat.bit_byterocketflight.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import orangeboat.bit_byterocketflight.Threads.Animation;

/**
 * Created by jawpa on 10/10/2016.
 */
public class Bomb extends Entity{
    Paint paintR = new Paint();
    Animation bombAni = new Animation();
    Bitmap[] frames = new Bitmap[4];
    int radius;
    Bitmap img;
    public Bomb(Bitmap img, int x, int y){
        super(img, x, y, 4);
        this.img = img;
    }
    public void load(){
        paintR.setColor(Color.RED);
        int width = img.getWidth()/4;
        int height = img.getHeight();
        for(int i = 0; i < frames.length;i++)
        {
            frames[i] = Bitmap.createBitmap(img,i*width,0,width,height);
        }
        bombAni.setFrames(frames);
        bombAni.setDelay(30);
        radius = width /2;
    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(bombAni.getImage(), x, y, null);
    }
    public void update(int x){
        y += (x+hitbox.top)*.035;
        bombAni.update();
        super.update();
    }
    public void resetX(int x){
        super.x = x;
        super.y = 0;
    }

}