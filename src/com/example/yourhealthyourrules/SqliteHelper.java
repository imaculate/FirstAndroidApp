package com.example.yourhealthyourrules;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "MyFavourites";

	public SqliteHelper(Context context) {

		super(context, DATABASE_NAME, null, 1);

	}

	@Override
	public void onCreate(SQLiteDatabase database) {

		database.execSQL("CREATE TABLE favourites (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, file TEXT);");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS favourites");

		onCreate(db);

	}

	public void addFavourite(String title, String file)

	{
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues(2);

		values.put("title", title);

		values.put("file", file);

		db.insert("favourites", "title", values);
		
	}
	
	public Cursor getFavourites()

	{
		
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from favourites",
				null);
		
		return cursor;

	}
	public boolean containsFile(String filename){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cur1=db.rawQuery("select * from favourites where file=$filename", null);
		
	     
	      if(cur1.getCount()>0)
	      {
	    	  return true;
	          //course id not present

	      }
	      else
	      {
	    	  return false;
	        //course id present
	      }
	}

}
