package kz.softrackmfs.epaystub.domain;

import kz.softrackmfs.epaystub.domain.utils.ActionLog;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class EpayApplication {

    private static final Logger logger = Logger.getLogger(EpayApplication.class.getName());

    private String completeTemplate = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<document><error input=\"\" payment=\"\" system=\"\"/>" +
            "<request Signed_Order_B64=\"VAR_BASE64ORDER\"/>" +
            "<payment ActionTime=\"VAR_ACTION_TIME\" MerchantID=\"VAR_MERCHANT_ID\" " +
            "OrderId=\"VAR_ORDER_ID\" OrderAL=\"null\" " +
            "amount=\"VAR_AMOUNT\" Result=\"00\" Reference=\"VAR_REFERENCE\" " +
            "type=\"21\" message=\"Approved\"/></document>";

    private String createTemplate = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<document><error input=\"\" payment=\"\" system=\"\"/>" +
            "<request Signed_Order_B64=\"VAR_BASE64ORDER\"/>" +
            "<payment ActionTime=\"VAR_ACTION_TIME\" MerchantID=\"VAR_MERCHANT_ID\" OrderId=\"VAR_ORDER_ID\" " +
            "OrderAL=\"null\" amount=\"VAR_AMOUNT\" card_id=\"800000478288\" Result=\"00\" Reference=\"111111111111\" " +
            "int_reference=\"AAAAAAAAAAAAAA\" approval_code=\"2222222\" type=\"VAR_TYPE_ID\" message=\"Approved\" " +
            "abonent_id=\"VAR_ABONENT_ID\" terminal=\"null\" phone=\"null\" service_id=\"\" " +
            "sessionid=\"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"/></document>";

    private String cardListTemplate = "" +
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<document><error/>" +
            "<request recepient=\"\" sessionid=\"null\" id=\"VAR_MERCHANT_ID\" abonent_id=\"VAR_ABONENT_ID\" action=\"list\" card_id=\"null\" order_id=\"\"/>" +
            "<cards>" +
            "<item MerchantID=\"VAR_MERCHANT_ID\" HBID=\"VAR_ABONENT_ID\" CardID=\"900000015420\" CardMask=\"440540-xx-xxxx-0846\" e_mm=\"01\" e_yy=\"14\" approve=\"0\" reference=\"180919184238\" card_kkb=\"no\"/>" +
            "</cards>" +
            "</document>";

    private ActionLog actionLog = new ActionLog();

    private static final Map<String, EpayApplication> apps = new HashMap<>();

    public static synchronized EpayApplication getApplication(String name) {
        if (!apps.containsKey(name)) {
            apps.put(name, new EpayApplication());
        }
        return apps.get(name);
    }

    public String getCompleteTemplate() {
        return completeTemplate;
    }

    public void setCompleteTemplate(String completeTemplate) {
        this.completeTemplate = completeTemplate;
    }

    public String getCreateTemplate() {
        return createTemplate;
    }

    public void setCreateTemplate(String createTemplate) {
        this.createTemplate = createTemplate;
    }

    public String getCardListTemplate() {
        return cardListTemplate;
    }

    public void setCardListTemplate(String cardListTemplate) {
        this.cardListTemplate = cardListTemplate;
    }

    public String setNewCreateTemplate(String content) {
        String oldTemplate = getCreateTemplate();
        setCreateTemplate(content);
        return "Done, changed template: " + oldTemplate + " -> " + content;
    }

    public String setNewCompleteTemplate(String content) {
        String oldTemplate = getCompleteTemplate();
        setCompleteTemplate(content);
        return "Done, changed template: " + oldTemplate + " -> " + content;
    }

    public String setNewCardListTemplate(String newTemplate) {
        String oldTemplate = getCardListTemplate();
        setCardListTemplate(newTemplate);
        return "Done, changed template: " + oldTemplate + " -> " + newTemplate;
    }

    public String generateCreateResponse(String content) {
        return new EpayNo3dCreateTransactionRequest(content).generateResponse(getCreateTemplate(), actionLog);
    }

    public String generateCompleteResponse(String content) {
        return new EpayNo3dCompleteTransactionRequest(content).generateResponse(getCompleteTemplate(), actionLog);
    }

    public String generateCardListResponse(String xml) {
        logger.info("getting xml =  " + xml);
        return new EpayCardListRequest(xml).generateResponse(cardListTemplate, actionLog);
    }

    public String getActionLog() {
        return actionLog.getAsString();
    }
}
