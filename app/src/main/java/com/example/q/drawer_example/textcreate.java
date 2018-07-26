package com.example.q.drawer_example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class textcreate extends Activity {
    EditText title=null;
    EditText memo=null;
    Button send=null;
    Button canc=null;
    ImageView iv=null;
    TextView tv=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);
        title=findViewById( R.id.texttitle );
        memo=findViewById( R.id.memo );
        send=findViewById( R.id.sendbtn );
        canc=findViewById( R.id.cancelbtn );
        iv=findViewById( R.id.imageView2 );
        tv=findViewById( R.id.textView2 );
        iv.setImageResource( R.mipmap.ic_launcher );
        send.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                String getTime = sdf.format(date);


                Intent tin=getIntent();
                String idd=tin.getStringExtra( "id" );
                String depp=tin.getStringExtra( "dep" );
                String deppp="";
                if(depp.equals("화학과")){
                    deppp="chem";
                }
                if(depp.equals("전산학부")){
                    deppp="cs";

                }
                if(depp.equals("산업디자인학과")){
                    deppp="Id";

                }
                if(depp.equals("생명공학과")){
                    deppp="bio";

                }
                if(depp.equals("수리과학과")){
                    deppp="ms";

                }
                if(depp.equals("전기및전자공학부")) {
                    deppp = "ee";

                }

                String temp=memo.getText().toString();
                String tempt=title.getText().toString();
                HashMap<String, String> param = new HashMap<>();
                param.put("url", "http://52.231.66.135:80/sendtext");
                param.put("id", idd);
                param.put("title", tempt);
                param.put("content", temp);
                param.put("time", getTime);
                param.put("dep", deppp);

                HttpRequest Login = new HttpRequest(new Callback<String>() {
                    @Override
                    public String onResponse(String result) {
                        if (result == null) return result;


                        return result;
                    }
                }, param);
                Login.execute();
                finish();
            }
        } );

        canc.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

    }
}
