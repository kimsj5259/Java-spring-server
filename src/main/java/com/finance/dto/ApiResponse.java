package com.finance.dto;

public class ApiResponse {

    private final int resultCode;
    private final String resultMsg;

    // 생성자에 token을 추가하고, 호출 시에 null을 전달할 수 있도록 변경
    public ApiResponse(int resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }
}
