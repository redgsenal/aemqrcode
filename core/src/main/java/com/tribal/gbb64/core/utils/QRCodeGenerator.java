package com.tribal.gbb64.core.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class QRCodeGenerator {   
	private static final int MIN_RANGE = 300;
	private static final int MAX_RANGE = 10000;
	private static final String IMAGE_TYPE = "PNG";
	
	public byte[] getQRCodeImageByteArray(String text, String w) throws WriterException, IOException {
		int d = getIntValue(w);
    	return this.getQRCodeImageByteArray(text, d, d);
    }

	public byte[] getQRCodeImageByteArray(String text) throws WriterException, IOException {
    	return this.getQRCodeImageByteArray(text, 600, 600);
    }

    public byte[] getQRCodeImageByteArray(String text, int width, int height) throws WriterException, IOException {
    	QRCodeWriter qrCodeWriter = new QRCodeWriter();
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, setLimit(width), setLimit(height)), IMAGE_TYPE, pngOutputStream);
        return pngOutputStream.toByteArray();
    }
    
    private int setLimit(int v) {
    	return (v <= 0 ) ? MIN_RANGE : (v > MAX_RANGE) ? MAX_RANGE : v;
    }
    
    private int getIntValue(String w) {
    	if (w == null || w.isEmpty()) {
    		return MIN_RANGE;
    	}
    	try {
    		return Integer.parseInt(w);
    	} catch (NumberFormatException e) {
    		e.printStackTrace();
    		return MIN_RANGE;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return MIN_RANGE;
    	}
	}
}