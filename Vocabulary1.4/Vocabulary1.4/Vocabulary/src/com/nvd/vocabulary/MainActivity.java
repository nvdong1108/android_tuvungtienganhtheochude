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
import com.nvd.data.datavocabulary;
import com.nvd.item.Topic;
import com.nvd.item.vocabulary;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {
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
		interstitial.setAdUnitId("ca-app-pub-1395380684132176/1215547441");
		adView = (AdView) this.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice("CC5F2C72DF2B356BBF0DA198").build();
		adView.loadAd(adRequest);
		interstitial.loadAd(adRequest);
		AnhXa();
		KhaiBao();
		//
		SharedPreferences pre = getSharedPreferences("sttdata", MODE_PRIVATE);
		sttdata = pre.getBoolean("sttdata", true);
		if (sttdata)// lần mở qpps đầu tiên
		{
			doCreateDb();
			doDeleteDb();
			SharedPreferences.Editor edit = pre.edit();
			edit.putBoolean("sttdata", false);
			edit.commit();
		}

		try {
			copydatabase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// try {
		// db.copydatabase();
		//
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		adapter = new AdapterTopic(this, R.layout.item_topic, arrTopic);
		lv_topic.setAdapter(adapter);
		lv_topic.setOnItemClickListener(this);
	}

	//
	public void doDeleteDb() {
		String msg = "";
		if (deleteDatabase("data.sqlite") == true) {
			msg = "Delete database  is successful";
		} else {
			msg = "Delete database is failed";
		}
		// Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}

	private void AnhXa() {
		Typeface facev = Typeface.createFromAsset(getAssets(),
				"fonts/ANTQUAB.TTF"); // tạo kiểu chữ tiếng việt
		Typeface facee = Typeface.createFromAsset(getAssets(),
				"fonts/ANTQUAI.TTF"); // tạo kiểu chữ tiếng anh
		lv_topic = (ListView) findViewById(R.id.lv_topic);
		txt1 = (TextView) findViewById(R.id.txt_heder1);
		txt2 = (TextView) findViewById(R.id.txt_heder2);
		txt1.setTypeface(facev);
		txt2.setTypeface(facee);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		Intent nextPage = new Intent(this, PageTopic.class);
		nextPage.putExtra("position", position);
		startActivity(nextPage);
	}

	private void KhaiBao() {
		//
		arrTopic.add(new Topic(R.drawable.icon_family32, "Gia Đình"));
		arrTopic.add(new Topic(R.drawable.icon_work32, "Nghề nghiệp"));
		arrTopic.add(new Topic(R.drawable.icon_sport32, "Thể Thao"));
		arrTopic.add(new Topic(R.drawable.icon_fruit48, "Trái Cây"));
		arrTopic.add(new Topic(R.drawable.icon_animals42, "Động Vật"));
		arrTopic.add(new Topic(R.drawable.icon_star48, "Yêu Thích"));
		managerdatabase = new dataSQLite(getApplicationContext());
		managerdatabase.opendatabase();
		managerdatabase.UPLOAD_ALL_IMG();
		managerdatabase.close();

	}

	//
	public void copydatabase() throws IOException {
		// Toast.makeText(this, "Đã đến hàm copy", Toast.LENGTH_LONG).show();
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

			// Toast.makeText(this, "Đã Copy", Toast.LENGTH_LONG).show();
		}
		// else
		// Toast.makeText(this, "Khong Copy", Toast.LENGTH_LONG).show();

	}

	//

	private boolean checkDataBase() {
		File dbFile = new File(path + name);
		Log.v("dbFile", dbFile + "   " + dbFile.exists());
		return dbFile.exists();
	}

	public void doCreateDb() {
		database = openOrCreateDatabase("data.sqlite", MODE_PRIVATE, null);
	}

}
