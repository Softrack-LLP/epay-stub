package kz.softrackmfs.epaystub.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class EpayApplicationTest {

    @Test
    public void generateResponse() {
        String input = "Signed_Order_B64=PGRvY3VtZW50PjxtZXJjaGFudCBtYWluPSI5ODQ3OTAzNCIgYmVuZWY9Ijk4NDc5MDM1Ij48Y29tbWFuZCB0eXBlPSJjb21wbGV0ZSIvPjxwYXltZW50IHJlZmVyZW5jZT0iOTIzODgxMzQyNzM5IiBhcHByb3ZhbF9jb2RlPSI3NTEwMTQiIG9yZGVyX2lkPSIyMDM5NDk5IiBvcmRlcmFtb3VudD0iODAsMDAiIGFtb3VudD0iODAsMDAiIGN1cnJlbmN5PSJLWlQiLz48L21lcmNoYW50PjxtZXJjaGFudF9zaWduIHR5cGU9IlJTQSI+TmxLMHZSRm5hTWw0NERoOE96Q3NpbEpkdG4zakJ1Mm1wWVI3ajNCQjd1ejZadlYrNWZUVzdacjZ6dmN6WWY2L3FFZ0RMMzNpV0JsOG9MWmxGOWJoUkdRaVlPUDdwS3ZOR1c1M1NzWDhHVEI3VUtKdFA2eXpFZEFkQWlkTlpXSFpVb083amg4Q2xIdGN0ZE5ISGxxcHo2aG0xdU9lNzZ0NGNtNDRVeC9mb1NvPTwvbWVyY2hhbnRfc2lnbj48L2RvY3VtZW50Pg==";
        String response = EpayApplication.getApplication("local").generateCreateResponse(input);
        System.out.println("response = " + response);
    }

    @Test
    public void createTransactionRequest_whenValidStringPassed_canBeParsed() {
        String input = "Signed_Order_B64=PGRvY3VtZW50PjxtZXJjaGFudCBjZXJ0X2lkPSJjMTgzZDk1NyIgbmFtZT0iYWxscGF5Ij48b3JkZXIgb3JkZXJfaWQ9IjIwMzk0OTkiIGFtb3VudD0iODAsMDAiIGN1cnJlbmN5PSIzOTgiIHR5cGU9IjAiPjxkZXBhcnRtZW50IGFtb3VudD0iODAsMDAiIGFib25lbnRfaWQ9Ijc3NzcyODIxMTMzIiBtYWluPSI5ODQ3OTAzNCIgYmVuZWY9Ijk4NDc5MDM1IiBjYXJkX2lkPSI4MDAwMDA0NzgyODgiIHJlY2VwaWVudD0iNzc3NzI4MjExMzMiLz48L29yZGVyPjwvbWVyY2hhbnQ+PG1lcmNoYW50X3NpZ24gdHlwZT0iUlNBIj50ZmEzZGZwQUQ0cWpkMDlPMFpNbFMrTW5Laml4clgvRUxsMFhCaEw0TWErVzl6eVpRZEVMQUJqMVMvZ2llVThzaE8ydWlyczBtTmw4aXRadjFVd0JBb0kzTldzOXJjSmpEdDViWXhKZTN1ZldCUFg4bVRMcEQwQmk0OGdoZi8ralhoQzRPbnpzRks3bGFQY1lkUFFUZUllRTU4SnAvVzhNVlJKRUVTQ0swMTg9PC9tZXJjaGFudF9zaWduPjwvZG9jdW1lbnQ+";
        String response = EpayApplication.getApplication("local").generateCompleteResponse(input);
        System.out.println("response = " + response);
    }
}