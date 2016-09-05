package com.blackpanther.findpeople.Wall;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.blackpanther.findpeople.R;
import com.blackpanther.findpeople.profile.Following;
import com.blackpanther.findpeople.profile.NamePic;
import com.blackpanther.findpeople.profile.Profile;
import com.blackpanther.findpeople.profile.Skills;

/**
 * Created by ubuntu on 2/9/16.
 */
public class WallFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private List<Object> content_list = new ArrayList<>();
    private RecyclerView recyclerView;
    private WallRecyclerViewAdapter adapter;
    private String WALL_URL="http://10.1.124.67:8080/homepage/wall";
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
               /*
        * Dummy Data for the Wall
        * */
        List<String> names= new ArrayList<>();
        names.add("Srinath");
        names.add("Divya");
        names.add("Kishore");
        List<String> skills_list = new ArrayList<>();
        skills_list.add("Android Programming");
        skills_list.add("PHP");
        skills_list.add("HTML");
        skills_list.add("Machine Learning");
        Profile srinath = new Profile(new Following(names),new NamePic("Srinath", BitmapFactory.decodeResource(getResources(),R.mipmap.srinath_profile_pic)),new Skills(skills_list));
        List<Profile> profiles = new ArrayList<>();
        profiles.add(srinath);
        List<Likes> likes = new ArrayList<>();
        Likes likes1 = new Likes(profiles);
        likes.add(likes1);
        Comments comments1 = new Comments(srinath,"Superb",likes);
        List<Comments> comments = new ArrayList<>();
        comments.add(comments1);
        WallProjectContent wallCardViewContent = new WallProjectContent("Find People",
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
        prepareCardData(teamJoinContent);
        /*
        * Dummy Data for the Wall
        * */
        new WallConnect().execute();
        return v;

    }

    private void prepareCardData(Object content) {
        content_list.add(content);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onRefresh() {


    }

    private class WallConnect extends AsyncTask<String,Void,String> {
        ProgressDialog progressDialog;
        @Override
        protected String doInBackground(String... strings) {
            String data = "";
                try {
                    CookieHandler.setDefault(new CookieManager());
                    URL url = new
                            URL("http://10.1.124.67:8080/homepage/projects/");
                    HttpURLConnection conn = (HttpURLConnection)
                            url.openConnection();
                    conn.setRequestMethod("GET");
                    SharedPreferences
                            myshare = getActivity().getSharedPreferences("login_details", Context.MODE_PRIVATE);
                    String cookie = myshare.getString("sessionid", "asdsa");
                    Log.w("cba", cookie);
                    conn.setRequestProperty("Cookie", "sessionid="
                            + URLEncoder.encode(cookie, "UTF-8"));
                    conn.connect();


                    InputStream ip = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new
                            InputStreamReader(ip));
                    final String temp = reader.readLine();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(getContext(), temp, Toast.LENGTH_LONG).show();
                        }
                    });
                    reader.close();
                } catch (Exception e) {
                    Log.w("abc", e);


                }
            return null;
        }
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {

            Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
            super.onPostExecute(s);
        }
        private String getQuery(String username,String password) throws UnsupportedEncodingException
        {
            String result = "&" +
                    URLEncoder.encode("username", "UTF-8") +
                    "=" +
                    URLEncoder.encode(username, "UTF-8") +
                    "&" +
                    URLEncoder.encode("password", "UTF-8") +
                    "=" +
                    URLEncoder.encode(password, "UTF-8");

            return result;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}
