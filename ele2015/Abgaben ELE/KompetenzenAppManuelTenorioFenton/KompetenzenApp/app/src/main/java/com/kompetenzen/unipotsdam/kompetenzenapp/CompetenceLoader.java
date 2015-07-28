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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;


public class CompetenceLoader extends Activity implements View.OnClickListener{


    public SparseArray<CompetenceGroup> groups= new SparseArray<CompetenceGroup>();
    private Competence_Adapter_Expandable adapter;

    Button mark_button, comment_button;

    //private String URL= "http://fleckenroller.cs.uni-potsdam.de/app/competence-servlet/competence/competences/xml/competencetree/university/all/nocache";

    private String URL= "http://10.0.2.2:8084/competences/xml/competencetree/university/all/nocache";

    ExpandableListView exListView;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competenceloader);
        loadCompetences();
    }

    private void initializeListView(){
        exListView = (ExpandableListView) findViewById(R.id.expandableListView);
        exListView.setDividerHeight(2);
        exListView.setGroupIndicator(null);
        exListView.setClickable(true);
        adapter= new Competence_Adapter_Expandable(this,groups);
        exListView.setAdapter(adapter);
        mark_button= (Button)findViewById(R.id.MarkierenButton);
        mark_button.setOnClickListener((View.OnClickListener) this);
        comment_button= (Button)findViewById(R.id.KommentierenButton);
        comment_button.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    private void loadCompetences(){
        if(isOnline()){
            //System.out.println("\nTrying to load competences!");
            DownloadCompetences(URL);
        }
    }


    private void DownloadCompetences(String url){

        UTF8StringRequest req= new UTF8StringRequest(url,

                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //System.out.print("\n Response obtained!");
                        XMLParser parser = new XMLParser(response, getBaseContext());
                        groups = parser.parse();

                        initializeListView();
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //System.out.println("\nError during connection!");
                        Toast.makeText(CompetenceLoader.this, volleyError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        req.setRetryPolicy(new DefaultRetryPolicy(
                80000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue= Volley.newRequestQueue(this);
        //System.out.println("\nAdding the server-request to a request-queue!");
        queue.add(req);
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
            case(R.id.MarkierenButton):
                startActivity(new Intent(this,AchievedCompetences.class));
                break;
            case(R.id.KommentierenButton):
                startActivity(new Intent(this,CommentedCompetences.class));
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
