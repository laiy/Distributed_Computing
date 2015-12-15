package rfc;

import http.Fields;

/**
 * HTTP1.0µÄÍ·²¿Óò
 */
public class HeaderFields extends Fields {
	@Override
	protected boolean validateFieldValue(String fieldName, String value) {
		return true;
	}
}
