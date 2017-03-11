package com.alex_johannes.forum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Beitrag extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beitrag);
        TextView titel= (TextView)findViewById(R.id.titel_beitrag);
        final TextView beitrag = (TextView)findViewById(R.id.conten_beitrag);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();



        String titelString = getIntent().getExtras().get("titel").toString();
        DatabaseReference root = FirebaseDatabase.getInstance().getReference().child(titelString);


        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                System.out.print("datasnapshot"+dataSnapshot);
                beitrag.setText( dataSnapshot.getValue(String.class));
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



        titel.setText(titelString);
    }
}
