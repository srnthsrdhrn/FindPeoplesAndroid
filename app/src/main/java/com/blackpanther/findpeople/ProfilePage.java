package com.blackpanther.findpeople;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProfilePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView profile_pic;
    ImageButton edit;
    TextView profile_name;
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



        profile_pic = (ImageView) findViewById(R.id.profile_pic);
        profile_name = (TextView) findViewById(R.id.name);
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
                            AlertDialog.Builder builder = new AlertDialog.Builder(ProfilePage.this);
                            final View view = LayoutInflater.from(ProfilePage.this).inflate(R.layout.activity_newteam,null,false);
                            builder.setView(view);
                            builder.setTitle("New Team");
                            builder.setPositiveButton("Create Team", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    EditText name = (EditText) view.findViewById(R.id.prjct_name);
                                    Spinner spinner = (Spinner) view.findViewById(R.id.prjct_category);
                                    List<String> list = new ArrayList<>();
                                    list.add("Computer Science");
                                    list.add("Android App Development");
                                    list.add("Web Development");
                                    spinner.setAdapter(new ArrayAdapter<>(ProfilePage.this,android.R.layout.simple_spinner_dropdown_item,list));
                                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                            categorys= (String) adapterView.getItemAtPosition(i);

                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }

                                    }
                                    );
                                    spinner.setEnabled(true);
                                    EditText description = (EditText) view.findViewById(R.id.prjct_description);
                                    EditText numbeOfParticipants = (EditText) view.findViewById(R.id.no_of_participants);
                                    RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radio_grp);
                                    boolean Started= radioGroup.getCheckedRadioButtonId() == R.id.prjct_started;

                                    dialogInterface.dismiss();
                                    Toast.makeText(getApplicationContext(), "Name: "+ name.getText()+"\nDescription: "+description.getText()+"\nCategory: "+categorys+"\nNumber of Participants: "
                                            +numbeOfParticipants.getText()+"\n Started Project "+ Started, Toast.LENGTH_SHORT).show();
                                }
                            });
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            final Dialog dialog = builder.create();
                            dialog.show();

                        }
                    }


                });


            }

        });
    }
String categorys="";
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

        switch (id) {
            case R.id.homepage:

                break;
            case R.id.profile_page:
                onBackPressed();
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
