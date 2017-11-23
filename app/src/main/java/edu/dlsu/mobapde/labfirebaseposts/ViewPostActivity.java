package edu.dlsu.mobapde.labfirebaseposts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class ViewPostActivity extends AppCompatActivity {

    TextView tvPost;
    TextView tvUsername;
    RecyclerView rvComments;
    Button buttonEdit, buttonDelete;
    String key;

    DatabaseReference postReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        tvPost = (TextView) findViewById(R.id.tv_post);
        tvUsername = (TextView) findViewById(R.id.tv_username);
        buttonEdit = (Button) findViewById(R.id.button_edit);
        buttonDelete = (Button) findViewById(R.id.button_delete);

        key = getIntent().getExtras().getString(Post.EXTRA_KEY);
        postReference = FirebaseDatabase.getInstance().getReference().child("posts").child(key);

        postReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Post p = dataSnapshot.getValue(Post.class);
                if(p!=null) {
                    tvPost.setText(p.getPost());
                    tvUsername.setText(p.getUsername());
                }else{
                    Log.i("ViewPostActivity", "Error");
                    finish();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postReference.setValue(null);
            }
        });
    }
}
