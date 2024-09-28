package com.digitalfintech.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.StringTokenizer;

public class MainActivity3 extends AppCompatActivity {
    TextView text1, text2, text3;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        text2 = findViewById(R.id.shant);
        String s = "razjvfdjscnjsndkcsdnakjsdnfka";
//        StringTokenizer st = new StringTokenizer(s, "=&");
//       // String community = st.nextToken();
//        String helpDesk = st.nextToken();
//        String localEmbassy = st.nextToken();
//        String referenceDesk = st.nextToken();
//        String siteNews = st.nextToken();
//         text1.setText(localEmbassy);
        String password = "Shantanu";
        StringBuilder sb = new StringBuilder();
        for (i = 0; i < 3; i++)
            sb.append("*");
        String l = String.valueOf(sb);
        StringBuilder k = new StringBuilder();
        for (i = 4; i < password.length(); i++)
            k.append(k);
    String g = String.valueOf(k);
    String today =  l+g;
        Toast.makeText(this, ""+today, Toast.LENGTH_SHORT).show();
    }
}