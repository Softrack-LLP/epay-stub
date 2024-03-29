### Description

A simple project for [epay](https://epay.kkb.kz/) stubbing.
Right now only [this](https://testpay.kkb.kz/doc/htm/interf_savecard.html) part is implemented.

### Usage

Setting up template for 'local' environment(not necessary - by default it is configured to always reply with 'SUCCESS' responses):

```
curl  'localhost:8080/local/manage/templates/create' -H 'Content-Type: application/xml' -d'<?xml version="1.0" encoding="UTF-8"?><document><error input="" payment="" system=""/><request Signed_Order_B64="VAR_BASE64ORDER"/><payment ActionTime="VAR_ACTION_TIME" MerchantID="VAR_MERCHANT_ID" OrderId="VAR_ORDER_ID" OrderAL="null" amount="VAR_AMOUNT" card_id="800000478288" Result="00" Reference="111111111111" int_reference="AAAAAAAAAAAAAA" approval_code="2222222" type="VAR_TYPE_ID" message="Approved" abonent_id="VAR_ABONENT_ID" terminal="null" phone="null" service_id="" sessionid="AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"/></document>'
curl  'localhost:8080/local/manage/templates/complete' -H 'Content-Type: application/xml' -d'<?xml version="1.0" encoding="UTF-8"?><document><error input="" payment="" system=""/><request Signed_Order_B64="VAR_BASE64ORDER"/><payment ActionTime="VAR_ACTION_TIME" MerchantID="VAR_MERCHANT_ID" OrderId="VAR_ORDER_ID" OrderAL="null" amount="VAR_AMOUNT" Result="00" Reference="VAR_REFERENCE" type="21" message="Approved"/></document>'
curl  'localhost:8080/local/manage/templates/cardList' -H 'Content-Type: application/xml' -d'<?xml version="1.0" encoding="UTF-8"?><document><error/><request recepient="" sessionid="null" id="VAR_MERCHANT_ID" abonent_id="VAR_ABONENT_ID" action="list" card_id="null" order_id=""/><cards><item MerchantID="VAR_MERCHANT_ID" HBID="VAR_ABONENT_ID" CardID="900000015420" CardMask="440540-xx-xxxx-0846" e_mm="01" e_yy="14" approve="0" reference="180919184238" card_kkb="no"/></cards></document>'
```

Endpoints 'localhost:8080/local' can be used as an endpoint providing the following local pathes:

    /jsp/hbpay/trans.jsp
    /jsp/hbpay/proc.jsp
    /jsp/hbpay/control.jsp
    
Setting delays in milliseconds:

```
curl localhost:8080/manage/delay/500 -H 'Content-Type: application/plan' -d'/local/jsp/hbpay/trans.jsp'
```

Stats:

```
curl localhost:8080/local/stats/actionLog
```