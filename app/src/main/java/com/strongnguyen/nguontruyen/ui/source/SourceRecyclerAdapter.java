package com.strongnguyen.nguontruyen.ui.source;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.strongnguyen.nguontruyen.R;
import com.strongnguyen.nguontruyen.data.Book;
import com.strongnguyen.nguontruyen.data.source.BooksRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class
 * Created by pc on 10/24/2018.
 */
public class SourceRecyclerAdapter extends RecyclerView.Adapter<SourceRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private final OnListener onListener;
    private ArrayList<Book> listBooks;
    private int dataSource;

    public SourceRecyclerAdapter(Context mContext, OnListener onListener) {
        this.mContext = mContext;
        this.onListener = onListener;
        listBooks = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book,parent,  false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binData(listBooks.get(position));
    }

    @Override
    public int getItemCount() {
        return listBooks.size();
    }

    public void setDataSource(int dataSource) {
        this.dataSource = dataSource;
    }

    public void updateListBooks(List<Book> listBooks) {
        if (listBooks == null) return;
        this.listBooks.clear();
        this.listBooks.addAll(listBooks);
        notifyDataSetChanged();
    }

    public void addNewBooks(List<Book> books) {
        if (books == null) return;
        listBooks.addAll(books);
        notifyDataSetChanged();
    }

    public void clear() {
        listBooks.clear();
        notifyDataSetChanged();
    }

    /**
     * Class ViewHolder;
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivPoster;
        TextView tvTitle, tvAuthor, tvChapter, tvRating, tvViewed;

        public ViewHolder(View itemView) {
            super(itemView);

            ivPoster = (ImageView) itemView.findViewById(R.id.item_book_poster);
            tvTitle = (TextView) itemView.findViewById(R.id.item_book_title);
            tvAuthor = (TextView) itemView.findViewById(R.id.item_book_author);
            tvChapter = (TextView) itemView.findViewById(R.id.item_book_chapter);
            tvRating = (TextView) itemView.findViewById(R.id.item_book_rating);
            tvViewed = (TextView) itemView.findViewById(R.id.item_book_viewed);

            itemView.setOnClickListener(this);
        }

        public void binData(Book book) {

            // Set image poster
            if (book.getPoster().length() > 5) {
                Picasso.get().load(book.getPoster())
                    .placeholder(R.drawable.poster_def)
                    .error(R.drawable.poster_def)
                    .into(ivPoster);
            } else {
                ivPoster.setImageResource(R.drawable.poster_def);
            }

            // Set title, author book;
            tvTitle.setText(book.getTitle());
            tvAuthor.setText(String.format(
                    mContext.getResources().getString(R.string.text_author),
                    book.getAuthor())
            );

            // Set total chapter text
            StringBuilder chapter = new StringBuilder();

            if (book.isFull()) chapter.append("Full - ");
            chapter.append(book.getTotalChapter());
            tvChapter.setText(chapter.toString());

            // Set rating & viewed;
            switch (dataSource) {
                default: tvViewed.setVisibility(View.GONE);

                case BooksRepository.TRUYEN_FULL:
                    tvViewed.setVisibility(View.GONE);
                    tvRating.setVisibility(View.GONE);

                    break;
                case BooksRepository.TRUYEN_CV:
                    tvViewed.setVisibility(View.VISIBLE);
                    tvRating.setVisibility(View.VISIBLE);

                    tvRating.setText(String.format(
                            mContext.getResources().getString(R.string.text_reating),
                            String.valueOf(book.getRating() / 10))
                    );

                    tvViewed.setText(String.format(
                            mContext.getResources().getString(R.string.text_reating),
                            String.valueOf(book.getViewed()))
                    );
                    break;
            }
        }

        @Override
        public void onClick(View view) {
            onListener.onItemClick(listBooks.get(getLayoutPosition()).getUrl());
        }
    }

    public interface OnListener {

        void onItemClick(String url);
    }
}
