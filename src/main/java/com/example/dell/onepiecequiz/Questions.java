package com.example.dell.onepiecequiz;

public class Questions {
    private int mTextResId;
    private boolean mIsAnswerTrue;
    public Questions(int TextResId, boolean IsAnswerTrue){
        mTextResId = TextResId;
        mIsAnswerTrue = IsAnswerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mIsAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mIsAnswerTrue = answerTrue;
    }
}
