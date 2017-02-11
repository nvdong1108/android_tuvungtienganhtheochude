package com.nvd.vocabulary;

import java.util.ArrayList;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.nvd.adapter.AdapterVocabulary;
import com.nvd.data.dataSQLite;
import com.nvd.data.datavocabulary;
import com.nvd.item.vocabulary;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class PageTopic extends Activity {
	ListView lv_vocabulary;
	AdapterVocabulary adapter;
	ArrayList<vocabulary> Listvocabulary = new ArrayList<vocabulary>();
	// dataSQLite db = new dataSQLite(this);
	//
	SQLiteDatabase database = null;
	private InterstitialAd interstitial;
	AdView adView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_page_topic);
		//
		interstitial = new InterstitialAd(PageTopic.this);
		interstitial.setAdUnitId("ca-app-pub-1395380684132176/1215547441");
		adView = (AdView) this.findViewById(R.id.adView2);
		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice("CC5F2C72DF2B356BBF0DA198").build();
		adView.loadAd(adRequest);
		interstitial.loadAd(adRequest);
		//
		AnhXa();
		// db.UpdateAudio();
		doCreateDb();
		Bundle extra = getIntent().getExtras();

		adapter = new AdapterVocabulary(this, R.layout.item_vocabulary,
				getListVocabulary(extra.getInt("position")));
		lv_vocabulary.setAdapter(adapter);

	}

	private void AnhXa() {
		lv_vocabulary = (ListView) findViewById(R.id.lv_vocabulary);
	}

	private ArrayList<vocabulary> getListVocabulary(int position) {
		ArrayList<vocabulary> list = new ArrayList<vocabulary>();
		Cursor c = null;

		switch (position) {
		case 0:

			c = database.query("family", null, null, null, null, null, null);
			break;
		// case 1:
		// // c = db.getData("SELECT * FROM tuvung WHERE CHUDE ='JOB'");
		// c = database.query("tuvung", null, "CHUDE ='JOB'", null, null,
		// null, null);
		// break;
		// case 2:
		// // c = db.getData("SELECT * FROM tuvung WHERE CHUDE ='SPORT'");
		// c = database.query("tuvung", null, "CHUDE ='SPORT'", null,
		// null, null, null);
		// break;
		// case 3:
		// // c = db.getData("SELECT * FROM tuvung WHERE CHUDE ='FRUITS'");
		// c = database.query("tuvung", null, "CHUDE ='FRUITS'", null,
		// null, null, null);
		// break;
		// case 4:
		// // c = db.getData("SELECT * FROM tuvung WHERE CHUDE ='ANIMAL'");
		// c = database.query("tuvung", null, "CHUDE ='ANIMAL'", null,
		// null, null, null);
		// break;
		// case 5:
		// // c = db.getData("SELECT * FROM tuvung WHERE YEUTHICH =1");
		// c = database.query("tuvung", null, "YEUTHICH =1", null, null,
		// null, null);
		// break;
		default: 
			c = database.query("job", null, null, null, null, null, null);
			break;
		}
		while (c.moveToNext()) {
			list.add(new vocabulary(c.getInt(0), c.getString(1),
					c.getString(2), c.getString(3), c.getInt(4), c.getInt(5)));
		}
		c.close();

		return list;
	}

	//
	public void doCreateDb() {
		database = openOrCreateDatabase("data.sqlite", MODE_PRIVATE, null);
	}

	public void updateLopName(String W_ENG, int YEUTHICH) {
		ContentValues values = new ContentValues();
		values.put("YEUTHICH", YEUTHICH);
		String msg = "";
		int ret = database.update("tuvung", values, "W_ENG=?",
				new String[] { W_ENG });
		if (ret == 0) {
			msg = "Failed to update";
		} else {
			msg = "updating is successful";
		}
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}

	public void loadalllop() {
		Cursor c = database.query("tbllop", null, null, null, null, null, null);
		c.moveToFirst();
		String data = "";
		while (c.isAfterLast() == false) {
			data += c.getString(0) + "-" + c.getString(1) + "-"
					+ c.getString(2);
			data += "\n";
			c.moveToNext();
		}
		Toast.makeText(this, data, Toast.LENGTH_LONG).show();
		c.close();
	}

}
