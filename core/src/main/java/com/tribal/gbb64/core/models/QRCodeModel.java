package com.tribal.gbb64.core.models;

import com.adobe.cq.sightly.WCMUsePojo;

public class QRCodeModel extends WCMUsePojo {

	private String url;
	private String width;

	public QRCodeModel() {
	}

	@Override
	public void activate() throws Exception {
		url = getProperties().get("url", "");
		width = getProperties().get("width", "");
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String w) {
		this.width = w;
	}
}
