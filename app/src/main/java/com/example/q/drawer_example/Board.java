package com.example.q.drawer_example;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ListView listView;
    MyListAdapter myListAdapter;
    public ArrayList<list_item> list_itemArrayList;
    String[] data;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        ImageView iv=findViewById( R.id.aimage );
        iv.setImageResource( R.mipmap.ic_launcher );
        tv=findViewById( R.id.todayboard );


        Intent intent = getIntent();
        String result = intent.getStringExtra("res");
        //data[0]가 id//
        //data[1]이 학교//
        //data[2]가 학과 정보!!!!//

        data = result.split(" ");


        String dd="";
        if(data[2].equals("화학과")){
            dd="chem";
        }
        if(data[2].equals("산업디자인학과")){
            tv.setText( "오늘의 주제 : 제품디자인" );
            dd="Id";
        }
        if(data[2].equals("생명과학공학과")){
            tv.setText( "오늘의 주제 : 세포자살(apoptosis)" );
            dd="bio";
        }
        if(data[2].equals("수리과학과")){
            tv.setText( "오늘의 주제 : 위상적 동형" );
            dd="ms";
        }
        if(data[2].equals("전기및전자공학부")){
            tv.setText( "오늘의 주제 : 시스템" );
            dd="ee";
        }
        if(data[2].equals("전산학부")){
            tv.setText( "오늘의 주제 : 소켓통신" );
            dd="cs";
        }
        listView=(ListView)findViewById( R.id.list );
        list_itemArrayList = new ArrayList<list_item>(  );


        HashMap<String, String> param = new HashMap<>();
        param.put("url", "http://52.231.66.135:80/gettext");

        param.put("dep",dd);
        HttpRequest lle = new HttpRequest(new Callback<String>() {
            @Override
            public String onResponse(String result) {

                Log.d("umm",result);
                try {
                    //JSONObject jsonObject = new JSONObject(result);
                    JSONArray r= new JSONArray(result);
                    for(int i=0;i<r.length();i++){
                        JSONObject temp=(JSONObject)r.get(i);
                        String idt=(String)temp.get("id");
                        Log.d("umm33",idt);
                        String tit=(String)temp.get("title");
                        String dep=(String)temp.get("department");
                        String tim=(String)temp.get("time");
                        String cont=(String)temp.get("contents");
                        list_itemArrayList.add(new list_item( idt,tit,cont,dep,tim));
                    }
                    myListAdapter = new MyListAdapter( Board.this,list_itemArrayList );
                    listView.setAdapter( myListAdapter );

                    listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                            Intent in=new Intent( Board.this,textreal.class );
                            in.putExtra( "text", list_itemArrayList.get( i ).getContent());
                            in.putExtra( "title", list_itemArrayList.get( i ).getTitle());
                            in.putExtra( "id",list_itemArrayList.get( i ).getId() );
                            startActivity( in );
                        }
                    } );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return result;
            }
        }, param);

        lle.execute();

       // myListAdapter = new MyListAdapter( Board.this,list_itemArrayList );
        //listView.setAdapter( myListAdapter );
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        TextView header_title = header.findViewById(R.id.nav_header_title);
        TextView header_subtitle = header.findViewById(R.id.nav_header_subtitle);

        header_title.setText(data[0]);
        header_subtitle.setText(data[1] + " " + data[2]);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
            case R.id.write:
                Intent in= new Intent( Board.this, textcreate.class);
                in.putExtra( "id" ,data[0]);
                in.putExtra( "dep" ,data[2]);
                startActivity( in );

            case R.id.major1:

                tv.setText( "오늘의 주제 : 제품디자인" );
                HashMap<String, String> param1 = new HashMap<>();
                param1.put("url", "http://52.231.66.135:80/gettext");
                list_itemArrayList.clear();
                param1.put("dep","Id");
                HttpRequest lle = new HttpRequest(new Callback<String>() {
                    @Override
                    public String onResponse(String result) {

                        Log.d("umm",result);
                        try {
                            //JSONObject jsonObject = new JSONObject(result);
                            JSONArray r= new JSONArray(result);
                            for(int i=0;i<r.length();i++){
                                JSONObject temp=(JSONObject)r.get(i);
                                String idt=(String)temp.get("id");
                                Log.d("umm33",idt);
                                String tit=(String)temp.get("title");
                                String dep=(String)temp.get("department");
                                String tim=(String)temp.get("time");
                                String cont=(String)temp.get("contents");
                                list_itemArrayList.add(new list_item( idt,tit,cont,dep,tim));
                            }
                            myListAdapter = new MyListAdapter( Board.this,list_itemArrayList );
                            listView.setAdapter( myListAdapter );

                            listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                                    Intent in=new Intent( Board.this,textreal.class );
                                    in.putExtra( "text", list_itemArrayList.get( i ).getContent());
                                    in.putExtra( "title", list_itemArrayList.get( i ).getTitle());
                                    in.putExtra( "id",list_itemArrayList.get( i ).getId() );
                                    startActivity( in );
                                }
                            } );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return result;
                    }
                }, param1);

                lle.execute();

                break;
            case R.id.major2:
                tv.setText( "오늘의 주제 : apoptosis" );

                HashMap<String, String> param2 = new HashMap<>();
                param2.put("url", "http://52.231.66.135:80/gettext");
                list_itemArrayList.clear();
                param2.put("dep","bio");
                HttpRequest lle2 = new HttpRequest(new Callback<String>() {
                    @Override
                    public String onResponse(String result) {

                        Log.d("umm",result);
                        try {
                            //JSONObject jsonObject = new JSONObject(result);
                            JSONArray r= new JSONArray(result);
                            for(int i=0;i<r.length();i++){
                                JSONObject temp=(JSONObject)r.get(i);
                                String idt=(String)temp.get("id");
                                Log.d("umm33",idt);
                                String tit=(String)temp.get("title");
                                String dep=(String)temp.get("department");
                                String tim=(String)temp.get("time");
                                String cont=(String)temp.get("contents");
                                list_itemArrayList.add(new list_item( idt,tit,cont,dep,tim));
                            }
                            myListAdapter = new MyListAdapter( Board.this,list_itemArrayList );
                            listView.setAdapter( myListAdapter );

                            listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                                    Intent in=new Intent( Board.this,textreal.class );
                                    in.putExtra( "text", list_itemArrayList.get( i ).getContent());
                                    in.putExtra( "title", list_itemArrayList.get( i ).getTitle());
                                    in.putExtra( "id",list_itemArrayList.get( i ).getId() );
                                    startActivity( in );
                                }
                            } );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return result;
                    }
                }, param2);

                lle2.execute();
                //생명과학공학과
                break;
            case R.id.major3:

                tv.setText( "오늘의 주제 : 위상적 동형" );
                HashMap<String, String> param3 = new HashMap<>();
                param3.put("url", "http://52.231.66.135:80/gettext");
                list_itemArrayList.clear();
                param3.put("dep","ms");
                HttpRequest lle3 = new HttpRequest(new Callback<String>() {
                    @Override
                    public String onResponse(String result) {

                        Log.d("umm",result);
                        try {
                            //JSONObject jsonObject = new JSONObject(result);
                            JSONArray r= new JSONArray(result);
                            for(int i=0;i<r.length();i++){
                                JSONObject temp=(JSONObject)r.get(i);
                                String idt=(String)temp.get("id");
                                Log.d("umm33",idt);
                                String tit=(String)temp.get("title");
                                String dep=(String)temp.get("department");
                                String tim=(String)temp.get("time");
                                String cont=(String)temp.get("contents");
                                list_itemArrayList.add(new list_item( idt,tit,cont,dep,tim));
                            }
                            myListAdapter = new MyListAdapter( Board.this,list_itemArrayList );
                            listView.setAdapter( myListAdapter );

                            listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                                    Intent in=new Intent( Board.this,textreal.class );
                                    in.putExtra( "text", list_itemArrayList.get( i ).getContent());
                                    in.putExtra( "title", list_itemArrayList.get( i ).getTitle());
                                    in.putExtra( "id",list_itemArrayList.get( i ).getId() );
                                    startActivity( in );
                                }
                            } );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return result;
                    }
                }, param3);

                lle3.execute();
                //수리과학과
                break;
            case R.id.major4:
                tv.setText( "오늘의 주제 : 시스템" );

                HashMap<String, String> param4 = new HashMap<>();
                param4.put("url", "http://52.231.66.135:80/gettext");
                list_itemArrayList.clear();
                param4.put("dep","ee");
                HttpRequest lle4 = new HttpRequest(new Callback<String>() {
                    @Override
                    public String onResponse(String result) {

                        Log.d("umm",result);
                        try {
                            //JSONObject jsonObject = new JSONObject(result);
                            JSONArray r= new JSONArray(result);
                            for(int i=0;i<r.length();i++){
                                JSONObject temp=(JSONObject)r.get(i);
                                String idt=(String)temp.get("id");
                                Log.d("umm33",idt);
                                String tit=(String)temp.get("title");
                                String dep=(String)temp.get("department");
                                String tim=(String)temp.get("time");
                                String cont=(String)temp.get("contents");
                                list_itemArrayList.add(new list_item( idt,tit,cont,dep,tim));
                            }
                            myListAdapter = new MyListAdapter( Board.this,list_itemArrayList );
                            listView.setAdapter( myListAdapter );

                            listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                                    Intent in=new Intent( Board.this,textreal.class );
                                    in.putExtra( "text", list_itemArrayList.get( i ).getContent());
                                    in.putExtra( "title", list_itemArrayList.get( i ).getTitle());
                                    in.putExtra( "id",list_itemArrayList.get( i ).getId() );
                                    startActivity( in );
                                }
                            } );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return result;
                    }
                }, param4);

                lle4.execute();
                //전기및전자공학부
                break;
            case R.id.major5:
                tv.setText( "오늘의 주제 : 소켓통신" );

                HashMap<String, String> param5 = new HashMap<>();
                param5.put("url", "http://52.231.66.135:80/gettext");
                list_itemArrayList.clear();
                param5.put("dep","cs");
                HttpRequest lle5 = new HttpRequest(new Callback<String>() {
                    @Override
                    public String onResponse(String result) {

                        Log.d("umm",result);
                        try {
                            //JSONObject jsonObject = new JSONObject(result);
                            JSONArray r= new JSONArray(result);
                            for(int i=0;i<r.length();i++){
                                JSONObject temp=(JSONObject)r.get(i);
                                String idt=(String)temp.get("id");
                                Log.d("umm33",idt);
                                String tit=(String)temp.get("title");
                                String dep=(String)temp.get("department");
                                String tim=(String)temp.get("time");
                                String cont=(String)temp.get("contents");
                                list_itemArrayList.add(new list_item( idt,tit,cont,dep,tim));
                            }
                            myListAdapter = new MyListAdapter( Board.this,list_itemArrayList );
                            listView.setAdapter( myListAdapter );

                            listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                                    Intent in=new Intent( Board.this,textreal.class );
                                    in.putExtra( "text", list_itemArrayList.get( i ).getContent());
                                    in.putExtra( "title", list_itemArrayList.get( i ).getTitle());
                                    in.putExtra( "id",list_itemArrayList.get( i ).getId() );
                                    startActivity( in );
                                }
                            } );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return result;
                    }
                }, param5);

                lle5.execute();

                //전산학부
                break;
            case R.id.Logout:
                HashMap<String, String> param = new HashMap<>();
                param.put("url", "http://52.231.66.135:80/logout");

                HttpRequest Logout = new HttpRequest(new Callback<String>() {
                    @Override
                    public String onResponse(String result) {
                        if (result == null) Toast.makeText(Board.this, "시스템 문제가 발생하였습니다. 개발자에게 문의해주시기 바랍니다.", Toast.LENGTH_SHORT).show();
                        else {
                            Intent intent = new Intent(Board.this, Login.class);
                            startActivity(intent);
                            finish();
                        }
                        return result;
                    }
                }, param);

                Logout.execute();
                break;
            case R.id.Search:
                //검색
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
