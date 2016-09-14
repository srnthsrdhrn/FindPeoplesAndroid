package com.blackpanther.findpeople;

<<<<<<< HEAD
import android.app.Dialog;
=======
import android.graphics.Bitmap;
>>>>>>> 7cd893fcf87673e03d908d095a6f2b427fafe9c4
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
<<<<<<< HEAD
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
=======
import android.widget.ArrayAdapter;

import com.blackpanther.findpeople.profile.NamePic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
>>>>>>> 7cd893fcf87673e03d908d095a6f2b427fafe9c4

public class ProfilePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView profile_pic;
    ImageButton edit;
    TextView profile_name,profile_age,profile_dob,profile_email,profile_gender;
    List<String> categories_list;
    ListView categories_list_view;

    FloatingActionButton fab_plus, fab_newbroadcast, fab_newteam, fab_newproject;
    Animation FabOpen, FabClose, FabRclockwise, FabRanticlockwise;
    boolean isOpen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
<<<<<<< HEAD



        profile_pic = (ImageView) findViewById(R.id.profile_pic);
        profile_name = (TextView) findViewById(R.id.name);
        profile_age = (TextView) findViewById(R.id.age);
        profile_dob = (TextView) findViewById(R.id.dob);
        profile_email= (TextView) findViewById(R.id.email_id);
        profile_gender = (TextView) findViewById(R.id.gender);
        categories_list_view = (ListView) findViewById(R.id.categories_list);
        edit = (ImageButton) findViewById(R.id.categories_edit);
        categories_list = new ArrayList<>();



        fab_plus = (FloatingActionButton) findViewById(R.id.fab_plus);
        fab_newbroadcast = (FloatingActionButton) findViewById(R.id.fab_newBroadcast);
        fab_newteam = (FloatingActionButton) findViewById(R.id.fab_newTeam);
        fab_newproject = (FloatingActionButton) findViewById(R.id.fab_newProject);
        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        FabRclockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        FabRanticlockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlockwise);

        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isOpen) {
                    fab_newproject.startAnimation(FabClose);
                    fab_newteam.startAnimation(FabClose);
                    fab_newbroadcast.startAnimation(FabClose);
                    fab_plus.startAnimation(FabRanticlockwise);
                    fab_newbroadcast.setClickable(false);
                    fab_newteam.setClickable(false);
                    fab_newproject.setClickable(false);
                    isOpen = false;


                } else {
                    fab_newproject.startAnimation(FabOpen);
                    fab_newteam.startAnimation(FabOpen);
                    fab_newbroadcast.startAnimation(FabOpen);
                    fab_plus.startAnimation(FabRclockwise);
                    fab_newbroadcast.setClickable(true);
                    fab_newteam.setClickable(true);
                    fab_newproject.setClickable(true);
                    isOpen = true;

                }

                fab_newproject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (v.getId() == R.id.fab_newProject) {
                            final Dialog dialog = new Dialog(ProfilePage.this);
                            dialog.setTitle("New Project");
                            dialog.setContentView(R.layout.activity_newproject);
                            dialog.show();
                            final EditText edittext1 = (EditText) dialog.findViewById(R.id.editText1);
                            final EditText edittext2 = (EditText) dialog.findViewById(R.id.editText2);
                            final EditText edittext3 = (EditText) dialog.findViewById(R.id.editText3);
                            final EditText edittext4 = (EditText) dialog.findViewById(R.id.editText4);
                            final EditText edittext5 = (EditText) dialog.findViewById(R.id.editText5);
                            final EditText edittext6 = (EditText) dialog.findViewById(R.id.editText6);
                            Button submit = (Button) dialog.findViewById(R.id.submit);
                            Button cancel = (Button) dialog.findViewById(R.id.cancel);
                            submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String text1 = edittext1.getText().toString();
                                    String text2 = edittext2.getText().toString();
                                    String text3 = edittext3.getText().toString();
                                    String text4 = edittext4.getText().toString();
                                    String text5 = edittext5.getText().toString();
                                    String text6 = edittext6.getText().toString();
                                    Toast.makeText(getApplicationContext(), "all entries taken!!", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();

                                }
                            });

                            cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    dialog.cancel();

                                }
                            });
                        }

                    }
                });

                fab_newbroadcast.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (v.getId() == R.id.fab_newBroadcast) {
                            final Dialog dialog = new Dialog(ProfilePage.this);
                            dialog.setTitle("Broadcast");
                            dialog.setContentView(R.layout.activity_broadcast);
                            dialog.show();
                            final EditText edittext1 = (EditText) dialog.findViewById(R.id.editText1);
                            Button post = (Button) dialog.findViewById(R.id.post);
                            Button cancel = (Button) dialog.findViewById(R.id.cancel);
                            post.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String text1 = edittext1.getText().toString();
                                    Toast.makeText(getApplicationContext(), "Broadcasted", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                }
                            });
                            cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    dialog.cancel();

                                }
                            });

                        }

                    }


                });

                fab_newteam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (v.getId() == R.id.fab_newTeam) {
                            final Dialog dialog = new Dialog(ProfilePage.this);
                            dialog.setTitle("New Team");
                            dialog.setContentView(R.layout.activity_newteam);
                            dialog.show();
                            final EditText edittext1 = (EditText) dialog.findViewById(R.id.editText1);
                            final EditText edittext2 = (EditText) dialog.findViewById(R.id.editText2);
                            final EditText edittext3 = (EditText) dialog.findViewById(R.id.editText3);
                            final EditText edittext4 = (EditText) dialog.findViewById(R.id.editText4);
                            final EditText edittext5 = (EditText) dialog.findViewById(R.id.editText5);
                            Button Register = (Button) dialog.findViewById(R.id.post);
                            Button cancel = (Button) dialog.findViewById(R.id.cancel);
                            Register.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String text1 = edittext1.getText().toString();
                                    String text2 = edittext2.getText().toString();
                                    String text3 = edittext3.getText().toString();
                                    String text4 = edittext4.getText().toString();
                                    String text5 = edittext5.getText().toString();
                                    Toast.makeText(getApplicationContext(), "NOTED", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                }
                            });
                            cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    dialog.cancel();

                                }
                            });

                        }
                    }


                });


            }

        });
    }
=======
        List<Object> myList=getData();
>>>>>>> 7cd893fcf87673e03d908d095a6f2b427fafe9c4

        ProfileRecyclerViewAdapter myAdapter=new ProfileRecyclerViewAdapter(getApplicationContext(),myList);
        RecyclerView myRecycler= (RecyclerView) findViewById(R.id.recycler_view);
        myRecycler.setAdapter(myAdapter);
    }
    public ArrayList<Object> getData(){
        ArrayList<Object> myObject=new ArrayList<Object>();
        //myObject.add(0,new NamePic("sabari",);
        return myObject;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
