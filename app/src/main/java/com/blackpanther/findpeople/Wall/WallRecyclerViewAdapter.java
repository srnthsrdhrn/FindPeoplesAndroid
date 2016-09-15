package com.blackpanther.findpeople.Wall;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
            //broadcast_category = (TextView) itemView.findViewById(R.id.broadcast_category);
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
                int a= wallProjectContent.getLikes().size();
                if(a>1) {
                    projectViewHolder.likes.setText(a + " likes");
                }else{
                    projectViewHolder.likes.setText(a+ " like");
                }
                int b = wallProjectContent.getComments().size();
                if(b>1) {
                    projectViewHolder.comments.setText(b+ " comments");
                }else{
                    projectViewHolder.comments.setText(b + " comment");
                }



                final Project project = (Project) content_list.get(position);
                //Log.w("wall",project.getTitle());
                projectViewHolder.project_title.setText(project.getTitle());
                //projectViewHolder.project_category.setText("Android");
                projectViewHolder.project_brief.setText(project.getBrief());
                projectViewHolder.likes.setText(project.getN_Likes()+" likes");
                //projectViewHolder.comments.setText(project.getComments().size()+" comments");

                break;
            case 1:
                BroadcastViewHolder broadcastViewHolder = (BroadcastViewHolder) holder;
                Event broadcastContent = (Event) content_list.get(position);
                //Log.w("wall",broadcastContent.getTitle());
                broadcastViewHolder.broadcast_title.setText(broadcastContent.getTitle());
                //broadcastViewHolder.broadcast_category.setText("Android");
                broadcastViewHolder.broadcast_brief.setText(broadcastContent.getBrief());

                 a= broadcastContent.getLikes().size();
                if(a>1) {
                    broadcastViewHolder.likes.setText(a + " likes");
                }else{
                    broadcastViewHolder.likes.setText(a+ " like");
                }
                 b = broadcastContent.getComments().size();
                if(b>1) {
                    broadcastViewHolder.comments.setText(b+ " comments");
                }else{
                    broadcastViewHolder.comments.setText(b + " comment");
                }
                break;
            case 2:
                TeamJoinViewHolder teamJoinViewHolder = (TeamJoinViewHolder) holder;
                TeamJoinContent teamJoinContent = (TeamJoinContent) content_list.get(position);
                teamJoinViewHolder.project_title.setText(teamJoinContent.getProject_title());
                teamJoinViewHolder.project_category.setText(teamJoinContent.getProject_category());
                teamJoinViewHolder.project_brief.setText(teamJoinContent.getProject_brief());
                teamJoinViewHolder.likes.setText(teamJoinContent.getLikes().size()+" likes");
                 a= teamJoinContent.getLikes().size();
                if(a>1) {
                    teamJoinViewHolder.likes.setText(a + " likes");
                }else{
                    teamJoinViewHolder.likes.setText(a+ " like");
                }
                 b = teamJoinContent.getComments().size();
                if(b>1) {
                    teamJoinViewHolder.comments.setText(b+ " comments");
                }else{
                    teamJoinViewHolder.comments.setText(b + " comment");
                }

                broadcastViewHolder.likes.setText(broadcastContent.getN_Likes()+" likes");
                //broadcastViewHolder.comments.setText(broadcastContent.getComments().size()+" comments");
                break;
            case 2:
                TeamJoinViewHolder teamJoinViewHolder = (TeamJoinViewHolder) holder;
                Team teamJoinContent = (Team) content_list.get(position);
                //Log.w("wall",teamJoinContent.getTitle());
                teamJoinViewHolder.project_title.setText(teamJoinContent.getTitle());
                //teamJoinViewHolder.project_category.setText("Android");
                teamJoinViewHolder.project_brief.setText(teamJoinContent.getBrief());
                teamJoinViewHolder.likes.setText(teamJoinContent.getN_Likes()+" likes");
                //teamJoinViewHolder.comments.setText(teamJoinContent.getComments().size()+" comments");
                teamJoinViewHolder.members.setText(teamJoinContent.getN_request()+" members");
                //teamJoinContent

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
        if(object instanceof Project){
            return 0;
        } else if(object instanceof Event){
            return 1;
        } else if (object instanceof Team){
            return 2;
        }
        return -1;
    }
}
