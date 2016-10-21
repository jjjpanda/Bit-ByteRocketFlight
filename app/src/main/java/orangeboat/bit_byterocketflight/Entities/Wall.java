package orangeboat.bit_byterocketflight.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by jawpa on 10/10/2016.
 */
public class Wall extends Entity{
    public Rect wallline;
    Paint paintW = new Paint();
    public Wall(Bitmap img, int x, int y){
        super(img, x, y, 1);
    }
    public void load(int width){
        super.load(img);
        paintW.setColor(Color.WHITE);
        wallline = new Rect(0,0,width,img.getHeight());
    }
    public void draw(Canvas canvas) {
        canvas.drawRect(wallline,paintW);
        canvas.drawBitmap(img, x, y, null);
    }
    public void update(int x){
        y += (x+hitbox.top)*.035;
        wallline.set(0,y,x,y+img.getHeight());
        super.update();
    }
    public void resetX(int x){
        super.x = x;
        super.y = 0;
    }
}