package com.rahul.aiapiassistant.service;

import com.rahul.aiapiassistant.dto.request.ApiDesignRequest;
import com.rahul.aiapiassistant.dto.response.ApiDesignResponse;

public interface ApiAssistantService {
    ApiDesignResponse generateApiDesign(ApiDesignRequest request);
}