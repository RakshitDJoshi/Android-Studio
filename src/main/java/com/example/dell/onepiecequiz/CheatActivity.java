package com.example.dell.onepiecequiz;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private boolean mAnswerIsTrue;
    private Button mShowAnswerButton;
    private TextView mAnswerTextView;
    private void setAnswerShown(){
        Intent intent = new Intent(CheatActivity.this, CheatActivity.class);
        intent.putExtra(TAG, true);
        setResult(RESULT_OK, intent);
    }
    public static boolean wasAnswerShown(Intent data){
        return data.getBooleanExtra(TAG, false);
    }
    private static final String TAG = "com.example.dell.onepiecequiz.answer_is_true";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue = getIntent().getBooleanExtra(TAG, false);
        mShowAnswerButton = (Button)findViewById(R.id.show_answer_button);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnswerTextView = (TextView)findViewById(R.id.answer_text_view);
                if(mAnswerIsTrue)
                    mAnswerTextView.setText(R.string.true_button);
                else
                    mAnswerTextView.setText(R.string.false_button);
                setAnswerShown();
            }
        });
    }
}
