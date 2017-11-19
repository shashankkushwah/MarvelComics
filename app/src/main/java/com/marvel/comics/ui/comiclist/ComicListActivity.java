package com.marvel.comics.ui.comiclist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.marvel.comics.MarvelComicsApplication;
import com.marvel.comics.R;
import com.marvel.comics.data.model.Comic;
import com.marvel.comics.data.network.ApiHelper;
import com.marvel.comics.di.comiclist.ComicListActivityComponent;
import com.marvel.comics.di.comiclist.DaggerComicListActivityComponent;
import com.marvel.comics.ui.comicdetail.ComicDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class ComicListActivity extends AppCompatActivity implements ComicListContract.View, ComicListAdapter
        .ComicClickListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private CardView totalCountCardView;
    private TextView totalCountTextView;

    @Inject
    public ComicListAdapter adapter;

    @Inject
    public ComicListPresenter presenter;

    @Inject
    public Picasso picasso;

    @Inject
    public ApiHelper apiHelper;

    private List<Comic> comicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_list);

        ComicListActivityComponent component = DaggerComicListActivityComponent.builder()
                .applicationComponent(MarvelComicsApplication.get(this)
                        .getComponent()).build();
        component.injectComicListActivity(this);

        setupViews();
        presenter.loadComicList();
        adapter.setComicClickListener(this);
    }

    private void setupViews() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
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

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (TextUtils.isEmpty(query)) {
                    adapter.setList(comicList);
                    totalCountCardView.setVisibility(View.GONE);
                } else {
                    presenter.filterComicForBudget(query, comicList);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    adapter.setList(comicList);
                    totalCountCardView.setVisibility(View.GONE);
                }
                return true;
            }
        });
        searchView.setInputType(InputType.TYPE_CLASS_NUMBER);

        totalCountCardView = findViewById(R.id.cardViewTotalPageCount);
        totalCountTextView = findViewById(R.id.totalCountTextView);
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
        this.comicList = new ArrayList<>(comicList);
        adapter.setList(comicList);
    }

    @Override
    public void showBudgetComicList(List<Comic> intBudgetComicList, int totalPageCount) {
        adapter.setList(intBudgetComicList);
        totalCountTextView.setText(String.format(Locale.UK, getString(R.string.total_page), totalPageCount));
        totalCountCardView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showComicDetail(Comic comic) {
        Intent intent = new Intent(this, ComicDetailActivity.class);
        intent.putExtra(ComicDetailActivity.EXTRA_COMIC, comic);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

}
