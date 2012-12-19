package eu.verdelhan.samples.jodreports;

public class JODReportsSamples {

	private static final String TARGET_FILE_PATH = "/tmp/target.odt";

	public static void main(String[] args) {

		ODTWriter odtWriter = new ODTWriter();
		odtWriter.addSimpleFields();
		odtWriter.addPicture();
		odtWriter.addOptionalSection(true);
		odtWriter.addSimpleTable();
		odtWriter.addSimpleTableFromLists();
		odtWriter.addDynamicColumnCountTable(5, 4);
		odtWriter.addPageHeader();
		odtWriter.addPageFooter();
		odtWriter.writeFile(TARGET_FILE_PATH);
	}
}
