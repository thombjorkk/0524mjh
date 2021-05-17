package com.cookandroid.splash_pleazz;

import android.content.Context;
import android.graphics.Color;
import android.speech.tts.Voice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AudioListAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private ArrayList<AudioFiles> Voices;
    private LayoutInflater inflater;

    //class Constructor
    public AudioListAdapter(Context mContext, ArrayList<AudioFiles> Voices) {

        this.mContext = mContext;
        this.Voices = Voices;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getGroupCount() {
        return Voices.size();
    }

    @Override
    public int getChildrenCount(int groupVoice) {
        return Voices.get(groupVoice).AudioComment.size();
    }

    //get Voice
    @Override
    public Object getGroup(int groupVoice) {
        return Voices.get(groupVoice);
    }

    //this is where we get the information of player
    @Override
    public Object getChild(int groupVoice, int childComment) {
        return Voices.get(groupVoice).AudioComment.get(childComment);
    }

    //position ID
    @Override
    public long getGroupId(int groupVoice) {
        return 0;
    }

    //where to get player's id
    @Override
    public long getChildId(int groupVoice, int childComment) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //get parent row
    @Override
    public View getGroupView(int groupVoice, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.audio_parent_list, null);
        }

        //get position

        AudioFiles Voices = (AudioFiles) getGroup(groupVoice);

        //set positionName
        String VoiceName = Voices.Voice;

        TextView textView = (TextView) convertView.findViewById(R.id.voice_name);
        textView.setText(VoiceName);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.audio_arrow_down);
        if(isExpanded){
            imageView.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
        } else {
            imageView.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
        }

        convertView.setBackgroundColor(Color.LTGRAY);
        return convertView;
    }

    //get child_list.xml (View)
    @Override
    public View getChildView(int groupVoice, int childComment, boolean isLastChild, View convertView, ViewGroup parent) {

        //inflate the layout
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.audio_child_list, null);
        }

        String child = (String) getChild(groupVoice, childComment);

        //set the child name
        TextView comment = (TextView) convertView.findViewById(R.id.audio_comment);
        //get the imageView
        ImageView img_play = (ImageView) convertView.findViewById(R.id.play);
        ImageView img_pause = (ImageView) convertView.findViewById(R.id.pause);
        ImageView img_download = (ImageView) convertView.findViewById(R.id.download);


        comment.setText(child);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupVoice, int childComment) {
        return true;
    }

}
