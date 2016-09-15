package com.blackpanther.findpeople.Trending;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blackpanther.findpeople.R;

import java.util.List;

/**
 * Created by ubuntu on 7/9/16.
 */
public class TrendingRecyclerViewAdapter extends RecyclerView.Adapter<TrendingRecyclerViewAdapter.TrendingViewHolder> {
private List<TrendingRecyclerContent> content_list;

    public TrendingRecyclerViewAdapter(List<TrendingRecyclerContent> content_list) {
        this.content_list = content_list;
    }

    public class TrendingViewHolder extends RecyclerView.ViewHolder{
        TextView project_title,project_brief,project_category,likes,comments;
        public TrendingViewHolder(View itemView) {
            super(itemView);
            project_title = (TextView) itemView.findViewById(R.id.project_title);
            project_brief = (TextView) itemView.findViewById(R.id.description);
            project_category = (TextView) itemView.findViewById(R.id.category);
            likes = (TextView) itemView.findViewById(R.id.liketv);
            comments= (TextView) itemView.findViewById(R.id.commenttv);
        }
    }

    @Override
    public TrendingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.wall_project_layout,parent,false);
        return new TrendingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TrendingViewHolder holder, int position) {
        TrendingRecyclerContent content = content_list.get(position);
        holder.project_title.setText(content.getProject_title());
        holder.project_category.setText(content.getProject_category());
        holder.project_brief.setText(content.getProject_brief());
        int a= content.getLikes().size();
        if(a>1) {
            holder.likes.setText(a + " likes");
        }else{
            holder.likes.setText(a+ " like");
        }
        int b = content.getComments().size();
        if(b>1) {
            holder.comments.setText(b+ " comments");
        }else{
            holder.comments.setText(b + " comment");
        }
    }



    @Override
    public int getItemCount() {
        return content_list.size();
    }
}
