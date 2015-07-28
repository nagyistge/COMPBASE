package com.kompetenzen.unipotsdam.kompetenzenapp;

import android.app.Activity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * Created by Manuel on 22/07/2015.
 */
public class Competence_Adapter_Expandable_B extends BaseExpandableListAdapter{

    private final SparseArray<CompetenceGroup> competenceGroups;
    public LayoutInflater inflater;
    public Activity activity;
    public XMLParser parser;

    private  String URL_Post_1= "http://10.0.2.2:8084/competences/json/link/create/university/";
    private String evidences= "Kompetenztool,link";

    public Competence_Adapter_Expandable_B(Activity act, SparseArray<CompetenceGroup> groups, XMLParser parser) {
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
                convertView = inflater.inflate(R.layout.item_competence_green, null);
                placeHolder = PlaceHolder.generate(convertView);
                convertView.setTag(placeHolder);
                placeHolder.name.setText(cG.upperCompetence.getName());
                viewCase=1;
            }
            if(cG.upperCompetence.getReadyToDo()) {
                convertView = inflater.inflate(R.layout.item_competence_yellow, null);
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
                break;
            case 2:
                Button markButton= (Button)convertView.findViewById(R.id.button2);
                markButton.setFocusable(false);
                markButton.setOnClickListener(new Button.OnClickListener(){
                    public void onClick(View v){
                        cG.upperCompetence.setFulfilled(true);
                        cG.upperCompetence.setReadyToDo(false);
                        //construct the URL that is to be used
                        String URL_final= URL_Post_1 + encode("manuel") + "/teacher/" + encode("manuel") + "?";
                        //fill map with parameters
                        Map<String,Object> params= new HashMap<String,Object>();
                        params.put("competences",encode(cG.upperCompetence.getName()));
                        params.put("evidences","KompetenzenApp"+encode("manuel")+",gutenAbend");
                        try{
                            postMarking(URL_final,params);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        if(cG.upperCompetence.getSons().size() != 0){
                            reportAdvance(parser.map.get(cG.upperCompetence.getName()));
                            notifyDataSetChanged();
                            Toast.makeText(activity,"Neue Kompetenzen Freigeschaltet!",Toast.LENGTH_LONG).show();
                        }
                        else{
                            notifyDataSetChanged();
                        }
                    }
                });
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
                convertView = inflater.inflate(R.layout.item_small_comp_green, null);
                placeHolder = PlaceHolder.generate(convertView);
                convertView.setTag(placeHolder);
                placeHolder.name.setText(children.getName());
                viewCase=1;
            }
            if(children.getReadyToDo()) {
                convertView = inflater.inflate(R.layout.item_small_comp_yellow, null);
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
                break;
            case 2:
                Button markButton= (Button)convertView.findViewById(R.id.button);
                markButton.setFocusable(false);
                markButton.setOnClickListener(new Button.OnClickListener() {
                    public void onClick(View v){
                        children.setFulfilled(true);
                        children.setReadyToDo(false);
                        //construct the URL that is to be used
                        String URL_final= URL_Post_1 + encode("manuel") + "/teacher/" + encode("manuel") + "?";
                        //fill map with parameters
                        Map<String,Object> params= new HashMap<String,Object>();
                        params.put("competences", encode(children.getName()));
                        params.put("evidences", "KompetenzenApp" + encode("manuel") + ",guntenAbend");
                        try{
                            postMarking(URL_final,params);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        if(children.getSons().size() != 0){
                            reportAdvance(parser.map.get(children.getName()));
                            notifyDataSetChanged();
                            Toast.makeText(activity,"Neue Kompetenzen Freigeschaltet!",Toast.LENGTH_LONG).show();
                        }
                        else{
                            notifyDataSetChanged();
                        }
                    }
                });
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
    private void reportAdvance(int[] compCode){
        CompetenceGroup cG= competenceGroups.get(compCode[0]);
        if(compCode[1] == -1){
            Competence compFound= cG.upperCompetence;
            compFound.setFulfilled(true);
            List<int[]> sons= compFound.getSons();
            ListIterator<int[]> iterator= sons.listIterator();
            int counter= 0;
            while(counter < sons.size()){
                int[] sonCoord= iterator.next();
                CompetenceGroup sonFound= competenceGroups.get(sonCoord[0]);
                if(sonCoord[1] == -1){
                    sonFound.upperCompetence.setReadyToDo(true);
                }
                else{
                    Competence child= sonFound.children.get(sonCoord[1]);
                    child.setReadyToDo(true);
                }
                counter++;
            }
        }
        else{
            Competence compFound= cG.children.get(compCode[1]);
            compFound.setFulfilled(true);
            String compFoundID= cG.children.get(compCode[1]).getName();
            List<int[]> sons= compFound.getSons();
            ListIterator<int[]> iterator= sons.listIterator();
            int counter= 0;
            while(counter < sons.size()){
                int[] sonCoord= iterator.next();
                CompetenceGroup sonFound= competenceGroups.get(sonCoord[0]);
                if(sonCoord[1] == -1){
                    sonFound.upperCompetence.setReadyToDo(true);
                }
                else{
                    Competence child= sonFound.children.get(sonCoord[1]);
                    child.setReadyToDo(true);
                }
                counter++;
            }
        }
    }

    public static HttpResponse postMarking(String URL, Map params) throws Exception {
        //instantiates httpclient
        DefaultHttpClient httpclient = new DefaultHttpClient();
        //url with the post data
        HttpPost httpost = new HttpPost(URL);
        String currURI = httpost.getURI().toString();
        currURI = currURI + "competences=" + params.get("competences") + "&evidences=" + params.get("evidences");
        httpost.setURI(URI.create(currURI));
        StringEntity se = new StringEntity("");
        //sets the post request as the resulting string
        httpost.setEntity(se);
        httpost.setHeader("Accept", "application/json");
        httpost.setHeader("Content-type", "application/json");
        //Handles what is returned from the page
        ResponseHandler responseHandler = new BasicResponseHandler();
        HttpResponse response;
        response = httpclient.execute(httpost);
        return null;
    }

    public String encode (String inputString) {
        String stringEncoded = "";
        try {
            stringEncoded = URLEncoder.encode(inputString, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return stringEncoded;
    }
}
