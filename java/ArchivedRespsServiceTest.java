import com.pooyaco.powercard.services.ArchivedRespsMaliService;
import com.pooyaco.powercard.services.ArchivedRespsService;
import com.pooyaco.powercard.services.ArchivedRespsSuspectService;
import com.pooyaco.powercard.services.HsmKeyServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.util.Date;

/**
 * Created by a.mahdavi on 9/15/2018.
 */

@ContextConfiguration({
        "classpath*:dispatcher-servlet-test.xml"

})
@EnableWebMvc
@Configuration
@WebAppConfiguration
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class ArchivedRespsServiceTest {


    @Autowired
    ArchivedRespsService archivedRepsService;

    @Autowired
    ArchivedRespsSuspectService archivedRepSuspectService;

    @Autowired
    ArchivedRespsMaliService archivedRespsMaliService;

    @Autowired
    HsmKeyServices hsmKeyServices;


    @Test
    public void testFindAll() {
        archivedRepsService.getAll();
    }

    @Test
    public void testFindByReportName() {
        archivedRespsMaliService.findArchievedList("Report02","1397/06/29","201");
    }

    @Test
    public void testEditArchieved() {
        archivedRespsMaliService.updateArchieved("Report02","1397/06/29","201","253","1397-03-5");
    }

    @Test
    public void testHsmServices() {
        hsmKeyServices.getHsmKey("maskan");
    }

    @Test
    public void testMD5Hash(){
      String password="secret";
        Md5PasswordEncoder passwordEncoder=new Md5PasswordEncoder();
        String hashedPassword=passwordEncoder.encodePassword(password,null);
        System.out.println(hashedPassword);
    }
}
