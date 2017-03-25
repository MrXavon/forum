package com.alex_johannes.forum;

import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Beitrag extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beitrag);
        TextView titel= (TextView)findViewById(R.id.titel_beitrag);
        final TextView beitrag = (TextView)findViewById(R.id.conten_beitrag);
        final EditText comment = (EditText) findViewById(R.id.comment_field);
        final Button CommentButton = (Button) findViewById(R.id.submit_comment);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();



        final String titelString = getIntent().getExtras().get("titel").toString();
         DatabaseReference root =database.getReference().getRoot().child("Beiträge");

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.getKey().equals(titelString)){
                    beitrag.setText(dataSnapshot.getValue().toString());
                    System.out.println( "IF-Abfrage: "+dataSnapshot.getValue().toString());
                }
                System.out.println( "IF-Abfrage: "+dataSnapshot.getValue().toString());
                //System.out.print("datasnapshot"+dataSnapshot.getValue());
                //System.out.println( "IF-Abfrage: "+dataSnapshot.hasChildren());
                //beitrag.setText(dataSnapshot.child("forum-5031d/"+titelString).getValue(Message.class).getMessage());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                beitrag.setText( dataSnapshot.getValue().toString());

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



         //root = FirebaseDatabase.getInstance().getReference().child(titelString);
        CommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comment.getText().toString()==""){
                    Toast.makeText(Beitrag.this, "Bitte geben sie ein Kommentar ein", Toast.LENGTH_LONG).show();
                    return;
                }
                Map<String, Object> map2 = new HashMap<String, Object>();
                //database.getReference().getRoot().child("Uhrzeit/Kommentare")
                map2.put("Autor Johannes", comment.getText().toString());
                database.getReference().getRoot().child("Uhrzeit/Kommentare").updateChildren(map2);

            }
        });



        titel.setText(titelString);
    }
}
