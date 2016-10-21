package orangeboat.bit_byterocketflight.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by jawpa on 10/10/2016.
 */
public class Entity {
    public Rect hitbox;
    int TH;
    int TW;
    int x;
    int y;
    Bitmap img;
    int numOfFrames;
    public Entity(Bitmap img, int x, int y, int numOfFrames)
    {
        this.img = img;
        this.x = x;
        this.y = y;
        this.numOfFrames=numOfFrames;
        TW = img.getWidth()/numOfFrames;
        TH = img.getHeight();
        hitbox = new Rect(x,y,x+TW,y+TH);
    }
    public void draw(Canvas canvas)
    {
    }
    public void update()
    {
        hitbox.set(x,y,x+TW,y+TH);
    }
    public void load(Bitmap img)
    {
    }
}
