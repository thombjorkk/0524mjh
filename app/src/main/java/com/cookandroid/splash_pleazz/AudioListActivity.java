package com.cookandroid.splash_pleazz;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AudioListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audiostreamingpage);

        ExpandableListView Audio_ListView = (ExpandableListView) findViewById(R.id.Audio_ListView);
        final ArrayList<AudioFiles> Voices = getData();

        AudioListAdapter adapter = new AudioListAdapter(this,Voices);
        Audio_ListView.setAdapter(adapter);

    }
    private ArrayList<AudioFiles> getData(){
        AudioFiles a1 = new AudioFiles("test voice 1");
        a1.AudioComment.add("테스트 1");
        a1.AudioComment.add("테스트 2");

        AudioFiles a2 = new AudioFiles("text voice 2");
        a2.AudioComment.add("테스트 3");
        a2.AudioComment.add("테스트 4");

        ArrayList<AudioFiles> allAudioFiles = new ArrayList<>();
        allAudioFiles.add(a1);
        allAudioFiles.add(a2);

        return allAudioFiles;
    }

}
