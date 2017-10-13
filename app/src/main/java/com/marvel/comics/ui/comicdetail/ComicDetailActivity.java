package com.marvel.comics.ui.comicdetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.marvel.comics.MarvelComicsApplication;
import com.marvel.comics.R;
import com.marvel.comics.data.model.Comic;
import com.marvel.comics.data.model.ComicImage;
import com.marvel.comics.data.model.CreatorDetails;
import com.marvel.comics.data.model.CreatorsData;
import com.marvel.comics.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class ComicDetailActivity extends AppCompatActivity implements ComicDetailContract.View {

    public static final String EXTRA_COMIC = "comic";

    private ImageView thumbnailImageView;
    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView pageCountTextView;
    private TextView priceTextView;
    private TextView authorsTextView;

    private ComicDetailContract.Presenter presenter;
    private Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_detail);

        picasso = getPicasso();
        presenter = new ComicDetailPresenter(this);
        setupViews();

        Comic comic = getIntent().getParcelableExtra(EXTRA_COMIC);
        presenter.loadComicDetail(comic);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViews() {
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        thumbnailImageView = findViewById(R.id.imageview_thumbnail);
        titleTextView = findViewById(R.id.textview_title);
        descriptionTextView = findViewById(R.id.textview_description);
        pageCountTextView = findViewById(R.id.textview_page_count);
        priceTextView = findViewById(R.id.textview_price);
        authorsTextView = findViewById(R.id.textview_authors);
    }


    @Override
    public void showComicDetail(Comic comic) {
        ComicImage thumbnail = comic.getThumbnail();
        if (null != thumbnail) {
            picasso.load(thumbnail.getFullPath()).placeholder(R.color.lightGray).into(thumbnailImageView);
        }

        titleTextView.setText(comic.getTitle());
        descriptionTextView.setText(comic.getDescription());
        pageCountTextView.setText(String.format(Locale.UK, getString(R.string.total_page), comic.getPageCount()));
        priceTextView.setText(String.format(Locale.UK, getString(R.string.price), comic.getPrice()));

        StringBuilder authorsBuilder = new StringBuilder();
        CreatorsData creatorsData = comic.getCreators();
        List<CreatorDetails> creators = creatorsData.getItems();
        if (!Utils.isNullOrEmpty(creators)) {
            for (int i = 0, size = creators.size(); i < size; i++) {
                authorsBuilder.append(creators.get(i));
                authorsBuilder.append("\n");
            }
        }
        authorsTextView.setText(authorsBuilder.toString());
    }

    @NonNull
    private Picasso getPicasso() {
        return ((MarvelComicsApplication) getApplication()).getPicasso();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
