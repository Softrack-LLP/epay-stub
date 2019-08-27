### Description

A simple project for epay stubbing

### Usage

Setting up template for 'local' environment:

```
curl  'localhost:8080/local/manage/templates/create' -H 'Content-Type: application/xml' -d'<?xml version="1.0" encoding="UTF-8"?><document><error input="" payment="" system=""/><request Signed_Order_B64="VAR_BASE64ORDER"/><payment ActionTime="VAR_ACTION_TIME" MerchantID="VAR_MERCHANT_ID" OrderId="VAR_ORDER_ID" OrderAL="null" amount="VAR_AMOUNT" card_id="800000478288" Result="00" Reference="111111111111" int_reference="AAAAAAAAAAAAAA" approval_code="2222222" type="VAR_TYPE_ID" message="Approved" abonent_id="VAR_ABONENT_ID" terminal="null" phone="null" service_id="" sessionid="AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"/></document>'
curl  'localhost:8080/local/manage/templates/complete' -H 'Content-Type: application/xml' -d'<?xml version="1.0" encoding="UTF-8"?><document><error input="" payment="" system=""/><request Signed_Order_B64="VAR_BASE64ORDER"/><payment ActionTime="VAR_ACTION_TIME" MerchantID="VAR_MERCHANT_ID" OrderId="VAR_ORDER_ID" OrderAL="null" amount="VAR_AMOUNT" Result="00" Reference="VAR_REFERENCE" type="21" message="Approved"/></document>'
```

once it is set up 'localhost:8080/local' now can be used as an endpoint providing the following local pathes:

    /jsp/hbpay/trans.jsp
    /jsp/hbpay/proc.jsp
    
Setting delays:

```
curl localhost:8080/manage/delay/{500} -H 'Content-Type: application/plan' -d'/local/jsp/hbpay/trans.jsp'
```