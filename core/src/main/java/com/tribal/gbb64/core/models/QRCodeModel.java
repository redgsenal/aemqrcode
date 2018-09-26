package com.tribal.gbb64.core.models;

import java.io.IOException;

import com.adobe.cq.sightly.WCMUsePojo;
import com.google.zxing.WriterException;
import com.tribal.gbb64.core.utils.QRCodeGenerator;

public class QRCodeModel extends WCMUsePojo {

	private String url;
	private String width;
	private String base64Image;

	public QRCodeModel() {
	}

	@Override
	public void activate() throws Exception {
		url = getProperties().get("url", "");
		width = getProperties().get("width", "");
		base64Image = "";
		try {
			base64Image = (new QRCodeGenerator()).getQRCodeImageBase64Image(this.url, this.width);
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public String getBase64Image() {
		return base64Image;
	}
}
