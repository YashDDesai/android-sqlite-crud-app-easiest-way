package com.sqlite;

import java.util.ArrayList;

import android.app.Activity;
import android.app.backup.RestoreObserver;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class SqliteActivity extends Activity {
    /** Called when the activity is first created. */
	
	SQLiteDatabase db;
	
	
	ListView lv;
	Button btn, btnClearAll;
	
	ArrayList<String> contacts = new ArrayList<String>();
	ArrayList<String> ids = new ArrayList<String>();
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        db=openOrCreateDatabase("ContactsDB",SQLiteDatabase.OPEN_READWRITE, null); 
        
        lv = (ListView) findViewById(R.id.listView1);
        btn = (Button) findViewById(R.id.btnAdd);
        btnClearAll =(Button) findViewById(R.id.btnClearAll);
        
        ArrayAdapter<String> da = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contacts);
        ArrayAdapter<String> da_ids = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ids);
        
        lv.setAdapter(da);
        
       Cursor resultSet = db.rawQuery("Select * from contacts",null);
       
	    if(resultSet.moveToFirst()){
	    	do{
	    		da.add(resultSet.getString(1)+"\n"+ resultSet.getString(2));
	    		da_ids.add(resultSet.getString(0));
	    	}while(resultSet.moveToNext());
	    }
         
        
       btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i =  new Intent(getApplicationContext(), AddActivity.class);
				startActivity(i);				
			}
		});
       
       btnClearAll.setOnClickListener(new OnClickListener() {
		
    	   @Override
    	   public void onClick(View v) {
    		   // TODO Auto-generated method stub
    		   db.execSQL("DELETE from contacts");
    		   finish();
    		   startActivity(getIntent());
    	   }
       });
       
       lv.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			
			Intent i = new Intent(getApplicationContext(), ViewActivity.class);
			i.putExtra("id", ids.get(arg2));
			startActivity(i);
			//Toast.makeText(getApplicationContext(), ids.get(arg2), Toast.LENGTH_LONG).show();
			
		}
	});
    }    
    
}