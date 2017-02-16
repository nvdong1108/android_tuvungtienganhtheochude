package com.nvd.vocabulary;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.nvd.adapter.AdapterVocabulary;
import com.nvd.data.dataSQLite;
import com.nvd.data.datavocabulary;
import com.nvd.item.vocabulary;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class PageTopic extends Activity implements OnClickListener {
	private ImageView ic_back_vocabulary;
	//
	ListView lv_vocabulary;
	ImageView ic_game;
	AdapterVocabulary adapter;
	ArrayList<vocabulary> Listvocabulary = new ArrayList<vocabulary>();
	// dataSQLite db = new dataSQLite(this);
	//

	SQLiteDatabase database = null;

	//
	private InterstitialAd interstitial;
	AdView adView;
	//
	private List<String> listSp;
	private int position_toolsbar;

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
		doCreateDb();
		Bundle extra = getIntent().getExtras();

		adapter = new AdapterVocabulary(this, R.layout.item_vocabulary,
				getListVocabulary(extra.getInt("position")));
		lv_vocabulary.setAdapter(adapter);
		// bắt sự kiện
		ic_game.setOnClickListener(this);

		//
		// Lấy đối tượng Spinner ra
		Spinner spin = (Spinner) findViewById(R.id.spinner1);
		// Gán Data source (arr) vào Adapter
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, listSp);
		// phải gọi lệnh này để hiển thị danh sách cho Spinner
		adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		// Thiết lập adapter cho Spinner
		spin.setAdapter(adapter1);
		// thiết lập sự kiện chọn phần tử cho Spinner
		spin.setOnItemSelectedListener(new MyProcessEvent());
		//

	}

	private class MyProcessEvent implements OnItemSelectedListener {
		// Khi có chọn lựa thì vào hàm này
		public void onItemSelected(AdapterView<?> arg0, View arg1,
				int position_Spinner, long arg3) {

			Bundle extra = getIntent().getExtras();
			ArrayList<vocabulary> list2 = new ArrayList<vocabulary>();
			Cursor c = null;
			if (extra.getInt("position") == 1) {

			} else if (extra.getInt("position") == 2) {

			} else if (extra.getInt("position") == 3) {

			} else if (extra.getInt("position") == 4) {
				// vat dung trong nha
				// listSp.add("Phòng khách");
				// listSp.add("Nhà bếp");
				// listSp.add("Phòng ngủ");
				// listSp.add("Buồng đựng đồ");
				// listSp.add("Nhà vệ sinh");
				if (position_Spinner == 0) {

					c = database.query("kitchen", null, null, null, null, null,
							null);
					while (c.moveToNext()) {
						list2.add(new vocabulary(c.getInt(0), c.getString(1), c
								.getString(2), c.getString(3), c.getInt(4), c
								.getInt(5), c.getString(6)));
					}
					c = database.query("bedroom", null, null, null, null, null,
							null);
					while (c.moveToNext()) {
						list2.add(new vocabulary(c.getInt(0), c.getString(1), c
								.getString(2), c.getString(3), c.getInt(4), c
								.getInt(5), c.getString(6)));
					}
					c = database.query("utilitytool", null, null, null, null,
							null, null);
					while (c.moveToNext()) {
						list2.add(new vocabulary(c.getInt(0), c.getString(1), c
								.getString(2), c.getString(3), c.getInt(4), c
								.getInt(5), c.getString(6)));
					}
					c = database.query("bathroom", null, null, null, null,
							null, null);
					while (c.moveToNext()) {
						list2.add(new vocabulary(c.getInt(0), c.getString(1), c
								.getString(2), c.getString(3), c.getInt(4), c
								.getInt(5), c.getString(6)));
					}
					c.close();

				} else if (position_Spinner == 1) {

					c = database.query("kitchen", null, null, null, null, null,
							null);

				} else if (position_Spinner == 2) {

					c = database.query("bedroom", null, null, null, null, null,
							null);

				} else if (position_Spinner == 3) {

					c = database.query("utilitytool", null, null, null, null,
							null, null);

				} else if (position_Spinner == 4) {

					c = database.query("bathroom", null, null, null, null,
							null, null);

				}

				if (position_Spinner != 0) {
					while (c.moveToNext()) {
						list2.add(new vocabulary(c.getInt(0), c.getString(1), c
								.getString(2), c.getString(3), c.getInt(4), c
								.getInt(5), c.getString(6)));
					}
					c.close();
				}
				adapter = new AdapterVocabulary(getApplicationContext(),
						R.layout.item_vocabulary, list2);
				lv_vocabulary.setAdapter(adapter);
			} else if (extra.getInt("position") == 5) {

			} else {

			}
			//

		}

		// Nếu không chọn gì cả
		public void onNothingSelected(AdapterView<?> arg0) {

		}
	}

	private void AnhXa() {
		ic_back_vocabulary = (ImageView) findViewById(R.id.ic_back_vocabulary);

		lv_vocabulary = (ListView) findViewById(R.id.lv_vocabulary);
		ic_game = (ImageView) findViewById(R.id.ic_game);
		//
		listSp = new ArrayList<String>();
		//
		ic_back_vocabulary.setOnClickListener(this);
	}

	private ArrayList<vocabulary> getListVocabulary(int position) {
		ArrayList<vocabulary> list = new ArrayList<vocabulary>();
		Cursor c = null;

		switch (position) {
		case 0:
			//
			listSp.add("Tất cả");
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
			c = database.query("bedroom", null, "yeuthich=1", null, null, null,
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
			listSp.add("Tất cả");
			c = database.query("family", null, null, null, null, null, null);
			break;
		case 2:
			listSp.add("Tất cả");
			c = database.query("job", null, null, null, null, null, null);
			break;
		case 3:
			listSp.add("Tất cả");
			c = database.query("sport", null, null, null, null, null, null);
			break;
		case 4:
			listSp.add("Tất cả");
			listSp.add("Nhà bếp");
			listSp.add("Phòng ngủ");
			listSp.add("Buồng đựng đồ");
			listSp.add("Nhà vệ sinh");
			if (true) {
				c = database.query("kitchen", null, null, null, null, null,
						null);
				while (c.moveToNext()) {
					list.add(new vocabulary(c.getInt(0), c.getString(1), c
							.getString(2), c.getString(3), c.getInt(4), c
							.getInt(5), c.getString(6)));
				}
				c = database.query("bedroom", null, null, null, null, null,
						null);
				while (c.moveToNext()) {
					list.add(new vocabulary(c.getInt(0), c.getString(1), c
							.getString(2), c.getString(3), c.getInt(4), c
							.getInt(5), c.getString(6)));
				}
				c = database.query("utilitytool", null, null, null, null, null,
						null);
				while (c.moveToNext()) {
					list.add(new vocabulary(c.getInt(0), c.getString(1), c
							.getString(2), c.getString(3), c.getInt(4), c
							.getInt(5), c.getString(6)));
				}
				c = database.query("bathroom", null, null, null, null, null,
						null);
				while (c.moveToNext()) {
					list.add(new vocabulary(c.getInt(0), c.getString(1), c
							.getString(2), c.getString(3), c.getInt(4), c
							.getInt(5), c.getString(6)));
				}
				c.close();
				return list;
			} else {

				c = database.query("bedroom", null, null, null, null, null,
						null);
			}

			break;
		case 5:
			listSp.add("Tất cả");
			c = database.query("animals", null, null, null, null, null, null);
			break;

		default:
			c = database.query("job", null, null, null, null, null, null);
			break;
		}
		while (c.moveToNext()) {
			list.add(new vocabulary(c.getInt(0), c.getString(1),
					c.getString(2), c.getString(3), c.getInt(4), c.getInt(5), c
							.getString(6)));
		}
		c.close();

		return list;
	}

	//
	public void doCreateDb() {
		database = openOrCreateDatabase("data.sqlite", MODE_PRIVATE, null);
	}

	@Override
	public void onClick(View v) {
		if (v == ic_back_vocabulary) {
			Intent inten = new Intent(getApplicationContext(),
					MainActivity.class);
			startActivity(inten);
		} else if (v == ic_game) {

		}

	}
}
