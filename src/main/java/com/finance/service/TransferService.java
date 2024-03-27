package com.finance.service;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.common.ApiResponse;
import com.finance.model.Order;
import com.finance.model.User;
import com.finance.repository.TransferRepository;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    private final String QUOTATION_API_URL = "https://quotation-api-cdn.dunamu.com:443/v1/forex/recent?codes=,FRX.KRW";

    @Transactional
    public ApiResponse calculateQuote(BigDecimal amount, String targetCurrency) {
        try {
            // Quotation API 호출
            String apiUrl = QUOTATION_API_URL + (targetCurrency.equals("JPY") ? "JPY" : "USD");
            String quotationApiResponse = new RestTemplate().getForObject(apiUrl, String.class);

            // JSON 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> quotationList = objectMapper.readValue(quotationApiResponse, List.class);

            // 필요한 정보 추출
            BigDecimal basePrice = (BigDecimal) quotationList.get(0).get("basePrice");
            Integer currencyUnit = (Integer) quotationList.get(0).get("currencyUnit");

            int fixedCharge = "JPY".equals(targetCurrency) ? 3000 : "USD".equals(targetCurrency) ? 1000 : 0;

            // 반환
            return new ApiResponse(200, "OK");
        } catch (Exception e) {
            return new ApiResponse(400, e.getMessage());
        }   
    }
    @Transactional
    public ApiResponse sendingMoney(Order orderReqeust) {
        return new ApiResponse(200, "OK");
    }
}
