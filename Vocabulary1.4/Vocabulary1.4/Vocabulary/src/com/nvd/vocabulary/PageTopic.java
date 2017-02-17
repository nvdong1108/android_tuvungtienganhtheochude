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
import android.app.Dialog;
import android.app.ActionBar.LayoutParams;
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
		interstitial.setAdUnitId("ca-app-pub-1395380684132176/8475294247");
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

				if (position_Spinner == 0) {

					c = database.query("house", null, null, null, null, null,
							null);

				} else if (position_Spinner == 1) {

					c = database.query("house", null, "nhom='utility-room'",
							null, null, null, null);

				} else if (position_Spinner == 2) {

					c = database.query("house", null, "nhom='the-den'", null,
							null, null, null);

				} else if (position_Spinner == 3) {

					c = database.query("house", null, "nhom='kitchen'", null,
							null, null, null);

				} else if (position_Spinner == 4) {

					c = database.query("house", null, "nhom='house'", null,
							null, null, null);

				} else if (position_Spinner == 5) {

					c = database.query("house", null, "nhom='dinning-room'",
							null, null, null, null);

				} else if (position_Spinner == 6) {

					c = database.query("house", null, "nhom='bathroom'", null,
							null, null, null);

				} else if (position_Spinner == 7) {

					c = database.query("house", null, "nhom='Bedroom'", null,
							null, null, null);

				}

				while (c.moveToNext()) {
					list2.add(new vocabulary(c.getInt(0), c.getString(1), c
							.getString(2), c.getString(3), c.getInt(4), c
							.getInt(5), c.getString(6)));
				}
				c.close();

				adapter = new AdapterVocabulary(getApplicationContext(),
						R.layout.item_vocabulary, list2);
				lv_vocabulary.setAdapter(adapter);
			} else if (extra.getInt("position") == 5) {
				if (position_Spinner == 1) {
					c = database.query("animals2", null,
							"nhom='african_nimals'", null, null, null, null);
				} else if (position_Spinner == 2) {
					c = database.query("animals2", null, "nhom='birds'", null,
							null, null, null);
				} else if (position_Spinner == 3) {
					c = database.query("animals2", null, "nhom='farm-animals'",
							null, null, null, null);
				} else if (position_Spinner == 4) {
					c = database.query("animals2", null, "nhom='insects'",
							null, null, null, null);
				} else if (position_Spinner == 5) {
					c = database.query("animals2", null, "nhom='mammals'",
							null, null, null, null);
				} else if (position_Spinner == 6) {
					c = database.query("animals2", null, "nhom='pets'", null,
							null, null, null);
				} else if (position_Spinner == 7) {
					c = database.query("animals2", null,
							"nhom='reptiles-amphibians'", null, null, null,
							null);
				} else if (position_Spinner == 8) {
					c = database.query("animals2", null, "nhom='sea-animals'",
							null, null, null, null);
				} else if (position_Spinner == 0) {
					c = database.query("animals2", null, null, null, null,
							null, null);
				}
				while (c.moveToNext()) {
					list2.add(new vocabulary(c.getInt(0), c.getString(1), c
							.getString(2), c.getString(3), c.getInt(4), c
							.getInt(5), c.getString(6)));
				}
				c.close();
				adapter = new AdapterVocabulary(getApplicationContext(),
						R.layout.item_vocabulary, list2);
				lv_vocabulary.setAdapter(adapter);

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
			listSp.add("All");
			listSp.add("Utility room");
			listSp.add("The den");
			listSp.add("Kitchen");
			listSp.add("House");
			listSp.add("dinning room ");
			listSp.add("Bathroom");
			listSp.add("Bedroom");
			c = database.query("house", null, null, null, null, null, null);

			while (c.moveToNext()) {
				list.add(new vocabulary(c.getInt(0), c.getString(1), c
						.getString(2), c.getString(3), c.getInt(4),
						c.getInt(5), c.getString(6)));
			}
			c.close();

			break;
		case 5:
			listSp.add("All");
			listSp.add("Afican nimals");
			listSp.add("Birds");
			listSp.add("Farm animals");
			listSp.add("Insects");
			listSp.add("Mammals");
			listSp.add("Pets");
			listSp.add("Reptiles amphibians");
			listSp.add("Sea animals");
			c = database.query("animals2", null, null, null, null, null, null);
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

	@Override
	public void onBackPressed() {

		final Dialog dialo = new Dialog(this, R.style.My_Dialog_Theme);
		dialo.setCancelable(false);
		dialo.setCanceledOnTouchOutside(false);
		dialo.setContentView(R.layout.dialog_back);
		dialo.getWindow().setLayout(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		dialo.show();
		//
		Button btb_co = (Button) dialo.findViewById(R.id.btb_co);
		Button btb_khong = (Button) dialo.findViewById(R.id.btb_khong);
		btb_co.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Khoi tao lai Activity main
				// Intent intent = new Intent(getApplicationContext(),
				// MainActivity.class);
				// startActivity(intent);
				// Tao su kien ket thuc app
				Intent startMain = new Intent(Intent.ACTION_MAIN);
				startMain.addCategory(Intent.CATEGORY_HOME);
				startActivity(startMain);
				finish();

			}
		});
		btb_khong.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				dialo.dismiss();
			}
		});

	}
}
