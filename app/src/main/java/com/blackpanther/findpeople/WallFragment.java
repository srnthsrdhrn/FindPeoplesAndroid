package com.blackpanther.findpeople;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.blackpanther.findpeople.profile.ProjectInformation;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ubuntu on 2/9/16.
 */
public class WallFragment extends Fragment {
    private List<ProjectInformation> content_list = new ArrayList<>();
    private RecyclerView recyclerView;
    private WallRecyclerViewAdapter adapter;

    public WallFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.wall_layout,container,false);
        adapter = new WallRecyclerViewAdapter(content_list);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareWallData();
        new AsyncTask<String,Void,Void>(){
            @Override
            protected Void doInBackground(String... strings) {
                try{
                    URL url=new URL("http://10.1.124.67:8080/homepage/projects");
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();
                    InputStream ip=conn.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(ip));
                    final String temp=reader.readLine();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(),temp,Toast.LENGTH_LONG).show();
                        }
                    });
                    reader.close();
                }catch (Exception e){

                }
                return null;
            }
        }.execute();

        return v;

    }

    private void prepareWallData() {
        ProjectInformation content = new ProjectInformation();
        content.setProject_title("Find People");
        content.setProject_category("Android App Development");
        content.setProject_description("The word project comes from the Latin word projectum from the Latin verb proicere, \"before an action\" which in turn comes from pro-, which denotes precedence, something that comes before something else in time (paralleling the Greek πρό) and iacere, \"to do\". The word \"project\" thus actually originally meant \"before an action\".\n" +
                "When the English language initially adopted the word, it referred to a plan of something, not to the act of actually carrying this plan out. Something performed in accordance with a project became known as an \"object\".Every project has certain phases of development.\n" +
                "    ");
        content_list.add(content);
        content.setProject_title("Find People");
        content.setProject_category("Android App Development");
        content.setProject_description("The word project comes from the Latin word projectum from the Latin verb proicere, \"before an action\" which in turn comes from pro-, which denotes precedence, something that comes before something else in time (paralleling the Greek πρό) and iacere, \"to do\". The word \"project\" thus actually originally meant \"before an action\".\n" +
                "When the English language initially adopted the word, it referred to a plan of something, not to the act of actually carrying this plan out. Something performed in accordance with a project became known as an \"object\".Every project has certain phases of development.\n" +
                "    ");
        content_list.add(content);
        content.setProject_title("Find People");
        content.setProject_category("Android App Development");
        content.setProject_description("The word project comes from the Latin word projectum from the Latin verb proicere, \"before an action\" which in turn comes from pro-, which denotes precedence, something that comes before something else in time (paralleling the Greek πρό) and iacere, \"to do\". The word \"project\" thus actually originally meant \"before an action\".\n" +
                "When the English language initially adopted the word, it referred to a plan of something, not to the act of actually carrying this plan out. Something performed in accordance with a project became known as an \"object\".Every project has certain phases of development.\n" +
                "    ");
        content_list.add(content);
        adapter.notifyDataSetChanged();
    }
}
