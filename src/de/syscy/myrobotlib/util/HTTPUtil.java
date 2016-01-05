package de.syscy.myrobotlib.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import de.syscy.myrobotlib.MyRobotLib;

public class HTTPUtil {
	public static void sendGETAsync(String url) {
		sendGETAsync(url, null);
	}

	public static void sendGETAsync(String url, HTTPResponseListener listener) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				HTTPResponse response = null;

				try {
					response = HTTPUtil.sendGET(url);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				if (listener != null) {
					if (response != null) {
						listener.onResponse(response.responseCode, response.content);
					} else {
						listener.onResponse(-1, "error");
					}
				}
			}
		}).start();
	}

	public static HTTPResponse sendGET(String url) throws Exception {
		MyRobotLib.getLogger().finest("Sending GET Request to " + url);
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", "MyRobotLib");

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		StringBuffer response = new StringBuffer();

		while ((line = bufferedReader.readLine()) != null) {
			response.append(line);
		}

		bufferedReader.close();

		return new HTTPResponse(connection.getResponseCode(), response.toString());
	}

	public static void sendPOSTAsync(String url, String parameters) {
		sendPOSTAsync(url, parameters, null);
	}

	public static void sendPOSTAsync(String url, String parameters, HTTPResponseListener listener) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				HTTPResponse response = null;

				try {
					response = HTTPUtil.sendPOST(url, parameters);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				if (listener != null) {
					if (response != null) {
						listener.onResponse(response.responseCode, response.content);
					} else {
						listener.onResponse(-1, "error");
					}
				}
			}
		});
	}

	public static HTTPResponse sendPOST(String url, String parameters) throws Exception {
		MyRobotLib.getLogger().finest("Sending POST Request to " + url + " with parameters " + parameters);

		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

		connection.setUseCaches(false);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Host", urlObject.getHost());
		connection.setRequestProperty("User-Agent", "Mozilla/5.0");
		connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		connection.setRequestProperty("Connection", "keep-alive");
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.setRequestProperty("Content-Length", Integer.toString(parameters.length()));

		connection.setDoOutput(true);
		DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
		outputStream.writeBytes(parameters);
		outputStream.flush();
		outputStream.close();

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		StringBuffer response = new StringBuffer();

		while ((line = bufferedReader.readLine()) != null) {
			response.append(line);
		}

		bufferedReader.close();

		return new HTTPResponse(connection.getResponseCode(), response.toString());
	}

	public static String escapeHTML(String s) {
		StringBuilder out = new StringBuilder(Math.max(16, s.length()));

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c > 127 || c == '"' || c == '<' || c == '>' || c == '&') {
				out.append("&#");
				out.append((int) c);
				out.append(';');
			} else {
				out.append(c);
			}
		}

		return out.toString();
	}
}