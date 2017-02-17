package com.nvd.vocabulary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import com.nvd.adapter.AdapterTopic;
import com.nvd.data.dataSQLite;

import com.nvd.item.Topic;

import android.app.Activity;
import android.app.Dialog;
import android.app.ActionBar.LayoutParams;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private boolean sttdata = true;
	ListView lv_topic;
	AdapterTopic adapter;
	TextView txt1;
	TextView txt2;
	private InterstitialAd interstitial;
	AdView adView;
	//
	ArrayList<Topic> arrTopic = new ArrayList<Topic>();
	//
	private static final String name = "data.sqlite";
	private static final String path = "/data/data/com.nvd.vocabulary/databases/";
	SQLiteDatabase database = null;
	private dataSQLite managerdatabase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		interstitial = new InterstitialAd(MainActivity.this);
		interstitial.setAdUnitId("ca-app-pub-1395380684132176/8475294247");
		adView = (AdView) this.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice("CC5F2C72DF2B356BBF0DA198").build();
		adView.loadAd(adRequest);
		interstitial.loadAd(adRequest);

		//
		SharedPreferences pre = getSharedPreferences("sttdata", MODE_PRIVATE);
		sttdata = pre.getBoolean("sttdata", true);
		if (true)// lần mở qpps đầu tiên
		{
			doCreateDb();
			doDeleteDb();
			try {
				copydatabase();
			} catch (IOException e) {
				e.printStackTrace();
			}
			SharedPreferences.Editor edit = pre.edit();
			edit.putBoolean("sttdata", false);
			edit.commit();
			managerdatabase = new dataSQLite(getApplicationContext());
			managerdatabase.opendatabase();
			managerdatabase.UPLOAD_ALL_IMG();
			managerdatabase.close();
		}

		KhaiBao();
		//
		adapter = new AdapterTopic(this, R.layout.item_topic, arrTopic);
		lv_topic.setAdapter(adapter);

	}

	private void KhaiBao() {
		Typeface facev = Typeface.createFromAsset(getAssets(),
				"fonts/ANTQUAB.TTF"); // tạo kiểu chữ tiếng việt
		Typeface facee = Typeface.createFromAsset(getAssets(),
				"fonts/ANTQUAI.TTF"); // tạo kiểu chữ tiếng anh
		lv_topic = (ListView) findViewById(R.id.lv_topic);
		txt1 = (TextView) findViewById(R.id.txt_heder1);
		txt2 = (TextView) findViewById(R.id.txt_heder2);
		txt1.setTypeface(facev);
		txt2.setTypeface(facee);
		//
		arrTopic.add(new Topic(R.drawable.icon_star48, "Từ vụng yêu thích"));
		//
		arrTopic.add(new Topic(R.drawable.icon_family32,
				"Xưng hô trong gia gình")); // 0
		arrTopic.add(new Topic(R.drawable.icon_work32, "Tên nghề nghiệp")); // 1
		arrTopic.add(new Topic(R.drawable.icon_sport32, "Tên môn thể thao")); // 2
		arrTopic.add(new Topic(R.drawable.icon_fruit48, "Vật dụng trong nhà")); // 3
		arrTopic.add(new Topic(R.drawable.icon_animals42, "Tên loại động vật")); // 4

	}

	//
	public void copydatabase() throws IOException {
		if (!checkDataBase()) {
			OutputStream myOutput = new FileOutputStream(path + name);

			byte[] buffer = new byte[1024];
			int length;
			InputStream myInput = this.getAssets().open("data.sqlite");

			while ((length = myInput.read(buffer)) > 0) {
				myOutput.write(buffer, 0, length);
			}
			myInput.close();
			myOutput.flush();
			myOutput.close();
		}
	}

	private boolean checkDataBase() {
		File dbFile = new File(path + name);
		Log.v("dbFile", dbFile + "   " + dbFile.exists());
		return dbFile.exists();
	}

	public void doCreateDb() {
		database = openOrCreateDatabase("data.sqlite", MODE_PRIVATE, null);
	}

	@Override
	public void onBackPressed() {

		final Dialog dialog = new Dialog(this, R.style.My_Dialog_Theme);
		dialog.setCancelable(false);
		dialog.setCanceledOnTouchOutside(false);
		dialog.setContentView(R.layout.dialog_back);
		dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		dialog.show();
		//
		Button btb_co = (Button) dialog.findViewById(R.id.btb_co);
		Button btb_khong = (Button) dialog.findViewById(R.id.btb_khong);
		btb_co.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				finish();

			}
		});
		btb_khong.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				dialog.dismiss();
			}
		});
	}

	public void doDeleteDb() {

		deleteDatabase("data.sqlite");

	}
}
