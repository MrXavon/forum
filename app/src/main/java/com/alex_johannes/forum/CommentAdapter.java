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

public class CommentAdapter extends ArrayAdapter<Comment> {
    public CommentAdapter(Context context, ArrayList<Comment> comments) {
        super(context, 0, comments);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Comment currentComment = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.comment_layout, parent, false);
        }
        // Lookup view for data population
        TextView content = (TextView) convertView.findViewById(R.id.content_comment);
        TextView author = (TextView) convertView.findViewById(R.id.author_comment);
        // Populate the data into the template view using the data object
        content.setText(currentComment.getContent());
        author.setText("Author: "+currentComment.getAuthor());
        // Return the completed view to render on screen
        return convertView;
    }
}
