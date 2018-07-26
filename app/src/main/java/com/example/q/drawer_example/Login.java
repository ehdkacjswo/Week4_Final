package com.example.q.drawer_example;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Login extends AppCompatActivity {
    public static SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("user", MODE_PRIVATE);
        String save_id = pref.getString("ID", null);
        String save_pass = pref.getString("Pass", null);

        HashMap<String, String> param = new HashMap<>();
        param.put("url", "http://52.231.66.135:80/login");

        if (save_id != null && save_pass != null) {
            param.put("id", save_id);
            param.put("pass", save_pass);
        }

        HttpRequest Login = new HttpRequest(new Callback<String>() {
            @Override
            public String onResponse(String result) {
                if (result == null) return result;

                switch (result) {
                    case "success":
                        Intent intent = new Intent(Login.this, Board.class);
                        startActivity(intent);
                        finish();
                        break;
                }
                return result;
            }
        }, param);
        Login.execute();

        final TextView reg = findViewById(R.id.Reg);
        final TextView find = findViewById(R.id.Find);
        final Button login = findViewById(R.id.Login);
        final EditText id = findViewById(R.id.ID_login);
        final EditText pass = findViewById(R.id.Pass_login);

        if (save_id != null && save_pass != null) {
            id.setText(save_id);
            pass.setText(save_pass);
        }

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Find_main.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ID = id.getText().toString();
                final String Pass = pass.getText().toString();

                HashMap<String, String> param = new HashMap<>();
                param.put("url", "http://52.231.66.135:80/login");
                param.put("id", ID);
                param.put("pass", Pass);

                HttpRequest Login = new HttpRequest(new Callback<String>() {
                    @Override
                    public String onResponse(String result) {
                        if (result == null) {
                            Toast.makeText(Login.this, "시스템 문제가 발생하였습니다. 개발자에게 문의해주시기 바랍니다.", Toast.LENGTH_SHORT).show();
                            return result;
                        }

                        switch (result) {
                            case "fail":
                                Toast.makeText(Login.this, "ID와 비밀번호를 다시한번 확인해주시기 바랍니다.", Toast.LENGTH_SHORT).show();
                                break;
                            case "auth":
                                Toast.makeText(Login.this, "이메일 인증을 먼저 해주시길 바랍니다.", Toast.LENGTH_SHORT).show();
                                break;

                            default:
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("ID", ID);
                                editor.putString("Pass", Pass);
                                editor.apply();

                                Intent intent = new Intent(Login.this, Board.class);
                                intent.putExtra("res", result);
                                startActivity(intent);
                                finish();
                                break;
                        }
                        return result;
                    }
                }, param);

                Login.execute();
            }
        });
    }
}
