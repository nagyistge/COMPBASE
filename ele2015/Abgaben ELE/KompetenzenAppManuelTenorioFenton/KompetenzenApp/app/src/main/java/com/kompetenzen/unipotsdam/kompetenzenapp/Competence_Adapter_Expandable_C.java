package com.kompetenzen.unipotsdam.kompetenzenapp;

import android.app.Activity;
import android.content.Intent;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Manuel on 24/07/2015.
 */
public class Competence_Adapter_Expandable_C extends BaseExpandableListAdapter {

    private final SparseArray<CompetenceGroup> competenceGroups;
    public LayoutInflater inflater;
    public Activity activity;
    public XMLParser parser;

    private  String URL_Post_1= "http://10.0.2.2:8084/competences/json/link/create/university/";
    private String evidences= "Kompetenztool,link";

    public Competence_Adapter_Expandable_C(Activity act, SparseArray<CompetenceGroup> groups, XMLParser parser) {
        this.activity= act;
        this.competenceGroups = groups;
        this.inflater= act.getLayoutInflater();
        this.parser= parser;
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
        final CompetenceGroup cG= (CompetenceGroup)this.getGroup(groupPosition);
        int viewCase= 0;
        PlaceHolder placeHolder;
        // convertView should re-inflate everytime to ensure the different presentation of layouts
        if(cG.upperCompetence.getFulfilled()) {
            convertView = inflater.inflate(R.layout.item_competence_purple, null);
            placeHolder = PlaceHolder.generate(convertView);
            convertView.setTag(placeHolder);
            placeHolder.name.setText(cG.upperCompetence.getName());
            viewCase=1;
        }
        if(cG.upperCompetence.getReadyToDo()) {
            convertView = inflater.inflate(R.layout.item_competence, null);
            placeHolder = PlaceHolder.generate(convertView);
            convertView.setTag(placeHolder);
            placeHolder.name.setText(cG.upperCompetence.getName());
            viewCase=2;
        }
        if(!cG.upperCompetence.getReadyToDo() && !cG.upperCompetence.getFulfilled()){
            convertView= inflater.inflate(R.layout.item_competence, null);
            placeHolder = PlaceHolder.generate(convertView);
            convertView.setTag(placeHolder);
            placeHolder.name.setText(cG.upperCompetence.getName());
            viewCase=3;
        }
        switch (viewCase){
            case 1:
                Button commentButton= (Button)convertView.findViewById(R.id.button2);
                commentButton.setFocusable(false);
                commentButton.setOnClickListener(new Button.OnClickListener(){

                    public void onClick(View v){
                        Intent intent = new Intent(activity,CommentCompetence.class);
                        intent.putExtra("comp",cG.upperCompetence);
                        activity.startActivity(intent);
                    }
                });
                break;
            case 2:
                break;
            case 3:
                break;
        }
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
        int viewCase =0;
        PlaceHolder placeHolder;
        //if(convertView == null){
        if(children.getFulfilled()) {
            convertView = inflater.inflate(R.layout.item_small_comp_purple, null);
            placeHolder = PlaceHolder.generate(convertView);
            convertView.setTag(placeHolder);
            placeHolder.name.setText(children.getName());
            viewCase=1;
        }
        if(children.getReadyToDo()) {
            convertView = inflater.inflate(R.layout.item_small_competence, null);
            placeHolder = PlaceHolder.generate(convertView);
            convertView.setTag(placeHolder);
            placeHolder.name.setText(children.getName());
            viewCase=2;
        }
        if(!children.getReadyToDo() && !children.getFulfilled()){
            convertView= inflater.inflate(R.layout.item_small_competence, null);
            placeHolder = PlaceHolder.generate(convertView);
            convertView.setTag(placeHolder);
            placeHolder.name.setText(children.getName());
            viewCase=3;
        }
        switch (viewCase){
            case 1:
                Button commentButton= (Button)convertView.findViewById(R.id.button);
                commentButton.setFocusable(false);
                commentButton.setOnClickListener(new Button.OnClickListener() {

                    public void onClick(View v){
                        Intent intent = new Intent(activity,CommentCompetence.class);
                        intent.putExtra("comp",children);
                        activity.startActivity(intent);
                    }
                });
                break;
            case 2:
                break;
            case 3:
                break;
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, children.getName(), Toast.LENGTH_LONG).show();
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    private static class PlaceHolder {

        TextView name;

        public static PlaceHolder generate(View convertView){
            PlaceHolder placeHolder= new PlaceHolder();
            placeHolder.name= (TextView) convertView.findViewById(R.id.competence_textview_name);
            return placeHolder;
        }

    }

}
