package com.alex_johannes.forum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

public class add_new_post extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_post);

        final EditText titelText = (EditText) findViewById(R.id.titel_field);
        final EditText beitrag = (EditText)findViewById(R.id.beitrag_field);
        Button submitButton = (Button) findViewById(R.id.submit_button);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();





        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put(titelText.getText().toString(), beitrag.getText().toString());
                database.getReference().getRoot().updateChildren(map2);
                Intent i= new Intent(add_new_post.this, MainActivity.class);
                startActivity(i);
            }
        });



    }
}
