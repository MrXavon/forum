package com.alex_johannes.forum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jstoetzel on 26.03.2017.
 */

public class PostAdapter extends ArrayAdapter<Post> {
    public PostAdapter(Context context, ArrayList<Post> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Post currentPost = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.post_layout, parent, false);
        }
        // Lookup view for data population
        TextView titlePost = (TextView) convertView.findViewById(R.id.title_post);
        TextView authorPost = (TextView) convertView.findViewById(R.id.author_post);
        // Populate the data into the template view using the data object
        titlePost.setText(currentPost.getTitel());
        authorPost.setText("Author: "+currentPost.getAuthor());
        // Return the completed view to render on screen
        return convertView;
    }

}
