import gr.isp.springbootapplication.entity.Advert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class tester {

    public static void main(String[] args) {
//       BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
//        System.out.println(bCryptPasswordEncoder.encode("321"));

//        Advert ad1 = new Advert();
//        ad1.setTitle("Plats");
//        ad1.setBody("PLats2341234 plats");
//        ad1.setStatus("Invisible");
//        Advert ad2 = new Advert();
//        ad2.setTitle("Plats1");
//        ad2.setBody("PLats235235 plats");
//        ad2.setStatus("Visible");
//        Advert ad3 = new Advert();
//        ad3.setTitle("Plat2");
//        ad3.setBody("PLats2352 plats");
//        ad3.setStatus("Draft");
//        Advert ad4 = new Advert();
//        ad4.setTitle("Plats3");
//        ad4.setBody("PLats236236 plats");
//        ad4.setStatus("Expired");
//        Advert ad5 = new Advert();
//        ad5.setTitle("Plats4");
//        ad5.setBody("PLats3256 plats");
//        ad5.setStatus("Visible");
//        Advert ad6 = new Advert();
//        ad6.setTitle("Plats5");
//        ad6.setBody("PLats23423 plats");
//        ad6.setStatus("Invisible");
//        Advert ad7 = new Advert();
//        ad7.setTitle("Plats6");
//        ad7.setBody("PLats63 plats");
//        ad7.setStatus("Visible");
//        ArrayList adList = new ArrayList();
//        adList.add(ad1);
//        adList.add(ad2);
//        adList.add(ad3);
//        adList.add(ad4);
//        adList.add(ad5);
//        adList.add(ad6);
//        adList.add(ad7);
//        Comparator<Map<String, String>> mapComparator = new Comparator<Map<String, String>>() {
//            public int compare(Map<String, String> m1, Map<String, String> m2) {
//                return m1.get("name").compareTo(m2.get("name"));
//            }
//        };
//
//        Collections.sort(adList, mapComparator);

//        String one = "TEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXTTEXT";
//        String two = "XAXAXA";
//        String three = "XOXOXO";
//        String four = null;
//
//        if(!one.equals(two)){
//            System.out.println("yeeeeap");
//        }
        //LocalDateTime.now() creates an object that is too long for SQL, so we have to cut the last parts (the nanoseconds) in order to not insert corrupt date to the DB
//        System.out.print(LocalDateTime.now());
//        String input = "2019-11-06T06:14:50";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//        LocalDateTime nowTooLong = LocalDateTime.now();
//        String nowStr = nowTooLong.format(formatter);
//        System.out.print("\n"+nowStr);
//        System.out.print("\n"+input);
//        //LocalDateTime timePosted = LocalDateTime.parse(nowStr, formatter);
//        LocalDateTime inputAfter = LocalDateTime.parse(input, formatter);


    }

}

