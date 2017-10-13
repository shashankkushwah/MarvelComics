package com.marvel.comics.ui.comiclist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marvel.comics.R;
import com.marvel.comics.data.model.Comic;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Shashank on 13/10/2017.
 */

public class ComicListAdapter extends RecyclerView.Adapter<ComicListAdapter.ComicListViewHolder> {

    private List<Comic> comicList;
    private ComicClickListener comicClickListener;
    private Picasso picasso;

    public ComicListAdapter(@NonNull List<Comic> comicList, @NonNull Picasso picasso, @NonNull ComicClickListener
            comicClickListener) {
        this.comicList = comicList;
        this.picasso = picasso;
        this.comicClickListener = comicClickListener;
    }

    @Override
    public ComicListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ComicListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .recyclerview_item_comic, parent, false));
    }

    @Override
    public void onBindViewHolder(ComicListViewHolder holder, int position) {
        holder.bind(comicList.get(position));
    }

    @Override
    public int getItemCount() {
        return comicList.size();
    }

    public void setList(List<Comic> comicList) {
        this.comicList = comicList;
        notifyDataSetChanged();
    }

    public class ComicListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView thumbnailImageView;
        private TextView titleTextView;
        private TextView priceTextView;

        private Comic comic;

        ComicListViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            thumbnailImageView = itemView.findViewById(R.id.imageview_thumbnail);
            titleTextView = itemView.findViewById(R.id.textview_title);
            priceTextView = itemView.findViewById(R.id.textview_price);
        }

        void bind(Comic comic) {
            this.comic = comic;
            picasso.load(comic.getThumbnail().getFullPath()).placeholder(R.color.lightGray).into(thumbnailImageView);
            titleTextView.setText(comic.getTitle());
            priceTextView.setText(String.valueOf(comic.getPrice()));
        }

        @Override
        public void onClick(View v) {
            comicClickListener.onComicClick(comic);
        }
    }

    interface ComicClickListener {
        void onComicClick(Comic comic);
    }
}
