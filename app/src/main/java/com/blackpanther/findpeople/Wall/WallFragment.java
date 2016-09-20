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



               /*
        * Dummy Data for the Wall
        * */
        /*List<String> names= new ArrayList<>();
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
        Project wallCardViewContent = new Project("Find People",
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
        BroadcastContent broadcastContent = new BroadcastContent("Srinath",
                "Engineer's Day celebration is to be held on KCT on Sep 15, and thus we are celebrating the occassion grandly",
                "Engineer's Day Celebration",
                "Engineering","Engineer's Day",
                likes,
                comments,
                srinath);
        TeamJoinContent teamJoinContent = new TeamJoinContent("FindPeoplesApp",
                "Android App Development","This app is aimed at connecting the like minded people who are in need to work together to progress further",
                "This is an app to connected the gifted",
                profiles,
                likes,
                comments);
        prepareCardData(wallCardViewContent);
        prepareCardData(broadcastContent);
        prepareCardData(teamJoinContent);*/
        /*
        * Dummy Data for the Wall
        * */

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
                    URL url = new URL("http://54.244.177.52:8000/following/post/0");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    SharedPreferences myshare = getActivity().getSharedPreferences("login_details", Context.MODE_PRIVATE);
                    String cookie = myshare.getString("sessionid", "asdsa");
                    Log.w("cba", cookie);
                    conn.setRequestProperty("Cookie", "sessionid=" + URLEncoder.encode(cookie, "UTF-8"));
                    Log.w("session", cookie);
                    conn.connect();
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
                swipeRefreshLayout.setRefreshing(false);
                Log.d("On Post Execute", s);
                super.onPostExecute(s);
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
