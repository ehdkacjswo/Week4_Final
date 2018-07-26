package com.example.q.drawer_example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class Register extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Button confirm = findViewById(R.id.Confirm_register);
        final EditText ID = findViewById(R.id.ID_register);
        final EditText Pass = findViewById(R.id.Pass_register);
        final EditText Email = findViewById(R.id.Email);
        final Spinner Major = findViewById(R.id.Major);

        String[] major_list = {"산업디자인학과", "생명과학공학과", "수리과학과", "전기및전자공학부", "전산학부"};

        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, major_list);
        Major.setAdapter(spinnerAdapter);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = ID.getText().toString();
                String pass = Pass.getText().toString();
                String email = Email.getText().toString();
                String major = Major.getSelectedItem().toString();

                String domain = email.substring((email.indexOf('@')) + 1, email.length());
                domain = domain.substring(0, Math.max(domain.indexOf(".ac.kr"), 0));
                System.out.println(domain);

                if(!email.contains("@") || !email.endsWith(".ac.kr"))
                    Toast.makeText(Register.this, "올바른 E-mail 주소를 입력해주세요.", Toast.LENGTH_SHORT).show();
                else if (id.length() < 1 || id.length() > 16)
                    Toast.makeText(Register.this, "ID는 1~16자 사이로 입력해주세요.", Toast.LENGTH_SHORT).show();
                else if (pass.length() < 1 || pass.length() > 16)
                    Toast.makeText(Register.this, "비밀번호는 1~16자 사이로 입력해주세요.", Toast.LENGTH_SHORT).show();
                else {
                    HashMap<String, String> param = new HashMap<>();
                    param.put("url", "http://52.231.66.135:80/reg");
                    param.put("id", id);
                    param.put("pass", pass);
                    param.put("email", email);
                    param.put("domain", domain);
                    param.put("major", major);

                    HttpRequest Login = new HttpRequest(new Callback<String>() {
                        @Override
                        public String onResponse(String result) {
                            if (result == null) return result;

                            switch (result) {
                                case "success":
                                    Toast.makeText(Register.this, "입력하신 이메일에서 계정을 인증해주세요.", Toast.LENGTH_SHORT).show();
                                    finish();
                                    break;
                                case "fail-school":
                                    Toast.makeText(Register.this, "등록되지 않은 대학교입니다. 서비스 관리자에게 문의해주세요.", Toast.LENGTH_SHORT).show();
                                    break;
                                case "fail-email":
                                    Toast.makeText(Register.this, "이미 등록된 이메일입니다. 비밀번호 찾기 서비스를 이용해주세요.", Toast.LENGTH_SHORT).show();
                                    break;
                                case "fail-id":
                                    Toast.makeText(Register.this, "이미 등록된 ID 입니다. 다른 ID를 사용해주세요.", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            return result;
                        }
                    }, param);
                    Login.execute();
                }
            }

        });
    }
}
