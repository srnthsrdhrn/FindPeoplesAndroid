package com.blackpanther.findpeople.Wall;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackpanther.findpeople.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by ubuntu on 2/9/16.
 */
public class WallFragment extends Fragment {
    private ArrayList<Object> content_list = new ArrayList<Object>();
    private RecyclerView recyclerView;
    private WallRecyclerViewAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    SwipeRefreshLayout.OnRefreshListener onRefreshListener;
    public WallFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.wall_layout,container,false);
        onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                //new WallConnect().execute();
            }
        };
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swiprerefreshlayout);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,android.R.color.holo_red_light,android.R.color.holo_green_light,android.R.color.holo_orange_light,android.R.color.holo_purple);
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        adapter = new WallRecyclerViewAdapter(content_list);
        Log.w("content","checkpoint");
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        new WallConnect().execute();
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                onRefreshListener.onRefresh();
            }
        });

        return v;

    }


    private class WallConnect extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            String data = "";
                try {
                    CookieHandler.setDefault(new CookieManager());
                    URL url = new URL(getResources().getString(R.string.wall_fragment_url));
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setRequestMethod("GET");
                    SharedPreferences myshare = getActivity().getSharedPreferences("login_details", Context.MODE_PRIVATE);
                    String cookie = myshare.getString("sessionid", "asdsa");
                    Log.w("cba", cookie);
                    conn.setRequestProperty("Cookie", "sessionid=" + URLEncoder.encode(cookie, "UTF-8"));
                    Log.w("session",cookie);
                    conn.connect();
                    InputStream ip = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(ip));
                    final String temp = reader.readLine();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.w("hai","hai");
                            JSONtoList(temp);
                        }
                    });
                }catch (Exception e) {
                    Log.w("Exception in internet", e);
                }
            return data;
        }


        @Override
        protected void onPostExecute(String s) {
            swipeRefreshLayout.setRefreshing(false);
            Log.d("On Post Execute", s);
            super.onPostExecute(s);
        }
    }

    private void JSONtoList(String temp) {
        Log.w("content",temp);
        int count=0;
        List<Object> temp_list = new ArrayList<>();
        try{
            JSONObject wall=new JSONObject(temp);
            JSONArray events=wall.getJSONArray("event");
            JSONArray teams=wall.getJSONArray("team");
            JSONArray projects=wall.getJSONArray("project");
            int i;
            for(i=0;i<events.length();i++){
                JSONObject event=events.getJSONObject(i);
                JSONObject post=event.getJSONObject("post");
                String title=post.getString("title");
                Log.w("content",title);
                String description=post.getString("description");
                String str_created=post.getString("created");
                Date created = ConvertToMilliSeconds(str_created);

                JSONArray catagories=post.getJSONArray("category");
                List<String> catagory=new ArrayList<String>();
                int j=0;
                for(j=0;j<catagories.length();j++){
                    catagory.add(i,catagories.getString(j));
                }
                Date due_date= ConvertToMilliSeconds(event.getString("due_date"));
                Date event_date= ConvertToMilliSeconds(event.getString("event_date"));

                temp_list.add(count++,new Event(title,description,created,catagory,event_date,due_date));

            }
            for(i=0;i<teams.length();i++){
                JSONObject team=teams.getJSONObject(i);
                JSONObject post=team.getJSONObject("post");
                String title=post.getString("title");
                Log.w("content",title);
                String description=post.getString("description");
                String str_created=post.getString("created");
                Date created = ConvertToMilliSeconds(str_created);

                JSONArray catagories=post.getJSONArray("category");
                List<String> catagory=new ArrayList<String>();
                int j=0;
                for(j=0;j<catagories.length();j++){
                    catagory.add(i,catagories.getString(j));
                }
                int n_request=team.getInt("n_request");
                String status=team.getString("status");
                temp_list.add(count++,new Team(title,description,created,catagory,n_request,status));



            }
            for(i=0;i<projects.length();i++){
                JSONObject project=projects.getJSONObject(i);
                JSONObject post=project.getJSONObject("post");
                String title=post.getString("title");
                Log.w("content",title);
                String description=post.getString("description");
                String str_created=post.getString("created");
                Date created = ConvertToMilliSeconds(str_created);

                JSONArray catagories=post.getJSONArray("category");
                List<String> catagory=new ArrayList<String>();
                int j;
                for(j=0;j<catagories.length();j++){
                    catagory.add(j,catagories.getJSONObject(j).getString("name"));
                }

                Date start_date= ConvertToMilliSeconds(project.getString("start_date"));
                Date end_date= ConvertToMilliSeconds(project.getString("end_date"));
                temp_list.add(count++,new Project(title,description,created,catagory,start_date,end_date));

            }

                Collections.sort(temp_list, new Comparator<Object>() {
                    @Override
                    public int compare(Object o, Object t1) {
                        Post post1 = (Post)o;
                        Post post2 = (Post)t1;
                        if(post1.created.getTime()<post2.created.getTime()){
                            return 1;
                        }else if(post1.created.getTime()>post2.created.getTime()){
                            return -1;
                        }else{
                            return 0;
                        }
                    }
                });
            for(int j=0;j<temp_list.size();j++)
                content_list.add(temp_list.get(j));
            if(content_list.isEmpty()){
                recyclerView.setVisibility(View.INVISIBLE);
                final Snackbar snackbar=Snackbar.make(swipeRefreshLayout," No posts to show",Snackbar.LENGTH_INDEFINITE);
                        snackbar.setAction("Follow a Category", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                             snackbar.dismiss();
                    }
                });
            }

        }
        catch (JSONException e){
            Log.w("error",e);
        }


    }

    private Date ConvertToMilliSeconds(String created) {
        String correct =created.replace("T","-");
        correct = correct.substring(0,18);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss", Locale.US);
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(correct);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

}
