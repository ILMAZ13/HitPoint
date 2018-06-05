package ru.hitpoint.lib.hitpoint.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import ru.hitpoint.lib.hitpoint.R;


public class CommentaryDialogue extends Dialog implements
        android.view.View.OnClickListener {

    private Context mContext;
    private Button yes, no;
    private RatingBar ratingBar;
    private EditText editText;

    public CommentaryDialogue(Context activity) {
        super(activity);
        mContext = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_SWIPE_TO_DISMISS);
        setContentView(R.layout.commentary);

        editText = findViewById(R.id.input_comment);
        ratingBar = findViewById(R.id.rating);
        yes = findViewById(R.id.confirm);
        no = findViewById(R.id.cancel);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.confirm) {
            if (ratingBar.getRating() != 0.0) {

                if (!editText.getText().toString().equals("")) {

                    //ToDo: push comment and rating on server

                } else {
                    Toast.makeText(mContext, "Оставьте свой комментарий, чтобы продолжить", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(mContext, "Поставьте свой рейтинг, чтобы продолжить", Toast.LENGTH_SHORT).show();
            }


        } else if (i == R.id.cancel) {
            dismiss();

        } else {
        }
    }
}
