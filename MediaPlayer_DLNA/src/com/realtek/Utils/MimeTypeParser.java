package com.realtek.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.res.XmlResourceParser;
import android.util.Log;

public class MimeTypeParser {

	private static final String LOG_TAG = "MimeTypeParser";

	public static final String TAG_MIMETYPES = "MimeTypes";
	public static final String TAG_TYPE = "type";
	
	public static final String ATTR_EXTENSION = "extension";
	public static final String ATTR_MIMETYPE = "mimetype";
	
	private XmlPullParser mXpp;
	private MimeTypes mMimeTypes;
    
	public MimeTypeParser() {
	}
	
	public MimeTypes fromXml(InputStream in)
			throws XmlPullParserException, IOException {
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

		mXpp = factory.newPullParser();
		mXpp.setInput(new InputStreamReader(in));

		return parse();
	}
	
	public MimeTypes fromXmlResource(XmlResourceParser in)
	throws XmlPullParserException, IOException {
		mXpp = in;
		
		return parse();
	}

	public MimeTypes parse()
			throws XmlPullParserException, IOException {
		
		mMimeTypes = new MimeTypes();
		
		int eventType = mXpp.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			String tag = mXpp.getName();

			if (eventType == XmlPullParser.START_TAG) {
				if (tag.equals(TAG_MIMETYPES)) {
					
				} else if (tag.equals(TAG_TYPE)) {
					addMimeTypeStart();
				}
			} else if (eventType == XmlPullParser.END_TAG) {
				if (tag.equals(TAG_MIMETYPES)) {
					
				}
			}

			eventType = mXpp.next();
		}

		return mMimeTypes;
	}
	
	private void addMimeTypeStart() {
		String extension = mXpp.getAttributeValue(null, ATTR_EXTENSION);
		String mimetype = mXpp.getAttributeValue(null, ATTR_MIMETYPE);
		//Log.d("FileFilter:","addMimeTypeStart: "+extension+" "+mimetype );
		mMimeTypes.put(extension, mimetype);
	}

	
}