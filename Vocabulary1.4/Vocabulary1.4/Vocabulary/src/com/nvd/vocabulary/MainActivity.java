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

	ListView lv_topic;
	AdapterTopic adapter;
	TextView txt1;
	TextView txt2;
	private InterstitialAd interstitial;
	AdView adView;
	//
	ArrayList<Topic> arrTopic = new ArrayList<Topic>();

	//

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		interstitial = new InterstitialAd(MainActivity.this);
		interstitial.setAdUnitId("ca-app-pub-1395380684132176/5598893041");
		adView = (AdView) this.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice("110894").build();
		adView.loadAd(adRequest);
		interstitial.loadAd(adRequest);

		//

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
		// 0
		arrTopic.add(new Topic(R.drawable.icon_family32, "Công việc")); // 1
		arrTopic.add(new Topic(R.drawable.icon_work32, "Trường học")); // 2
		arrTopic.add(new Topic(R.drawable.icon_sport32, "Giao thông")); // 3
		arrTopic.add(new Topic(R.drawable.icon_family32, "Thức ăn")); // 4
		arrTopic.add(new Topic(R.drawable.icon_work32, "Trang phuc")); // 5
		arrTopic.add(new Topic(R.drawable.icon_sport32, "Thời tiết")); // 6
		arrTopic.add(new Topic(R.drawable.icon_sport32, "Địa lý")); // 7
		arrTopic.add(new Topic(R.drawable.icon_sport32, "Động vật")); // 8

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

}
