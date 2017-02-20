package com.nvd.adapter;

import java.util.List;

import com.nvd.data.dataSQLite;
import com.nvd.item.Topic;

import com.nvd.vocabulary.GamePicture;
import com.nvd.vocabulary.PageTopic;
import com.nvd.vocabulary.R;

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
		// chuyền dũ liều vào
		//
		LinearLayout hoctu = (LinearLayout) view
				.findViewById(R.id.layout_hoctu);
		hoctu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent inten = new Intent(context, PageTopic.class);
				inten.putExtra("nametb", getNameTable(position));
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
					if (managerdatabase.GET_SIZE_LIST() < 5) {
						Toast.makeText(getContext(),
								"Số từ vụng yêu thích phải lớn hơn 4",
								Toast.LENGTH_LONG).show();
					} else {
						Intent inten = new Intent(context, GamePicture.class);
						inten.putExtra("position", position);
						context.startActivity(inten);
					}
					managerdatabase.close();

				} else {
					Intent inten = new Intent(context, GamePicture.class);
					inten.putExtra("position", position);
					context.startActivity(inten);
				}

			}
		});
		txt_name_topic.setText(topic.getNameTopic());
		return view;
	}

	public String getNameTable(int position) {
		//
		if (position == 1) {
			return "tb_congviec";
		} else if (position == 2) {
			return "tb_school";
		} else if (position == 3) {
			return "tb_giaothong";
		} else if (position == 4) {
			return "tb_thucan";
		} else if (position == 5) {
			return "tb_trangphuc";
		} else if (position == 6) {
			return "tb_thoitiet";
		} else if (position == 7) {
			return "tb_dialy";
		} else if (position == 8) {
			return "tb_dongvat";
		} else if (position == 9) {
			return "tb_congviec";
		} else if (position == 10) {
			return "tb_congviec";
		} else {
			return "yt";
		}

	}
}
