package com.blackpanther.findpeople;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ubuntu on 3/9/16.
 */
public class WallRecyclerViewAdapter extends RecyclerView.Adapter<WallRecyclerViewAdapter.WallRecyclerViewHolder> {
    private List<WallRecyclerViewContent> content_list;

    public class WallRecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView project_title,project_description,project_category;
        ImageButton like,comment,read_full;
        public WallRecyclerViewHolder(View itemView) {
            super(itemView);
            project_title = (TextView) itemView.findViewById(R.id.project_title);
            project_description= (TextView) itemView.findViewById(R.id.description);
            project_category = (TextView) itemView.findViewById(R.id.category);
            like = (ImageButton) itemView.findViewById(R.id.like);
            comment = (ImageButton) itemView.findViewById(R.id.comment);
            read_full = (ImageButton) itemView.findViewById(R.id.read_full);

        }
    }

    public  WallRecyclerViewAdapter(List<WallRecyclerViewContent> list){
        content_list=list;
    }

    @Override
    public WallRecyclerViewAdapter.WallRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.wall_recycler_view_layout,parent,false);
        return new WallRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(WallRecyclerViewHolder holder, int position) {
        final WallRecyclerViewContent content = content_list.get(position);
        holder.project_title.setText(content.getProject_title());
        holder.project_category.setText(content.getProject_category());
        holder.project_description.setText(content.getProject_description());
    }

    @Override
    public int getItemCount() {
        return content_list.size();
    }
}
