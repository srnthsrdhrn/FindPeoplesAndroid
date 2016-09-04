package com.blackpanther.findpeople;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.blackpanther.findpeople.profile.Following;
import com.blackpanther.findpeople.profile.NamePic;
import com.blackpanther.findpeople.profile.ProjectInformation;
import com.blackpanther.findpeople.profile.Skills;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by ubuntu on 5/9/16.
 */
public class ProfileRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Object> baseList= Collections.emptyList();
    LayoutInflater myLayoutInflater;
    Context myContext;
    public ProfileRecyclerViewAdapter(Context myCon, List<Object> baseList) {
        this.baseList = baseList;
        myContext=myCon;
        myLayoutInflater=LayoutInflater.from(myCon);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myView;
        RecyclerView.ViewHolder holder;
        switch (viewType){
            case 0:
                myView=myLayoutInflater.inflate(R.layout.name_dp,parent,false);
                holder=new ProfileNameDpRecyclerViewHolder(myView);
                break;
            case 1:
                myView=myLayoutInflater.inflate(R.layout.skills,parent,false);
                holder=new ProfileSkillsRecyclerViewHolder(myView);
                break;
            case 2:
                myView=myLayoutInflater.inflate(R.layout.following,parent,false);
                holder=new ProfileFollowingRecyclerViewHolder(myView);
                break;
            default:
                myView=myLayoutInflater.inflate(R.layout.wall_recycler_view_layout,parent,false);
                holder=new ProfileProjectRecyclerViewHolder(myView);


        }
        return holder;




    }
    class ProfileNameDpRecyclerViewHolder extends RecyclerView.ViewHolder{
        ImageView dp;
        TextView name;
        public ProfileNameDpRecyclerViewHolder(View itemView) {
            super(itemView);
            dp= (ImageView) itemView.findViewById(R.id.dp);
            name=(TextView) itemView.findViewById(R.id.name);
        }

    }
    class ProfileSkillsRecyclerViewHolder extends RecyclerView.ViewHolder{

        ListView skills;
        public ProfileSkillsRecyclerViewHolder(View itemView) {
            super(itemView);
            skills=(ListView)itemView.findViewById(R.id.listskills);

        }

    }
    class ProfileFollowingRecyclerViewHolder extends RecyclerView.ViewHolder{
        ListView following;

        public ProfileFollowingRecyclerViewHolder(View itemView) {
            super(itemView);
            following=(ListView) itemView.findViewById(R.id.listfollowing);

        }

    }
    public class ProfileProjectRecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView project_title,project_description,project_category;
        ImageButton like,comment,read_full;
        public ProfileProjectRecyclerViewHolder(View itemView) {
            super(itemView);
            project_title = (TextView) itemView.findViewById(R.id.project_title);
            project_description= (TextView) itemView.findViewById(R.id.description);
            project_category = (TextView) itemView.findViewById(R.id.category);
            like = (ImageButton) itemView.findViewById(R.id.like);
            comment = (ImageButton) itemView.findViewById(R.id.comment);
            read_full = (ImageButton) itemView.findViewById(R.id.read_full);


        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case 0:
            {
                ProfileNameDpRecyclerViewHolder namehold=(ProfileNameDpRecyclerViewHolder)holder;
                namehold.dp.setImageResource(R.drawable.common_google_signin_btn_icon_dark);
                namehold.name.setText(((NamePic)baseList.get(0)).name);

            }
            break;
            case 1:
            {
                ProfileSkillsRecyclerViewHolder hold=(ProfileSkillsRecyclerViewHolder)holder;
                ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(myContext, R.layout.text, R.id.text,((Skills)baseList.get(1)).myList);
                hold.skills.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            }
            case 2:
            {
                ProfileFollowingRecyclerViewHolder followhold=(ProfileFollowingRecyclerViewHolder)holder;
                ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(myContext,R.layout.text,R.id.text,((Following)baseList.get(2)).myList);
                followhold.following.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            }
            default:
            {
                ProfileProjectRecyclerViewHolder hold=(ProfileProjectRecyclerViewHolder)holder;
                final ProjectInformation content = (ProjectInformation) baseList.get(position);
                hold.project_title.setText(content.getProject_title());
                hold.project_category.setText(content.getProject_category());
                hold.project_description.setText(content.getProject_description());
            }
        }


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
