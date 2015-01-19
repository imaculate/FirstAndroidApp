package com.example.yourhealthyourrules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class List extends Activity {
	ListView listv;
	int type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		Intent intent = getIntent();
		Bundle b = intent.getExtras();
		type = b.getInt("typeList");
		

		listv = (ListView) findViewById(R.id.listview1);
		try {
			listv.setAdapter(new ListAdapter(List.this,
					android.R.layout.simple_list_item_1, getArray()));
			
		} catch (IOException e) {
			Log.e("Fav", "Couldnt get a favourts list");
		}

	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		switch (item.getItemId()) {
		
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void onBackPressed(){
		finish();
	}

	public ArrayList<Item> getArray() throws IOException {
		
		ArrayList<Item> ret = new ArrayList<Item>();

		ArrayList<String> arr = new ArrayList<String>();

		ArrayList<String> arrF = new ArrayList<String>();

		switch (type) {
		case R.id.beforeid: {
			System.out.println("Getting before array");
			arr = new ArrayList<String>(Arrays.asList(getResources()
					.getStringArray(R.array.beforearray)));
			arrF = new ArrayList<String>(Arrays.asList(getResources()
					.getStringArray(R.array.beforefiles)));
			break;
		}

		case R.id.afterid: {
			arr = new ArrayList<String>(Arrays.asList(getResources()
					.getStringArray(R.array.afterarray)));
			arrF = new ArrayList<String>(Arrays.asList(getResources()
					.getStringArray(R.array.afterfiles)));
			break;

		}

		case R.id.duringid: {
			arr = new ArrayList<String>(Arrays.asList(getResources()
					.getStringArray(R.array.duringarray)));
			arrF = new ArrayList<String>(Arrays.asList(getResources()
					.getStringArray(R.array.duringfiles)));
			break;
		}

		case 1111: {
			SqliteHelper help = new SqliteHelper(this);
			Cursor AllFavs = help.getFavourites();

			AllFavs.moveToFirst();

			while (!AllFavs.isAfterLast()) {

				String Name = AllFavs.getString(1);

				String file = AllFavs.getString(2);

				AllFavs.moveToNext();
				ret.add(new Item(Name, file));

			}
		
			return ret;
		}

		}
		//System.out.println(arr.size() + " hoping its the right array");
		for (int i = 0; i < arr.size(); i++) {
			Item item = new Item(arr.get(i), arrF.get(i));
			ret.add(item);

		}

		return ret;
	}

	private class ListAdapter extends ArrayAdapter<Item> { // --CloneChangeRequired
		private ArrayList<Item> mList; // --CloneChangeRequired
	

		public ListAdapter(Context context, int textViewResourceId,
				ArrayList<Item> list) { // --CloneChangeRequired
			super(context, textViewResourceId, list);
			this.mList = list;
			
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			try {
				if (view == null) {
					LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					view = vi.inflate(R.layout.list_item, null); // --CloneChangeRequired(list_item)
				}
				final Item listItem = (Item) mList.get(position); // --CloneChangeRequired
				if (listItem != null) {
					//System.out.println("list item is not null");
					// setting list_item views
					((TextView) view.findViewById(R.id.title)).setText(listItem
							.getName());
					view.setOnClickListener(new OnClickListener() {
						public void onClick(View v) { // --clickOnListItem
							Intent myIntent = new Intent(List.this,
									Message.class);
							myIntent.putExtra("NAME", listItem.getName());
							myIntent.putExtra("FILE", listItem.getFile());
							startActivity(myIntent);
							
						}
					});
				}
			} catch (Exception e) {
				Log.i("List.ListAdapter.CLASS", e.getMessage());
			}
			return view;
		}
	}

}
