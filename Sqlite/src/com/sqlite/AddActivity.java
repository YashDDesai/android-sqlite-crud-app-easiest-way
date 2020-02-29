package com.sqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends Activity {
	
	EditText etname, etnumber;
	Button btnAddFinal;
	SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_layout);	
		
		
		etname = (EditText) findViewById(R.id.etname);
		etnumber = (EditText) findViewById(R.id.etnumber);
		btnAddFinal = (Button) findViewById(R.id.btnAddfinal);
		
		
		
		btnAddFinal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				db=openOrCreateDatabase("ContactsDB",SQLiteDatabase.OPEN_READWRITE, null);
				//db=openOrCreateDatabase("ContactsDB",SQLiteDatabase.CREATE_IF_NECESSARY, null);
				db.execSQL("CREATE TABLE IF NOT EXISTS contacts( " +
		    				"id INTEGER PRIMARY KEY AUTOINCREMENT," +
		    				"name TEXT," +
		    				"number TEXT)");
				
				
				// TODO Auto-generated method stub
				String name_, num_;
				name_ = etname.getText().toString();
				num_ = etnumber.getText().toString();
				db.execSQL("insert into  contacts (name, number) values('"+name_+"', '"+num_+"')");
				
				//ContentValues values=new ContentValues();
				
				//values.put("name",name_);
				//values.put("number", num_);
				
				//db.insert("contacts", null, values);
				
				Intent i = new Intent(getApplicationContext(), SqliteActivity.class);
				startActivity(i);
				

			}
		});
		
		
		
		
		
	}

}
