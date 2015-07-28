package com.kompetenzen.unipotsdam.kompetenzenapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

/**
 * Created by Manuel on 07/07/2015.
 */
public class Competence_Adapter extends ArrayAdapter<Object> {

    Context context;
    private ArrayList<String> comments;
    RequestQueue requestQueue;

    public Competence_Adapter(Context con, ArrayList<String> comp){
        super(con, R.layout.item_competence);
        //super(con,R.layout.item_small_competence);
        this.context= con;
        this.comments= comp;

        requestQueue = Volley.newRequestQueue(context);
    }

    @Override
    public int getCount() {return comments.size();}

    private static class PlaceHolder {

        TextView name;

        public static PlaceHolder generate(View convertView){
            PlaceHolder placeHolder= new PlaceHolder();
            placeHolder.name= (TextView) convertView.findViewById(R.id.competence_textview_name);
            return placeHolder;
        }

    }

    public View getView(int position, View convertView, ViewGroup parent){
        PlaceHolder placeHolder;
        if(convertView == null){
            convertView = View.inflate(context,R.layout.item_competence,null);
            placeHolder = PlaceHolder.generate(convertView);
            convertView.setTag(placeHolder);
        }else{
            placeHolder= (PlaceHolder) convertView.getTag();
        }
        placeHolder.name.setText(comments.get(position));
        final int positioning= position;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, comments.get(positioning), Toast.LENGTH_LONG).show();
            }
        });
        return (convertView);
    }


}
