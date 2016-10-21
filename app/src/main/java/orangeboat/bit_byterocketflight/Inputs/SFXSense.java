package orangeboat.bit_byterocketflight.Inputs;

import android.content.Context;
import android.media.MediaPlayer;

import orangeboat.bit_byterocketflight.Panels.GamePanel;
import orangeboat.bit_byterocketflight.R;

/**
 * Created by jawpa on 10/10/2016.
 */
public class SFXSense{
        MediaPlayer temp;
        Context context;
        GamePanel g;
        public SFXSense(Context context, GamePanel gamePanel)
        {
            g = gamePanel;
            this.context = context;
            start();
        }
public void start(){
        temp = MediaPlayer.create(context, R.raw.reg);
        temp.setLooping(true);
        //g.sfxLoad(temp);
        temp = MediaPlayer.create(context, R.raw.beeps);
        temp.setLooping(true);
        //g.sfxLoad(temp);
        temp = MediaPlayer.create(context, R.raw.errgs);
        temp.setLooping(true);
       // g.sfxLoad(temp);
        }
}
