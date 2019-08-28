package kz.softrackmfs.epaystub.domain.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActionLog {

    private List<String> rows = new ArrayList<>();

    public void write(String orderId, String operationName) {
        rows.add(getDate() + " | " + orderId + " : " + operationName);
    }

    private String getDate() {
        return new Date().toString();
    }

    public String getAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String row : rows) {
            stringBuilder.append(row).append("\n");
        }
        return stringBuilder.toString();
    }
}
