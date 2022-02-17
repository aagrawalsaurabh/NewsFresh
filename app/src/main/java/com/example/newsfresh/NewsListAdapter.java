package com.example.newsfresh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NewsListAdapter extends RecyclerView.Adapter<NewsViewHolder>{
    ArrayList<News> items = new ArrayList<>();

    private NewsItemClicked listener;
    public NewsListAdapter(NewsItemClicked listener){
        this.listener = listener;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
        NewsViewHolder viewHolder = new NewsViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(items.get(viewHolder.getAdapterPosition()));
            }
        });
//        return new NewsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        News currentItem = items.get(position);
        holder.titleView.setText(currentItem.title);
        holder.author.setText(currentItem.author);
        Glide.with(holder.itemView.getContext()).load(currentItem.imageUrl).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateNews(ArrayList<News> updatedNews){
        items.clear();
        items.addAll(updatedNews);
        notifyDataSetChanged();
    }


}

class NewsViewHolder extends RecyclerView.ViewHolder{
    TextView titleView;
    TextView author;
    ImageView image;
    public NewsViewHolder(View itemView) {
        super(itemView);
//        TextView titleView = (TextView) itemView.findViewById(R.id.title);
        titleView = (TextView) itemView.findViewById(R.id.title);
        image = (ImageView) itemView.findViewById(R.id.image);
        author = (TextView) itemView.findViewById(R.id.author);
    }
    public TextView getTitleView(){
        return titleView;
    }

    public ImageView getImage(){
        return image;
    }
    public TextView getAuthor(){
        return author;
    }
}
interface NewsItemClicked{
    void onItemClicked(News item);
}