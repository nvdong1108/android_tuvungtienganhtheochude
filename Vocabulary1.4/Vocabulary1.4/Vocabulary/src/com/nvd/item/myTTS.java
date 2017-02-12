package com.nvd.item;

import java.util.Locale;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;



public class myTTS implements OnInitListener{
	private TextToSpeech mTts;
	private String message;
	public myTTS(String message, Context context){
		this.message = message;
		mTts = new TextToSpeech(context, this);
		mTts.setLanguage(Locale.UK);
	}
	@Override
	 public void onInit(int i)
    {
    	  if (i==mTts.SUCCESS)
    	    {
    		  mTts.speak(message, TextToSpeech.QUEUE_FLUSH, null);
    	    }
    }
}
