package com.kompetenzen.unipotsdam.kompetenzenapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Manuel on 26/07/2015.
 */
public class CommentCompetence extends Activity implements View.OnClickListener{

    Competence competence;

    Button comment;

    ListView listView;

    String URL_comment= "http://10.0.2.2:8084/competences/json/link/comment/KompetenzenApp";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(null);
        setContentView(R.layout.activity_commentcompetences);
        getParameter();
        displayCompetence();
    }

    public void getParameter(){
        competence= (Competence) getIntent().getExtras().getSerializable("comp");
    }


    public void displayCompetence(){
        TextView textView= (TextView)findViewById(R.id.textView);
        textView.setText(competence.getName());
        ArrayList<String> comments= competence.getComments();
        if (comments.size() == 0){
            comments.add("Es gibt keine kommentare noch für diese Kompetenz");
        }
        listView= (ListView)findViewById(R.id.listView);
        Competence_Adapter competence_adapter= new Competence_Adapter(this,comments);
        listView.setAdapter(competence_adapter);
        comment= (Button)findViewById(R.id.button3);
        comment.setOnClickListener((View.OnClickListener)this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.button3):
                EditText editText= (EditText) findViewById(R.id.editText);
                String commentWritten= editText.getText().toString();
                if(commentWritten.equals("")){
                    Toast.makeText(this,"Bitte schreibt deine Kommentare",Toast.LENGTH_LONG).show();
                    break;
                }
                //construct the URL that is to be used
                String linkID= competence.getName().replaceAll(" ","");
                String URL_final = URL_comment + encode("manuel") + "link" + linkID + "/" + encode("manuel") + "/university/teacher?";
                //fill map with parameters
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("text", encode(commentWritten));
                try {
                    postCommenting(URL_final, params);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(this, CommentedCompetences.class));
                break;
        }
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

    public static HttpResponse postCommenting(String URL, Map params) throws Exception {
        //instantiates httpclient
        DefaultHttpClient httpclient = new DefaultHttpClient();
        //url with the post data
        HttpPost httpost = new HttpPost(URL);
        String currURI = httpost.getURI().toString();
        currURI = currURI + "text=" + params.get("text");
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

}
