package kz.softrackmfs.epaystub.rest;

import kz.softrackmfs.epaystub.domain.EpayApplication;
import kz.softrackmfs.epaystub.services.Delayer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author <a href="mailto:mark.jay.mk@gmail.com">mark jay</a>
 * @since 7/24/19 3:14 PM
 */
@RestController
//@RequestMapping("/")
public class EpayController {

    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private Delayer delayer;

    @GetMapping("/{app}/manage/templates/create")
    public String getCreateTemplate(@PathVariable("app") String app) {
        return EpayApplication.getApplication(app).getCreateTemplate();
    }

    @PostMapping("/{app}/manage/templates/create")
    public String setCreateTemplate(@PathVariable("app") String app, @RequestBody String content) {
        return EpayApplication.getApplication(app).setNewCreateTemplate(content);
    }

    @GetMapping("/{app}/manage/templates/complete")
    public String getCompleteTemplate(@PathVariable("app") String app) {
        return EpayApplication.getApplication(app).getCompleteTemplate();
    }

    @PostMapping("/{app}/manage/templates/complete")
    public String setCompleteTemplate(@PathVariable("app") String app, @RequestBody String content) {
        return EpayApplication.getApplication(app).setNewCompleteTemplate(content);
    }

    @PostMapping("/manage/delay/{delay}")
    public String setCompleteTemplate(@PathVariable("delay") Long delay, @RequestBody String path) {
        delayer.setDelay(path, delay);
        return "Done";
    }


    @PostMapping("/{app}/jsp/hbpay/trans.jsp")
    public String pay(@PathVariable("app") String app, @RequestBody String content, HttpServletRequest request) {
        delayer.waitForDelay(request.getRequestURI());
        return EpayApplication.getApplication(app).generateCreateResponse(content);
    }

    @PostMapping("/{app}/jsp/hbpay/proc.jsp")
    public String confirm(@PathVariable("app") String app, @RequestBody String content, HttpServletRequest request) {
        delayer.waitForDelay(request.getRequestURI());
        return EpayApplication.getApplication(app).generateCompleteResponse(content);
    }

}
