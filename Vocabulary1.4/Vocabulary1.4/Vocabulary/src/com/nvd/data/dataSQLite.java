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

	public dataSQLite(Context context) {
		super(context, "data.sqlite", null, 1);

	}

	public Cursor getData(String sql) {
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.rawQuery(sql, null);
		return c;

	}

	public void QueryData(String sql) {
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(sql);

	}
	public void UpdateVocabulary (String W_ENG, int YEUTHICH)
	{
		String sql =  "UPDATE tuvung SET YEUTHICH = "+YEUTHICH+" WHERE W_ENG = '"+W_ENG+"'";
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(sql);
		
	}
	//
	public void UpdateAudio()
	{
		SQLiteDatabase db = getWritableDatabase();
		// GIA ĐINH
		
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.father+" WHERE W_ENG = 'Father'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.mother+" WHERE W_ENG = 'Mother'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.son+" WHERE W_ENG = 'Son'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.daughter+" WHERE W_ENG = 'Daughter'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.parent+" WHERE W_ENG = 'Parent'");
		
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.husband+" WHERE W_ENG = 'Husband'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.wife+" WHERE W_ENG = 'Wife'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.brother+" WHERE W_ENG = 'Brother'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.sister+" WHERE W_ENG = 'Sister'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.uncle+" WHERE W_ENG = 'Uncle'");
		
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.aunt+" WHERE W_ENG = 'Aunt'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.nephew+" WHERE W_ENG = 'Nephew'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.niece+" WHERE W_ENG = 'Niece'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.grandmother+" WHERE W_ENG = 'Grandmother'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.grandfather+" WHERE W_ENG = 'Grandfather'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.grandparents+" WHERE W_ENG = 'Grandparents'");
		// NGHE NGHIEP
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.accountant+" WHERE W_ENG = 'Accountant'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.actor+" WHERE W_ENG = 'Actor'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.lawyer+" WHERE W_ENG = 'Lawyer'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.manager+" WHERE W_ENG = 'Manager'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.musician+" WHERE W_ENG = 'Musician'");
		
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.engineer+" WHERE W_ENG = 'Engineer'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.doctor+" WHERE W_ENG = 'Doctor'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.dentist+" WHERE W_ENG = 'Dentist'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.cashier+" WHERE W_ENG = 'Cashier'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.builder+" WHERE W_ENG = 'Builder'");
		
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.reporter+" WHERE W_ENG = 'Reporter'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.tailor+" WHERE W_ENG = 'Tailor'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.teacher+" WHERE W_ENG = 'Teacher'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.cook+" WHERE W_ENG = 'Cook'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.magician+" WHERE W_ENG = 'Magician'");
		
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.baker+" WHERE W_ENG = 'Baker'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.singer+" WHERE W_ENG = 'Singer'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.artist+" WHERE W_ENG = 'Artist'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.waiter+" WHERE W_ENG = 'Waiter'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.carpenter+" WHERE W_ENG = 'Carpenter'");
		
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.accountant+" WHERE W_ENG = 'Actor'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.nurse+" WHERE W_ENG = 'Nurse'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.secretary+" WHERE W_ENG = 'Secretary'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.gardener+" WHERE W_ENG = 'Gardener'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.vet+" WHERE W_ENG = 'Vet'");
		
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.businessman+" WHERE W_ENG = 'Businessman'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.policeman+" WHERE W_ENG = 'Policeman'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.painter+" WHERE W_ENG = 'Painter'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.hairdresser+" WHERE W_ENG = 'Hairdresser'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.dancer+" WHERE W_ENG = 'Dancer'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.farmer+" WHERE W_ENG = 'Farmer'");
		// SPORT
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.baker+" WHERE W_ENG = 'Horse'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.singer+" WHERE W_ENG = 'Soccer'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.artist+" WHERE W_ENG = 'Basketball'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.waiter+" WHERE W_ENG = 'Baseball'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.carpenter+" WHERE W_ENG = 'Tennis'");
		
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.table_tennis+" WHERE W_ENG = 'Table tennis'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.regatta+" WHERE W_ENG = 'Regatta'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.volleyball+" WHERE W_ENG = 'Volleyball'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.badminton+" WHERE W_ENG = 'Badminton'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.rugby+" WHERE W_ENG = 'Rugby'");
		
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.eurythmics+" WHERE W_ENG = 'Eurythmics'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.gymnastics+" WHERE W_ENG = 'Gymnastics'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.marathon_race+" WHERE W_ENG = 'Marathon race'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.javelin_throw+" WHERE W_ENG = 'Javelin throw'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.athletics+" WHERE W_ENG = 'Athletics'");
		
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.hurdle_rate+" WHERE W_ENG = 'Hurdle rate'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.weightliting+" WHERE W_ENG = 'Weightliting'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.wrestle+" WHERE W_ENG = 'Wrestle'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.goal+" WHERE W_ENG = 'Goal'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.swimming+" WHERE W_ENG = 'Swimming'");
		
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.boxing+" WHERE W_ENG = 'Boxing'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.archery+" WHERE W_ENG = 'Archery'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.windsurfing+" WHERE W_ENG = 'Windsurfing'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.cycling+" WHERE W_ENG = 'Cycling'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.fencing+" WHERE W_ENG = 'Fencing'");
		//TRAI CAY 
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.avocado+" WHERE W_ENG = 'Avocado'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.apple+" WHERE W_ENG = 'Apple'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.orange+" WHERE W_ENG = 'Orange'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.banana+" WHERE W_ENG = 'Banana'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.grape+" WHERE W_ENG = 'Grape'");
		
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.mango+" WHERE W_ENG = 'Mango'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.pineapple+" WHERE W_ENG = 'Pineapple'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.mangosteen+" WHERE W_ENG = 'Mangosteen'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.jackfruit+" WHERE W_ENG = 'Jackfruit'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.durian+" WHERE W_ENG = 'Durian'");
		
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.lemon+" WHERE W_ENG = 'Lemon'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.papaya+" WHERE W_ENG = 'Papaya'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.soursop+" WHERE W_ENG = 'Soursop'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.plum+" WHERE W_ENG = 'Plum'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.apricot+" WHERE W_ENG = 'Apricot'");
		
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.peach+" WHERE W_ENG = 'Peach'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.cherry+" WHERE W_ENG = 'Cherry'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.rambutan+" WHERE W_ENG = 'Rambutan'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.coconut+" WHERE W_ENG = 'Coconut'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.guava+" WHERE W_ENG = 'Guava'");
		
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.pear+" WHERE W_ENG = 'Pear'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.melon+" WHERE W_ENG = 'Melon'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.watermelon+" WHERE W_ENG = 'Watermelon'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.litchi+" WHERE W_ENG = 'litchi'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.tamarind+" WHERE W_ENG = 'Tamarind'");
		//DONG VAT 
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.cow+" WHERE W_ENG = 'Cow'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.buffalo+" WHERE W_ENG = 'Buffalo'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.goat+" WHERE W_ENG = 'Goat'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.dog+" WHERE W_ENG = 'Dog'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.cat+" WHERE W_ENG = 'Cat'");
		
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.horse+" WHERE W_ENG = 'Horse'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.pig+" WHERE W_ENG = 'Pig'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.ox+" WHERE W_ENG = 'Ox'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.camel+" WHERE W_ENG = 'Camel'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.donkey+" WHERE W_ENG = 'Donkey'");
		
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.deer+" WHERE W_ENG = 'Deer'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.lion+" WHERE W_ENG = 'Lion'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.tiger+" WHERE W_ENG = 'Tiger'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.elephant+" WHERE W_ENG = 'Elephant'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.bear+" WHERE W_ENG = 'Bear'");
		
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.hippo+" WHERE W_ENG = 'Hippo'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.kangaroo+" WHERE W_ENG = 'Kangaroo'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.rhino+" WHERE W_ENG = 'Rhino'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.fox+" WHERE W_ENG = 'Fox'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.duck+" WHERE W_ENG = 'Duck'");
		
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.penguin+" WHERE W_ENG = 'Penguin'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.wolf+" WHERE W_ENG = 'Wolf'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.monkey+" WHERE W_ENG = 'Monkey'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.cheetah+" WHERE W_ENG = 'Cheetah'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.giraffe+" WHERE W_ENG = 'Giraffe'");
		
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.zebra+" WHERE W_ENG = 'Zebra'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.bee+" WHERE W_ENG = 'Bee'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.dophin+" WHERE W_ENG = 'Dophin'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.frog+" WHERE W_ENG = 'Frog'");
		db.execSQL("UPDATE tuvung SET AUDIO = "+R.raw.rooster+" WHERE W_ENG = 'Rooster'");

	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

}
