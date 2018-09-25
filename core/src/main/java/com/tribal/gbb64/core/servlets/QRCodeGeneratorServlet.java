/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.tribal.gbb64.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

import com.google.zxing.WriterException;
import com.tribal.gbb64.core.utils.QRCodeGenerator;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */
@Component(service=Servlet.class,
           property={
                   Constants.SERVICE_DESCRIPTION + "=QR Code GeneratorServlet"
                   ,"sling.servlet.paths=/bin/gbb64/qrcodegenerator"
                   ,"sling.servlet.methods=" + HttpConstants.METHOD_GET
                   /*,"sling.servlet.resourceTypes="+ "GBB64/components/structure/page"
                   ,"sling.servlet.extensions=" + "html"*/
           })
public class QRCodeGeneratorServlet extends SlingSafeMethodsServlet {


    /**
	 * 
	 */
	private static final String CONTENT_TYPE = "image/png";
	private static final String ENCODE_TYPE = "UTF-8";
	private static final String PARAM_NAME_TO_CODE = "s";
	private static final String PARAM_IMAGE_SIZE = "w";
	
	private static final long serialVersionUID = 4359231384382447644L;

	@Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws ServletException, IOException {
		String s = req.getParameter(PARAM_NAME_TO_CODE);
		String w = req.getParameter(PARAM_IMAGE_SIZE);
		if (s != null && !s.isEmpty()) {
			try {
				resp.setContentType(CONTENT_TYPE);
				//resp.setHeader("Content-Disposition", "attachment; filename=qrcode.png");
				resp.getOutputStream().write((new QRCodeGenerator()).getQRCodeImageByteArray(decode(s), decode(w)));
				resp.getOutputStream().flush();
				resp.getOutputStream().close();
			} catch (WriterException e) {
				e.printStackTrace();
			}
		}		
    }
	
	private String decode(String s) {
		if (s == null || s.isEmpty())
			return "";
		try {
			return URLDecoder.decode(s, ENCODE_TYPE);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
