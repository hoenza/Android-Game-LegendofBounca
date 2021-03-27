package com.RAHA.Activities;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.RAHA.R;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.concurrent.ThreadLocalRandom;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //get current layout object of the game: id is defined in activity_game.xml
        ConstraintLayout game_layout = findViewById(R.id.game_layout);

        //adding a touch listener to the current layout: we pass a touch listener as callback function
        game_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //https://stackoverflow.com/questions/8182513/ontouch-event-of-ontouchlistener-gets-called-twice-in-android
                if (event.getAction() != MotionEvent.ACTION_DOWN)
                    return false;
                ImageView ballImage = new ImageView(getApplicationContext());
                ballImage.setImageResource(R.drawable.ball);

                //This comments are for when we want to present this project to TAs: Don't remove!
                //https://stackoverflow.com/questions/2994494/how-do-i-create-an-imageview-in-java-code-within-an-existing-layout

                //LayoutParams are used by views to tell their parents how they want to be laid out. See ViewGroup Layout Attributes for a list of all child view attributes that this class supports.
                ConstraintLayout.LayoutParams ballParams = new ConstraintLayout.LayoutParams(
                        //WRAP_CONTENT, which means that the view wants to be just big enough to enclose its content (plus padding)
                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT
                );
                int ball_radius = 200;
                ballParams.height = ball_radius;
                ballParams.width = ball_radius;
                int left = randInt(ball_radius / 2, game_layout.getWidth() - ball_radius / 2);
                int top = randInt(ball_radius / 2, game_layout.getHeight() - ball_radius / 2);
                System.out.println((int) event.getX());
                System.out.println((int) event.getY());
                ballImage.setX(left);
                ballImage.setY(top);

                game_layout.addView(ballImage, ballParams);
                return true;
            }

            ;
        });
    }

    int randInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}