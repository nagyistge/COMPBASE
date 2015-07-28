package maximiliankoebler.competencetool;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class MainActivity extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    //Needed to save the username in a local file
    SharedPreferences mSharedPreferences;
    private static final String PREFS = "prefs";
    private static final String PREF_NAME = "name";
    //Central elements of the application
    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    ArrayList mCompetenceList = new ArrayList();
    //List of items that the User has marked as learned
    public static ArrayList<String> learnedCompetencesList = new ArrayList();
    //Needed to generate the LinkID which is a parameter of the comment request
    String LinkIDForComment = "";
    //URLs
    private static final String HOST_URL = "http://10.0.2.2:8084";
    private static final String COMPETENCELIST_URL = HOST_URL + "/competences/xml/competencetree/university/all/nocache";
    private static final String COMMENT_URL = HOST_URL + "/competences/json/link/comment/Kompetenztool";
    private static final String MARK_URL = HOST_URL + "/competences/json/link/create/university/";
    private static final String LEARNED_COMPETENCES_URL = HOST_URL + "/competences/json/link/overview/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // 0. Set the global layout
        setContentView(R.layout.activity_main);

        // 1. Greet the user or ask for his name if new
        displayWelcome();

        // 2. Define the ListView in Java
        mainListView = (ListView) findViewById(R.id.main_listview);

        // 3. Create a custom ArrayAdapter for the ListView
        mArrayAdapter = new competenceAdapter(this, mCompetenceList);

        // 4. Set the ListView to use the ArrayAdapter
        mainListView.setAdapter(mArrayAdapter);

        // 5. Set this activity to react to list items being clicked or long clicked
        mainListView.setOnItemClickListener(this);
        mainListView.setOnItemLongClickListener(this);

        // 7. Load the competences from the server
        loadAllCompetences(COMPETENCELIST_URL);

        // 8. Mark the already learned competences
        if (!getUserName().equals("")) {
            try {

                markAlreadyLearnedCompetences();
            } catch (IOException e) {
                //do nothing
            }
        }
    }

    public boolean onItemLongClick(AdapterView<?> parent, View view,
                                   int position, long id) {

        // Access the device's key-value storage
        mSharedPreferences = getSharedPreferences(PREFS, MODE_PRIVATE);

        // show a dialog to ask for his input
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Commenting function");
        alert.setMessage("Please enter a comment!");

        // Create EditText for entry
        final EditText input = new EditText(this);
        alert.setView(input);

        //Get Text of clicked item to build LinkID, the parameter needed for a comment
        String linkID = mainListView.getItemAtPosition(position).toString();
        linkID = linkID.replace(" ", "");
        LinkIDForComment = linkID;

        // Make an "OK" button to save the name
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {

                //Build URL for the comment request
                String url = COMMENT_URL + encode(getUserName()) + "link" + LinkIDForComment + "/" + encode(getUserName()) + "/university/teacher?";
                LinkIDForComment = "";

                //Fill map which includes the JSON parameters
                Map<String, Object> mapObject = new HashMap<String, Object>();
                mapObject.put("text", encode(input.getText().toString()));

                //Call HTTP Request
                try {
                    makeRequestComment(url, mapObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //Show user that his comment was successfully sent to the server
                Toast.makeText(getApplicationContext(), "Competence successfully commented!", Toast.LENGTH_LONG).show();
            }
        });

        // Make a "Cancel" button that simply dismisses the alert
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });

        //Display the Pop-Up in the application
        alert.show();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void displayWelcome() {

        // Access the device's key-value storage
        mSharedPreferences = getSharedPreferences(PREFS, MODE_PRIVATE);

        // Read the user's name,
        // or an empty string if nothing found
        String name = getUserName();

        if (name.length() > 0) {

            // If the name is valid, display a Toast welcoming them
            Toast.makeText(this, "Welcome back, " + name + "!", Toast.LENGTH_LONG).show();
        }
        else {

            // otherwise, show a dialog to ask for his name
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Welcome to the Competence-App!");
            alert.setMessage("Select a username!");

            // Create EditText for entry
            final EditText input = new EditText(this);
            alert.setView(input);

            // Make an "OK" button to save the name
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {

                    // Grab the EditText's input
                    String inputName = input.getText().toString();

                    // Put it into memory (don't forget to commit!)
                    SharedPreferences.Editor e = mSharedPreferences.edit();
                    e.putString(PREF_NAME, inputName);
                    e.commit();

                    // Welcome the new user
                    Toast.makeText(getApplicationContext(), "Welcome, " + inputName + "!", Toast.LENGTH_LONG).show();
                }
            });

            // Make a "Cancel" button
            // that simply dismisses the alert
            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                    finish();

                }
            });

            alert.show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //retrieve text from clicked competence
        String markedCompetenceText = mCompetenceList.get(position).toString();

        //build URL to mark competence as learned
        String url = MARK_URL + encode(getUserName()) + "/teacher/" + encode(getUserName()) + "?";

        //Fill map which includes the JSON parameters
        Map<String, Object> mapObject = new HashMap<String, Object>();
        mapObject.put("competences", encode(markedCompetenceText));
        mapObject.put("evidences", "Kompetenztool" + encode(getUserName()) + ",link");

        //Call HTTP Request
        try {
            makeRequest(url, mapObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Add clicked competence to the list of learned and therefore highlighted competences
        learnedCompetencesList.add(markedCompetenceText);

        //Update Listview
        mArrayAdapter.notifyDataSetChanged();

        //Show the user that he has successfully marked a competence
        Toast.makeText(getApplicationContext(), "Competence successfully marked!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        //Do nothing
    }

    public void loadAllCompetences (String searchString) {
        InputStream in = null;
        apiCallResult result = null;

        //Load the XML with the list of competences from the server
        try {
            URL url = new URL(searchString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream());
        } catch (Exception e ) {
            System.out.println(e.getMessage());
        }
        // Parse XML
        XmlPullParserFactory pullParserFactory;

        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();

            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            result = parseXML(parser);

            //add the competences from the server to the internal data structure mCompetenceList
            addItemsToCompetenceList(result);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Update the Listview because now the competences from the server are loaded into the list
        mArrayAdapter.notifyDataSetChanged();
    }

    private apiCallResult parseXML( XmlPullParser parser ) throws XmlPullParserException, IOException {

        int eventType = parser.getEventType();
        int index = 0;
        apiCallResult result = null;
        competence currentCompetence = null;

        //Loop over the XML from the server and parse it so that the competences are put into a list
        while( eventType!= XmlPullParser.END_DOCUMENT) {
            String name = null;

            switch(eventType)
            {
                case XmlPullParser.START_DOCUMENT:
                    result = new apiCallResult();
                    result.apiCallResultCompetences = new ArrayList<competence>();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();

                    //Only the competence-tags are relevant
                    if (name.equals("competence")) {
                        currentCompetence = new competence();
                        //The attribute name contains the text of the competence
                        currentCompetence.name = parser.getAttributeValue(null, "name");
                        result.apiCallResultCompetences.add(index, currentCompetence);
                        index = index + 1;
                    }
                    break;

                case XmlPullParser.END_TAG:
                    name = parser.getName();
            } // end switch
            eventType = parser.next();
        } // end while
        return result;
    }

    private void addItemsToCompetenceList(apiCallResult result) {
        //Loop over the list of all competences from the server and add them to the internal competence list
        String content = "";
        Iterator it = result.apiCallResultCompetences.iterator();
        Object currCompetence = null;
        while(it.hasNext()) {
            currCompetence = it.next();
            mCompetenceList.add(((competence) currCompetence).getCompetence());
        }
    }

    public static HttpResponse makeRequest(String path, Map params) throws Exception {
        //instantiates httpclient to make request
        DefaultHttpClient httpclient = new DefaultHttpClient();

        //url with the post data
        HttpPost httpost = new HttpPost(path);

        String currURI = httpost.getURI().toString();
        currURI = currURI + "competences=" + params.get("competences") + "&evidences=" + params.get("evidences");
        httpost.setURI(URI.create(currURI));
        StringEntity se = new StringEntity("");


        //sets the post request as the resulting string
        httpost.setEntity(se);
        //sets a request header so the page receving the request
        //will know what to do with it
        httpost.setHeader("Accept", "application/json");
        httpost.setHeader("Content-type", "application/json");

        //Handles what is returned from the page
        ResponseHandler responseHandler = new BasicResponseHandler();
        HttpResponse response;
        response = httpclient.execute(httpost);
        return null;
    }

    public static HttpResponse makeRequestComment(String path, Map params) throws Exception {
        //instantiates httpclient to make request
        DefaultHttpClient httpclient = new DefaultHttpClient();

        //url with the post data
        HttpPost httpost = new HttpPost(path);

        String currURI = httpost.getURI().toString();
        currURI = currURI + "text=" + params.get("text");
        httpost.setURI(URI.create(currURI));
        StringEntity se = new StringEntity("");

        //sets the post request as the resulting string
        httpost.setEntity(se);

        //sets a request header so the page receving the request
        //will know what to do with it
        httpost.setHeader("Accept", "application/json");
        httpost.setHeader("Content-type", "application/json");

        //Handles what is returned from the page
        ResponseHandler responseHandler = new BasicResponseHandler();
        HttpResponse response;
        response = httpclient.execute(httpost);
        return null;
    }

    public void markAlreadyLearnedCompetences () throws  IOException {

        //instantiate client and reader
        DefaultHttpClient httpclient = new DefaultHttpClient();
        BufferedReader in = null;

        //Build URI and request
        String URI = LEARNED_COMPETENCES_URL + encode(getUserName());
        HttpGet request = new HttpGet(URI);
        StringBuilder builder = new StringBuilder();

        //execute request
        HttpResponse httpResponse = httpclient.execute(request);
        HttpEntity responseEntity = httpResponse.getEntity();

        //read the input stream
        Reader reader = new InputStreamReader(responseEntity.getContent());

        //parse JSON response
        Gson gson = new Gson();
        JsonElement element = gson.fromJson(reader, JsonElement.class);
        Map<String, Object> javaRootMapObject = new Gson().fromJson(element, Map.class);
        Map<String, Object> competenceMap = (Map<String, Object>) javaRootMapObject.get("mapUserCompetenceLinks");
        Iterator it = competenceMap.keySet().iterator();

        //Loop through already learned competences and highlight them in the listview
        while (it.hasNext()) {
            String s2 = it.next().toString();
            Iterator it2 = mCompetenceList.iterator();
            while (it2.hasNext()) {
                String s1 = it2.next().toString();
                if (s1.equals(s2)) {
                    /* if a competence is already learned and also contained in mCompetenceList then add it
                       to the learnedCompetenceList. These items are then highlighted, functionality can
                       be found in the array adapter class */
                    learnedCompetencesList.add(s2);
                }
            }
        }

        //Update the Listview because now some competences are highlighted
        mArrayAdapter.notifyDataSetChanged();
    }

    public String getUserName () {

        //return username which is read from a local file on the device
        String usernameRaw = mSharedPreferences.getString(PREF_NAME, "");
        return usernameRaw;
    }

    public String encode (String inputString) {

        //encode a string according to UTF-8 to make it usable within an URL
        String stringEncoded = "";
        try {
            stringEncoded = URLEncoder.encode(inputString, "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return stringEncoded;
    }

    public class competence {

        public String name;

        public String getCompetence() {
            return name;
        }
    }

    public class apiCallResult {
        public ArrayList<competence> apiCallResultCompetences;
    }
}
