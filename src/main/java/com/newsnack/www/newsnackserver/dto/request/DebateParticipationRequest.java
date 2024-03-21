package com.newsnack.www.newsnackserver.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DebateParticipationRequest(@Size(min = 1, max = 200) @NotBlank String content) {
}
