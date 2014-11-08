package com.example.skrewskinny;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class List extends Activity {
	ListView listv;
	String type;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		listv = (ListView)findViewById(listview1);
		listv.setAdapter(new ListAdapter(BeforeList.this,android.R.layout.simple_list_item_1, getArray()));
		Intent intent = getIntent();
		Bundle b = intent.getExtras(); 
		type = b.getString("typeList");
		
	}
	
	public ArrayList<Item> getArray(){
		ArrayList<Item> ret;
		
		ArrayList<String> arr;
		ArrayList<String> arrF;
		
		switch(type){
			case "beforeid":
			{
					arr = getResources().getStringArray(R.array.beforearray);
					arrF = getResources().getStringArray(R.array.beforefiles);
			}
			
			case "afterid":
			{
				arr = getResources().getStringArray(R.array.afterarray);
				arrF = getResources().getStringArray(R.array.afterfiles);
				
			}
			
			case "duringid":
			{
				arr = getResources().getStringArray(R.array.duringarray);
				arrF = getResources().getStringArray(R.array.duringfiles);
			}
			
			
			
			
		}
			
			for(int i=0; i<arr.size(); i++ ){
				Item item = new Item(arr.get(i), arrF.get(i));
				ret.add(item);
				
			}
		
		
		return ret;
	}
	
	
	private class ListAdapter extends ArrayAdapter<Item> { //--CloneChangeRequired
        private ArrayList<Item> mList; //--CloneChangeRequired
        private Context mContext;
 
        public ListAdapter(Context context, int textViewResourceId,
                ArrayList<Item> list) { //--CloneChangeRequired
            super(context, textViewResourceId, list);
            this.mList = list;
            this.mContext = context;
        }
 
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            try {
                if (view == null) {
                    LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = vi.inflate(R.layout.list_item, null); //--CloneChangeRequired(list_item)
                }
                final Item listItem = (Item) mList.get(position); //--CloneChangeRequired
                if (listItem != null) {
                    // setting list_item views
                    ((TextView) view.findViewById(R.id.title)).setText(listItem.getName());
                    view.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) { //--clickOnListItem
                            Intent myIntent = new Intent(List.this, Message.class);
                            myIntent.putExtra("NAME", listItem.getName());
                            myIntent.putExtra("FILE", listItem.getFile());
                            startActivity(myIntent);
                            finish();
                        }
                    });
                }
            } catch (Exception e) {
                Log.i(List.ListAdapter.CLASS.toString(), e.getMessage());
            }
            return view;
        }
    }

}
