/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2012-2015 Marc de Verdelhan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eu.verdelhan.samples.jodreports;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import net.sf.jooreports.templates.DocumentTemplate;
import net.sf.jooreports.templates.DocumentTemplateFactory;
import net.sf.jooreports.templates.image.RenderedImageSource;

import org.apache.log4j.Logger;

public class ODTWriter {

	private static Logger LOGGER = Logger.getLogger(ODTWriter.class);

	private static final URL TEMPLATE_URL = ODTWriter.class.getClassLoader().getResource("template.odt");
	private static final URL PICTURE_URL = ODTWriter.class.getClassLoader().getResource("lena.png");
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy - HH:mm");

	private DocumentTemplate document;
	private HashMap<String, Object> data;

	public ODTWriter() {
		DocumentTemplateFactory documentTemplateFactory = new DocumentTemplateFactory();
		try {
			document = documentTemplateFactory.getTemplate(new File(TEMPLATE_URL.toURI()));
		} catch (Exception e) {
			LOGGER.error("Error initializing document", e);
		}

		data = new HashMap<String, Object>();
	}

	public void addSimpleFields() {
		data.put("title", "_Parametrized title for the document_");
		data.put("simple_field", "_A Simple Value For A Simple Field_");
	}

	public void addPicture() {
		try {
			// The name (in the template) of the replaced image must be
			// "jooscript.name(picture_of_lena)" (without quotes)
			data.put("picture_of_lena", new RenderedImageSource(ImageIO.read(new File(PICTURE_URL.toURI()))));
		} catch (Exception e) {
			LOGGER.error("Error adding picture", e);
		}
	}

	/**
	 * @param display true to display the optional section, false to hide it
	 */
	public void addOptionalSection(boolean display) {
		data.put("displayoptional", display);
	}

	public void addSimpleTable() {
		final List<TableItem> items = new ArrayList<TableItem>();
		TableItem i = new TableItem("42", "forty-two", 42);
		TableItem i2 = new TableItem();
		i2.setFoo("test for table");
		i2.setBar("");
		TableItem i3 = new TableItem("foo", "bar", 100);

		items.add(i);
		items.add(i2);
		items.add(i3);

		data.put("tableitems", items);
	}

	public void addSimpleTableFromLists() {
		final List<List<String>> rows = new ArrayList<List<String>>();
		List<String> i = new ArrayList<String>();
		i.add("foo");
		i.add("1000");
		List<String> i2 = new ArrayList<String>();
		i2.add("bar");
		i2.add("300");
		List<String> i3 = new ArrayList<String>();
		i3.add("test");
		i3.add("42");
		List<String> i4 = new ArrayList<String>();
		i4.add("huhu");
		i4.add("0");
		List<String> i5 = new ArrayList<String>();
		i5.add("goodbye world");
		i5.add("-123");

		rows.add(i);
		rows.add(i2);
		rows.add(i3);
		rows.add(i4);
		rows.add(i5);

		data.put("listofrows", rows);
	}

	/**
	 * @param nbCols the number of columns for the table
	 * @param nbRows the number of rows for the table
	 */
	public void addDynamicColumnCountTable(int nbCols, int nbRows) {
		// Header row
		final List<String> headerRow = new ArrayList<String>();
		for (int c = 0; c < nbCols; c++) {
			headerRow.add("My Col Header " + c);
		}
		// Other rows
		final List<List<String>> rows = new ArrayList<List<String>>();
		for (int r = 0; r < nbRows; r++) {
			List<String> row = new ArrayList<String>();
			for (int c = 0; c < nbCols; c++) {
				row.add("row " + r + ", col " + c);
			}
			rows.add(row);
		}

		data.put("tableheaders", headerRow);
		data.put("tablerows", rows);
	}

	public void addPageHeader() {
		data.put("pageheader", "_Here is the header_");
		Calendar c = new GregorianCalendar();
		c.setTimeInMillis(System.currentTimeMillis());
		data.put("date", DATE_FORMAT.format(c.getTime()));
	}

	public void addPageFooter() {
		data.put("pagefooter", "_Here is the footer_");
	}

	/**
	 * @param filename the path of the file to be written (e.g. "/tmp/test.odt")
	 */
	public void writeFile(String filename) {
		try {
			document.createDocument(data, new FileOutputStream(filename));
		} catch (Exception e) {
			LOGGER.error("Error writing file", e);
		}
	}
}
