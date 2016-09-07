package com.blackpanther.findpeople.Trending;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackpanther.findpeople.R;
import com.blackpanther.findpeople.Wall.BroadcastContent;
import com.blackpanther.findpeople.Wall.Comments;
import com.blackpanther.findpeople.Wall.Likes;
import com.blackpanther.findpeople.Wall.TeamJoinContent;
import com.blackpanther.findpeople.Wall.WallProjectContent;
import com.blackpanther.findpeople.Wall.WallRecyclerViewAdapter;
import com.blackpanther.findpeople.profile.Following;
import com.blackpanther.findpeople.profile.NamePic;
import com.blackpanther.findpeople.profile.Profile;
import com.blackpanther.findpeople.profile.Skills;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ubuntu on 2/9/16.
 */
public class TrendingFragment extends Fragment {
    private List<Object> content_list = new ArrayList<>();
    private RecyclerView recyclerView;
    private WallRecyclerViewAdapter adapter;
    private String WALL_URL="http://10.1.124.67:8080/homepage/wall";
    private SwipeRefreshLayout swipeRefreshLayout;
    public TrendingFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.wall_layout,container,false);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swiprerefreshlayout);
        adapter = new WallRecyclerViewAdapter(content_list);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
               /*
        * Dummy Data for the Wall
        * */
        List<String> names= new ArrayList<>();
        names.add("Srinath");
        names.add("Divya");
        names.add("Prithvi");
        names.add("Sherly");
        List<String> skills_list = new ArrayList<>();
        skills_list.add("Android Programming");
        skills_list.add("PHP");
        skills_list.add("HTML");
        skills_list.add("Machine Learning");
        Profile srinath = new Profile(new Following(names),new NamePic("Srinath", BitmapFactory.decodeResource(getResources(),R.mipmap.srinath_profile_pic)),new Skills(skills_list));
        List<Likes> likes = new ArrayList<>();
        Likes likes1 = new Likes(srinath);
        likes.add(likes1);
        Comments comments1 = new Comments(srinath,"Superb",likes);
        List<Comments> comments = new ArrayList<>();
        comments.add(comments1);
        TrendingRecyclerViewContent wallCardViewContent = new TrendingRecyclerViewContent("Find People",
                "The word project comes from the Latin word projectum from the Latin verb proicere, before an action which in turn comes from pro-," +
                        " which denotes precedence, something that comes before something else in time (paralleling the Greek πρό) and iacere, " +
                        "to do. The word project thus actually originally meant \"before an action\".\n" +
                        "When the English language initially adopted the word, it referred to a plan of something, " +
                        "not to the act of actually carrying this plan out. Something performed in accordance with a project became known as an" +
                        "object.Every project has certain phases of development.\n",
                "Android App Development","Description"
                ,
                likes,
                comments);

        prepareCardData(wallCardViewContent);
        /*
        * Dummy Data for the Wall
        * */
        return v;

    }

    private void prepareCardData(TrendingRecyclerViewContent content) {
        content_list.add(content);
        adapter.notifyDataSetChanged();
    }
}
