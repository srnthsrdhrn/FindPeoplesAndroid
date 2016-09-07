package com.blackpanther.findpeople.Wall;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blackpanther.findpeople.R;

import java.util.List;

/**
 * Created by ubuntu on 3/9/16.
 */
public class WallRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> content_list;

    public class ProjectViewHolder extends RecyclerView.ViewHolder{
        TextView project_title, project_brief,project_category;
        TextView likes,comments;
        public ProjectViewHolder(View itemView) {
            super(itemView);
            project_title = (TextView) itemView.findViewById(R.id.project_title);
            project_brief = (TextView) itemView.findViewById(R.id.description);
            project_category = (TextView) itemView.findViewById(R.id.category);
            likes = (TextView) itemView.findViewById(R.id.liketv);
            comments= (TextView) itemView.findViewById(R.id.commenttv);

        }
    }
    public class BroadcastViewHolder extends RecyclerView.ViewHolder{
        TextView broadcast_title, broadcast_category, broadcast_brief;
        TextView likes,comments;

        public BroadcastViewHolder(View itemView) {
            super(itemView);
            broadcast_title = (TextView) itemView.findViewById(R.id.broadcast_title);
            broadcast_brief = (TextView) itemView.findViewById(R.id.broadcast_brief);
            broadcast_category = (TextView) itemView.findViewById(R.id.broadcast_category);
            likes = (TextView) itemView.findViewById(R.id.liketv);
            comments = (TextView) itemView.findViewById(R.id.commenttv);

        }
    }
    public class TeamJoinViewHolder extends RecyclerView.ViewHolder{
        TextView project_title, project_brief,project_category;
        TextView likes,comments,members;

        public TeamJoinViewHolder(View itemView) {
            super(itemView);
            project_title = (TextView) itemView.findViewById(R.id.project_title);
            project_brief = (TextView) itemView.findViewById(R.id.project_brief);
            project_category = (TextView) itemView.findViewById(R.id.project_category);
            likes = (TextView) itemView.findViewById(R.id.liketv);
            comments = (TextView) itemView.findViewById(R.id.commenttv);
            members = (TextView) itemView.findViewById(R.id.jointv);
        }
    }

    public  WallRecyclerViewAdapter(List<Object> list){
        content_list=list;
    }
   @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0: {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.wall_project_layout, parent, false);
                return new ProjectViewHolder(v);
            }
            case 1: {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.wall_broadcast_layout, parent, false);
                return new BroadcastViewHolder(v);
            }
            case 2:{
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.wall_team_join, parent, false);
                return new TeamJoinViewHolder(v);
            }

        }
       return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                ProjectViewHolder projectViewHolder = (ProjectViewHolder)holder;
                final WallProjectContent wallProjectContent = (WallProjectContent) content_list.get(position);
                projectViewHolder.project_title.setText(wallProjectContent.getProject_title());
                projectViewHolder.project_category.setText(wallProjectContent.getProject_category());
                projectViewHolder.project_brief.setText(wallProjectContent.getProject_brief());
                int a = wallProjectContent.getLikes().size();
                if(a==1)
                {
                    projectViewHolder.likes.setText(wallProjectContent.getLikes().size() + " like");
                }else {
                    projectViewHolder.likes.setText(wallProjectContent.getLikes().size() + " likes");
                }
                int b= wallProjectContent.getComments().size();
                if(b==1){
                    projectViewHolder.comments.setText(wallProjectContent.getComments().size() + " comment");
                }else {
                    projectViewHolder.comments.setText(wallProjectContent.getComments().size() + " comments");
                }
                break;
            case 1:
                BroadcastViewHolder broadcastViewHolder = (BroadcastViewHolder) holder;
                BroadcastContent broadcastContent = (BroadcastContent) content_list.get(position);
                broadcastViewHolder.broadcast_title.setText(broadcastContent.getTitle());
                broadcastViewHolder.broadcast_category.setText(broadcastContent.getCategory());
                broadcastViewHolder.broadcast_brief.setText(broadcastContent.getBrief());
                a = broadcastContent.getLikes().size();
                if(a==1){
                    broadcastViewHolder.likes.setText(broadcastContent.getLikes().size() + " like");
                }else {
                    broadcastViewHolder.likes.setText(broadcastContent.getLikes().size() + " likes");
                }
                b = broadcastContent.getComments().size();
                if(b==1){
                    broadcastViewHolder.comments.setText(broadcastContent.getComments().size() + " comment");
                }else {
                    broadcastViewHolder.comments.setText(broadcastContent.getComments().size() + " comments");
                }
                break;
            case 2:
                TeamJoinViewHolder teamJoinViewHolder = (TeamJoinViewHolder) holder;
                TeamJoinContent teamJoinContent = (TeamJoinContent) content_list.get(position);
                teamJoinViewHolder.project_title.setText(teamJoinContent.getProject_title());
                teamJoinViewHolder.project_category.setText(teamJoinContent.getProject_category());
                teamJoinViewHolder.project_brief.setText(teamJoinContent.getProject_brief());
                a = teamJoinContent.getLikes().size();
                if(a==1){
                    teamJoinViewHolder.likes.setText(teamJoinContent.getLikes().size() + " like");
                }else {
                    teamJoinViewHolder.likes.setText(teamJoinContent.getLikes().size() + " likes");
                }
                b= teamJoinContent.getComments().size();
                if(b==1){
                    teamJoinViewHolder.comments.setText(teamJoinContent.getComments().size() + " comment");
                }else {
                    teamJoinViewHolder.comments.setText(teamJoinContent.getComments().size() + " comments");
                }
                int c= teamJoinContent.getMembers().size();
                if(c==1){
                    teamJoinViewHolder.members.setText(teamJoinContent.getMembers().size() + " member");
                }else {
                    teamJoinViewHolder.members.setText(teamJoinContent.getMembers().size() + " members");
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return content_list.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object object=content_list.get(position);
        if(object instanceof WallProjectContent){
            return 0;
        } else if(object instanceof BroadcastContent){
            return 1;
        } else if (object instanceof TeamJoinContent){
            return 2;
        }
        return -1;
    }
}
