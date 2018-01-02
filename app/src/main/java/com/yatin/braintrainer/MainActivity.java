package com.yatin.braintrainer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timer;
    TextView answerText;
    TextView scoreText;
    TextView sum;
    TextView popUp;
    Button go;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;





    TextView goText;
    TextView goText1;


    Button button0;
    Button button1;
    Button button2;
    Button button3;

    public void playAgain(View view ){

        score = 0;
        numberOfQuestions = 0;
        timer.setText("10s");
        scoreText.setText("0/0");
        answerText.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        popUp.setVisibility(View.INVISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        scoreText.setVisibility(View.VISIBLE);
        sum.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);

        generateQuestion();
        new CountDownTimer(10010, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText("0:" + Integer.toString((int) millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);
                timer.setText("0s");
                answerText.setText("SCORE :" + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                button0.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                scoreText.setVisibility(View.INVISIBLE);
                sum.setVisibility(View.INVISIBLE);
                popUp.setVisibility(View.VISIBLE);

                timer.setVisibility(View.INVISIBLE);

                popUp.setRotation(360f);
            }
        }.start();

    }

    public void generateQuestion(){

        Random r = new Random();
        int a = r.nextInt(21) ;//till 20
        int b = r.nextInt(21) ;
        sum.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = r.nextInt(4);
        answers.clear();

        int incorrectAnswer ;
        for(int i = 0 ; i < 4 ; i++)
        {
            if(i == locationOfCorrectAnswer){
                Log.i("S",Integer.toString(i));
                answers.add(a + b);
            }else {
                incorrectAnswer = r.nextInt(41);
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = r.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }


    public void chooseAnswer(View view){

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score ++;
            answerText.setText("CORRECT!");
        }else
        {
            answerText.setText("WRONG");
        }

        numberOfQuestions++;
        scoreText.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();

    }

    public void go(View view){

        go.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgianButton));//doesnt matter take any argument here
        goText.setVisibility(View.INVISIBLE);
        goText1.setVisibility(View.INVISIBLE);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         timer = (TextView)findViewById(R.id.timer);
         answerText = (TextView)findViewById(R.id.answer);
        sum = (TextView)findViewById(R.id.sum);
        scoreText = (TextView)findViewById(R.id.score);
        go = (Button)findViewById(R.id.go);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        playAgainButton = (Button)findViewById(R.id.playAgianButton);
        gameRelativeLayout = (RelativeLayout)findViewById(R.id.gameRelativeLayout);
        popUp = (TextView) findViewById(R.id.popUp);
         goText = (TextView) findViewById(R.id.goText);
         goText1 = (TextView) findViewById(R.id.goText1);


        goText.animate().rotationBy(360f).scaleX(2).translationYBy(100f).setDuration(6000);
        goText1.animate().rotationBy(-360f).scaleY(2).translationXBy(-100f).setDuration(6000);


    }
}
