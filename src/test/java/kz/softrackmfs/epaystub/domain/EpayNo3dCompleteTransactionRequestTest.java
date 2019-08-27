package kz.softrackmfs.epaystub.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class EpayNo3dCompleteTransactionRequestTest {

    @Test
    public void generateResponse() {
        String input = "Signed_Order_B64=PGRvY3VtZW50PjxtZXJjaGFudCBtYWluPSI5ODQ3OTAzNCIgYmVuZWY9Ijk4NDc5MDM1Ij48Y29tbWFuZCB0eXBlPSJjb21wbGV0ZSIvPjxwYXltZW50IHJlZmVyZW5jZT0iOTIzODgxMzQyNzM5IiBhcHByb3ZhbF9jb2RlPSI3NTEwMTQiIG9yZGVyX2lkPSIyMDM5NDk5IiBvcmRlcmFtb3VudD0iODAsMDAiIGFtb3VudD0iODAsMDAiIGN1cnJlbmN5PSJLWlQiLz48L21lcmNoYW50PjxtZXJjaGFudF9zaWduIHR5cGU9IlJTQSI+TmxLMHZSRm5hTWw0NERoOE96Q3NpbEpkdG4zakJ1Mm1wWVI3ajNCQjd1ejZadlYrNWZUVzdacjZ6dmN6WWY2L3FFZ0RMMzNpV0JsOG9MWmxGOWJoUkdRaVlPUDdwS3ZOR1c1M1NzWDhHVEI3VUtKdFA2eXpFZEFkQWlkTlpXSFpVb083amg4Q2xIdGN0ZE5ISGxxcHo2aG0xdU9lNzZ0NGNtNDRVeC9mb1NvPTwvbWVyY2hhbnRfc2lnbj48L2RvY3VtZW50Pg==";
        String response = new EpayNo3dCompleteTransactionRequest(input).generateResponse();
        System.out.println("response = " + response);
    }
}