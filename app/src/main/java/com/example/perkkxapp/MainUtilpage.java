package com.example.perkkxapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainUtilpage extends Activity {
	// Declare Variables
	JSONObject jsonobject;
	JSONArray jsonarray;
	ListView listview;
	ListViewAdapter adapter;
	ProgressDialog mProgressDialog;
	ArrayList<Catalouge_json> arraylist;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from listview_main.xml
		setContentView(R.layout.listview_main);
		// Execute DownloadJSON AsyncTask
		new DownloadJSON().execute();
	}

	// DownloadJSON AsyncTask
	private class DownloadJSON extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressdialog
			mProgressDialog = new ProgressDialog(MainUtilpage.this);
			// Set progressdialog title
			mProgressDialog.setTitle("please wait");
			// Set progressdialog message
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			// Show progressdialog
			mProgressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// Create an array
			arraylist = new ArrayList<Catalouge_json>();
			// Retrieve JSON Objects from the given URL address
			jsonobject = JSONfunctions
					.getJSONfromURL("http://jlabs.co/api.php");

			try {
				// Locate the array name in JSON
				jsonarray = jsonobject.getJSONArray("data");

				for (int i = 0; i < jsonarray.length(); i++)
                {

                    Catalouge_json catalouge_json=new Catalouge_json();

					jsonobject = jsonarray.getJSONObject(i);
					// Retrive JSON Objects
//					map.put("rank", jsonobject.getString("rank"));
//					map.put("country", jsonobject.getString("country"));
//					map.put("population", jsonobject.getString("population"));
//					map.put("flag", jsonobject.getString("flag"));
//					// Set the JSON Objects into the array
//					arraylist.add(map);

                    catalouge_json.rating.value=jsonobject.getString(catalouge_json.rating.getKey());
                    catalouge_json.close_time.value=jsonobject.getString(catalouge_json.close_time.getKey());
                    catalouge_json.desc2.value=jsonobject.getString(catalouge_json.desc2.getKey());
                    catalouge_json.deal.value=jsonobject.getString(catalouge_json.deal.getKey());
                    catalouge_json.other_offers.value=jsonobject.getString(catalouge_json.other_offers.getKey());
                    catalouge_json.cat.value=jsonobject.getString(catalouge_json.cat.getKey());
                    catalouge_json.phone.value=jsonobject.getString(catalouge_json.phone.getKey());
                    catalouge_json.open_time.value=jsonobject.getString(catalouge_json.open_time.getKey());
                    catalouge_json.vendor_name.value=jsonobject.getString(catalouge_json.vendor_name.getKey());
                    catalouge_json.type.value=jsonobject.getString(catalouge_json.type.getKey());
                    catalouge_json.desc.value=jsonobject.getString(catalouge_json.desc.getKey());
                    catalouge_json.followers.value=jsonobject.getString(catalouge_json.followers.getKey());
                    catalouge_json.img.value=jsonobject.getString(catalouge_json.img.getKey());
                    catalouge_json.price.value=jsonobject.getString(catalouge_json.price.getKey());
                   // catalouge_json.reservation.value=jsonobject.getString(catalouge_json.reservation.getKey());
                    catalouge_json.rcode.value=jsonobject.getString(catalouge_json.rcode.getKey());




                    //address class
                    JSONObject jsonobject_address=   jsonobject.getJSONObject(catalouge_json.address.getKey());
                    catalouge_json.address.getValue().lat.value=   jsonobject_address.getString(catalouge_json.address.getValue().lat.getKey());
                    catalouge_json.address.getValue().text.value=   jsonobject_address.getString(catalouge_json.address.getValue().text.getKey());
                    catalouge_json.address.getValue().lng.value=   jsonobject_address.getString(catalouge_json.address.getValue().lng.getKey());

                    //icons class
                    JSONObject jsonobject_icons=   jsonobject.getJSONObject(catalouge_json.icons.getKey());
                    catalouge_json.icons.getValue().cards.value=   jsonobject_icons.getString(catalouge_json.icons.getValue().cards.getKey());
                    catalouge_json.icons.getValue().delivery.value=   jsonobject_icons.getString(catalouge_json.icons.getValue().delivery.getKey());
                    catalouge_json.icons.getValue().drinks.value=   jsonobject_icons.getString(catalouge_json.icons.getValue().drinks.getKey());
                    catalouge_json.icons.getValue().outside.value=   jsonobject_icons.getString(catalouge_json.icons.getValue().outside.getKey());
                    catalouge_json.icons.getValue().parking.value=   jsonobject_icons.getString(catalouge_json.icons.getValue().parking.getKey());
                    catalouge_json.icons.getValue().smoking.value=   jsonobject_icons.getString(catalouge_json.icons.getValue().smoking.getKey());
                    catalouge_json.icons.getValue().wifi.value=   jsonobject_icons.getString(catalouge_json.icons.getValue().wifi.getKey());
                    catalouge_json.icons.getValue().veg.value=jsonobject_icons.getString(catalouge_json.icons.getValue().veg.getKey());




                    System.out.println("enter into aaraylist");
                    System.out.println(catalouge_json.rcode.value);

                    arraylist.add(catalouge_json);

                    //cuisine array

                    JSONArray jsonarray_cuisine=   jsonobject.getJSONArray(catalouge_json.cuisine.getKey());
                    for (int j=0; j<jsonarray_cuisine.length();j++)
                    {
                        catalouge_json.cuisine.getValue().add(jsonarray_cuisine.getString(j));
                    }


                    //menu array

                    JSONArray jsonarray_menu=   jsonobject.getJSONArray(catalouge_json.menu.getKey());
                    for (int j=0; j<jsonarray_menu.length();j++)
                    {
                        catalouge_json.menu.getValue().add(jsonarray_menu.getString(j));
                    }
                    //pics array

                    JSONArray jsonarray_pics=   jsonobject.getJSONArray(catalouge_json.pics.getKey());
                    for (int j=0; j<jsonarray_pics.length();j++)
                    {
                        catalouge_json.pics.getValue().add(jsonarray_pics.getString(j));
                    }
				}
			} catch (JSONException e) {
				Log.e("Error", e.getMessage());
                System.out.println("enter into Error");
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void args) {
			// Locate the listview in listview_main.xml
			listview = (ListView) findViewById(R.id.listview);
			// Pass the results into ListViewAdapter.java
			adapter = new ListViewAdapter(MainUtilpage.this, arraylist);
			// Set the adapter to the ListView
			listview.setAdapter(adapter);
			// Close the progressdialog
			mProgressDialog.dismiss();
		}
	}
}