package scraper;

import spec.Spec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class ScraperTester {
      /*
    public static void main(String[] args) throws Exception {

        ScraperHelper sc = new ScraperHelper();
        final String url = "https://www.hdblog.it/schede-tecniche/apple-iphone-12-pro-max_i4623/";
        Spec s1 = sc.getSpecByURL(url);
        System.out.println(s1);

    }
*/

    public static int saveSpecByLink(String url) throws Exception {


        Spec spec = ScraperHelper.getSpecByURL(url);
        System.out.println(spec);
        Class.forName("com.mysql.jdbc.Driver");
        String dbUrl = "jdbc:mysql://localhost:3306/gen" + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection conn = DriverManager.getConnection(dbUrl, "root", "");
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO spec (name, so, cpu, chipset, gpu, ram, memory, screen_size, image, display, camera, performance, battery, date, price) VALUES (" +
                " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, spec.getName());
        preparedStatement.setString(2, spec.getSo());
        preparedStatement.setString(3, spec.getCpu());
        preparedStatement.setString(4, spec.getChipset());
        preparedStatement.setString(5, spec.getGpu());
        preparedStatement.setString(6, spec.getRam());
        preparedStatement.setString(7, spec.getMemory());
        preparedStatement.setString(8, spec.getScreenSize());
        preparedStatement.setString(9, spec.getImage());
        preparedStatement.setDouble(10, spec.getDisplay());
        preparedStatement.setDouble(11, spec.getCamera());
        preparedStatement.setDouble(12, spec.getPerformance());
        preparedStatement.setInt(13, spec.getBattery());
        preparedStatement.setString(14, spec.getDate());
        preparedStatement.setDouble(15, spec.getPrice());

        return preparedStatement.executeUpdate();

    }

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 70; i++) {
            ArrayList<String> links = ScraperHelper.getLinksByPage("https://www.hdblog.it/schede-tecniche/page/" + i + "/");
            for (String link : links) {
                saveSpecByLink(link);
            }
        }
    }
}
