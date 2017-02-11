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
		UPLOAD_IMG("job", 10, R.drawable.chef);
		//
		UPLOAD_IMG("job", 11, R.drawable.cleaner);
		UPLOAD_IMG("job", 12, R.drawable.dentist);
		UPLOAD_IMG("job", 13, R.drawable.photographer);
		UPLOAD_IMG("job", 14, R.drawable.doctor);
		UPLOAD_IMG("job", 15, R.drawable.electrician);
		//
		UPLOAD_IMG("job", 16, R.drawable.engineer);
		UPLOAD_IMG("job", 17, R.drawable.fireman);
		UPLOAD_IMG("job", 18, R.drawable.fishmonger);
		UPLOAD_IMG("job", 19, R.drawable.flight_attendant);
		UPLOAD_IMG("job", 20, R.drawable.hairdresser);
		//
		UPLOAD_IMG("job", 21, R.drawable.judge);
		UPLOAD_IMG("job", 22, R.drawable.lawyer);
		UPLOAD_IMG("job", 23, R.drawable.nurse);
		UPLOAD_IMG("job", 24, R.drawable.optician);
		UPLOAD_IMG("job", 25, R.drawable.painter);
		//
		UPLOAD_IMG("job", 26, R.drawable.plumber);
		UPLOAD_IMG("job", 27, R.drawable.policeman);
		UPLOAD_IMG("job", 28, R.drawable.porter);
		UPLOAD_IMG("job", 29, R.drawable.receptionist);
		UPLOAD_IMG("job", 30, R.drawable.reporter);
		//
		UPLOAD_IMG("job", 31, R.drawable.sales_assistant);
		UPLOAD_IMG("job", 32, R.drawable.sales_representative);
		UPLOAD_IMG("job", 33, R.drawable.scientist);
		UPLOAD_IMG("job", 34, R.drawable.secretary);
		UPLOAD_IMG("job", 35, R.drawable.surgeon);
		//
		UPLOAD_IMG("job", 36, R.drawable.tailor);
		UPLOAD_IMG("job", 37, R.drawable.teacher);
		UPLOAD_IMG("job", 38, R.drawable.technician);
		UPLOAD_IMG("job", 39, R.drawable.vet);
		UPLOAD_IMG("job", 40, R.drawable.waiter);
		//
		UPLOAD_IMG("job", 41, R.drawable.welder);
		//
		// table family
		UPLOAD_IMG("family", 1, R.drawable.family);
		UPLOAD_IMG("family", 2, R.drawable.ancestor);
		UPLOAD_IMG("family", 3, R.drawable.great_grandparent);
		UPLOAD_IMG("family", 4, R.drawable.great_grandfather);
		UPLOAD_IMG("family", 5, R.drawable.great_grandmother);
		//
		UPLOAD_IMG("family", 6, R.drawable.grandfather);
		UPLOAD_IMG("family", 7, R.drawable.grandmother);
		UPLOAD_IMG("family", 8, R.drawable.great_uncle);
		UPLOAD_IMG("family", 9, R.drawable.great_aunt);
		UPLOAD_IMG("family", 10, R.drawable.parent);
		//
		UPLOAD_IMG("family", 11, R.drawable.grandparent);
		UPLOAD_IMG("family", 12, R.drawable.father);
		UPLOAD_IMG("family", 13, R.drawable.mother);
		UPLOAD_IMG("family", 14, R.drawable.uncle);
		UPLOAD_IMG("family", 15, R.drawable.uncle);
		//
		UPLOAD_IMG("family", 16, R.drawable.aunt);
		UPLOAD_IMG("family", 17, R.drawable.cousin);
		UPLOAD_IMG("family", 18, R.drawable.sister);
		UPLOAD_IMG("family", 19, R.drawable.brother);
		UPLOAD_IMG("family", 20, R.drawable.sister_in_law);
		//
		UPLOAD_IMG("family", 21, R.drawable.brother_in_law);
		UPLOAD_IMG("family", 22, R.drawable.mother_in_law);
		UPLOAD_IMG("family", 23, R.drawable.father_in_law);
		UPLOAD_IMG("family", 24, R.drawable.son);
		UPLOAD_IMG("family", 25, R.drawable.daughter);
		//
		UPLOAD_IMG("family", 26, R.drawable.nephew);
		UPLOAD_IMG("family", 27, R.drawable.niece);
		UPLOAD_IMG("family", 28, R.drawable.grandson);
		UPLOAD_IMG("family", 29, R.drawable.granddaughter);
		UPLOAD_IMG("family", 30, R.drawable.godfather);
		//
		UPLOAD_IMG("family", 31, R.drawable.adopted_child);
		UPLOAD_IMG("family", 32, R.drawable.half_sister);
		UPLOAD_IMG("family", 33, R.drawable.half_brother);
		UPLOAD_IMG("family", 34, R.drawable.step_father);
		UPLOAD_IMG("family", 35, R.drawable.step_mother);
		//
		UPLOAD_IMG("family", 36, R.drawable.fosterling);
		UPLOAD_IMG("family", 37, R.drawable.orphan);
		// UPLOAD_IMG("family", 8, R.drawable.great_uncle);
		// UPLOAD_IMG("family", 9, R.drawable.great_aunt);
		// UPLOAD_IMG("family", 10, R.drawable.parent);

	}

	//

	@Override
	public void onCreate(SQLiteDatabase arg0) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

}
