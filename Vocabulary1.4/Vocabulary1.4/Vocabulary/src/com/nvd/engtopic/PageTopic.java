package com.nvd.engtopic;

import java.io.IOException;

import java.util.ArrayList;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.google.android.gms.ads.InterstitialAd;
import com.nvd.adapter.AdapterVocabulary;
import com.nvd.data.dataSQLite;

import com.nvd.item.vocabulary;

import android.app.Activity;
import android.app.Dialog;
import android.app.ActionBar.LayoutParams;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;

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
	private String NAME_TABLE;

	ListView lv_vocabulary;
	Spinner spin;
	ImageView ic_game;
	ImageView ic_back_vocabulary;

	AdapterVocabulary adapter;
	ArrayList<vocabulary> Listvocabulary = new ArrayList<vocabulary>();
	InterstitialAd interstial;
	private String[] listSp;
	private dataSQLite managerdatabase;

	private InterstitialAd interstitial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_page_topic);

		AdView adview = (AdView) findViewById(R.id.adView_t);
		interstitial = new InterstitialAd(PageTopic.this);
		interstitial.setAdUnitId("ca-app-pub-1395380684132176/9369297849");
		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice("CC5F2C72DF2B356BBF0DA198").build();
		adview.loadAd(adRequest);
		interstitial.loadAd(adRequest);
		//
		showAds();
		managerdatabase = new dataSQLite(getApplicationContext());
		SharedPreferences pre = getSharedPreferences("TMP_DATA", MODE_PRIVATE);
		int sttdata = pre.getInt("STT_vertion", 1);
		if (sttdata == 1)// lần mở qpps đầu tiên
		{
			managerdatabase.doCreateDb();
			managerdatabase.doDeleteDb();

			try {
				managerdatabase.copydatabase();
				managerdatabase.UPLOAD_IMG_TABLE("");
				Toast.makeText(getApplicationContext(), "copy",
						Toast.LENGTH_SHORT).show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			SharedPreferences.Editor edit = pre.edit();
			edit.putInt("STT_vertion", 2);
			edit.commit();

		}
		AnhXa();

	}

	private class MyProcessEvent implements OnItemSelectedListener {
		// Khi có ch�?n lựa thì vào hàm này
		public void onItemSelected(AdapterView<?> arg0, View arg1,
				int position_Spinner, long arg3) {
			adapter = new AdapterVocabulary(getApplicationContext(),
					R.layout.item_vocabulary,
					managerdatabase.SELECT_LIST_WHERE_NHOM(NAME_TABLE,
							position_Spinner));
			lv_vocabulary.setAdapter(adapter);
		}

		// Nếu không ch�?n gì cả
		public void onNothingSelected(AdapterView<?> arg0) {

		}
	}

	private void AnhXa() {
		//
		Bundle extra = getIntent().getExtras();
		NAME_TABLE = extra.getString("nametb");
		listSp = getListSP(NAME_TABLE);
		//
		spin = (Spinner) findViewById(R.id.spinner1);
		ic_back_vocabulary = (ImageView) findViewById(R.id.ic_back_vocabulary);
		lv_vocabulary = (ListView) findViewById(R.id.lv_vocabulary);
		ic_game = (ImageView) findViewById(R.id.ic_game);
		// bắt sự kiện
		ic_back_vocabulary.setOnClickListener(this);
		ic_game.setOnClickListener(this);
		// database
		managerdatabase.opendatabase();
		//
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, listSp);
		adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		// Thiết lập adapter cho Spinner
		spin.setAdapter(adapter1);
		// thiết lập sự kiện ch�?n phần tử cho Spinner
		spin.setOnItemSelectedListener(new MyProcessEvent());
	}

	private String[] getListSP(String nametb) {

		if (nametb.equals("tb_family")) { // 1
			String[] list = { "Tất cả - ALL", "Trong gia đình",
					"Các mối quan hệ", };
			return list;
		} else if (nametb.equals("tb_houses")) { // 2
			String[] list = { "Tất cả - ALL", "Nhà", "Phòng khách",
					"Phòng ngủ", "Nhà bếp", "Động từ sử dụng trong bếp",
					"Phòng tắm", "Phòng em bé", "Phòng ăn", "Phòng chứa đồ" };
			return list;
		} else if (nametb.equals("tb_school")) { // 2
			String[] list = { "Tất cả - ALL", "Trong lớp h�?c",
					"Động từ dùng trong lớp học", "Phòng thí nghiệm hóa học",
					"Toán học" };
			return list;
		} else if (nametb.equals("tb_thucan")) { // 4
			String[] list = { "Tất cả - ALL", "Các loại trái cây",
					"Các loại rau củ quả", "Thịt gia súc, gia cầm , hải sản",
					"Các món ăn", "Quán ăn và quầy nước",
					"Động từ dùng trong nhà hàng", "Siêu thị",
					"Các loại chai,hộp và ti�?n" };
			return list;
		} else if (nametb.equals("tb_trangphuc")) { // 5
			String[] list = { "Tất cả - ALL", "Quần áo ngoài trời",
					"Quần áo hàng ngày", "Đồ lót và đồ ngủ",
					"Đồ trang sức và mỹ phẩm" };
			return list;
		} else if (nametb.equals("tb_congviec")) { // 1
			String[] list = { "Tất cả - ALL", "Nghề nghiệp",
					"Nông trại và chăn nuôi gia súc", "Xây dựng",
					"Trong văn phòng" };
			return list;
		} else if (nametb.equals("tb_suckhoe")) { // 5
			String[] list = { "Tất cả - ALL", "Thuốc điều trị-Phương pháp",
					"Sụ ốm đau bệnh tật", "Chắm sóc răng-khám sức khỏe", };
			return list;
		} else if (nametb.equals("tb_dongvat")) { //
			String[] list = { "Tất cả - ALL", "1", "2", "3", "4", "5", "6",
					"7", "8" };
			return list;
		} else if (nametb.equals("tb_thucvat")) { //
			String[] list = { "Tất cả - ALL", "Loài hoa - cây" };
			return list;
		} else if (nametb.equals("tb_thoitiet")) { // 6
			String[] list = { "Tất cả - ALL", "Các động từ theo mùa",
					"Mô tả về thời tiết" };
			return list;
		} else if (nametb.equals("tb_sports")) { // 6
			String[] list = { "Tất cả - ALL", "Môn thể thao đồng đội",
					"Môn thể thao cá nhân", "Động từ về thể thao" };
			return list;
		} else if (nametb.equals("tb_music")) { // 6
			String[] list = { "Tất cả - ALL", "Các loại nhạc cụ",
					"Âm nhạc,Điệu nhảy,Rạp hát" };
			return list;
		} else if (nametb.equals("tb_giaothong")) { // 3
			String[] list = { "Tất cả - ALL", "Xe tải", "Otô con", "Xe đạp",
					"Phương tiện công cộng", "Đường quốc lộ", "Hàng không",
					"Các phương tiện bay", "Hải cảng", "Du thuyền",
					"Lực lượng vũ trang" };
			return list;
		} else if (nametb.equals("tb_dialy")) { //
			String[] list = { "Tất cả - ALL", "Bản đồ thế giới",
					"Chương trình không gian", "Vũ trụ" };
			return list;
		} else if (nametb.equals("tb_houses")) { //
			String[] list = { "Tất cả - ALL", "Nhà", "Phòng khách",
					"Phòng ngủ", "Nhà bếp", "Động từ sử dụng trong bếp",
					"Phòng tắm", "Phòng em bé", "Phòng ăn", "Phòng chứa đồ" };
			return list;
		}
		String[] list = { "Tất cả - ALL" };
		return list;

	}

	//

	@Override
	public void onClick(View v) {
		if (v == ic_back_vocabulary) {

			// showAds();

			Intent inten = new Intent(getApplicationContext(),
					MainActivity.class);
			startActivity(inten);
		} else if (v == ic_game) {

		}

	}

	private void showAds() {
		interstial = new InterstitialAd(this);
		interstial.setAdUnitId("ca-app-pub-1395380684132176/7405200241");
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
