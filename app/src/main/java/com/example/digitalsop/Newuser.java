package com.example.digitalsop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.digitalsop.Config.DBHelper;

public class Newuser extends AppCompatActivity {
     EditText baseurl;
     CardView sub;
     String base;
    DBHelper dbHelper;
    TextView logbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newuser);
        dbHelper=new DBHelper(this);

        baseurl=findViewById(R.id.baseurl);
        sub=findViewById(R.id.sub);
        logbtn=findViewById(R.id.logbtn);


        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                base=baseurl.getText().toString();
                dbHelper.insertData(base);
                Intent i=new Intent(Newuser.this,MainActivity.class);
                startActivity(i);
            }
        });



    }
}