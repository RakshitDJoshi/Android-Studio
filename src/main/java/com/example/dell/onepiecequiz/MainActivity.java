package com.example.dell.onepiecequiz;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    public boolean mIsCheater;
    private Intent data;
    private int REQUEST_CODE = 0;
    private int Recieved_code;
    private static final String KEY_INDEX = "index";
    private static final String TAG = "com.example.dell.onepiecequiz.answer_is_true";
    private Questions[] mQuestionBank = new Questions[]{
            new Questions(R.string.q_1, true),
            new Questions(R.string.q_2, true),
            new Questions(R.string.q_3, false),
            new Questions(R.string.q_4, true)
    };
    private int mCurrentIndex=0;
    private TextView mTextViewId;
    public Intent newIntent(Context packageContext, boolean answerIsTrue){
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(TAG, answerIsTrue);
        return intent;
    }
    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mTextViewId.setText(question);
    }
    private void checkAnswer(boolean mUserAnswer){
        if(mIsCheater){
            Toast.makeText(MainActivity.this, R.string.is_cheater, Toast.LENGTH_SHORT).show();
            return;
        }
        int messageId;
        boolean mCorrectAnswer = mQuestionBank[mCurrentIndex].isAnswerTrue();
        if(mUserAnswer == mCorrectAnswer){
            messageId=R.string.true_toast;
        }
        else
            messageId=R.string.false_toast;
        Toast.makeText(MainActivity.this, messageId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int RequestCode, int ReceivedCode, Intent data){
        if(ReceivedCode != RESULT_OK)
            return;
        if(RequestCode == REQUEST_CODE){
            if(data == null)
                return;
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
        }
        mTextViewId = (TextView) findViewById(R.id.question_view);
        updateQuestion();
        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });
        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });
        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex+1)%4;
                updateQuestion();
                mIsCheater=false;
            }
        });
        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CheatActivity.class);
                intent.putExtra(TAG, mQuestionBank[mCurrentIndex].isAnswerTrue());
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

}
