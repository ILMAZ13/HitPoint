package com.study.ilmaz.patternmvp.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.study.ilmaz.patternmvp.R;
import com.study.ilmaz.patternmvp.model.entities.User;
import com.study.ilmaz.patternmvp.presenter.PeoplePresenter;
import com.study.ilmaz.patternmvp.view.interfaces.PeopleActivityInterface;

import java.util.List;

public class PeopleActivity extends AppCompatActivity implements PeopleActivityInterface {
    private PeoplePresenter presenter;
    private RecyclerView recyclerView;
    private FloatingActionButton button;
    private PeopleAdapter peopleAdapter;
    private ProgressBar progressBar;

    private static int REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        presenter = new PeoplePresenter(this);

        initViews();
        setupRecyclerView();
//        checkDrawOverlayPermission();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLoadButtonPressed(v);
                System.out.println("Clicked!!!");
            }
        });
    }

    private void setupRecyclerView() {
        peopleAdapter = new PeopleAdapter();
        peopleAdapter.setItemClickListener(new PeopleAdapter.ItemClickListener() {
            @Override
            public void onItemClick(User user, View view) {
                presenter.onItemClicked(user, view);
            }
        });
        recyclerView.setAdapter(peopleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initViews() {
        recyclerView = findViewById(R.id.rv_people);
        progressBar = findViewById(R.id.progressBar);
        button = findViewById(R.id.fab_add);
    }

    @Override
    public void showPeopleList(List<User> userList) {
        progressBar.setVisibility(View.GONE);
        peopleAdapter.setUserList(userList);
        peopleAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void navigateToDetailActivity(User user, View view) {
        startActivity(DetailActivity.launchDetail(view.getContext(), user));
    }
}
