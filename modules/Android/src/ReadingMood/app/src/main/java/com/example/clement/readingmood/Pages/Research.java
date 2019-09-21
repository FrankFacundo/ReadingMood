package com.example.clement.readingmood.Pages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clement.readingmood.R;

public class Research extends AppCompatActivity {

    private EditText myEdit ;
    private ImageButton back;
    private String currentResearch;
    private int codeSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research);
        myEdit = findViewById(R.id.research_library);
        back = findViewById(R.id.return_research);
        if (getIntent().getAction().equals("0"))
        {
            codeSend = 0;
        } else if (getIntent().getAction().equals("1"))
        {
            codeSend = 1;
        }


        initialiseListener();


    }


    private void initialiseListener()
    {
        myEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                currentResearch = v.getText().toString();
                return false;
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentResearch == null)
                {
                    currentResearch = myEdit.getText().toString();
                }
                Bundle myBundle = new Bundle();
                Intent myIntent = new Intent();
                myBundle.putString("research",currentResearch);
                myIntent.putExtras(myBundle);
                setResult(codeSend, myIntent);
                finish();
            }
        });


    }





}
