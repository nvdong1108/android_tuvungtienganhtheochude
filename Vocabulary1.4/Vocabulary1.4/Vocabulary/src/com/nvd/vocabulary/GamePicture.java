package com.nvd.vocabulary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.nvd.data.dataSQLite;
import com.nvd.item.myTTS;
import com.nvd.item.vocabulary;

import android.R.array;
import android.app.Activity;
import android.app.Dialog;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GamePicture extends Activity implements OnClickListener {

	private ImageView ic_home_game;
	private String NAME_TABLE;

	private TextView txt_diem_game;
	private TextView txt_best_diem;
	private TextView txt_eng_game;

	private LinearLayout layoutgame1;
	private LinearLayout layoutgame2;
	private LinearLayout layoutgame3;
	private LinearLayout layoutgame4;
	//
	private ImageView img1_game;
	private ImageView img2_game;
	private ImageView img3_game;
	private ImageView img4_game;

	private TextView txt_vn1_game;
	private TextView txt_vn2_game;
	private TextView txt_vn3_game;
	private TextView txt_vn4_game;
	//
	private TextView txt_id_tv1_game;
	private TextView txt_id_tv2_game;
	private TextView txt_id_tv3_game;
	private TextView txt_id_tv4_game;
	//
	private SharedPreferences pre;
	private dataSQLite managerdata;
	//
	private ArrayList<vocabulary> list;
	private List<Integer> arrRd;
	private int STT_COUNT;
	//
	//
	private InterstitialAd interstitial;
	AdView adView;

	//

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_picture);
		//
		interstitial = new InterstitialAd(GamePicture.this);
		interstitial.setAdUnitId("ca-app-pub-1395380684132176/8475294247");
		adView = (AdView) this.findViewById(R.id.adView2);
		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice("CC5F2C72DF2B356BBF0DA198").build();
		adView.loadAd(adRequest);
		interstitial.loadAd(adRequest);
		//
		anhxa();
		showQuestion(arrRd.get(0));
	}

	private void anhxa() {
		STT_COUNT = 0;
		//
		ic_home_game = (ImageView) findViewById(R.id.ic_home_game);
		//
		layoutgame1 = (LinearLayout) findViewById(R.id.layout_eng1);
		layoutgame2 = (LinearLayout) findViewById(R.id.layout_eng2);
		layoutgame3 = (LinearLayout) findViewById(R.id.layout_eng3);
		layoutgame4 = (LinearLayout) findViewById(R.id.layout_eng4);

		img1_game = (ImageView) findViewById(R.id.img1_game);
		img2_game = (ImageView) findViewById(R.id.img2_game);
		img3_game = (ImageView) findViewById(R.id.img3_game);
		img4_game = (ImageView) findViewById(R.id.img4_game);

		txt_vn1_game = (TextView) findViewById(R.id.txt_vn1_game);
		txt_vn2_game = (TextView) findViewById(R.id.txt_vn2_game);
		txt_vn3_game = (TextView) findViewById(R.id.txt_vn3_game);
		txt_vn4_game = (TextView) findViewById(R.id.txt_vn4_game);

		txt_id_tv1_game = (TextView) findViewById(R.id.txt_id_tv1_game);
		txt_id_tv2_game = (TextView) findViewById(R.id.txt_id_tv2_game);
		txt_id_tv3_game = (TextView) findViewById(R.id.txt_id_tv3_game);
		txt_id_tv4_game = (TextView) findViewById(R.id.txt_id_tv4_game);

		txt_eng_game = (TextView) findViewById(R.id.txt_eng_game);
		txt_diem_game = (TextView) findViewById(R.id.txt_diem_game);
		txt_best_diem = (TextView) findViewById(R.id.txt_bestdiem);
		//
		ic_home_game.setOnClickListener(this);
		//
		layoutgame1.setOnClickListener(this);
		layoutgame2.setOnClickListener(this);
		layoutgame3.setOnClickListener(this);
		layoutgame4.setOnClickListener(this);
		//
		list = new ArrayList<vocabulary>();
		arrRd = new ArrayList<Integer>();

		pre = getSharedPreferences("tmp_data", MODE_PRIVATE);
		Bundle extra = getIntent().getExtras();
		//
		int positionLvM = extra.getInt("position");

		managerdata = new dataSQLite(getApplicationContext());
		managerdata.opendatabase();
		if (positionLvM == 0) {
			list = managerdata.SELECT_TABLE("yt");
		} else if (positionLvM == 1) {
			NAME_TABLE = "family";
			list = managerdata.SELECT_TABLE("family");
		} else if (positionLvM == 2) {
			NAME_TABLE = "job";
			list = managerdata.SELECT_TABLE("job");
		} else if (positionLvM == 3) {
			NAME_TABLE = "sport";
			list = managerdata.SELECT_TABLE("sport");
		} else if (positionLvM == 4) {
			NAME_TABLE = "house";
			list = managerdata.SELECT_TABLE("house");
		} else if (positionLvM == 5) {
			NAME_TABLE = "animals";
			list = managerdata.SELECT_TABLE("animals");
		}

		arrRd = createRandom(list.size());

		managerdata.close();
	}

	private List<Integer> createRandom(int size) {
		Random rd = new Random();
		arrRd.clear();
		int iNew = 0;
		for (int i = 0; i < size;) {
			iNew = rd.nextInt(size);
			if (!arrRd.contains(iNew)) {
				i++;
				arrRd.add(iNew);

			}
		}

		return arrRd;
	}

	int b, c, d;

	private void showdialog() {
		final Dialog dialog = new Dialog(GamePicture.this,
				R.style.My_Dialog_Theme);
		dialog.setContentView(R.layout.dialog_game_over);
		dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		dialog.show();
		//
		final String nameTABLE = list.get(arrRd.get(STT_COUNT)).getNametable();
		//

		TextView eng = (TextView) dialog.findViewById(R.id.txt_tvEng);
		TextView vn = (TextView) dialog.findViewById(R.id.txt_tvVN);
		TextView phatam = (TextView) dialog.findViewById(R.id.txt_phatam);
		ImageView hinhanh = (ImageView) dialog.findViewById(R.id.img_tv);
		eng.setText(list.get(arrRd.get(STT_COUNT)).getEng());
		vn.setText(list.get(arrRd.get(STT_COUNT)).getVn());
		phatam.setText(list.get(arrRd.get(STT_COUNT)).getPhatam());
		hinhanh.setImageResource(list.get(arrRd.get(STT_COUNT)).getHinhanh());
		//
		ImageView ic_cancel = (ImageView) dialog
				.findViewById(R.id.ic_cancel_dialog_game_over);
		//
		final ImageView ic_love_t = (ImageView) dialog
				.findViewById(R.id.ic_love_t);
		final ImageView ic_love_f = (ImageView) dialog
				.findViewById(R.id.ic_love_f);
		if (list.get(arrRd.get(STT_COUNT)).getYeuthich() == 0) {
			ic_love_t.setVisibility(View.GONE);
			ic_love_f.setVisibility(View.VISIBLE);
		} else {
			ic_love_t.setVisibility(View.VISIBLE);
			ic_love_f.setVisibility(View.GONE);
		}

		ImageView ic_speak_game = (ImageView) dialog
				.findViewById(R.id.ic_speakers_dialog_game);
		ic_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialog.dismiss();

			}
		});
		ic_love_t.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ic_love_t.setVisibility(View.GONE);
				ic_love_f.setVisibility(View.VISIBLE);
				managerdata.opendatabase();
				managerdata.UPLOAD_YEUTHICH(nameTABLE,
						list.get(arrRd.get(STT_COUNT)).getId(), 0);
				managerdata.close();

			}
		});
		ic_love_f.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ic_love_t.setVisibility(View.VISIBLE);
				ic_love_f.setVisibility(View.GONE);
				managerdata.opendatabase();
				managerdata.UPLOAD_YEUTHICH(nameTABLE,
						list.get(arrRd.get(STT_COUNT)).getId(), 1);
				managerdata.close();

			}
		});
		ic_speak_game.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String eng = "";
				eng = list.get(arrRd.get(STT_COUNT)).getEng();
				myTTS speak = new myTTS(eng, getBaseContext());

			}
		});

	}

	private void showQuestion(int stt) {

		txt_diem_game.setText(STT_COUNT + "");
		txt_best_diem.setText(pre.getInt("bestdiem", 0) + "");
		txt_eng_game.setText(list.get(stt).getEng());
		//
		Random rd = new Random();
		do {
			b = rd.nextInt(list.size());
		} while (b == stt);
		do {
			c = rd.nextInt(list.size());
		} while (c == b || c == stt);
		do {
			d = rd.nextInt(list.size());
		} while (d == b || d == c || d == stt);

		//
		List<Integer> tmpList = new ArrayList<Integer>();
		tmpList.add(stt);
		tmpList.add(b);
		tmpList.add(c);
		tmpList.add(d);
		//
		int ia, ib, ic, id;
		ia = rd.nextInt(4);
		do {
			ib = rd.nextInt(4);
		} while (ib == ia);
		do {
			ic = rd.nextInt(4);
		} while (ic == ib || ic == ia);
		do {
			id = rd.nextInt(4);
		} while (id == ia || id == ib || id == ic);

		img1_game.setImageResource(list.get(tmpList.get(ia)).getHinhanh());
		img2_game.setImageResource(list.get(tmpList.get(ib)).getHinhanh());
		img3_game.setImageResource(list.get(tmpList.get(ic)).getHinhanh());
		img4_game.setImageResource(list.get(tmpList.get(id)).getHinhanh());

		txt_vn1_game.setText(list.get(tmpList.get(ia)).getVn());
		txt_vn2_game.setText(list.get(tmpList.get(ib)).getVn());
		txt_vn3_game.setText(list.get(tmpList.get(ic)).getVn());
		txt_vn4_game.setText(list.get(tmpList.get(id)).getVn());

		txt_id_tv1_game.setText(list.get(tmpList.get(ia)).getId() + "");
		txt_id_tv2_game.setText(list.get(tmpList.get(ib)).getId() + "");
		txt_id_tv3_game.setText(list.get(tmpList.get(ic)).getId() + "");
		txt_id_tv4_game.setText(list.get(tmpList.get(id)).getId() + "");
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

	@Override
	public void onClick(View v) {

		if (v == ic_home_game) {
			Intent inten = new Intent(getApplicationContext(),
					MainActivity.class);
			startActivity(inten);
		} else if (v == layoutgame1) {
			if (list.get(arrRd.get(STT_COUNT)).getId() == Integer
					.parseInt(txt_id_tv1_game.getText().toString())) {

				STT_COUNT++;
				showQuestion(arrRd.get(STT_COUNT));
			} else {
				showdialog();
				if (STT_COUNT > pre.getInt("betsdiem", 0)) {
					SharedPreferences.Editor edit = pre.edit();
					edit.putInt("bestdiem", STT_COUNT);
					edit.commit();
				}

				STT_COUNT = 0;
				createRandom(list.size());
				showQuestion(arrRd.get(0));

			}

		} else if (v == layoutgame2) {
			if (list.get(arrRd.get(STT_COUNT)).getId() == Integer
					.parseInt(txt_id_tv2_game.getText().toString())) {

				STT_COUNT++;
				showQuestion(arrRd.get(STT_COUNT));
			} else {
				showdialog();
				if (STT_COUNT > pre.getInt("bestdiem", 0)) {
					SharedPreferences.Editor edit = pre.edit();
					edit.putInt("bestdiem", STT_COUNT);
					edit.commit();

				}
				STT_COUNT = 0;
				createRandom(list.size());
				showQuestion(arrRd.get(0));

			}

		} else if (v == layoutgame3) {
			if (list.get(arrRd.get(STT_COUNT)).getId() == Integer
					.parseInt(txt_id_tv3_game.getText().toString())) {

				STT_COUNT++;
				showQuestion(arrRd.get(STT_COUNT));
			} else {
				showdialog();
				if (STT_COUNT > pre.getInt("bestdiem", 0)) {
					SharedPreferences.Editor edit = pre.edit();
					edit.putInt("bestdiem", STT_COUNT);
					edit.commit();
				}
				STT_COUNT = 0;
				createRandom(list.size());
				showQuestion(arrRd.get(0));
			}

		} else if (v == layoutgame4) {
			if (list.get(arrRd.get(STT_COUNT)).getId() == Integer
					.parseInt(txt_id_tv4_game.getText().toString())) {
				STT_COUNT++;
				showQuestion(arrRd.get(STT_COUNT));
			} else {
				showdialog();
				if (STT_COUNT > pre.getInt("bestdiem", 0)) {
					SharedPreferences.Editor edit = pre.edit();
					edit.putInt("bestdiem", STT_COUNT);
					edit.commit();
				}

				STT_COUNT = 0;
				createRandom(list.size());
				showQuestion(arrRd.get(0));
			}

		}

	}
}
