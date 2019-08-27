package kz.softrackmfs.epaystub.rest;

import kz.softrackmfs.epaystub.domain.EpayNo3dCompleteTransactionRequest;
import kz.softrackmfs.epaystub.domain.EpayNo3dCreateTransactionRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * @author <a href="mailto:mark.jay.mk@gmail.com">mark jay</a>
 * @since 7/24/19 3:14 PM
 */
@RestController
//@RequestMapping("/")
public class EpayController {

    private final Log logger = LogFactory.getLog(getClass());

    @GetMapping("/manage/templates/create")
    public String getCreateTemplate() {
        return EpayNo3dCreateTransactionRequest.getTemplate();
    }

    /**
     * curl  'localhost:8080/manage/templates/create' -H 'Content-Type: application/xml' -d'<?xml version="1.0" encoding="UTF-8"?><document><error input="" payment="" system=""/><request Signed_Order_B64="VAR_BASE64ORDER"/><payment ActionTime="VAR_ACTION_TIME" MerchantID="VAR_MERCHANT_ID" OrderId="VAR_ORDER_ID" OrderAL="null" amount="VAR_AMOUNT" card_id="800000478288" Result="00" Reference="111111111111" int_reference="AAAAAAAAAAAAAA" approval_code="2222222" type="VAR_TYPE_ID" message="Approved" abonent_id="VAR_ABONENT_ID" terminal="null" phone="null" service_id="" sessionid="AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"/></document>'
     * @param content
     * @return
     */
    @PostMapping("/manage/templates/create")
    public String setCreateTemplate(@RequestBody String content) {
        String oldTemplate = EpayNo3dCreateTransactionRequest.getTemplate();
        EpayNo3dCreateTransactionRequest.setTemplate(content);
        return "Done, changed template: " + oldTemplate + " -> " + content;
    }

    @GetMapping("/manage/templates/complete")
    public String getCompleteTemplate() {
        return EpayNo3dCompleteTransactionRequest.getTemplate();
    }

    /**
     * curl  'localhost:8080/manage/templates/complete' -H 'Content-Type: application/xml' -d'<?xml version="1.0" encoding="UTF-8"?><document><error input="" payment="" system=""/><request Signed_Order_B64="VAR_BASE64ORDER"/><payment ActionTime="VAR_ACTION_TIME" MerchantID="VAR_MERCHANT_ID" OrderId="VAR_ORDER_ID" OrderAL="null" amount="VAR_AMOUNT" Result="00" Reference="VAR_REFERENCE" type="21" message="Approved"/></document>'
     * @param content
     * @return
     */
    @PostMapping("/manage/templates/complete")
    public String setCompleteTemplate(@RequestBody String content) {
        String oldTemplate = EpayNo3dCompleteTransactionRequest.getTemplate();
        EpayNo3dCompleteTransactionRequest.setTemplate(content);
        return "Done, changed template: " + oldTemplate + " -> " + content;
    }


    @PostMapping("/jsp/hbpay/trans.jsp")
    public String pay(@RequestBody String content) {
        return new EpayNo3dCreateTransactionRequest(content).generateResponse();
    }

    @PostMapping("/jsp/hbpay/proc.jsp")
    public String confirm(@RequestBody String content) {
        return new EpayNo3dCompleteTransactionRequest(content).generateResponse();
    }

}
