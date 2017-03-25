package com.alex_johannes.forum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

public class add_new_post extends AppCompatActivity {
    private String temp_key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_post);

        final EditText titelText = (EditText) findViewById(R.id.titel_field);
        final EditText beitrag = (EditText)findViewById(R.id.beitrag_field);
        Button submitButton = (Button) findViewById(R.id.submit_button);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
         //final DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Beitrage");



        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //temp_key = root.push().getKey();
//                //Kommentare erstesKommentar = new Kommentare("Toller Beitrag");
//                Kommentare zweitesKomentar = new Kommentare("Der BEitrag ist ziemlich kacke");
//
//                Kommentare[]liste= {erstesKommentar, zweitesKomentar};
//                Message neueMessage= new Message("Hallöle","Von Mir",liste);

                Post currentPost = new Post(titelText.getText().toString(),"Default",beitrag.getText().toString());

                DatabaseReference root = database.getReference().getRoot().child("Beiträge");
                temp_key = root.push().getKey();

                //DatabaseReference message_root = root.child(temp_key);
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2.put(temp_key, currentPost);

                root.updateChildren(map2);

                Intent i= new Intent(add_new_post.this, MainActivity.class);
                startActivity(i);

            }
        });



    }
}
