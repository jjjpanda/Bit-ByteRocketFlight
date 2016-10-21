package orangeboat.bit_byterocketflight.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by jawpa on 10/12/2016.
 */
public class Orb extends Entity {
    public Orb(Bitmap img, int x, int y){
        super(img, x, y, 1);
    }
    public void load(){
        super.load(img);
    }
    public void update(int x){
        y+= (x+hitbox.top)*.035;
        super.update();
    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(img,x,y,null);
    }
    public void resetX(int x){
        super.x = x;
        super.y = 0;
    }
}
