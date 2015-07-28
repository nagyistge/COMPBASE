package de.gimpel.kompetenz;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class MainActivity extends ActionBarActivity {
	private static ProgressDialog prgDialog;
	private static ProgressDialog prgDialog2;
	private static ProgressDialog prgDialog3;
	MyCustomAdapter dataAdapter = null;

	// public static String IP = "192.168.2.113";
	public static String IP = "fleckenroller.cs.uni-potsdam.de/app/competence-servlet/competence";
	// public static String PORT = ":8084";
	public static String PORT = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		prgDialog = new ProgressDialog(this);
		prgDialog.setMessage("Lade Kompetenzen...");
		prgDialog.setCancelable(false);
		prgDialog2 = new ProgressDialog(this);
		prgDialog2.setMessage("Gelernt markieren...");
		prgDialog2.setCancelable(false);
		prgDialog3 = new ProgressDialog(this);
		prgDialog3.setMessage("Kommentar senden...");
		prgDialog3.setCancelable(false);
		ladeKompetenzen();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void ladeKompetenzen() {
		prgDialog.show();
		// Rest-Anfrage
		AsyncHttpClient client = new AsyncHttpClient();
		client.get("http://" + IP + PORT
				+ "/competences/xml/competencetree/university/all/nocache",
				new AsyncHttpResponseHandler() {
					// Rückgabecode '200'
					@Override
					public void onSuccess(String response) {
						prgDialog.hide();
						ArrayList<Kompetenz> kompetenzen = XML
								.parseXml(response);
						displayListView(kompetenzen);
					}

					// Rückgabecode nicht '200'
					@Override
					public void onFailure(int statusCode, Throwable error,
							String content) {
						prgDialog.hide();
						// Rückgabecode '404'
						if (statusCode == 404) {
							Toast.makeText(getApplicationContext(),
									"Resource nicht gefunden!",
									Toast.LENGTH_LONG).show();
						}
						// Rückgabecode '500'
						else if (statusCode == 500) {
							Toast.makeText(getApplicationContext(),
									"Serverseitiger Fehler!", Toast.LENGTH_LONG)
									.show();
						}
						// Rückgabecode nicht 404, 500
						else {
							Toast.makeText(
									getApplicationContext(),
									"Unerwarteter Fehler: Keine Internetverbindung vorhanden?",
									Toast.LENGTH_LONG).show();
						}
					}
				});
	}

	public void gelerntMarkieren(final Kompetenz kompetenz) {
		prgDialog2.show();
		String dieKompetenz = kompetenz.getName().replace(' ', '+')
				.replace("ä", "%C3%A4").replace("Ä", "%C3%84")
				.replace("ö", "%C3%B6").replace("Ö", "%C3%96")
				.replace("ü", "%C3%BC").replace("Ü", "%C3%9C")
				.replace("ß", "%C3%9F");
		// Rest-Anfrage
		AsyncHttpClient client = new AsyncHttpClient();
		client.post(
				"http://"
						+ IP
						+ PORT
						+ "/competences/json/link/create/university/derbenutzer/student/derbenutzer?competences="
						+ dieKompetenz + "&evidences=tool%2Clink",
				new AsyncHttpResponseHandler() {
					// Rückgabecode '200'
					@Override
					public void onSuccess(String response) {
						prgDialog2.hide();
						/*
						 * Toast.makeText(getApplicationContext(),
						 * kompetenz.getName() + " gelernt!",
						 * Toast.LENGTH_LONG).show();
						 */
					}

					// Rückgabecode nicht '200'
					@Override
					public void onFailure(int statusCode, Throwable error,
							String content) {
						prgDialog.hide();
						// Rückgabecode '404'
						if (statusCode == 404) {
							Toast.makeText(getApplicationContext(),
									"Resource nicht gefunden!",
									Toast.LENGTH_LONG).show();
						}
						// Rückgabecode '500'
						else if (statusCode == 500) {
							Toast.makeText(getApplicationContext(),
									"Serverseitiger Fehler!", Toast.LENGTH_LONG)
									.show();
						}
						// Rückgabecode nicht 404, 500
						else {
							Toast.makeText(
									getApplicationContext(),
									"Unerwarteter Fehler: Keine Internetverbindung vorhanden?",
									Toast.LENGTH_LONG).show();
						}
					}
				});
	}

	public void kommentarSenden(Kompetenz kompetenz, String kommentar) {
		prgDialog3.show();
		String dieKompetenz = kompetenz.getName().replace(" ", "")
				.replace("ä", "%C3%A4").replace("Ä", "%C3%84")
				.replace("ö", "%C3%B6").replace("Ö", "%C3%96")
				.replace("ü", "%C3%BC").replace("Ü", "%C3%9C")
				.replace("ß", "%C3%9F");
		String linkId = "I" + dieKompetenz + "tool";
		String derKommentar = kommentar.replace(' ', '+')
				.replace("ä", "%C3%A4").replace("Ä", "%C3%84")
				.replace("ö", "%C3%B6").replace("Ö", "%C3%96")
				.replace("ü", "%C3%BC").replace("Ü", "%C3%9C")
				.replace("ß", "%C3%9F");
		// Rest-Anfrage
		AsyncHttpClient client = new AsyncHttpClient();
		client.post("http://" + IP + PORT + "/competences/json/link/comment/"
				+ linkId + "/derbenutzer/university/student?text="
				+ derKommentar, new AsyncHttpResponseHandler() {
			// Rückgabecode '200'
			@Override
			public void onSuccess(String response) {
				prgDialog3.hide();
				/*
				 * Toast.makeText(getApplicationContext(),
				 * "Kommentar gesendet!", Toast.LENGTH_LONG).show();
				 */
			}

			// Rückgabecode nicht '200'
			@Override
			public void onFailure(int statusCode, Throwable error,
					String content) {
				prgDialog.hide();
				// Rückgabecode '404'
				if (statusCode == 404) {
					Toast.makeText(getApplicationContext(),
							"Resource nicht gefunden!", Toast.LENGTH_LONG)
							.show();
				}
				// Rückgabecode '500'
				else if (statusCode == 500) {
					Toast.makeText(getApplicationContext(),
							"Serverseitiger Fehler!", Toast.LENGTH_LONG).show();
				}
				// Rückgabecode nicht 404, 500
				else {
					Toast.makeText(
							getApplicationContext(),
							"Unerwarteter Fehler: Keine Internetverbindung vorhanden?",
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	private void displayListView(ArrayList<Kompetenz> kompetenzen) {
		// ArrayAdapter aus Array erstellen
		dataAdapter = new MyCustomAdapter(this, R.layout.kompetenz_info,
				kompetenzen);
		ListView listView = (ListView) findViewById(R.id.listView1);
		// Adapter an ListView hängen
		listView.setAdapter(dataAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Kompetenz kompetenz = (Kompetenz) parent
						.getItemAtPosition(position);
				// wenn Kompetenz gelernt, kommentieren zulassen
				if (kompetenz.isSelected()) {
					kommentarEingabe(kompetenz);
				} else {
					Toast.makeText(
							getApplicationContext(),
							"Ein Kommentar kann nur für eine gelernte Kompetenz abgegeben werden.",
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	private class MyCustomAdapter extends ArrayAdapter<Kompetenz> {
		private ArrayList<Kompetenz> kompetenzen;

		public MyCustomAdapter(Context context, int textViewResourceId,
				ArrayList<Kompetenz> kompetenzen) {
			super(context, textViewResourceId, kompetenzen);
			this.kompetenzen = new ArrayList<Kompetenz>();
			this.kompetenzen.addAll(kompetenzen);
		}

		private class ViewHolder {
			TextView text;
			CheckBox box;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			Log.v("ConvertView", String.valueOf(position));

			if (convertView == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = vi.inflate(R.layout.kompetenz_info, null);

				holder = new ViewHolder();
				holder.text = (TextView) convertView.findViewById(R.id.text1);
				holder.box = (CheckBox) convertView
						.findViewById(R.id.checkBox1);
				convertView.setTag(holder);

				holder.box.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						CheckBox cb = (CheckBox) v;
						Kompetenz kompetenz = (Kompetenz) cb.getTag();
						/*
						 * Toast.makeText( getApplicationContext(),
						 * "Clicked on Checkbox: " + kompetenz.getName() +
						 * " is " + cb.isChecked(), Toast.LENGTH_LONG).show();
						 */
						// Kompetenz als gelernt markieren (Umkehrung nicht
						// möglich)
						if (cb.isChecked()) {
							kompetenz.setSelected(true);
							gelerntMarkieren(kompetenz);
						} else {
							cb.setChecked(true);
						}
					}
				});
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			Kompetenz kompetenz = kompetenzen.get(position);
			holder.text.setText(kompetenz.getName());
			holder.box.setChecked(kompetenz.isSelected());
			holder.box.setTag(kompetenz);

			return convertView;
		}
	}

	private void kommentarEingabe(final Kompetenz kompetenz) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Kommentieren");

		// Eingabefeld
		final EditText input = new EditText(this);
		input.setInputType(InputType.TYPE_CLASS_TEXT);
		builder.setView(input);

		// Knöpfe
		builder.setPositiveButton("Senden",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String eingabe = input.getText().toString();
						/*
						 * Toast.makeText(getApplicationContext(), "Eingabe: " +
						 * eingabe, Toast.LENGTH_LONG) .show();
						 */
						// Eingabe senden
						kommentarSenden(kompetenz, eingabe);
					}
				});
		builder.setNegativeButton("Abbrechen",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});

		builder.show();
	}
}