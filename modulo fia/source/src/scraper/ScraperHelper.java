package scraper;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ScraperHelper {

    public static ArrayList<Spec> getSpecs() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String dbUrl = "jdbc:mysql://localhost:3306/gen" + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        java.sql.Connection conn = DriverManager.getConnection(dbUrl, "root", "");
        PreparedStatement preparedStatement = null;

        ArrayList<Spec> specs = new ArrayList<Spec>();

        String TABLE_NAME = "spec";
        String selectSQL = "SELECT * FROM " + TABLE_NAME;

        preparedStatement = conn.prepareStatement(selectSQL);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Spec spec = new Spec();
            spec.setName(rs.getString("name"));

            spec.setDisplay(rs.getDouble("display"));
            spec.setCamera(rs.getDouble("camera"));
            spec.setPerformance(rs.getDouble("performance"));
            spec.setBattery(rs.getInt("battery"));
            spec.setDate(rs.getString("date"));
            spec.setId(rs.getInt("id"));

            specs.add(spec);
        }
        return specs;
    }

    public static Spec getSpecByURL(String url) throws Exception{

        Spec spec = new Spec();
        Connection jsoup = Jsoup.connect(url);
        Elements keys = jsoup.get().getElementById("controles_titles").getElementsByClass("phone_column_features");
        Elements values = jsoup.get().getElementById("phone_columns").getElementsByClass("phone_column_features");
        Element imageAndName = jsoup.get().getElementsByClass("narrow_column").get(0).getElementsByTag("img").get(0);
        spec.setImage(imageAndName.attr("src"));
        spec.setName(imageAndName.attr("alt"));

        for(int i = 0; i < keys.size(); i++){

            Elements localKeys = keys.get(i).getElementsByTag("li");
            Elements localValues = values.get(i).getElementsByTag("li");
            for(int j = 0; j < localKeys.size(); j++){

                String localKey = localKeys.get(j).text();
                String localValue = localValues.get(j).text();

                //Non trova l'accento su disponibilità
                if(localKey.contains("Disponibilit")){
                    System.out.println("Data trovata");
                    localKey=DATE;
                }


                switch (localKey){
                    case OS: spec.setSo(localValue);
                        break;
                    case DATE:
                        System.out.println("Date found");
                        spec.setDate(localValue);
                        break;
                    case CPU: spec.setCpu(localValue);
                        break;
                    case CHIPSET: spec.setChipset(localValue);
                        break;
                    case GPU: spec.setGpu(localValue);
                        break;
                    case RAM: spec.setRam(localValue);
                        break;
                    case MEMORY: spec.setMemory(localValue);
                        break;
                    case SCREEN_SIZE: spec.setScreenSize(localValue);
                        break;
                    case BATTERY: spec.setBattery(Integer.parseInt(localValue.split(" mAh")[0]));
                        break;
                    case DISPLAY: spec.setDisplay(Double.parseDouble(localValue.split(" /")[0]));
                        break;
                    case CAMERA: spec.setCamera(Double.parseDouble(localValue.split(" /")[0]));
                        break;
                    case PERFORMANCE: spec.setPerformance(Double.parseDouble(localValue.split(" /")[0]));
                        break;
                    case BEST_PRICE:
                        spec.setPrice(Double.parseDouble(localValue.split(" ")[0]));
                        break;
                }
            }
        }



        return spec;

    }

    public static ArrayList<String> getLinksByPage(String pageUrl) throws Exception{

        Spec spec = new Spec();
        Connection jsoup = Jsoup.connect(pageUrl);
        ArrayList<String> pages = new ArrayList<>();
        Elements phoneList = jsoup.get().getElementsByClass("phonelist_item");



        for(Element e : phoneList){
            Element priceEl = e.getElementsByClass("price").first();
            if(!priceEl.text().equals("Avviso di Prezzo")){
                pages.add(e.getElementsByTag("a").get(0).attr("href"));
            }
        }

        return pages;

    }

    private final static String OS = "Sistema Operativo";
    private final static String DATE = "Disponibilità";
    private final static String CPU = "Processore";
    private final static String CHIPSET = "Chipset";
    private final static String GPU = "GPU";
    private final static String RAM = "RAM";
    private final static String MEMORY = "Memoria (Max)";
    private final static String SCREEN_SIZE = "Pollici";        //TODO: modificare il documento di analisi
    private final static String BATTERY = "Ampere";
    private final static String DISPLAY = "- Display";
    private final static String CAMERA = "- Fotocamera";
    private final static String PERFORMANCE = "- Prestazioni";
    private final static String BEST_PRICE = "Miglior Prezzo";


}
