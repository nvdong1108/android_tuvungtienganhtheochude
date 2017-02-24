package com.nvd.adapter;

import java.util.List;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.nvd.data.dataSQLite;

import com.nvd.item.myTTS;
import com.nvd.item.vocabulary;

import com.nvd.tuvungtienganh.PageTopic;
import com.nvd.tuvungtienganh.R;

import android.content.Context;

import android.graphics.Typeface;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterVocabulary extends ArrayAdapter<vocabulary> {
	Context context = null;
	InterstitialAd interstial;
	int resID;
	List<vocabulary> data;
	dataSQLite db = new dataSQLite(getContext().getApplicationContext());
	PageTopic tp = new PageTopic();

	public AdapterVocabulary(Context c, int resource, List<vocabulary> objects) {
		super(c, resource, objects);
		this.context = c;
		this.resID = resource;
		this.data = objects;
	}

	private ImageView hinhanh;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(resID, parent, false);
		//
		Typeface facev = Typeface.createFromAsset(context.getAssets(),
				"fonts/TIMESBD.TTF"); // tạo kiểu chữ tiếng việt
		Typeface facee = Typeface.createFromAsset(context.getAssets(),
				"fonts/TIMESI.TTF"); // tạo kiểu chữ tiếng anh
		//

		vocabulary v = data.get(position);
		// anh xa
		final TextView txtWordEnglish = (TextView) view
				.findViewById(R.id.txt_tvEng);
		TextView txtWordVietNam = (TextView) view.findViewById(R.id.txt_tvVN);
		ImageView icon_music = (ImageView) view.findViewById(R.id.ic_speakers);
		hinhanh = (ImageView) view.findViewById(R.id.img_tv);

		final ImageView icon_yeuthich_true = (ImageView) view
				.findViewById(R.id.ic_yeuthich_true);
		final ImageView icon_yeuthich_false = (ImageView) view
				.findViewById(R.id.ic_yeuthich_false);
		final int id = v.getId();
		final String nametable = v.getNametable();
		//
		txtWordVietNam.setTypeface(facev); //
		txtWordVietNam.setTypeface(facee); //
		//

		hinhanh.setImageResource(v.getHinhanh());
		txtWordEnglish.setText(v.getEng());
		txtWordVietNam.setText(v.getVn());

		if (v.getYeuthich() == 1) {
			icon_yeuthich_true.setVisibility(View.VISIBLE);
			icon_yeuthich_false.setVisibility(View.GONE);
		} else {
			icon_yeuthich_true.setVisibility(View.GONE);
			icon_yeuthich_false.setVisibility(View.VISIBLE);
		}

		// bắt sự kiện
		icon_music.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showAds();
				//
				myTTS speak = new myTTS(txtWordEnglish.getText().toString(),
						context);
			}
		});
		// bỏ chọn yêu thích
		icon_yeuthich_true.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//
				// showAds();
				//

				icon_yeuthich_true.setVisibility(View.GONE);
				icon_yeuthich_false.setVisibility(View.VISIBLE);
				db.opendatabase();
				db.UPLOAD_YEUTHICH(nametable, id, 0);
				db.close();

			}
		});
		// chọn yêu thích
		icon_yeuthich_false.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// showAds();
				//
				icon_yeuthich_true.setVisibility(View.VISIBLE);
				icon_yeuthich_false.setVisibility(View.GONE);
				db.opendatabase();

				db.UPLOAD_YEUTHICH(nametable, id, 1);
				db.close();
			}
		});

		return view;
	}

	private void showAds() {
		interstial = new InterstitialAd(context);
		interstial.setAdUnitId("ca-app-pub-1395380684132176/6140289849");
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
}
