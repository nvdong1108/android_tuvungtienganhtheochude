package com.nvd.vocabulary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import android.util.Log;
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
	private String[] listSp;

	private dataSQLite managerdatabase;
	private static final String name = "data.sqlite";
	private static final String path = "/data/data/com.nvd.vocabulary/databases/";

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

		SharedPreferences pre = getSharedPreferences("sttdata", MODE_PRIVATE);
		int sttdata = pre.getInt("STT_vertion", 0);
		if (sttdata == 3)// lần mở qpps đầu tiên
		{
			doCreateDb();
			doDeleteDb();
			try {
				copydatabase();
				Toast.makeText(getApplicationContext(), "copy",
						Toast.LENGTH_SHORT).show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			SharedPreferences.Editor edit = pre.edit();
			edit.putInt("STT_vertion", 4);
			edit.commit();
			managerdatabase = new dataSQLite(getApplicationContext());
			managerdatabase.opendatabase();
			managerdatabase.UPLOAD_ALL_IMG();
			managerdatabase.close();
		}
		//
		AnhXa();
		doCreateDb();
		Bundle extra = getIntent().getExtras();

		adapter = new AdapterVocabulary(this, R.layout.item_vocabulary,
				getListVocabulary(extra.getString("nametb")));
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

	private class MyProcessEvent implements OnItemSelectedListener {
		// Khi có chọn lựa thì vào hàm này
		public void onItemSelected(AdapterView<?> arg0, View arg1,
				int position_Spinner, long arg3) {

			Bundle extra = getIntent().getExtras();
			ArrayList<vocabulary> list2 = new ArrayList<vocabulary>();
			Cursor c = null;
			if (extra.getString("nametb").equals("yt")) {
				String[] listNametb = { "tb_congviec", "tb_school",
						"tb_giaothong" };
				for (int i = 0; i < listNametb.length; i++) {
					c = database.query(listNametb[i], null, "yeuthich=1", null,
							null, null, null);
					while (c.moveToNext()) {
						list2.add(new vocabulary(c.getInt(0), c.getString(1), c
								.getString(2), c.getString(3), c.getInt(4), c
								.getInt(5), c.getString(7)));
					}
				}

			} else {
				if (position_Spinner == 0) {
					c = database.query(extra.getString("nametb"), null, null,
							null, null, null, null);
				} else {
					c = database.query(extra.getString("nametb"), null, "nhom="
							+ position_Spinner + "", null, null, null, null);
				}
				while (c.moveToNext()) {
					list2.add(new vocabulary(c.getInt(0), c.getString(1), c
							.getString(2), c.getString(3), c.getInt(4), c
							.getInt(5), c.getString(7)));
				}
			}

			c.close();
			adapter = new AdapterVocabulary(getApplicationContext(),
					R.layout.item_vocabulary, list2);
			lv_vocabulary.setAdapter(adapter);

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

		//
		ic_back_vocabulary.setOnClickListener(this);
	}

	private ArrayList<vocabulary> getListVocabulary(String namtb) {
		ArrayList<vocabulary> list = new ArrayList<vocabulary>();
		Cursor c = null;
		listSp = getListSP(namtb);
		if (namtb.equals("yt")) {
			String[] listNametb = { "tb_congviec", "tb_school", "tb_giaothong",
					"tb_thucan", "tb_trangphuc", "tb_thoitiet", "tb_dialy" };
			for (int i = 0; i < listNametb.length; i++) {
				c = database.query(listNametb[i], null, "yeuthich=1", null,
						null, null, null);
				while (c.moveToNext()) {
					list.add(new vocabulary(c.getInt(0), c.getString(1), c
							.getString(2), c.getString(3), c.getInt(4), c
							.getInt(5), c.getString(7)));
				}
			}

		} else {
			c = database.query(namtb, null, null, null, null, null, null);

			while (c.moveToNext()) {
				list.add(new vocabulary(c.getInt(0), c.getString(1), c
						.getString(2), c.getString(3), c.getInt(4),
						c.getInt(5), c.getString(7)));
			}
		}
		c.close();
		return list;
	}

	private String[] getListSP(String nametb) {

		if (nametb.equals("tb_congviec")) { // 1
			String[] list = { "Tất cả - ALL", "Nghề nghiệp",
					"Nông trại và chăn nuôi gia súc", "Xây dựng",
					"Trong văn phòng" };
			return list;
		} else if (nametb.equals("tb_school")) { // 2
			String[] list = { "Tất cả - ALL", "Trong lớp học",
					"Động từ dùng trong lớp học", "Phòng thí nghiệm hóa học",
					"Toán học" };
			return list;
		} else if (nametb.equals("tb_giaothong")) { // 3
			String[] list = { "Tất cả - ALL", "Xe tải", "Otô con", "Xe đạp",
					"Phương tiện công cộng", "Đường quốc lộ", "Hàng không",
					"Các phương tiện bay", "Hải cảng", "Du thuyền",
					"Lực lượng vũ trang" };
			return list;
		} else if (nametb.equals("tb_thucan")) { // 4
			String[] list = { "Tất cả - ALL", "Các loại trái cây",
					"Các loại rau củ quả", "Thịt gia súc, gia cầm , hải sản",
					"Các món ăn", "Quán ăn và quầy nước",
					"Động từ dùng trong nhà hàng", "Siêu thị",
					"Các loại chai,hộp và tiền" };
			return list;
		} else if (nametb.equals("tb_trangphuc")) { // 5
			String[] list = { "Tất cả - ALL", "Quần áo ngoài trời",
					"Quần áo hàng ngày", "Đồ lót và đồ ngủ",
					"Đồ trang sức và mỹ phẩm" };
			return list;
		} else if (nametb.equals("tb_thoitiet")) { // 6
			String[] list = { "Tất cả - ALL", "Các động từ theo mùa",
					"Mô tả về thời tiết" };
			return list;
		} else if (nametb.equals("tb_dialy")) { //
			String[] list = { "Tất cả - ALL", "Bản đồ thế giới",
					"Chương trình không gian", "Vũ trụ" };
			return list;
		} else if (nametb.equals("tb_dongvat")) { //
			String[] list = { "Tất cả - ALL", "1", "2", "3", "4", "5", "6",
					"7", "8" };
			return list;
		}
		String[] list = { "Tất cả - ALL" };
		return list;

	}

	//
	public void doCreateDb() {
		database = openOrCreateDatabase("data.sqlite", MODE_PRIVATE, null);
	}

	public void doDeleteDb() {

		deleteDatabase("data.sqlite");

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
