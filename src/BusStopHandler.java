import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.ArrayList;

public class BusStopHandler extends org.xml.sax.helpers.DefaultHandler {
    BusStop bs;
    private double lat;
    private double lon;

    private double orig_lat;
    private double orig_lon;

    private ArrayList<BusStop> busStopList = new ArrayList<>();

    public ArrayList<BusStop> getBusStopList() {
        return busStopList;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "node":
                bs = new BusStop();
                lat = Double.parseDouble(attributes.getValue("lat"));
                lon = Double.parseDouble(attributes.getValue("lon"));
                break;
            case "tag":
                if (attributes.getValue("v").equals("bus_stop")) {
                    bs.setValid(true);
                    bs.setDistance(dist1(lat, lon, orig_lat, orig_lon));
                } else if (attributes.getValue("k").equals("name"))
                    bs.setName(attributes.getValue("v"));
                else if (attributes.getValue("k").equals("old_name"))
                    bs.setOldName(attributes.getValue("v"));
                else if (attributes.getValue("k").equals("wheelchair"))
                    bs.setWheelchair(attributes.getValue("v"));
                break;
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("node"))
            if (bs.isValid()) {
                //System.out.println(bs);
                busStopList.add(bs);
            }
    }

    public void setOrig_lat(double orig_lat) {
        this.orig_lat = orig_lat;
    }

    public void setOrig_lon(double orig_lon) {
        this.orig_lon = orig_lon;
    }

    private double dist1(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371000; // metres
        double phi1 = Math.toRadians(lat1);
        double phi2 = Math.toRadians(lat2);
        double dphi = phi2 - phi1;
        double dl = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dphi / 2) * Math.sin(dphi / 2) +
                Math.cos(phi1) * Math.cos(phi2) *
                        Math.sin(dl / 2) * Math.sin(dl / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return d;
    }
}
