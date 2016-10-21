package orangeboat.bit_byterocketflight.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import orangeboat.bit_byterocketflight.Threads.Animation;

/**
 * Created by jawpa on 10/10/2016.
 */
public class Shield extends Entity{
    Animation shieldAni = new Animation();
    Bitmap[] frames = new Bitmap[2];
    Bitmap img;
    public Shield(Bitmap img, int x, int y){
        super(img, x, y, 2);
        this.img = img;
    }
    public void load(){
        int width = img.getWidth()/2;
        int height = img.getHeight();
        for(int i = 0; i < frames.length;i++)
        {
            frames[i] = Bitmap.createBitmap(img,i*width,0,width,height);
        }
        shieldAni.setFrames(frames);
        shieldAni.setDelay(150);
    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(shieldAni.getImage(), x, y, null);
    }
    public void update(int x){
        y += (x+hitbox.top)*.035;
        shieldAni.update();
        super.update();
    }
    public void resetX(int x){
        super.x = x;
        super.y = 0;
    }


}
