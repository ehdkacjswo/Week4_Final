package com.example.q.drawer_example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class textreal extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textreal);
        TextView tv=findViewById( R.id.textView );
        TextView tvti=findViewById( R.id.titletext );
        TextView tvid=findViewById( R.id.idtext );
        Button b=findViewById( R.id.button );
        ImageView iv=findViewById( R.id.imageView );
        iv.setImageResource(  R.mipmap.ic_launcher );
        Intent i =getIntent();
        String g=i.getStringExtra( "text" ).toString();
        String id=i.getStringExtra( "id" ).toString();
        String title=i.getStringExtra( "title" ).toString();
        //Toast.makeText( textreal.this, g,Toast.LENGTH_SHORT).show();
        tv.setText( g );
        tvid.setText( id );
        tvti.setText( title );
        b.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

    }
}
