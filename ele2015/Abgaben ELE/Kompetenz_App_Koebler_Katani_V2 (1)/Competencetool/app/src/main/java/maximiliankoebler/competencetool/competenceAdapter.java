package maximiliankoebler.competencetool;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class competenceAdapter extends ArrayAdapter<String> {
    public competenceAdapter(Context context, ArrayList<String> competences) {
        super(context, 0, competences);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String competence = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_main, parent, false);
        }

        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);

        // Populate the data into the template view using the data object
        tvName.setText(competence);

        //if the competence is contained within the learnedCompetenceList, then...
        if (MainActivity.learnedCompetencesList.contains(competence)) {

            //...highlight it as a learned competence
            tvName.setBackgroundColor(Color.LTGRAY);
        }
        else {
            tvName.setBackgroundColor(Color.TRANSPARENT);
        }

        // Return the completed view to render on screen
        return convertView;
    }
}