package orangeboat.bit_byterocketflight.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by jawpa on 10/10/2016.
 */
public class Rock extends Entity {
    public Rock(Bitmap img, int x, int y){
        super(img,x,y,1);
    }
    public void load(){
        super.load(img);
    }
    public void update(int x){
        y += (x+hitbox.top)*0.035;
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
