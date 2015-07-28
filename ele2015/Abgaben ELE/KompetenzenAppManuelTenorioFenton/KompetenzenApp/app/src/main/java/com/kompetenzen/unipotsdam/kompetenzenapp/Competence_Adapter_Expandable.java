package com.kompetenzen.unipotsdam.kompetenzenapp;

import android.app.Activity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Manuel on 14/07/2015.
 */
public class Competence_Adapter_Expandable extends BaseExpandableListAdapter {

    private final SparseArray<CompetenceGroup> competenceGroups;
    public LayoutInflater inflater;
    public Activity activity;

    public Competence_Adapter_Expandable(Activity act, SparseArray<CompetenceGroup> groups) {
        this.activity= act;
        this.competenceGroups = groups;
        this.inflater= act.getLayoutInflater();
    }

    @Override
    public int getGroupCount() {
        return competenceGroups.size();
    }

    /*returns the amount of subitems that one item has*/
    @Override
    public int getChildrenCount(int groupPosition) {
        return competenceGroups.get(groupPosition).children.size();
    }

    /*returns the list of subitems inside a group*/
    @Override
    public CompetenceGroup getGroup(int groupPosition) {
        return competenceGroups.get(groupPosition);
    }


    /*returns the information associated with a child regarding the position*/
    @Override
    public Competence getChild(int groupPosition, int childPosition) {
        return competenceGroups.get(groupPosition).children.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    /*returns the id of an item or subitem regarding the position of both */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView= inflater.inflate(R.layout.item_competence,null);
        }
        CompetenceGroup cG= (CompetenceGroup)this.getGroup(groupPosition);
        //convertView.setText(cG.upperCompetence);
        //CheckedTextView.convertView.setChecked(isExpanded);
        TextView text= (TextView)convertView.findViewById(R.id.competence_textview_name);
        text.setText(cG.upperCompetence.getName());
        return convertView;
    }

    /* returns the corresponding View object to the object identified with the parameters*/
    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final Competence children= (Competence)getChild(groupPosition,childPosition);
        TextView textView = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_small_competence,null);
        }
        textView= (TextView)convertView.findViewById(R.id.competence_textview_name);
        textView.setText(children.getName());
        /*  HERE SHOULD GO THE CODE FOR THE MARKING ACTIVITY*/
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,children.getName(),Toast.LENGTH_LONG).show();
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
