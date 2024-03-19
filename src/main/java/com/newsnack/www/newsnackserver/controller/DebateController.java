package com.newsnack.www.newsnackserver.controller;

import com.newsnack.www.newsnackserver.common.code.success.DebateSuccessCode;
import com.newsnack.www.newsnackserver.common.response.NewSnackResponse;
import com.newsnack.www.newsnackserver.dto.response.DebateIndividualResponse;
import com.newsnack.www.newsnackserver.dto.response.DebateMainPageResponse;
import com.newsnack.www.newsnackserver.service.DebateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/debates")
@RequiredArgsConstructor
public class DebateController {

    private final DebateService debateService;

    @GetMapping
    public NewSnackResponse<List<DebateMainPageResponse>> getDebates() {
        return NewSnackResponse.success(DebateSuccessCode.GET_DEBATES_SUCCESS, debateService.getDebates());
    }

    @GetMapping("/main")
    public NewSnackResponse<DebateMainPageResponse> getMainDebate() {
        return NewSnackResponse.success(DebateSuccessCode.GET_DEBATES_SUCCESS, debateService.getMainDebate());
    }

    @GetMapping("/{debateId}")
    public NewSnackResponse<DebateIndividualResponse> getDebate(@PathVariable Long debateId) {
        return NewSnackResponse.success(DebateSuccessCode.GET_DEBATES_SUCCESS, debateService.getDebate(debateId));
    }
}
