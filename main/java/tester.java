import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class tester {

    public static void main(String[] args) {
//       BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
//        System.out.println(bCryptPasswordEncoder.encode("321"));
        String one = "XAXAXA";
        String two = "XAXAXA";
        String three = "XOXOXO";
        String four = null;

        if(one.equals(two)){
            System.out.println("yeeeeap");
        }
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
