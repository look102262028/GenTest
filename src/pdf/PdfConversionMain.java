package pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class PdfConversionMain {

	public static String inputFile = "D:\\workspace\\Mobile\\GenTest\\test.pdf";
	public static String outputFile = "D:\\workspace\\Mobile\\GenTest\\";

	public static void main(String[] args) throws IOException, DocumentException {
		String day = "2023/05/19";
		String system = "TCBBMNB_WEB";
		String fileNum = "66"; // 檔案數
		String codeNum = "595"; // 程式碼總⾏數
		String random=getRandom();	//隨機數
		String dayChinese = day.substring(0, 4) + "年"
				+ String.valueOf(Integer.parseInt(day.substring(5, 7)) < 10 ? day.substring(6, 7) : day.substring(5, 7))
				+ "月" + day.substring(8) + "日";
		Map<String, String> map = new HashMap<String, String>();
		map.put("day", day);
		map.put("text1", "檢測日期：" + dayChinese + "，檢測主機名稱:SC-Fority-02，檢測專案代號");
		map.put("text2", "(BuildID)：Fortify_" + random + "_" + system);
		map.put("text3", "檢測總檔案數：" + fileNum + "，程式碼總⾏數：" + codeNum);
		outputFile=outputFile+"Fortify_"+random+"_"+system+".pdf";
		File file = new File(outputFile);
		file.getParentFile().mkdirs();
		new PdfConversionMain().manipulatePdf(inputFile, outputFile, map);
	}

	public void manipulatePdf(String src, String dest, Map<String, String> map) throws IOException, DocumentException {
		PdfReader reader = new PdfReader(src);
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
		// 1.日期
		float[] result = PdfConversion.getKeyWords(src, "2023/4/25");
		PdfContentByte canvas = stamper.getOverContent((int) result[2]);
		float x, y;
		x = result[0];
		y = result[1];
		canvas.saveState();
		// 设置覆盖面
		canvas.setColorFill(BaseColor.WHITE);
		canvas.rectangle(x, y - 2, 60, 20);
		canvas.fill();
		canvas.restoreState();
		canvas.beginText();
		// 设置字体,大小,输出位置,替換文字
		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED);
		Font font = new Font(bf, 10, Font.BOLD);
		canvas.setFontAndSize(font.getBaseFont(), 11);
		canvas.setTextMatrix(x, y);
		canvas.showText(map.get("day"));
		canvas.endText();
		//Arial Unicode.ttf
		BaseFont bf2 = BaseFont.createFont("C:\\Windows\\Fonts\\ARIALUNI.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font font2 = new Font(bf2, 10, Font.BOLD);
		// 1.檢測日期:2023年4月25日，檢測主機名稱:SC-Fority-02，檢測專案代號
		float[] result1 = PdfConversion.getKeyWords(src, "SC-Fority");
		PdfContentByte canvas1 = stamper.getOverContent((int) result1[2]);
		float x1, y1;
		x1 = result1[0];
		y1 = result1[1];
		canvas1.saveState();
		canvas1.setColorFill(BaseColor.WHITE);
		canvas1.rectangle(x1, y1 - 2, 500, 13);
		canvas1.fill();
		canvas1.restoreState();
		canvas1.beginText();
		canvas1.setFontAndSize(font2.getBaseFont(), 10);
		canvas1.setTextMatrix(x1, y1);
		String day = "2023年7月27日";
		String text1 = "檢測日期:" + day + "，檢測主機名稱:SC-Fority-02，檢測專案代號";
		canvas1.showText(map.get("text1"));
		canvas1.endText();

		// 2.(BuildID):Fortify_698049700_TCBBMNB_WEB
		float[] result2 = PdfConversion.getKeyWords(src, "Fortify_698049700");
		PdfContentByte canvas2 = stamper.getOverContent((int) result2[2]);
		float x2, y2;
		x2 = result2[0];
		y2 = result2[1];
		canvas2.saveState();
		canvas2.setColorFill(BaseColor.WHITE);
		canvas2.rectangle(x2, y2 - 2, 500, 13);
		canvas2.fill();
		canvas2.restoreState();
		canvas2.beginText();
		canvas2.setFontAndSize(font2.getBaseFont(), 10);
		canvas2.setTextMatrix(x2, y2);
		canvas2.showText(map.get("text2"));
		canvas2.endText();

		// 3.檢測總檔案數:534程，式程碼式總碼行總數:4619369
		float[] result3 = PdfConversion.getKeyWords(src, "5");
		PdfContentByte canvas3 = stamper.getOverContent((int) result3[2]);
		float x3, y3;
		x3 = result3[0];
		y3 = result3[1];
		canvas3.saveState();
		canvas3.setColorFill(BaseColor.WHITE);
		canvas3.rectangle(x3, y3 - 2, 500, 13);
		canvas3.fill();
		canvas3.restoreState();
		canvas3.beginText();
		canvas3.setFontAndSize(font2.getBaseFont(), 10);
		canvas3.setTextMatrix(x3, y3);
		canvas3.showText(map.get("text3"));
		canvas3.endText();

		// 測試結果: , 共找到 0 個問題
		float[] result4 = PdfConversion.getKeyWords(src, "測試結果");
		PdfContentByte canvas4 = stamper.getOverContent((int) result4[2]);
		float x4, y4;
		x4 = result4[0];
		y4 = result4[1];
		canvas4.saveState();
		canvas4.setColorFill(BaseColor.WHITE);
		canvas4.rectangle(x4, y4 - 20, 500, 33);//
		canvas4.fill();
		canvas4.restoreState();
		canvas4.beginText();
		canvas4.setFontAndSize(font2.getBaseFont(), 10);
		canvas4.setTextMatrix(x4 + 10, y4);
		String text4 = "測試結果:";
		String text5 = "共找到 0 個問題";
		canvas4.showText(text4);
		canvas3.setTextMatrix(x4 + 10, y4 - 14);
		canvas4.showText(text5);
		canvas4.endText();

		stamper.close();
		reader.close();
		System.out.println("complete");
	}

	public static String getRandom() {
		Random random = new Random();
		int randomNumber = random.nextInt(9000000) + 1000000; // 生成7位数范围内的随机数
		return String.valueOf(randomNumber+"00");
	}

}
