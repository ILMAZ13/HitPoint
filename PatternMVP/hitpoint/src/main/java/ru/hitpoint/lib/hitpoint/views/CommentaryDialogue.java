package ru.hitpoint.lib.hitpoint.views;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import model.entities.HitPointAPI;
import model.entities.ViewsBlock;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.hitpoint.lib.hitpoint.HitPoint;
import ru.hitpoint.lib.hitpoint.R;
import ru.hitpoint.lib.hitpoint.network.ApiService;
import ru.hitpoint.lib.hitpoint.network.NetServiceManager;


public class CommentaryDialogue extends Dialog implements
        android.view.View.OnClickListener {

    private Context mContext;
    private Button yes, no;
    private RatingBar ratingBar;
    private EditText editText;
    private ApiService apiService;
    private String path;
    private int dialogueColor;

    public CommentaryDialogue(Context activity, String path, int dialogueColor) {
        super(activity);
        mContext = activity;
        this.path = path;
        this.dialogueColor = dialogueColor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_SWIPE_TO_DISMISS);
        setContentView(R.layout.commentary);

        findViewById(R.id.main).setBackgroundColor(dialogueColor);
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
                    SharedPreferences preferences = mContext.getApplicationContext().getSharedPreferences("HitPoint", Context.MODE_PRIVATE);

                    apiService = new NetServiceManager().getApiService();
                    List<ViewsBlock> viewsBlocks = new ArrayList<>();
                    apiService.report(new HitPointAPI(preferences.getString("UDID", ""),
                            getWindow().getClass().getName(),
                            editText.getText().toString(), "1.0", path, "",
                            String.valueOf(ratingBar.getRating()), HitPoint.API_KEY, getWindow().getClass().getName(),
                            viewsBlocks)).enqueue(new Callback<Request>() {
                        @Override
                        public void onResponse(Call<Request> call, Response<Request> response) {
                            Toast.makeText(mContext, "Отзыв успешно добавлен!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Request> call, Throwable t) {
                            Toast.makeText(mContext, "Не удалось добавить!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dismiss();
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
