package orangeboat.bit_byterocketflight.Inputs;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

import orangeboat.bit_byterocketflight.Panels.EndPanel;
import orangeboat.bit_byterocketflight.Panels.GamePanel;
import orangeboat.bit_byterocketflight.Panels.PausePanel;
import orangeboat.bit_byterocketflight.Panels.TitlePanel;
import orangeboat.bit_byterocketflight.R;

/**
 * Created by jawpa on 10/10/2016.
 */
public class IMGSense {
    Bitmap temp;
    Resources resources;
    DisplayMetrics displayMetrics;
    GamePanel g;
    TitlePanel t;
    EndPanel e;
    PausePanel p;
    public IMGSense(Resources resources, DisplayMetrics m, GamePanel gamePanel, TitlePanel titlePanel, EndPanel endPanel, PausePanel pausePanel)
    {
        g = gamePanel;
        t = titlePanel;
        e = endPanel;
        p = pausePanel;
        this.resources = resources;
        displayMetrics = m;
        start();
    }
    public void start(){
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.rocket), BitmapFactory.decodeResource(resources, R.drawable.rocket).getWidth()*3, BitmapFactory.decodeResource(resources, R.drawable.rocket).getHeight()*3, true);
        g.imgLoad(temp);
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.shield), BitmapFactory.decodeResource(resources, R.drawable.shield).getWidth()*5, BitmapFactory.decodeResource(resources, R.drawable.shield).getHeight()*5, true);
        g.imgLoad(temp);
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.hole), BitmapFactory.decodeResource(resources, R.drawable.hole).getWidth(), BitmapFactory.decodeResource(resources, R.drawable.hole).getHeight(), true);
        g.imgLoad(temp);
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.rocketdeath), BitmapFactory.decodeResource(resources, R.drawable.rocketdeath).getWidth()*4, BitmapFactory.decodeResource(resources, R.drawable.rocketdeath).getHeight()*4, true);
        g.imgLoad(temp);
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.pause), BitmapFactory.decodeResource(resources, R.drawable.pause).getWidth(), BitmapFactory.decodeResource(resources, R.drawable.pause).getHeight(), true);
        g.imgLoad(temp);
        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.bombrock), BitmapFactory.decodeResource(resources, R.drawable.bombrock).getWidth()*3, BitmapFactory.decodeResource(resources, R.drawable.bombrock).getHeight()*3, true);
        g.imgLoad(temp);

        temp = Bitmap.createBitmap(temp, temp.getWidth()/4,0,temp.getWidth()/4,temp.getHeight());
        g.imgLoad(temp);

        temp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.bombrock), BitmapFactory.decodeResource(resources, R.drawable.bombrock).getWidth()*3, BitmapFactory.decodeResource(resources, R.drawable.bombrock).getHeight()*4, true);
        temp = Bitmap.createBitmap(temp, temp.getWidth()/4*3,0,temp.getWidth()/4,temp.getHeight());
        g.imgLoad(temp);

        temp = BitmapFactory.decodeResource(resources, R.drawable.retrybutton);
        e.imgLoad(temp);
        temp = BitmapFactory.decodeResource(resources, R.drawable.resumebutton);
        p.imgLoad(temp);

        temp = BitmapFactory.decodeResource(resources, R.drawable.playbutton1);
        t.imgLoad(temp);

        temp = BitmapFactory.decodeResource(resources, R.drawable.quitbutton);
        t.imgLoad(temp);
        e.imgLoad(temp);
        p.imgLoad(temp);
        temp = BitmapFactory.decodeResource(resources,R.drawable.touchtoggle);
        t.imgLoad(temp);
        e.imgLoad(temp);
        p.imgLoad(temp);
        temp = BitmapFactory.decodeResource(resources,R.drawable.tilttoggle);
        t.imgLoad(temp);
        e.imgLoad(temp);
        p.imgLoad(temp);

        temp = BitmapFactory.decodeResource(resources,R.drawable.titletext);
        t.imgLoad(temp);
    }
}
