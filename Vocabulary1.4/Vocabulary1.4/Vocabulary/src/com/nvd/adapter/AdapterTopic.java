package com.nvd.adapter;

import java.util.List;

import com.nvd.data.dataSQLite;
import com.nvd.item.Topic;

import com.nvd.tuvungtienganh.GamePicture;
import com.nvd.tuvungtienganh.PageTopic;
import com.nvd.tuvungtienganh.R;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AdapterTopic extends ArrayAdapter<Topic> {
	Context context = null;
	int resID;
	List<Topic> data;

	public AdapterTopic(Context c, int resource, List<Topic> objects) {
		super(c, resource, objects);
		this.context = c;
		this.resID = resource;
		this.data = objects;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(resID, parent, false);
		Topic topic = data.get(position);
		// ánh xạ

		TextView txt_name_topic = (TextView) view
				.findViewById(R.id.txt_name_topic);
		TextView txt_best_diem = (TextView) view.findViewById(R.id.bestdiem_tb);
		txt_best_diem.setText("Best " + topic.getBestdiem());
		txt_name_topic.setText(topic.getNameTopic());

		final String[] arrTB = { "yt", "tb_family", "tb_houses", "tb_school",
				"tb_thucan", "tb_trangphuc", "tb_congviec", "tb_suckhoe",
				"tb_dongvat", "tb_thucvat", "tb_thoitiet", "tb_sports",
				"tb_music", "tb_giaothong", "tb_dialy" };

		// chuyền dũ liều vào
		//
		LinearLayout hoctu = (LinearLayout) view
				.findViewById(R.id.layout_hoctu);
		hoctu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent inten = new Intent(context, PageTopic.class);
				inten.putExtra("nametb", arrTB[position]);
				context.startActivity(inten);
			}
		});
		LinearLayout choigame = (LinearLayout) view
				.findViewById(R.id.layout_choigame);
		choigame.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (position == 0) {
					dataSQLite managerdatabase = new dataSQLite(getContext());
					managerdatabase.opendatabase();
					if (managerdatabase.GET_SIZE_LIST_YT() < 5) {
						Toast.makeText(getContext(),
								"Số từ vụng yêu thích phải lớn hơn 4",
								Toast.LENGTH_LONG).show();
					} else {

						Intent inten = new Intent(context, GamePicture.class);
						inten.putExtra("nametb", "yt");
						context.startActivity(inten);
					}
					managerdatabase.close();

				} else {
					Intent inten = new Intent(context, GamePicture.class);
					inten.putExtra("nametb", arrTB[position]);
					context.startActivity(inten);
				}

			}
		});

		return view;
	}

}
