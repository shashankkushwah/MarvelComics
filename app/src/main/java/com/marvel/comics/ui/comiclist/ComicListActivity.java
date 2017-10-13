package com.marvel.comics.ui.comiclist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.marvel.comics.MarvelComicsApplication;
import com.marvel.comics.R;
import com.marvel.comics.data.model.Comic;
import com.marvel.comics.data.network.ApiHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ComicListActivity extends AppCompatActivity implements ComicListContract.View, ComicListAdapter
        .ComicClickListener {

    private SwipeRefreshLayout swipeRefreshLayout;

    private ComicListContract.Presenter presenter;
    private ComicListAdapter adapter;
    private Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_list);

        picasso = getPicasso();
        presenter = new ComicListPresenter(this, getApiHelper());
        setupViews();
        presenter.loadComicList();
    }

    private void setupViews() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        adapter = new ComicListAdapter(new ArrayList<Comic>(0), picasso, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                //presenter.loadComicList();
            }
        });
    }

    @Override
    public void onComicClick(Comic comic) {
        presenter.onComicItemClick(comic);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setProgressIndicator(final boolean active) {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(active);
            }
        });
    }

    @Override
    public void showComicList(List<Comic> comicList) {
        adapter.setList(comicList);
    }

    @Override
    public void showComicDetail(Comic comic) {
    }

    @NonNull
    private ApiHelper getApiHelper() {
        return ((MarvelComicsApplication) getApplication()).getApiHelper();
    }

    @NonNull
    private Picasso getPicasso() {
        return ((MarvelComicsApplication) getApplication()).getPicasso();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

}
