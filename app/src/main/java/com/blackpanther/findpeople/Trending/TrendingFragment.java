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
import com.blackpanther.findpeople.Wall.Comments;
import com.blackpanther.findpeople.Wall.Likes;
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
    SwipeRefreshLayout.OnRefreshListener onRefreshListener;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    TrendingRecyclerViewAdapter adapter;
    List<TrendingRecyclerContent> content_list;
    public TrendingFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.trending_layout,container,false);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swiprerefreshlayout);
        content_list = new ArrayList<>();
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        adapter = new TrendingRecyclerViewAdapter(content_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.currentThread().sleep(5000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });


                    }
                }).start();

            }
        };
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light,android.R.color.holo_orange_light,android.R.color.holo_blue_bright,android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);

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
        List<Profile> profiles = new ArrayList<>();
        profiles.add(srinath);
        List<Likes> likes = new ArrayList<>();
        Likes likes1 = new Likes(srinath);
        likes.add(likes1);
        Comments comments1 = new Comments(srinath,"Superb",likes);
        List<Comments> comments = new ArrayList<>();
        comments.add(comments1);
        TrendingRecyclerContent trendingRecyclerContent = new TrendingRecyclerContent("Find People",
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
        prepareTrendingCardData(trendingRecyclerContent);
        return v;
    }
    private void prepareTrendingCardData(TrendingRecyclerContent content) {
        content_list.add(content);
        adapter.notifyDataSetChanged();
    }
}
