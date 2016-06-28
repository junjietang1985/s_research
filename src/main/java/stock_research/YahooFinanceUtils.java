package stock_research;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YahooFinanceUtils {
	public static final String DOWNLOAD_TO_SPREADSHEET_URL_PREFIX = "http://real-chart.finance.yahoo.com/table.csv";

	public static final String HISTORICAL_PRICES_URL_PREFIX = "http://finance.yahoo.com/q/hp?s=";

	public static final String HISTORICAL_PRICES_URL_SUFFIX = "+Historical+Prices";

	public static String getDownloadToSpreadsheetURL(String stockCode) {
		String targetUrl = getHistoricalPrices(stockCode);
		try {
			URL url = new URL(targetUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			int responseCode = conn.getResponseCode();
			System.out.println(responseCode);
			try (BufferedReader br = new BufferedReader(new InputStreamReader(
					conn.getInputStream()))) {
				String line;
				while ((line = br.readLine()) != null) {
					if (!line.contains("Download to Spreadsheet")) {
						continue;
					}
					String prefix = "[\\s\\S]*<a href=\"http:\\/\\/real-chart.finance.yahoo.com\\/table.csv";
					String suffix = "\"+[\\s\\S]*";
					String downloadToSpreadsheetURL = DOWNLOAD_TO_SPREADSHEET_URL_PREFIX
							+ line.replaceAll(prefix, "")
									.replaceAll(suffix, "");
					return downloadToSpreadsheetURL;
				}
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getHistoricalPrices(String stockCode) {
		return HISTORICAL_PRICES_URL_PREFIX + stockCode
				+ HISTORICAL_PRICES_URL_SUFFIX;
	}

	public static List<String> getSSSZ300Stocks() {
		List<String> res = new ArrayList<>();
		String targetUrl = "http://www.goomj.com/zqts/zjzl/zjzl300.htm";
		String regexStock = "\\s(0|6)\\d{5}";
		String regexHTMLTag = "<.*?>";
		Pattern pattern = Pattern.compile(regexStock);
		try {
			URL url = new URL(targetUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			int responseCode = conn.getResponseCode();
			System.out.println(responseCode);
			try (BufferedReader br = new BufferedReader(new InputStreamReader(
					conn.getInputStream()))) {
				String line;
				int count = 0;
				while ((line = br.readLine()) != null) {
					line = line.replaceAll(regexHTMLTag, "");
					Matcher matcher = pattern.matcher(line);
					if (matcher.find()) {
						count++;
						String stockCode = line.substring(matcher.start() + 1,
								matcher.start() + 7);
						System.out.println(count + "." + stockCode);
						res.add(stockCode);
					}
				}
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static void main(String args[]) {
		// System.out.println(getDownloadToSpreadsheetURL("000858.SZ"));
		getSSSZ300Stocks();
	}
}
