package kz.softrackmfs.epaystub.domain;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

public class EpayApplication {

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

    public String generateCreateResponse(String content) {
        return new EpayNo3dCreateTransactionRequest(content).generateResponse(getCreateTemplate());
    }

    public String generateCompleteResponse(String content) {
        return new EpayNo3dCompleteTransactionRequest(content).generateResponse(getCompleteTemplate());
    }
}
