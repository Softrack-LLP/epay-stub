package kz.softrackmfs.epaystub.domain;

import kz.softrackmfs.epaystub.domain.utils.ActionLog;
import kz.softrackmfs.epaystub.domain.utils.ParsedDocument;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class EpayNo3dCompleteTransactionRequest {

    private final ParsedDocument document;
    private String base64Input;
    private Date date;

    public EpayNo3dCompleteTransactionRequest(String encoded) {
        String base64Input = URLDecoder.decode(encoded);
        this.base64Input = base64Input;
        String decoded = new String(Base64.getDecoder().decode(base64Input.replaceAll("Signed_Order_B64=", "")));
        this.document = new ParsedDocument(decoded);
        this.date = new Date();
    }

    public String generateResponse(String template, ActionLog actionLog) {
        actionLog.write(getOrderId(), "finish, status = " + getCommandType());
        return template
                .replaceAll("VAR_BASE64ORDER", base64Input)
                .replaceAll("VAR_ACTION_TIME", getFormattedDate())
                .replaceAll("VAR_MERCHANT_ID", getMerchantId())
                .replaceAll("VAR_AMOUNT", getAmount())
                .replaceAll("VAR_ORDER_ID", getOrderId())
                .replaceAll("VAR_REFERENCE", getReference())
                ;
    }

    private String getReference() {
        return document.getByXPath("/document/merchant/payment/@reference");
    }

    private String getCommandType() {
        return document.getByXPath("/document/merchant/command/@type");
    }

    private String getAmount() {
        return document.getByXPath("/document/merchant/payment/@amount").replaceAll(",", ".");
    }

    private String getOrderId() {
        return document.getByXPath("/document/merchant/payment/@order_id");
    }

    private String getMerchantId() {
        return document.getByXPath("/document/merchant/@benef");
    }

    private String getFormattedDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

}
