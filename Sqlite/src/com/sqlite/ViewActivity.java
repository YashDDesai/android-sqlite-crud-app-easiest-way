package com.sqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ViewActivity extends Activity {
	
	
	EditText etname, etnumber;
	Button btnUpdate, btnDelete;
	SQLiteDatabase db;
	//String id;
	Integer id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_layout);	
		
		
		
		btnUpdate = (Button) findViewById(R.id.btnUpdate);
		btnDelete  =(Button) findViewById(R.id.btnDelete);
		etname  = (EditText) findViewById(R.id.etupname);
		etnumber  = (EditText) findViewById(R.id.etupnumber);
		
		
		
		// TODO Auto-generated method stub
		Intent i = getIntent();
		id = Integer.parseInt(i.getStringExtra("id"));
		//id = i.getStringExtra("id");
		
		db=openOrCreateDatabase("ContactsDB",SQLiteDatabase.OPEN_READWRITE, null);
		
		Cursor resultSet = db.rawQuery("Select * from contacts where id="+id,null);
		 if (resultSet != null)
		        resultSet.moveToFirst();
	       
	    etname.setText(resultSet.getString(1));
	    etnumber.setText(resultSet.getString(2));         
		
		
		//Toast.makeText(getApplicationContext(), resultSet.toString(), Toast.LENGTH_LONG).show();
		
		btnDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {						
				
				
				db.execSQL("DELETE FROM contacts WHERE id="+id);
				
				//ContentValues values=new ContentValues();
				
				//values.put("name",name_);
				//values.put("number", num_);
				
				//db.insert("contacts", null, values);
				Intent i = new Intent(getApplicationContext(), SqliteActivity.class);
				startActivity(i);

			}
		});
		
		btnUpdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {						
				
				
				//db.execSQL("UPDATE contacts set name='yash d desai', number='12131415161718' WHERE id="+id);
				db.execSQL("UPDATE contacts set name='"+etname.getText()+"', number='"+etnumber.getText()+"' WHERE id="+id);
				
				
				
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
