package com.newsnack.www.newsnackserver.dto.request;

import com.newsnack.www.newsnackserver.domain.member.model.Provider;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(@NotNull Provider provider, String name) {
}
