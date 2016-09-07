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
    private List<TrendingRecyclerViewContent> content_list;

    public class TrendingViewHolder extends RecyclerView.ViewHolder{
        TextView project_title, project_brief,project_category;
        TextView likes,comments;
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
    public TrendingRecyclerViewAdapter.TrendingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.wall_project_layout,parent,false);
        return new TrendingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TrendingViewHolder holder, int position) {
        TrendingRecyclerViewContent trendingRecyclerViewContent = content_list.get(position);
        holder.project_title.setText(trendingRecyclerViewContent.getProject_title());
        holder.project_category.setText(trendingRecyclerViewContent.getProject_category());
        holder.project_brief.setText(trendingRecyclerViewContent.getProject_brief());
        int a = trendingRecyclerViewContent.getLikes().size();
        if(a==1){
            holder.likes.setText(trendingRecyclerViewContent.getLikes().size() + " like");
        }else {
            holder.likes.setText(trendingRecyclerViewContent.getLikes().size() + " likes");
        }
        int b = trendingRecyclerViewContent.getComments().size();
        if(b==1){
            holder.comments.setText(trendingRecyclerViewContent.getComments().size()+" comment");
        }else{
            holder.comments.setText(trendingRecyclerViewContent.getComments().size()+" comments");
        }

    }


    @Override
    public int getItemCount() {
        return content_list.size();
    }


}
