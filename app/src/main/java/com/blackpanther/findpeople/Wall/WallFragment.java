package com.blackpanther.findpeople.Wall;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.blackpanther.findpeople.R;
import com.blackpanther.findpeople.profile.Following;
import com.blackpanther.findpeople.profile.NamePic;
import com.blackpanther.findpeople.profile.Profile;
import com.blackpanther.findpeople.profile.Skills;

import org.json.JSONArray;
import org.json.JSONException;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ubuntu on 2/9/16.
 */
public class WallFragment extends Fragment {
    String baseurl;
    private ArrayList<Object> content_list = new ArrayList<Object>();
    private RecyclerView recyclerView;
    private WallRecyclerViewAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    SwipeRefreshLayout.OnRefreshListener onRefreshListener;

    public WallFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        baseurl= getResources().getString(R.string.baseURL);
        View v = inflater.inflate(R.layout.wall_layout, container, false);
        onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //new WallConnect().execute();
            }
        };
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swiprerefreshlayout);

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_blue_bright, android.R.color.holo_purple);
        adapter = new WallRecyclerViewAdapter(content_list);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter = new WallRecyclerViewAdapter(content_list);
        Log.w("content", "checkpoint");
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
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

    /*@Override
    public void onRefresh() {
        new WallConnect().execute();
    }*/

    private class WallConnect extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... strings) {
                String data = "";
                try {
                    CookieHandler.setDefault(new CookieManager());
                    String u=baseurl+"following/posts/0";
                    URL url = new URL(u);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    SharedPreferences myshare = getActivity().getSharedPreferences("login_details", Context.MODE_PRIVATE);
                    String token = myshare.getString("token", "asdsa");
                    conn.setRequestProperty("Authorization", "Token "+token);
                    InputStream ip = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(ip));
                    final String temp = reader.readLine();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.w("hai", "hai");
                            JSONtoList(temp);
                        }
                    });
                } catch (Exception e) {
                    Log.w("Exception in internet", e);
                }
                return data;
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //swipeRefreshLayout.setRefreshing(false);

            }
        }

        private void JSONtoList(String temp) {
            Log.w("content", temp);
            int count = 0;
            try {
                JSONObject wall = new JSONObject(temp);
                JSONArray events = (JSONArray) wall.getJSONArray("event");
                JSONArray teams = (JSONArray) wall.getJSONArray("team");
                JSONArray projects = (JSONArray) wall.getJSONArray("project");
                int i;
                for (i = 0; i < events.length(); i++) {
                    JSONObject event = events.getJSONObject(i);
                    JSONObject post = event.getJSONObject("post");
                    String title = post.getString("title");
                    Log.w("content", title);
                    String description = post.getString("description");
                    String str_created = post.getString("created");
                    Date created = new Date(convetToMilliSeconds(str_created));

                    JSONArray catagories = post.getJSONArray("catagory");
                    List<String> catagory = new ArrayList<String>();
                    int j = 0;
                    for (j = 0; j < catagories.length(); j++) {
                        catagory.add(i, catagories.getString(j));
                    }
                    Date due_date = new Date(convetToMilliSeconds(event.getString("due_date")));
                    Date event_date = new Date(convetToMilliSeconds(event.getString("event_date")));

                    content_list.add(count++, new Event(title, description, created, catagory, event_date, due_date));
                    adapter.notifyDataSetChanged();
                }
                for (i = 0; i < teams.length(); i++) {
                    JSONObject team = teams.getJSONObject(i);
                    JSONObject post = team.getJSONObject("post");
                    String title = post.getString("title");
                    Log.w("content", title);
                    String description = post.getString("description");
                    String str_created = post.getString("created");
                    Date created = new Date(convetToMilliSeconds(str_created));

                    JSONArray catagories = post.getJSONArray("catagory");
                    List<String> catagory = new ArrayList<String>();
                    int j = 0;
                    for (j = 0; j < catagories.length(); j++) {
                        catagory.add(i, catagories.getString(j));
                    }
                    int n_request = team.getInt("n_request");
                    String status = team.getString("status");
                    content_list.add(count++, new Team(title, description, created, catagory, n_request, status));
                    adapter.notifyDataSetChanged();


                }
                for (i = 0; i < projects.length(); i++) {
                    JSONObject project = projects.getJSONObject(i);
                    JSONObject post = project.getJSONObject("post");
                    String title = post.getString("title");
                    Log.w("content", title);
                    String description = post.getString("description");
                    String str_created = post.getString("created");
                    Date created = new Date(convetToMilliSeconds(str_created));

                    JSONArray catagories = post.getJSONArray("catagory");
                    List<String> catagory = new ArrayList<String>();
                    int j = 0;
                    for (j = 0; j < catagories.length(); j++) {
                        catagory.add(j, catagories.getJSONObject(j).getString("name"));
                    }

                    Date start_date = new Date(convetToMilliSeconds(project.getString("start_date")));
                    Date end_date = new Date(convetToMilliSeconds(project.getString("end_date")));
                    content_list.add(count++, new Project(title, description, created, catagory, start_date, end_date));

                    adapter.notifyDataSetChanged();
                }

            } catch (JSONException e) {
                Log.w("error", e);
            }


        }

        private long convetToMilliSeconds(String created) {
            long milliseconds = 0;
        /*int year=Integer.parseInt(created.substring(0,4));
        int month=Integer.parseInt(created.substring(5,7));
        int date=Integer.parseInt(created.substring(8,10));
        milliseconds+=(long) ((year-1970)*365.25*24*60*60*10);
        milliseconds+=month*30*/

            return milliseconds;

        }

    }
