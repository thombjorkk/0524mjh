package com.cookandroid.splash_pleazz;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import java.util.List;
import java.util.Map;

public class AudioListActivity extends AppCompatActivity {

    ArrayList<AudioFiles> Voices = getData();                       //계속 바뀔 리스트
    ArrayList<AudioFiles> VoicesOriginal = getData();              //원래 리스트. 바뀌지 않음

    AudioListAdapter adapter;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audiostreamingpage);

        EditText editSearch = (EditText) findViewById(R.id.editSearch);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String text = editSearch.getText().toString();
                search(text);

            }
        });

//        Voices = new ArrayList<AudioFiles>();
//        Voices = getData();
        ExpandableListView Audio_ListView = (ExpandableListView) findViewById(R.id.Audio_ListView);


//        AudioListAdapter adapter = new AudioListAdapter(this,Voices);
        adapter = new AudioListAdapter(this,Voices);
        Audio_ListView.setAdapter(adapter);

    }





    public ArrayList<AudioFiles> getData(){

        AudioFiles a1 = new AudioFiles("test voice 1");
        AudioFiles a2 = new AudioFiles("text voice 2");

        a1.AudioComment.add("테스트 1");
        a1.AudioComment.add("테스트 2");

        a2.AudioComment.add("테스트 3");
        a2.AudioComment.add("테스트 4");

        ArrayList<AudioFiles> allAudioFiles = new ArrayList<>();
        allAudioFiles.add(a1);
        allAudioFiles.add(a2);

        return allAudioFiles;
    }




    // 검색을 수행하는 메소드
    public void search(String charText) {

        Voices.clear();

        if (charText.length() <= 0){
            Voices.addAll(VoicesOriginal);
            Toast.makeText(getApplicationContext(),"대사를 입력하세요",Toast.LENGTH_SHORT).show();
        }
        else {

            for (int i = 0 ; i < VoicesOriginal.size(); i++){
                if (VoicesOriginal.get(i).Voice.toLowerCase().contains(charText)){
                    Toast.makeText(getApplicationContext(),"일치하는 결과 존재",Toast.LENGTH_SHORT).show();
                    Voices.add(VoicesOriginal.get(i));
                }
            }




//            for(int i = 0;i < Voices_forSearch.size(); i++)
//            {
//                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
//                if (voice_parent_name.get(i).toLowerCase().contains(charText))
//                {
//                    // 검색된 데이터를 리스트에 추가한다.
//
//                    Voices.add(voice_parent_name.get(i));
//                }
//            }
        }
        adapter.notifyDataSetChanged();
    }
}
