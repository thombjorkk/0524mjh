package com.cookandroid.splash_pleazz;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AskActivity extends AppCompatActivity {

    EditText add_comment;
    String postbox_comment,taxi_comment;
    Button btn_add_comment;
    RadioGroup rgroup;
    RadioButton rbtn_postbox,rbtn_taxi;
    DatabaseReference myRef;
    DatabaseReference myRef2;
    static boolean calledAlready = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        if(!calledAlready){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }

        add_comment = findViewById(R.id.add_comment);
        btn_add_comment = findViewById(R.id.btn_add_comment);
        rgroup = findViewById(R.id.rgroup);
        rbtn_postbox = findViewById(R.id.rbtn_postbox);
        rbtn_taxi = findViewById(R.id.rbtn_taxi);
        myRef = FirebaseDatabase.getInstance().getReference("택배");
        myRef2 = FirebaseDatabase.getInstance().getReference("택시");


        rgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId==R.id.rbtn_postbox){
                    btn_add_comment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String key_post = myRef.child("택배 멘트").push().getKey();
                            postbox_comment = add_comment.getText().toString();
                            Map<String,Object> commentUpdates_post = new HashMap<>();
                            commentUpdates_post.put(key_post,postbox_comment);
                            myRef.updateChildren(commentUpdates_post);
                        }
                    });

                }else if(checkedId==R.id.rbtn_taxi){

                    btn_add_comment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String key_taxi = myRef2.child("택시 멘트").push().getKey();
                            taxi_comment = add_comment.getText().toString();
                            Map<String,Object> commentUpdates_taxi = new HashMap<>();
                            commentUpdates_taxi.put(key_taxi,taxi_comment);
                            myRef2.updateChildren(commentUpdates_taxi);
                        }
                    });

                }
            }
        });

        ExpandableListView ListView = (ExpandableListView)findViewById(R.id.ListView);
        final ArrayList<position> positions = getData();

        myAdapter adapter = new myAdapter(this,positions);
        ListView.setAdapter(adapter);

        ListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getApplicationContext(),positions.get(groupPosition).comments.get(childPosition),Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }
    private ArrayList<position> getData(){

        ArrayList<position> allcomment = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("택배");
        position p1 = new position("택배");
        position p2 = new position("택시");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                p1.comments.clear();
                for(DataSnapshot fileSnapshot : snapshot.getChildren()){
                    String str = fileSnapshot.getValue(String.class);
                    Log.i("Tag: value is",str);
                    p1.comments.add(str);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Tag: ","Failed to read value",error.toException());
            }
        });

        DatabaseReference databaseReference2 = database.getReference("택시");

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                p2.comments.clear();
                for(DataSnapshot fileSnapshot : snapshot.getChildren()){
                    String str = fileSnapshot.getValue(String.class);
                    Log.i("Tag: value is",str);
                    p2.comments.add(str);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Tag: ","Failed to read value",error.toException());
            }
        });

        allcomment.add(p1);
        allcomment.add(p2);
        return allcomment;
    }

}
