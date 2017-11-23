package edu.dlsu.mobapde.labfirebaseposts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvPosts;
    EditText etUsername, etPost;
    Button buttonPost;
    String key;

    DatabaseReference databaseReference;

    FirebaseRecyclerAdapter<Post, PostViewHolder> postPostViewHolderFirebaseRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPosts = (RecyclerView) findViewById(R.id.rv_posts);
        etPost = (EditText) findViewById(R.id.et_post);
        etUsername = (EditText) findViewById(R.id.et_username);
        buttonPost = (Button) findViewById(R.id.button_post);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference postDatabaseReference = databaseReference.child("posts");


        postPostViewHolderFirebaseRecyclerAdapter
                = new FirebaseRecyclerAdapter<Post, PostViewHolder>(Post.class, R.layout.list_post, PostViewHolder.class, postDatabaseReference) {
            @Override
            protected void populateViewHolder(PostViewHolder viewHolder, Post model, int position) {
                viewHolder.tvPost.setText(model.getPost());
                viewHolder.tvUsername.setText(model.getUsername());

                String key = getRef(position).getKey();
                viewHolder.itemView.setTag(key);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getBaseContext(), ViewPostActivity.class);
                        intent.putExtra(Post.EXTRA_KEY, view.getTag().toString());
                        startActivity(intent);
                    }
                });

            }
        };

        rvPosts.setAdapter(postPostViewHolderFirebaseRecyclerAdapter);
        rvPosts.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                key = postDatabaseReference.push().getKey();

                Post post = new Post();
                post.setPost(etPost.getText().toString());
                post.setUsername(etUsername.getText().toString());

                postDatabaseReference.child(key).setValue(post);
            }
        });
    }
}
