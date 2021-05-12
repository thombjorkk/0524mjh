package com.cookandroid.splash_pleazz;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class myAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private ArrayList<position> position;
    private LayoutInflater inflater;

    //class Constructor
    public myAdapter (Context mContext, ArrayList<position> position) {

        this.mContext = mContext;
        this.position = position;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getGroupCount() {
        return position.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return position.get(groupPosition).comments.size();
    }

    //get position
    @Override
    public Object getGroup(int groupPosition) {
        return position.get(groupPosition);
    }

    //this is where we get the information of player
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return position.get(groupPosition).comments.get(childPosition);
    }

    //position ID
    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    //where to get player's id
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //get parent row
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.parent_list, null);
        }

        //get position
        position position = (position) getGroup(groupPosition);

        //set positionName
        String positionName = position.position;

        TextView textView = (TextView) convertView.findViewById(R.id.id_listitem);
        textView.setText(positionName);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.arrow_down);
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
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        //inflate the layout
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child_list, null);
        }

        String child = (String) getChild(groupPosition, childPosition);

        //set the child name
        TextView name = (TextView) convertView.findViewById(R.id.comments);
        //get the imageView
        ImageView img = (ImageView) convertView.findViewById(R.id.favorite);

        name.setText(child);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
