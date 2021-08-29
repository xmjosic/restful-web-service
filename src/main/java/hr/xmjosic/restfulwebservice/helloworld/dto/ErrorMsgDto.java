package hr.xmjosic.restfulwebservice.helloworld.dto;

import java.time.Instant;

/**
 * Error message object.
 */
public record ErrorMsgDto(int status, String errorMsg, String path, Instant timestamp) {
}
