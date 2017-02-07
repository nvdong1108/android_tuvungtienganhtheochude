package com.nvd.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class datavocabulary extends SQLiteOpenHelper{
	private static final String name = "data.sqlite";
	private static final String path = "/data/data/com.nvd.vocabulary/databases/";
	private Context mContext;
	public datavocabulary(Context context) {
		super(context, name, null, 1);
		this.mContext = context ;
	}
	
	public void copydatabase() throws IOException {
		
		if(!checkDataBase())
		{
			OutputStream myOutput = new FileOutputStream(path + name);
	        byte[] buffer = new byte[1024];
	        int length;
	        InputStream myInput = mContext.getAssets().open("data.sqlite");
	        while ((length = myInput.read(buffer)) > 0) {
	            myOutput.write(buffer, 0, length);
	        }
	        myInput.close();
	        myOutput.flush();
	        myOutput.close();
	        
	        Toast.makeText(mContext, "Đã Copy", Toast.LENGTH_LONG).show();
		}
		else 
		Toast.makeText(mContext, "Khong Copy", Toast.LENGTH_LONG).show();
        
    }
	//
	
	private boolean checkDataBase() 
    { 
        File dbFile = new File(path + name);
        Log.v("dbFile", dbFile + "   "+ dbFile.exists()); 
        return dbFile.exists(); 
    }
	//
	
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	

}
