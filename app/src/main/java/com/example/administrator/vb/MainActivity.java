package com.example.administrator.vb;

import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import activity.sortrandom;
import fragment.zhuye;
import sqlite.operate;
import sqlite.writedb;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private FrameLayout frameLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private String path="/data/data/com.example.administrator.vb/databases/hehe.db";
    private FragmentManager f=getSupportFragmentManager();
   // public static final String DB_PATH = "/data" + Environment.getDataDirectory().getAbsolutePath() + "/" + "com.example.administrator.vb";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       writedb s=new writedb(this);
        s.wr(path);
        init();
        zhuye zhuye=new zhuye();
        FragmentManager f=getSupportFragmentManager();
        f.beginTransaction().replace(R.id.frame_content,zhuye).commit();


    }
    private void init(){
        drawerLayout=(DrawerLayout)findViewById(R.id.dl);
        frameLayout=(FrameLayout)findViewById(R.id.frame_content);
        navigationView=(NavigationView)findViewById(R.id.na);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);

        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        drawerLayout.closeDrawers();
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.first:
                zhuye s=new zhuye();
                f.beginTransaction().replace(R.id.frame_content,s).commit();

                break;
            case R.id.clean:

                operate op=new operate(this);
                op.clean();
                break;
            case R.id.set:
                break;
            case R.id.feedback:
                break;
            case R.id.about:
                break;
        }
        drawerLayout.closeDrawers();

        return true;
    }
}
