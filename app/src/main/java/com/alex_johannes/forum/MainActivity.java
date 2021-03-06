package com.alex_johannes.forum;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Post> list_posts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        ListView liste = (ListView) findViewById(R.id.listview);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference root =database.getReference().getRoot().child("Beiträge");

        //final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list_posts);
        final PostAdapter adapter = new PostAdapter(this,list_posts);
        liste.setAdapter(adapter);


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("Methode wird ausgeführt");
                ArrayList<Post> currentPosts = new ArrayList<Post>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()){
                    currentPosts.add(((DataSnapshot)i.next()).getValue(Post.class));
                }

                list_posts.clear();
                list_posts.addAll(currentPosts);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Post clickedPost = list_posts.get(i);
                Intent intent = new Intent(getApplicationContext(),Beitrag.class);
                intent.putExtra("titel", clickedPost.getTitel());
                intent.putExtra("content", clickedPost.getContent());
                intent.putExtra("author", clickedPost.getAuthor());
                intent.putExtra("key", clickedPost.getKey());
                startActivity(intent);
            }
        });















        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Intent newPost= new Intent(MainActivity.this, add_new_post.class);
            startActivity(newPost);

            }
        });
//        Map<String, Object> map2 = new HashMap<String, Object>();
//        map2.put("Titel", "Wie baue ich ein Haus");
//        map2.put("Inhalt", "Lege viele Steine aufeinader");
//        database.getReference().getRoot().child("Uhrzeit").updateChildren(map2);




    }
}
