package de.syscy.myrobotlib.util;

public interface HTTPResponseListener {
	public void onResponse(int responseCode, String response);
}