package changgou;

import com.changgou.SecurityApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecurityApplication.class)
public class Test01 {
    @Test
    public void testpasswordEncode(){
        String idForEncode = "bcrypt";
//        Map encoders = new HashMap<>();
//        encoders.put(idForEncode, new BCryptPasswordEncoder());
////        encoders.put("noop", NoOpPasswordEncoder.getInstance());
//        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
//        encoders.put("scrypt", new SCryptPasswordEncoder());
////        encoders.put("sha256", new StandardPasswordEncoder());
//
//        PasswordEncoder passwordEncoder =
//                new DelegatingPasswordEncoder(idForEncode, encoders);
//
//        System.out.println(passwordEncoder);
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
////        String aaa = passwordEncoder.encode("aaa");
////        System.out.println(aaa);
       User user = (User) User.withDefaultPasswordEncoder().username("user").password("password").roles("user").build();

        System.out.println(user.getPassword());
    }
}
