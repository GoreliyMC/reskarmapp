package com.example.reskarmv01alfa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

//import org.w3c.dom.Document;
//import org.w3c.dom.Element;

public class MainActivity extends AppCompatActivity {
    private EditText login;
    private EditText password;
    private Button connectButt;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectButt = (Button) findViewById(R.id.butConnect);
        login = (EditText) findViewById(R.id.editLogin);
        password = (EditText) findViewById(R.id.editPwd);
        tv = (TextView) findViewById(R.id.tv);
        final String url = "http://reskarmapp.na4u.ru/auth.php";
        connectButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final StringBuilder builder = new StringBuilder();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Document doc = Jsoup.connect(url)
                                    .data("log_in", "true")
                                    .data("login", login.getText().toString())
                                    .data("password", password.getText().toString())
                                    .post();

                            if (doc.title().toString().equals("Correct")) {
                                tv.setText("ok");
                                Intent intent = new Intent(MainActivity.this, AppMain.class);
                                startActivity(intent);
                            } else {
                            }

                        } catch (Exception e) {
                            builder.append("Error ").append(e.getMessage());
                        }
                    }
                }).start();

            }
        });
    }
}
