package com.newsnack.www.newsnackserver.dto.request;

import jakarta.validation.constraints.Max;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

public record DebateVoteRequest(boolean vote) {
}
