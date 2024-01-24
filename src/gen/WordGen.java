package gen;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
//D:\workspace\Mobile\GenTest
public class WordGen {

	static Map map = new HashMap();
	static String basePath = "D:\\workspace\\Mobile\\GenTest\\";

	public static void main(String[] args) throws Exception {
		//ONLINE_SERVICE / MOBILE_BANK_APP  TCBBMNB_WEB 
		String system="ONLINE_SERVICE";
		String jobNum="58265";
		String createday="1130115";
		String subject="修改線上申請平台信用卡申請欄位";
		String testPeopleA="黃偉誠";
		String testPeopleB="戴尚斈";
		if(args.length>0){
			System.out.println("使用cmd 參數執行");
			for (int i = 0; i < args.length; i++) {
				System.out.println("arg"+args[i]);
				system=args[0];
				jobNum=args[1];
				createday=args[2];
				subject=args[3];
				testPeopleA=args[4];
				testPeopleB=args[5];
			}
		}else {
			System.out.println("使用java 程式設定執行");
			
		}
		map.put("system", system);
		map.put("jobNum", jobNum); // 工作單後五碼
		map.put("createday", createday); // 年月日
		map.put("subject", subject);// 主題
		map.put("testPeopleA", testPeopleA);// 指定測試人1
		map.put("testPeopleB", testPeopleB);// 指定測試人2

		createExcel("1");
		createExcel("2");
		createWord("1");	//1:QA
		createWord("2");	//2:(單元測試作業單乙)
		openFolder(basePath);
	}
	public static void openFolder(String path) {
		Desktop desktop =Desktop.getDesktop();
		File folder =new File(path);
		if(folder.exists()) {
			try {
				desktop.open(folder);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("folder not exist");
		}
	}
	public static void createExcel(String type) {

		String system = (String) map.get("system");
		String jobNum = (String) map.get("jobNum"); // 工作單後五碼
		String createday = (String) map.get("createday"); // 年月日
		String subject = (String) map.get("subject"); // 主題
		String testPeople = "1".equals(type) ? (String) map.get("testPeopleA") : (String) map.get("testPeopleB"); // 指定測試人
		String intputPath = basePath + "testexcel.xlsx";
		String outputFilePath1 = basePath + "(QA)單元測試作業單JOB-0000000" + jobNum + ".xlsx";
		String outputFilePath2 = basePath + "單元測試作業單JOB-0000000" + jobNum + ".xlsx";
		String outputpath = "1".equals(type) ? outputFilePath1 : outputFilePath2;
		String sheetName = "測試案例紀錄";

		int cellIndex = 1; // 左右
		int rowIndex = 3; // 上下
		int rowIndex2 = 4; // 上下
		try (FileInputStream fis = new FileInputStream(intputPath); Workbook workbook = WorkbookFactory.create(fis)) {

	        String setVal=system+"_"+subject+"_TS0001_TC0001";
	        String setVal2=system+"_"+subject+"(反向測試)_TS0001_TC0001";
			Sheet sheet = workbook.getSheet(sheetName);
	        Row row_ = sheet.getRow(rowIndex);
	        Cell cell1 = row_.getCell(cellIndex, MissingCellPolicy.CREATE_NULL_AS_BLANK);
	        cell1.setCellValue(setVal);
	        Row row2 = sheet.getRow(rowIndex2);
	        Cell cell2 = row2.getCell(cellIndex, MissingCellPolicy.CREATE_NULL_AS_BLANK);
	        cell2.setCellValue(setVal2);
			for (Row row : sheet) {
				for (Cell cell : row) {
					if (cell.getCellType() == CellType.STRING) {
						String cellValue = cell.getStringCellValue();
						//cellvalueSystem.out.println("value:" + cellValue);
						if (cellValue.contains("sss")) { // 需求內容
							cellValue = cellValue.replace("sss", subject);
							cell.setCellValue(cellValue);
						} else if (cellValue.contains("people")) { 
							cellValue = cellValue.replace("people", testPeople);
							cell.setCellValue(cellValue);
						} else if (cellValue.contains("ymd")) { // 日期
							String formatday = createday.substring(0, 3) + "/" + createday.substring(3, 5) + "/"
									+ createday.substring(5, 7);
							cellValue = cellValue.replace("ymd", formatday);
							cell.setCellValue(cellValue);
						} else if (cellValue.contains("system")) { // 系統代碼
							cellValue = cellValue.replace("system", system);
							cell.setCellValue(cellValue);
						} 

					}
				}
			}

			// 儲存修改後的Excel檔案
			try (FileOutputStream fos = new FileOutputStream(outputpath)) {
				workbook.write(fos);
			}

			System.out.println("Excel檔案中的字串取代完成。");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 1:QA 2:(單元測試作業單乙)
	public static void createWord(String type) throws IOException {
		// 更改此處資料
		String system = (String) map.get("system");
		String jobNum = (String) map.get("jobNum"); // 工作單後五碼
		String createday = (String) map.get("createday"); // 年月日
		String subject = (String) map.get("subject"); // 主題
		String testPeopleA = (String) map.get("testPeopleA"); // 指定測試人1
		String testPeopleB = (String) map.get("testPeopleB"); // 指定測試人2

		String output1 = basePath+"(QA)單元測試作業單JOB-0000000";
		String output2 = basePath+"單元測試作業單(乙)JOB-0000000";
		String outputpath = "1".equals(type) ? output1 + jobNum + ".docx" : output2 + jobNum + ".docx";
		System.out.println("start");
		FileOutputStream out = null;
		XWPFDocument docx = null;
		try {
			// 創建一個新的Word文檔
			docx = new XWPFDocument(new FileInputStream(basePath+"testword.docx"));

			// 取代文字
			for (XWPFParagraph p : docx.getParagraphs()) {
				for (XWPFRun r : p.getRuns()) {
					String text = r.getText(0);
					System.out.println("取代文字text:"+text);
					if (text != null && text.contains("hhh")) {
						text = text.replaceAll("hhh", system);
						r.setText(text, 0);
					} else if (text != null && text.contains("sss")) {
						text = text.replaceAll("sss", subject);
						r.setText(text, 0);
					}
				}
			}
			// 遍歷表格進行文字的取代
			for (XWPFTable table : docx.getTables()) {
				for (XWPFTableRow row : table.getRows()) {
					for (XWPFTableCell cell : row.getTableCells()) {
						for (XWPFParagraph p : cell.getParagraphs()) {
							//System.out.println("p: " + p.getText());
							for (XWPFRun run : p.getRuns()) {
								String text = run.getText(0);
								System.out.println("表格text: " + text);
								if (text != null) {
									if (text.contains("hhh")) { // 系統別
										p.setAlignment(ParagraphAlignment.CENTER);
										text = text.replace("hhh", system);
										run.setText(text, 0);
									} else if (text.contains("nnn")) { // 系統別中文
										p.setAlignment(ParagraphAlignment.LEFT);
										text = text.replace("nnn", system.equals("ONLINE_SERVICE") ? "網路銀行" : "行動網銀");
										run.setFontSize(12);
										run.setText(text, 0);
									} else if (text.contains("99999")) { // 工作單號
										p.setAlignment(ParagraphAlignment.RIGHT);
										text = text.replace("99999", jobNum);
										run.setText(text, 0);
									} else if (text.contains("YYY")) {
										text = text.replace("YYY", createday.substring(0, 3));
										run.setText(text, 0);
									} else if (text.contains("MM")) {
										text = text.replace("MM", createday.substring(3, 5));
										run.setText(text, 0);
									} else if (text.contains("DD")) {
										text = text.replace("DD", createday.substring(5, 7));
										run.setText(text, 0);
									} else if (text.contains("sss")) { // 測試主題
										text = text.replace("sss", subject);
										run.setText(text, 0);
									} else if (text.contains("people")) { // 指定測試人1
										text = text.replace("people", "1".equals(type) ? testPeopleA : testPeopleB);
										run.setText(text, 0);
									}
								}
							}
						}

					}
				}
			}

			// 保存Word文檔

			out = new FileOutputStream(outputpath);
			docx.write(out);
		} catch (Exception e) {
			System.err.println("exception:" + e);
		} finally {
			if (out != null)
				out.close();
			if (docx != null)
				docx.close();
		}

		System.out.println("ok");
	}
}