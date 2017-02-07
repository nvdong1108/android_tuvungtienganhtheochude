package com.nvd.adapter;

import java.util.List;

import com.nvd.item.Topic;
import com.nvd.vocabulary.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterTopic extends ArrayAdapter<Topic> {
	Context context = null;
	int resID;
	List<Topic> data;

	public AdapterTopic(Context c, int resource, List<Topic> objects) {
		super(c, resource, objects);
		this.context = c ;
		this.resID = resource ; 
		this.data= objects;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(resID, parent,false);
		Topic topic = data.get(position);
		// ánh xạ 
		ImageView icon_topic = (ImageView)view.findViewById(R.id.icon_topic);
		TextView txt_name_topic = (TextView)view.findViewById(R.id.txt_name_topic);
		// chuyền dũ liều vào 
		icon_topic.setImageResource(topic.getIcon());
		txt_name_topic.setText(topic.getNameTopic());
		return view;
	}

}
