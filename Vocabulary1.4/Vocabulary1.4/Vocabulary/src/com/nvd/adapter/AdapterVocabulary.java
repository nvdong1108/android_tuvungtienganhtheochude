package com.nvd.adapter;

import java.util.List;

import com.nvd.data.dataSQLite;
import com.nvd.data.datavocabulary;
import com.nvd.item.vocabulary;
import com.nvd.vocabulary.PageTopic;
import com.nvd.vocabulary.R;

import android.content.Context;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterVocabulary extends ArrayAdapter<vocabulary> {
	Context context = null;
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final int i = position;
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
		TextView txtWordEnglish = (TextView) view.findViewById(R.id.txt_tvEng);
		TextView txtWordVietNam = (TextView) view.findViewById(R.id.txt_tvVN);
		ImageView icon_music = (ImageView) view.findViewById(R.id.ic_speakers);

		final ImageView icon_yeuthich_true = (ImageView) view
				.findViewById(R.id.ic_yeuthich_true);
		final ImageView icon_yeuthich_false = (ImageView) view
				.findViewById(R.id.ic_yeuthich_false);
		//
		txtWordVietNam.setTypeface(facev); //
		txtWordVietNam.setTypeface(facee); //
		MediaPlayer media = new MediaPlayer();

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

			}
		});
		// bỏ chọn yêu thích
		icon_yeuthich_true.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				icon_yeuthich_true.setVisibility(View.GONE);
				icon_yeuthich_false.setVisibility(View.VISIBLE);
				// String ENG = data.get(i).getWordEnglish();
				// db.UpdateVocabulary(ENG, 0);

			}
		});
		// chọn yêu thích
		icon_yeuthich_false.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				icon_yeuthich_true.setVisibility(View.VISIBLE);
				icon_yeuthich_false.setVisibility(View.GONE);
				//
				// String ENG = data.get(i).getWordEnglish();

				// db.UpdateVocabulary(ENG, 1);

			}
		});

		return view;
	}

}
