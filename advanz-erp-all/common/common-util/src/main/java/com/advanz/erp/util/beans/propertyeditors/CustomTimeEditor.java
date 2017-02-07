package com.advanz.erp.util.beans.propertyeditors;

import java.beans.PropertyEditorSupport;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.springframework.util.StringUtils;

	public class CustomTimeEditor extends PropertyEditorSupport {

		private final DateFormat dateFormat;

		private final boolean allowEmpty;

		private final int exactDateLength;


		/**
		 * Create a new CustomTimeEditor instance, using the given DateFormat
		 * for parsing and rendering.
		 * <p>The "allowEmpty" parameter states if an empty String should
		 * be allowed for parsing, i.e. get interpreted as null value.
		 * Otherwise, an IllegalArgumentException gets thrown in that case.
		 * @param dateFormat DateFormat to use for parsing and rendering
		 * @param allowEmpty if empty strings should be allowed
		 */
		public CustomTimeEditor(DateFormat dateFormat, boolean allowEmpty) {
			this.dateFormat = dateFormat;
			this.allowEmpty = allowEmpty;
			this.exactDateLength = -1;
		}

		


		/**
		 * Parse the Date from the given text, using the specified DateFormat.
		 */
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			if (this.allowEmpty && !StringUtils.hasText(text)) {
				// Treat empty String as null value.
				setValue(null);
			}
			else if (text != null && this.exactDateLength >= 0 && text.length() != this.exactDateLength) {
				throw new IllegalArgumentException(
						"Could not parse date: it is not exactly" + this.exactDateLength + "characters long");
			}
			else {
				try {
					setValue(new Time(this.dateFormat.parse(text).getTime()));
				}
				catch (ParseException ex) {
					throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
				}
			}
		}

		/**
		 * Format the Date as String, using the specified DateFormat.
		 */
		@Override
		public String getAsText() {
			Date value = (Date) getValue();
			return (value != null ? this.dateFormat.format(value) : "");
		}

	}
