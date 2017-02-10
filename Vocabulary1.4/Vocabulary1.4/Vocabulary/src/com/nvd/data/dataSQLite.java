package com.nvd.data;

import com.nvd.vocabulary.R;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class dataSQLite extends SQLiteOpenHelper {
	Context context = null;
	private static final String name = "data.sqlite";
	private static final String path = "/data/data/com.nvd.vocabulary/databases/";
	SQLiteDatabase database = null;

	public dataSQLite(Context context) {
		super(context, "data.sqlite", null, 1);
		this.context = context;
	}

	public Cursor getData(String sql) {
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.rawQuery(sql, null);
		return c;

	}

	public void opendatabase() {
		String urn = path + name;
		database = SQLiteDatabase.openDatabase(urn, null,
				SQLiteDatabase.CREATE_IF_NECESSARY);
	}

	public void QueryData(String sql) {
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(sql);

	}

	public void UpdateVocabulary(String W_ENG, int YEUTHICH) {
		String sql = "UPDATE tuvung SET YEUTHICH = " + YEUTHICH
				+ " WHERE W_ENG = '" + W_ENG + "'";
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(sql);

	}

	//
	public void UPLOAD_IMG(String tb, int id, int img) {
		String sql = "UPDATE " + tb + " SET hinhanh = " + img + " WHERE id = "
				+ id + "";
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(sql);
	}

	//
	public void UPLOAD_ALL_IMG() {
		UPLOAD_IMG("job", 1, R.drawable.accountant);
		UPLOAD_IMG("job", 2, R.drawable.baker);
		UPLOAD_IMG("job", 3, R.drawable.barber);
		UPLOAD_IMG("job", 4, R.drawable.barman);
		UPLOAD_IMG("job", 5, R.drawable.builder);
		//
		UPLOAD_IMG("job", 6, R.drawable.butcher);
		UPLOAD_IMG("job", 7, R.drawable.carpenter);
		UPLOAD_IMG("job", 8, R.drawable.carpenter);
		UPLOAD_IMG("job", 9, R.drawable.chambermaid);
	}

	//

	@Override
	public void onCreate(SQLiteDatabase arg0) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

}
