package com.amikom.thesaint2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Button player = (Button) findViewById(R.id.btn_player);
        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in1 = new Intent(activity_intro.this, Player.class);
                startActivity(in1);
            }
        });

        Button next = (Button) findViewById(R.id.btn_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in2 = new Intent(activity_intro.this, Next.class);
                startActivity(in2);
            }
        });

        Button last = (Button) findViewById(R.id.btn_last);
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in3 = new Intent(activity_intro.this, Last.class);
                startActivity(in3);
            }
        });
    }

}
