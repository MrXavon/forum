package com.alex_johannes.forum;

import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Beitrag extends AppCompatActivity {
    private ArrayList<Comment> list_comments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_post);
        TextView titel= (TextView)findViewById(R.id.titel_beitrag);
        final TextView beitrag = (TextView)findViewById(R.id.conten_beitrag);
        final EditText comment = (EditText) findViewById(R.id.comment_field);
        final TextView author = (TextView) findViewById(R.id.author_post_field);
        final Button CommentButton = (Button) findViewById(R.id.submit_comment);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();



        final String titelString = getIntent().getExtras().get("titel").toString();
        final DatabaseReference root =database.getReference().getRoot().child("Beiträge/"+getIntent().getExtras().get("key"));





        //Fill TextViews with content
        titel.setText(titelString);
        beitrag.setText( getIntent().getExtras().get("content").toString());
        author.setText( getIntent().getExtras().get("author").toString());



        //Get comments

        ListView commentListe = (ListView) findViewById(R.id.comment_list);
        final CommentAdapter adapter = new CommentAdapter(this,list_comments);
        commentListe.setAdapter(adapter);






        root.child("Kommentare").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("Methode wird ausgeführt");
                ArrayList<Comment> list_comments_zw = new ArrayList<>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()){

                    list_comments_zw.add(((DataSnapshot)i.next()).getValue(Comment.class));
                }
                System.out.println(list_comments_zw);
                list_comments.clear();
                list_comments.addAll(list_comments_zw);

                adapter.notifyDataSetChanged();
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
                Comment currentComment= new Comment(comment.getText().toString(),"Author Default");
                //database.getReference().getRoot().child("Uhrzeit/Comment")
                map2.put(System.currentTimeMillis()+"", currentComment);
                root.child("Kommentare").updateChildren(map2);
                comment.setText("");

            }
        });




    }
}
