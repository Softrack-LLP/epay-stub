package kz.softrackmfs.epaystub.domain;

import kz.softrackmfs.epaystub.domain.utils.ParsedDocument;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class EpayNo3dCreateTransactionRequest {

    private static String template = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<document><error input=\"\" payment=\"\" system=\"\"/>" +
            "<request Signed_Order_B64=\"VAR_BASE64ORDER\"/>" +
            "<payment ActionTime=\"VAR_ACTION_TIME\" MerchantID=\"VAR_MERCHANT_ID\" OrderId=\"VAR_ORDER_ID\" " +
            "OrderAL=\"null\" amount=\"VAR_AMOUNT\" card_id=\"800000478288\" Result=\"00\" Reference=\"111111111111\" " +
            "int_reference=\"AAAAAAAAAAAAAA\" approval_code=\"2222222\" type=\"VAR_TYPE_ID\" message=\"Approved\" " +
            "abonent_id=\"VAR_ABONENT_ID\" terminal=\"null\" phone=\"null\" service_id=\"\" " +
            "sessionid=\"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"/></document>";

    private final ParsedDocument document;
    private String base64Input;
    private Date date;

    public EpayNo3dCreateTransactionRequest(String base64Input) {
        this.base64Input = base64Input;
        String decoded = new String(Base64.getDecoder().decode(base64Input.replaceAll("Signed_Order_B64=", "")));
        this.document = new ParsedDocument(decoded);
        this.date = new Date();
    }

    public String generateResponse() {
        return template
                .replaceAll("VAR_BASE64ORDER", base64Input)
                .replaceAll("VAR_ACTION_TIME", getFormattedDate())
                .replaceAll("VAR_MERCHANT_ID", getMerchantId())
                .replaceAll("VAR_AMOUNT", getAmount())
                .replaceAll("VAR_ORDER_ID", getOrderId())
                .replaceAll("VAR_TYPE_ID", getType())
                .replaceAll("VAR_ABONENT_ID", getAbonentId())
                ;
    }

    private String getAbonentId() {
        return document.getByXPath("/document/merchant/order/department/@abonent_id");
    }

    private String getType() {
        return document.getByXPath("/document/merchant/order/@type");
    }

    private String getAmount() {
        return document.getByXPath("/document/merchant/order/department/@amount").replaceAll(",", ".");
    }

    private String getOrderId() {
        return document.getByXPath("/document/merchant/order/@order_id");
    }

    private String getMerchantId() {
        return document.getByXPath("/document/merchant/order/department/@benef");
    }

    private String getFormattedDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static String getTemplate() {
        return template;
    }

    public static void setTemplate(String template) {
        EpayNo3dCreateTransactionRequest.template = template;
    }
}
