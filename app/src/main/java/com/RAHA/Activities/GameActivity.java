package com.RAHA.Activities;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.RAHA.Logic.Room;
import com.RAHA.R;
import java.util.concurrent.ThreadLocalRandom;

public abstract class GameActivity extends Activity implements SensorEventListener {

    protected Room room = new Room();
    protected SensorManager sensorManager;
    protected Sensor sensor;
    protected float timestamp;
    protected abstract void initSensor();
    protected abstract void updateAngles(float axisX, float axisY, float axisZ, float dt);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("new game activity created");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initSensor();
        //This comments are for when we want to present this project to TAs: Don't remove!
        //get current layout object of the game: id is defined in activity_game.xml
        ConstraintLayout game_layout = findViewById(R.id.game_layout);

        //https://stackoverflow.com/questions/3591784/views-getwidth-and-getheight-returns-0
        game_layout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                room.setSize(game_layout.getWidth(), game_layout.getHeight());
                game_layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                insertBallOnRandomPoint(game_layout);
            }
        });
        Button btnShoot = findViewById(R.id.button_shoot);
        View.OnClickListener handler = new View.OnClickListener() {

            public void onClick(View v) {
                if (v == btnShoot) {
                    room.shootBall();
                }
            }
        };
        btnShoot.setOnClickListener(handler);
    }

    void insertBallOnRandomPoint(ConstraintLayout game_layout) {
        //https://stackoverflow.com/questions/2994494/how-do-i-create-an-imageview-in-java-code-within-an-existing-layout
        ImageView ballImage = new ImageView(getApplicationContext());
        ballImage.setImageResource(R.drawable.ball);

        //LayoutParams are used by views to tell their parents how they want to be laid out. See ViewGroup Layout Attributes for a list of all child view attributes that this class supports.
        int ball_radius = room.getBallRadius();
        ConstraintLayout.LayoutParams ballParams = new ConstraintLayout.LayoutParams(ball_radius, ball_radius);

        int ball_x = randInt(ball_radius, game_layout.getWidth() - ball_radius);
        int ball_y = randInt(ball_radius, game_layout.getHeight() - ball_radius);
        ballImage.setX(ball_x);
        ballImage.setY(ball_y);

        game_layout.addView(ballImage, ballParams);
        room.addBall(ballImage, ball_x, ball_y);
    }

    int randInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}