package com.kompetenzen.unipotsdam.kompetenzenapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AchievedCompetences extends Activity implements View.OnClickListener{


    public SparseArray<CompetenceGroup> groups= new SparseArray<CompetenceGroup>();
    private Competence_Adapter_Expandable_B adapter;

    XMLParser parser;

    Button zurueck_button;

    //private String URL= "http://fleckenroller.cs.uni-potsdam.de/app/competence-servlet/competence/competences/xml/competencetree/university/all/nocache";

    private String URL= "http://10.0.2.2:8084/competences/xml/competencetree/university/all/nocache";

    private String URL_two= "http://10.0.2.2:8084/competences/json/prerequisite/graph/university";

    private String URL_three= "http://10.0.2.2:8084/competences/json/link/overview/Admin%20User";

    ExpandableListView exListView;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(null);
        setContentView(R.layout.activity_achievedcompetences);
        LaunchThreads();
    }

    private void LaunchThreads(){
        Thread firstThread= new Thread(){
            public void run(){
                try{
                    // no rest
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    if(isOnline()){
                        System.out.println("\nTrying to load competences!");
                        DownloadCompetences(URL);
                    }
                }
            }
        };
        Thread secondThread= new Thread() {
            public void run(){
                try{
                    sleep(100);
                }catch(Exception e){
                    e.printStackTrace();
                }
                finally{
                    actualizeLearnPaths();
                }
            }
        };
        Thread thirdThread= new Thread() {
            public void run(){
                try{
                    sleep(100);
                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    loadAdvance();
                }
            }
        };
        try{
            ExecutorService executor= Executors.newSingleThreadExecutor();
            executor.submit(firstThread);
            executor.submit(secondThread);
            executor.submit(thirdThread);
            executor.shutdown();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void initializeListView(){
        exListView = (ExpandableListView) findViewById(R.id.expandableListView);
        exListView.setDividerHeight(2);
        exListView.setGroupIndicator(null);
        exListView.setClickable(true);

        adapter= new Competence_Adapter_Expandable_B(this,groups,parser);
        exListView.setAdapter(adapter);
        zurueck_button= (Button)findViewById(R.id.zurueck);
        zurueck_button.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }



    private void DownloadCompetences(String url){
        UTF8StringRequest req= new UTF8StringRequest(url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.print("\n Response obtained!");
                        parser = new XMLParser(response, getBaseContext());
                        groups = parser.parse();
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        System.out.println("\nError during connection!");
                        Toast.makeText(AchievedCompetences.this, volleyError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


        req.setRetryPolicy(new DefaultRetryPolicy(
                80000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue= Volley.newRequestQueue(this);
        System.out.println("\nAdding the server-request to a request-queue!");
        queue.add(req);

    }


    private void actualizeLearnPaths(){
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,
                URL_two,
                null,
                new Response.Listener<JSONObject>(){

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Parsing json object response
                            // response will be a json object with different
                            // parts, such as an json-array which contains many
                            // jason objects by its own
                            //String res= response.toString();
                            //System.out.println("\n\nResponse from Json: "+res);
                            JSONArray triples= response.getJSONArray("triples");
                            for(int i=0; i < triples.length(); i++) {
                                JSONObject triplesContents = triples.getJSONObject(i);
                                String fromNode = triplesContents.getString("fromNode");
                                String toNode = triplesContents.getString("toNode");
                                int[] parentKey= parser.map.get(fromNode);
                                int[] childKey= parser.map.get(toNode);
                                CompetenceGroup parentGroup = groups.get(parentKey[0]);
                                CompetenceGroup childGroup = groups.get(childKey[0]);
                                if(parentKey[1] == -1){
                                    Competence parentCompetence= parentGroup.upperCompetence;
                                    parentCompetence.addSon(childKey);
                                }
                                else{
                                    Competence parentCompetence= parentGroup.children.get(parentKey[1]);
                                    parentCompetence.addSon(childKey);
                                }
                                if(childKey[1] == -1){
                                    Competence childCompetence= childGroup.upperCompetence;
                                    childCompetence.addParent(parentKey);
                                }
                                else{
                                    Competence childCompetence= childGroup.children.get(childKey[1]);
                                    childCompetence.addParent(parentKey);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                        //For explanatory purpuses, a learn-path from the first competence to de second will be
                        // set. The following lines of code should be commented for the launch of the application:
                        CompetenceGroup example1= groups.get(0);
                        CompetenceGroup example2= groups.get(1);
                        int[] sonCode= {1,-1};
                        int[] parentCode= {0,-1};
                        example1.upperCompetence.addSon(sonCode);
                        example2.upperCompetence.addParent(parentCode);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                80000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);

    }

    private void loadAdvance(){
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET,
                URL_three,
                null,
                new Response.Listener<JSONObject>(){

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Parsing json object response
                            // response will be a json object with different
                            // parts, such as an json-array which contains many
                            // jason objects by its own
                            //String res= response.toString();
                            //System.out.println("\n\nResponse from Json: "+res);
                            JSONObject obj= response.getJSONObject("mapUserCompetenceLinks");
                            //String objStr= obj.toString();
                            Iterator<String> keys= obj.keys();
                            while(keys.hasNext()){
                                String competenceKey= keys.next();
                                int[] compCode= parser.map.get(competenceKey);
                                reportAdvance(compCode);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                        setReadyToDo();
                        initializeListView();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                80000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }

    private void reportAdvance(int[] compCode){
        CompetenceGroup cG= groups.get(compCode[0]);
        if(compCode[1] == -1){
            Competence compFound= cG.upperCompetence;
            compFound.setFulfilled(true);
            List<int[]> sons= compFound.getSons();
            ListIterator<int[]> iterator= sons.listIterator();
            int counter= 0;
            while(counter < sons.size()){
                int[] sonCoord= iterator.next();
                CompetenceGroup sonFound= groups.get(sonCoord[0]);
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
            //String compFoundID= cG.children.get(compCode[1]).getName();
            List<int[]> sons= compFound.getSons();
            ListIterator<int[]> iterator= sons.listIterator();
            int counter= 0;
            while(counter < sons.size()){
                int[] sonCoord= iterator.next();
                CompetenceGroup sonFound= groups.get(sonCoord[0]);
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

    private void setReadyToDo(){
        //iterate over all competences in order to set the variable "Ready to Do" true
        // a competence is considered to be ready to be marked if it is at the end of
        // a learn-path, namely, if it has son-competence(s) but no parent(s)
        // This method cooperates with the loadAdvance method, in order to mark all competences
        // that are ready to be marked.
        int w= groups.size();
        for(int i=0; i< w; i++){
            CompetenceGroup cG= groups.get(i);
            Competence parentCompetence= cG.upperCompetence;
            if(parentCompetence.getParents().size() == 0 && parentCompetence.getSons().size() != 0){
                parentCompetence.setReadyToDo(true);
            }
            List<Competence> sons = cG.children;
            ListIterator<Competence> iterator= sons.listIterator();
            int counter= 0;
            while(counter < sons.size()){
                Competence sonFound= iterator.next();
                if(sonFound.getParents().size() == 0 && sonFound.getSons().size() != 0){
                    sonFound.setReadyToDo(true);
                }
                counter++;
            }
        }
        // For explanatory purpuses the first competence will be set as ready to do:
        // the following lines of this method should be commented or erased for the application launch
        CompetenceGroup example1= groups.get(0);
        example1.upperCompetence.setReadyToDo(true);
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getApplication()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case(R.id.zurueck):
                startActivity(new Intent(this,CompetenceLoader.class));
                break;
        }
    }

    private class UTF8StringRequest extends StringRequest {
        public UTF8StringRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }

        public UTF8StringRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(url, listener, errorListener);
        }

        @Override
        protected Response<String> parseNetworkResponse(NetworkResponse response) {

            String utf8String = null;
            try {
                utf8String = new String(response.data, "UTF-8");
                return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));

            } catch (UnsupportedEncodingException e) {
                return Response.error(new ParseError(e));
            }
        }
    }

}