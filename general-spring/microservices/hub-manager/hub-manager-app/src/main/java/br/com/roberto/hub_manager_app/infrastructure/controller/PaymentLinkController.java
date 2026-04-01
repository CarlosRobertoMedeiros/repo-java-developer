package br.com.roberto.hub_manager_app.infrastructure.controller;

import br.com.roberto.hub_manager_app.infrastructure.dto.request.HubManagerRequest;
import br.com.roberto.hub_manager_app.infrastructure.dto.response.HubManagerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/")
public class HubManagerController {

    @PostMapping
    public ResponseEntity<HubManagerResponse> createHub(@RequestBody HubManagerRequest hubManagerRequest){
        return null;
    }


}
