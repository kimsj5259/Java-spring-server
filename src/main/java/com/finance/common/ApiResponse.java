package com.finance.common;

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

    // token이 null이거나 resultCode가 200이 아니면 해당 키를 반환하지 않도록 수정
    // public String getToken() {
    //     return (token == null || resultCode != 200) ? null : token;
    // }
}
