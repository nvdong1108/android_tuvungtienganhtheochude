package com.nvd.engtopic;

import java.util.ArrayList;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.nvd.adapter.AdapterTopic;
import com.nvd.item.Topic;

import android.app.Activity;
import android.app.Dialog;
import android.app.ActionBar.LayoutParams;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
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
	//

	//
	ArrayList<Topic> arrTopic = new ArrayList<Topic>();

	//
	private InterstitialAd interstitial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		AdView adview = (AdView) findViewById(R.id.adView_M);
		interstitial = new InterstitialAd(MainActivity.this);
		interstitial.setAdUnitId("ca-app-pub-1395380684132176/7892564640");
		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice("CC5F2C72DF2B356BBF0DA198").build();
		adview.loadAd(adRequest);
		interstitial.loadAd(adRequest);

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
		SharedPreferences pre = getSharedPreferences("TMP_DATA", MODE_PRIVATE);

		String[] arrtopic = { "Mục yêu thích", "Gia đình", "Ngôi nhà",
				"Trường học", "Thức ăn", "Trang phục", "Công việc", "Sức khỏe",
				"Động vật", "Thực vật", "Thời tiết", "Thể thảo", "Âm nhạc",
				"Giao thông", "Địa lý" };
		String[] arrTB = { "yt", "tb_family", "tb_houses", "tb_school",
				"tb_thucan", "tb_trangphuc", "tb_congviec", "tb_suckhoe",
				"tb_dongvat", "tb_thucvat", "tb_thoitiet", "tb_sports",
				"tb_music", "tb_giaothong", "tb_dialy" };

		for (int i = 0; i < arrtopic.length; i++)
			arrTopic.add(new Topic(pre.getInt("BEST_DIEM_" + arrTB[i], 0),
					arrtopic[i]));

	}

	InterstitialAd interstial;

	private void showAds() {
		interstial = new InterstitialAd(this);
		interstial.setAdUnitId("ca-app-pub-1395380684132176/4663556640");
		AdRequest adreques1 = new AdRequest.Builder().build();
		interstial.loadAd(adreques1);
		interstial.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				super.onAdLoaded();
				interstial.show();
			}
		});
	}

	@Override
	public void onBackPressed() {

		showAds();

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
