package kz.softrackmfs.epaystub.domain;

import kz.softrackmfs.epaystub.domain.utils.ParsedDocument;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class EpayCardListRequest {

    private final ParsedDocument document;

    public EpayCardListRequest(String input) {
        this.document = new ParsedDocument(input);
    }

    public String generateResponse(String template) {
        return template
                .replaceAll("VAR_MERCHANT_ID", getMerchantId())
                .replaceAll("VAR_ABONENT_ID", getAbonentId())
                ;
    }

    private String getAbonentId() {
        return document.getByXPath("/document/merchant/client/@abonent_id");
    }

    private String getMerchantId() {
        return document.getByXPath("/document/merchant/@id");
    }
}
