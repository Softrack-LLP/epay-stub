package kz.softrackmfs.epaystub.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class EpayNo3dCreateTransactionRequestTest {
    @Test
    public void createTransactionRequest_whenValidStringPassed_canBeParsed() {
        String input = "Signed_Order_B64=PGRvY3VtZW50PjxtZXJjaGFudCBjZXJ0X2lkPSJjMTgzZDk1NyIgbmFtZT0iYWxscGF5Ij48b3JkZXIgb3JkZXJfaWQ9IjIwMzk0OTkiIGFtb3VudD0iODAsMDAiIGN1cnJlbmN5PSIzOTgiIHR5cGU9IjAiPjxkZXBhcnRtZW50IGFtb3VudD0iODAsMDAiIGFib25lbnRfaWQ9Ijc3NzcyODIxMTMzIiBtYWluPSI5ODQ3OTAzNCIgYmVuZWY9Ijk4NDc5MDM1IiBjYXJkX2lkPSI4MDAwMDA0NzgyODgiIHJlY2VwaWVudD0iNzc3NzI4MjExMzMiLz48L29yZGVyPjwvbWVyY2hhbnQ+PG1lcmNoYW50X3NpZ24gdHlwZT0iUlNBIj50ZmEzZGZwQUQ0cWpkMDlPMFpNbFMrTW5Laml4clgvRUxsMFhCaEw0TWErVzl6eVpRZEVMQUJqMVMvZ2llVThzaE8ydWlyczBtTmw4aXRadjFVd0JBb0kzTldzOXJjSmpEdDViWXhKZTN1ZldCUFg4bVRMcEQwQmk0OGdoZi8ralhoQzRPbnpzRks3bGFQY1lkUFFUZUllRTU4SnAvVzhNVlJKRUVTQ0swMTg9PC9tZXJjaGFudF9zaWduPjwvZG9jdW1lbnQ+";
        EpayNo3dCreateTransactionRequest request = new EpayNo3dCreateTransactionRequest(input);
        System.out.println("request.generateResponse() = " + request.generateResponse());
        System.out.println("request.getTemplate() = " + request.getTemplate());
    }
}