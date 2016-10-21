package orangeboat.bit_byterocketflight.Threads;

import android.graphics.Bitmap;

/**
 * Created by jawpa on 10/10/2016.
 */
public class Animation {
    private Bitmap[] frames;
    private int currentFrame;
    private long startTime;
    private long delay;
    private boolean playedOnce;
    private int numFrames;

    public void setFrames(Bitmap[] frames)
    {
        this.frames = frames;
        currentFrame = 0;
        numFrames = frames.length;
        startTime = System.nanoTime();
    }
    public void setDelay(long d){delay = d;}
    public void setFrame(int i){currentFrame= i;}
    public void setPlayedOnce(boolean b) {playedOnce = b;}
    public void setRandomFrame(){
        //currentFrame = (int)(Math.random()*numFrames);
        currentFrame = (currentFrame+1)%numFrames;
    }
    public void update()
    {
        long elapsed = (System.nanoTime()-startTime)/1000000;

        if(elapsed>delay)
        {
            currentFrame++;
            startTime = System.nanoTime();
        }
        if(currentFrame == frames.length){
            currentFrame = 0;
            playedOnce = true;
        }
    }
    public Bitmap getImage(){
        return frames[currentFrame];
    }
    public int getFrame(){return currentFrame;}
    public boolean playedOnce(){return playedOnce;}

}
