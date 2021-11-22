import java.io.File;
import java.util.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

public class ReadFileSAX {

    private static double lat = 47.4786346;
    private static double lon = 19.0555773;
    private static String filename = "bme.xml";


    public static void main(String[] args) {
        String lastarg = new String();
        for (String arg : args) {
            if (arg.charAt(0) == '-') {
                if (arg.charAt(1) == 'i') {
                    lastarg = "filename";
                } else if (arg.contains("lat")) {
                    lastarg = "lat";
                } else if (arg.contains("lon")) {
                    lastarg = "lon";
                }
            } else {
                switch (lastarg) {
                    case "filename":
                        filename = arg;
                        break;
                    case "lat":
                        lat = Double.parseDouble(arg);
                        break;
                    case "lon":
                        lon = Double.parseDouble(arg);
                        break;

                }
            }
        }

        DefaultHandler h = new TagCounter();
        BusStopHandler bsh = new BusStopHandler();
        bsh.setOrig_lat(lat);
        bsh.setOrig_lon(lon);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {

            SAXParser p = factory.newSAXParser();
            File f = new java.io.File(filename);
            p.parse(f, h);
            p.parse(f, bsh);
            ArrayList<BusStop> bslist = bsh.getBusStopList();
            Collections.sort(bslist, Comparator.comparingDouble(BusStop::getDistance));
            for (BusStop bs : bslist) {
                System.out.println(bs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
