
package com.example.quizapk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapk.data.AnswerListAsync;
import com.example.quizapk.data.QuestionBank;
import com.example.quizapk.models.Question;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener ,AnswerListAsync{
    private ImageButton next;
    private ImageButton previous;
    private Button True;
    private Button False;
    private TextView counter,text;
    int index = 0;
    private boolean a;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       next=findViewById(R.id.nextButton);
       previous=findViewById(R.id.previousButton);
       True=findViewById(R.id.trueButton);
       False=findViewById(R.id.falseButton);
       text=findViewById(R.id.textView6);
       counter=findViewById(R.id.count);
            next.setOnClickListener(this);
            previous.setOnClickListener(this);
            True.setOnClickListener(this);
            False.setOnClickListener(this);

         updateQuestion();
         new QuestionBank().getQuestions(new AnswerListAsync() {
             @Override
             public void processFinished(ArrayList<Question> questionArrayList) {

             }
         });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.previousButton: {
                index--;
                updateQuestion();
                break;
            }
            case R.id.nextButton:
            {
                index++;
                updateQuestion();
                break;
        }
            case R.id.trueButton:
            {
                checkable(true);
                updateQuestion();
                
                break;
        }
            case R.id.falseButton:
            {
                checkable(false);
                  updateQuestion();
                break;
        }

        }



    }

    private void checkable(boolean b) {
        a=b;
        new QuestionBank().getQuestions(new AnswerListAsync() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {
                if(questionArrayList.get(index).isAnswer() == a)
                {

                    Toast.makeText(MainActivity.this,"Correct",Toast.LENGTH_SHORT).show();
                    shakeAnimation();
                    shakeQuestion();
                }
        else
                {

                    Toast.makeText(MainActivity.this,"Wrong!",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void shakeQuestion() {
        new QuestionBank().getQuestions(new AnswerListAsync() {
            @SuppressLint("SetTextI18n")
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {
                text.setText(questionArrayList.get(index).getQuestion());
                counter.setText(index + " / " + questionArrayList.size());
            }
        });
    }
    private void updateQuestion() {
      new QuestionBank().getQuestions(new AnswerListAsync() {
          @SuppressLint("SetTextI18n")
          @Override
          public void processFinished(ArrayList<Question> questionArrayList) {
              if(index==questionArrayList.size()) {
                  index--;
                  text.setText(questionArrayList.get(index).getQuestion());
                  counter.setText(index + " / " + questionArrayList.size());
              }
              else if(index==-1)
              {index++;
                  new QuestionBank().getQuestions(new AnswerListAsync() {
                      @Override
                      public void processFinished(ArrayList<Question> questionArrayList) {
                          text.setText(questionArrayList.get(index).getQuestion());
                          counter.setText(index +" / "+questionArrayList.size());
                      }
                  });
              }
              else
              {shakeQuestion();
                  }



          }
      });


    }
private void shakeAnimation()
{
    Animation shake= AnimationUtils.loadAnimation(MainActivity.this,R.anim.shake_animation);
     CardView card=findViewById(R.id.cardView);
     card.setAnimation(shake);



}

    @Override
    public void processFinished(ArrayList<Question> questionArrayList) {

    }
}

