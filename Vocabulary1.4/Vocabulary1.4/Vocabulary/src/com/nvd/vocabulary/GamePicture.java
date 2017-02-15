package com.nvd.vocabulary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.nvd.data.dataSQLite;
import com.nvd.item.vocabulary;

import android.R.array;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GamePicture extends Activity implements OnClickListener {

	private LinearLayout layoutgame1;
	private LinearLayout layoutgame2;
	private LinearLayout layoutgame3;
	private LinearLayout layoutgame4;
	//
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
	private ImageView img1_game;
	private ImageView img2_game;
	private ImageView img3_game;
	private ImageView img4_game;
	//
	private TextView txt_eng_game;
	private TextView txt_diem_game;
	//
	private dataSQLite managerdata;
	private ArrayList<vocabulary> list;
	private List<Integer> arrRd;
	private int STT_COUNT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_picture);
		anhxa();
		showQuestion(arrRd.get(0));
	}

	private void anhxa() {
		STT_COUNT = 0;
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
		//
		layoutgame1.setOnClickListener(this);
		layoutgame2.setOnClickListener(this);
		layoutgame3.setOnClickListener(this);
		layoutgame4.setOnClickListener(this);
		//
		list = new ArrayList<vocabulary>();
		arrRd = new ArrayList<Integer>();
		//
		managerdata = new dataSQLite(getApplicationContext());
		managerdata.opendatabase();
		list = managerdata.SELECT_TABLE("animals");
		arrRd = createRandom(list.size());
		managerdata.close();
	}

	private List<Integer> createRandom(int size) {
		Random rd = new Random();

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

	private void showQuestion(int stt) {

		txt_diem_game.setText(STT_COUNT + "");
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
	public void onClick(View v) {

		if (v == layoutgame1) {
			if (list.get(arrRd.get(STT_COUNT)).getId() == Integer
					.parseInt(txt_id_tv1_game.getText().toString())) {

				STT_COUNT++;
				showQuestion(arrRd.get(STT_COUNT));
			} else {
				Toast.makeText(getApplicationContext(), "sai",
						Toast.LENGTH_SHORT).show();
			}

		} else if (v == layoutgame2) {
			if (list.get(arrRd.get(STT_COUNT)).getId() == Integer
					.parseInt(txt_id_tv2_game.getText().toString())) {

				STT_COUNT++;
				showQuestion(arrRd.get(STT_COUNT));
			} else {
				Toast.makeText(getApplicationContext(), "sai",
						Toast.LENGTH_SHORT).show();
			}

		} else if (v == layoutgame3) {
			if (list.get(arrRd.get(STT_COUNT)).getId() == Integer
					.parseInt(txt_id_tv3_game.getText().toString())) {

				STT_COUNT++;
				showQuestion(arrRd.get(STT_COUNT));
			} else {
				Toast.makeText(getApplicationContext(), "sai",
						Toast.LENGTH_SHORT).show();
			}

		} else if (v == layoutgame4) {
			if (list.get(arrRd.get(STT_COUNT)).getId() == Integer
					.parseInt(txt_id_tv4_game.getText().toString())) {
				STT_COUNT++;
				showQuestion(arrRd.get(STT_COUNT));
			} else {
				Toast.makeText(getApplicationContext(), "sai",
						Toast.LENGTH_SHORT).show();
			}

		}

	}
}
