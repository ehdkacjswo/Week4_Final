package com.example.q.drawer_example;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Find_ID extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_id, null);

        final EditText email = view.findViewById(R.id.Email_find_id);
        Button confirm = view.findViewById(R.id.Confirm_find_id);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> param = new HashMap<>();
                param.put("url", "http://52.231.66.135:80/find_id");
                param.put("email", email.getText().toString());

                HttpRequest Find_id= new HttpRequest(new Callback<String>() {
                    @Override
                    public String onResponse(String result) {
                        if (result == null) {
                            Toast.makeText(getContext(), "시스템 문제가 발생하였습니다. 개발자에게 문의해주시기 바랍니다.", Toast.LENGTH_SHORT).show();
                            return result;
                        }

                        switch (result) {
                            case "fail":
                                Toast.makeText(getContext(), "해당 이메일로 등록된 계정이 없습니다.", Toast.LENGTH_SHORT).show();
                                break;
                            case "success":
                                Toast.makeText(getContext(), "해당 이메일로 아이디를 전송하였습니다.", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return result;
                    }
                }, param);

                Find_id.execute();
            }
        });

        return view;
    }
}
