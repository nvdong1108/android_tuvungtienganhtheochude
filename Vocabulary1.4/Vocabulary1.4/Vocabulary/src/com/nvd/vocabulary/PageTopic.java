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
import android.content.SharedPreferences;
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
		SharedPreferences pre = getSharedPreferences("name_table", MODE_PRIVATE);
		SharedPreferences.Editor edit = pre.edit();

		switch (position) {
		case 0:
			//
			edit.putString("name", "yt");
			edit.commit();
			//
			c = database.query("family", null, "yeuthich=1", null, null, null,
					null);
			while (c.moveToNext()) {
				list.add(new vocabulary(c.getInt(0), c.getString(1), c
						.getString(2), c.getString(3), c.getInt(4),
						c.getInt(5), c.getString(6)));
			}
			c = database.query("job", null, "yeuthich=1", null, null, null,
					null);
			while (c.moveToNext()) {
				list.add(new vocabulary(c.getInt(0), c.getString(1), c
						.getString(2), c.getString(3), c.getInt(4),
						c.getInt(5), c.getString(6)));
			}
			c = database.query("sport", null, "yeuthich=1", null, null, null,
					null);
			while (c.moveToNext()) {
				list.add(new vocabulary(c.getInt(0), c.getString(1), c
						.getString(2), c.getString(3), c.getInt(4),
						c.getInt(5), c.getString(6)));
			}
			c = database.query("home", null, "yeuthich=1", null, null, null,
					null);
			while (c.moveToNext()) {
				list.add(new vocabulary(c.getInt(0), c.getString(1), c
						.getString(2), c.getString(3), c.getInt(4),
						c.getInt(5), c.getString(6)));
			}
			c = database.query("animals", null, "yeuthich=1", null, null, null,
					null);
			while (c.moveToNext()) {
				list.add(new vocabulary(c.getInt(0), c.getString(1), c
						.getString(2), c.getString(3), c.getInt(4),
						c.getInt(5), c.getString(6)));
			}

			c.close();

			return list;

		case 1:

			edit.putString("name", "family");
			edit.commit();
			c = database.query("family", null, null, null, null, null, null);
			break;
		case 2:

			edit.putString("name", "job");
			edit.commit();
			c = database.query("job", null, null, null, null, null, null);
			break;
		case 3:
			edit.putString("name", "sport");
			edit.commit();
			c = database.query("sport", null, null, null, null, null, null);
			break;
		case 4:
			edit.putString("name", "home");
			edit.commit();
			c = database.query("home", null, null, null, null, null, null);
			break;
		case 5:
			edit.putString("name", "animals");
			edit.commit();
			c = database.query("animals", null, null, null, null, null, null);
			break;

		default:
			c = database.query("job", null, null, null, null, null, null);
			break;
		}
		while (c.moveToNext()) {
			list.add(new vocabulary(c.getInt(0), c.getString(1),
					c.getString(2), c.getString(3), c.getInt(4), c.getInt(5),c.getString(6)));
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
