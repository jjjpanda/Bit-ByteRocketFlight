package orangeboat.bit_byterocketflight.Panels;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;

import java.util.ArrayList;

import orangeboat.bit_byterocketflight.Display;
import orangeboat.bit_byterocketflight.Entities.Bomb;
import orangeboat.bit_byterocketflight.Entities.Orb;
import orangeboat.bit_byterocketflight.Entities.Rock;
import orangeboat.bit_byterocketflight.Entities.Rocket;
import orangeboat.bit_byterocketflight.Entities.Shield;
import orangeboat.bit_byterocketflight.Entities.Wall;

/**
 * Created by jawpa on 10/10/2016.
 */
public class GamePanel {
    ArrayList<Bitmap> imgloader = new ArrayList<>();
    ArrayList<MediaPlayer> sfxloader = new ArrayList<>();
    public Rect left, right, pause;
    public int touchOn = 1;
    public boolean gameEnded;
    int widthOfPhone, heightOfPhone;
    int sfxstart = 0;
    public int score = 0;
    public int highScore = score;
    Paint paint = new Paint();
    Paint paint2 = new Paint();
    Paint paint3 = new Paint();
    private boolean shieldOn = false;
    Rocket rocket;
    Rock rock;
    Orb orb;
    Shield shield;
   // Wall wall;
    Bomb bomb;
    public GamePanel(int x, int y){
        widthOfPhone = x;
        heightOfPhone = y;
        paint2 = new Paint();
        paint2.setColor(Color.BLACK);
        paint.setColor(Color.WHITE);
        paint.setTextSize(50f);
        paint3.setColor(Color.BLACK);
        left = new Rect(0, 0, Display.displayMetrics.widthPixels/2,Display.displayMetrics.heightPixels );
        right = new Rect(Display.displayMetrics.widthPixels/2, 0, Display.displayMetrics.widthPixels,Display.displayMetrics.heightPixels);
    }
    public void load()
    {
        gameEnded = false;
        score = 0;
        //sfxloader.get(0).seekTo(sfxstart);
        // sfxloader.get(2).start();
        rocket = new Rocket(imgloader.get(0),widthOfPhone/2,heightOfPhone*2/3, widthOfPhone, imgloader.get(3));
        orb = new Orb(imgloader.get(6), (int)(Math.random()*(widthOfPhone-200)), 0);
        shield = new Shield(imgloader.get(1),(int)(Math.random()*(6*widthOfPhone-200)), 0);
        rock = new Rock(imgloader.get(7), (int)(Math.random()*(widthOfPhone-200)), 0);
        pause = new Rect(Display.displayMetrics.widthPixels-imgloader.get(4).getWidth(),0, widthOfPhone, imgloader.get(4).getHeight());
        bomb = new Bomb(imgloader.get(5),(int)(Math.random()*(5*widthOfPhone-200)), 0);
      //  wall = new Wall(imgloader.get(2),(int)(Math.random()*(widthOfPhone-200)), 0);
        rocket.load();
        orb.load();
        shield.load();
        rock.load();
        bomb.load();
       // wall.load(widthOfPhone);
    }
    public void update()
    {
        /*
        if(rocket.hitbox.intersect(wall.wallline) && !rocket.hitbox.intersect(wall.hitbox)){
            if(!shieldOn){
                rocket.updateDeath();
                return;
            }
            else if(shieldOn){
                wall.resetX((int)(Math.random() * (widthOfPhone -200)));
                shieldOn = false;
            }
        }
        */

        if(rocket.hitbox.intersect(rock.hitbox)){
            if(!shieldOn){
                rocket.updateDeath();
                return;
            }
            else if(shieldOn){
                rock.resetX((int)(Math.random() * (widthOfPhone - 200)));
                shieldOn = false;
            }
        }
        if(rocket.hitbox.intersect(bomb.hitbox)){
            if(!shieldOn){
                rocket.updateDeath();
                return;
            }
            else if(shieldOn) {
                bomb.resetX((int)(Math.random() * (widthOfPhone - 200)));
                shieldOn = false;
            }
        }
        if(rocket.hitbox.intersect(orb.hitbox)){
            orb.resetX((int) (Math.random() * (widthOfPhone-200)));
            score++;
            if (score > highScore)
                setHighScore(score);
        }
        if(rocket.hitbox.intersect(shield.hitbox)){
            shield.resetX((int) (Math.random() * (6*widthOfPhone-200)));
            shieldOn = true;
            /*
            if (sfxloader.get(0).isPlaying()) {
                sfxloader.get(1).seekTo(sfxloader.get(0).getCurrentPosition());
            }
            sfxloader.get(0).pause();
            sfxloader.get(1).start();
            */
            score++;
            if (score > highScore) {
                setHighScore(score);
            }
        }
        if(touchOn == 2){
            rocket.touchUpdate();
        }
        else if (touchOn == 1){
            rocket.update();
        }
        orb.update(widthOfPhone);
        shield.update(widthOfPhone);
        rock.update(widthOfPhone);
            bomb.update(widthOfPhone);
       // wall.update(widthOfPhone);
        if (orb.hitbox.bottom >= heightOfPhone){
            orb.resetX((int)(Math.random()*(widthOfPhone-200)));
        }
        if(rock.hitbox.bottom >= heightOfPhone){
            rock.resetX(((int) (Math.random()*(widthOfPhone-200))));
        }
        if(shield.hitbox.bottom >= heightOfPhone*2){
            shield.resetX(((int) (Math.random()*(6*widthOfPhone-200))));
        }
        if(bomb.hitbox.bottom >= heightOfPhone*2){
            bomb.resetX(((int) (Math.random()*(5*widthOfPhone- 200))));
        }
        /*
        if(wall.hitbox.bottom >= heightOfPhone*5){
            wall.resetX(((int) (Math.random()*(widthOfPhone- 200))));
        }
        */

       // sfxloader.get(0).start();
    }
    public void draw(Canvas canvas)
    {
        canvas.drawRect(0, 0, Display.displayMetrics.widthPixels, Display.displayMetrics.heightPixels, paint2);
        if(rocket.hitbox.intersect(rock.hitbox) || rocket.hitbox.intersect(bomb.hitbox) ){
                //|| rocket.hitbox.intersect(wall.hitbox)
            if(!shieldOn) {
                if (rocket.rocketDeath.playedOnce()) {
                    canvas.drawRect(0, 0, Display.displayMetrics.widthPixels, Display.displayMetrics.heightPixels, paint3);
                    gameEnded = true;
                    return;
                }
                canvas.drawText("you lost fam", widthOfPhone/2, heightOfPhone/2, paint);
                rocket.drawDeath(canvas);
                return;
            }
        }

        rocket.draw(canvas);
        orb.draw(canvas);
        rock.draw(canvas);
        shield.draw(canvas);
            bomb.draw(canvas);
/*
        if(wall.hitbox.right < widthOfPhone){
            wall.draw(canvas);
        }
        */

        //pause button
        canvas.drawBitmap(imgloader.get(4), widthOfPhone - imgloader.get(4).getWidth(), 0, null);
        if(shieldOn) {
            canvas.drawText("SHIELD ACTIVE", 50, 50, paint);
        }
    }
    public void setHighScore(int num)
    {
        highScore = num;
    }
    public void imgLoad(Bitmap image) {imgloader.add(image);}
    public void sfxLoad(MediaPlayer sfx){ sfxloader.add(sfx);}

    public void downTouch(int x, int y, int pointerNumber) {
        if(left.contains(x,y)){
            rocket.moveLeft();
        }
        if(right.contains(x,y)){
            rocket.moveRight();
        }
    }
    public void upTouch(int x, int y,int pointerNumber) {
        rocket.straighten();
    }
}
