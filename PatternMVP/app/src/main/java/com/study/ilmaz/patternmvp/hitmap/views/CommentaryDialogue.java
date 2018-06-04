package com.study.ilmaz.patternmvp.hitmap.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.study.ilmaz.patternmvp.R;


public class CommentaryDialogue extends Dialog implements
        android.view.View.OnClickListener {

    public Context mContext;
    public Button yes, no;

    public CommentaryDialogue(Context activity) {
        super(activity);
        mContext = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_SWIPE_TO_DISMISS);
        setContentView(R.layout.commentary);
        yes = findViewById(R.id.confirm);
        no = findViewById(R.id.cancel);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm:
                dismiss();
                break;
            case R.id.cancel:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
