package org.llin.twelvequotes.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {

	@GetMapping("/error")
	public String handleError(HttpStatus status) {
		String page = "500";

		switch (status) {

		// 100 103 102 101
		case CONTINUE, EARLY_HINTS, PROCESSING, SWITCHING_PROTOCOLS:
			break;
		// 202 208 201 226 207
		case ACCEPTED, ALREADY_REPORTED, CREATED, IM_USED, MULTI_STATUS,
				// 204 200 206 205
				NON_AUTHORITATIVE_INFORMATION, NO_CONTENT, OK, PARTIAL_CONTENT, RESET_CONTENT:
			break;
		// 302 301 300 304
		case FOUND, MOVED_PERMANENTLY, MULTIPLE_CHOICES, NOT_MODIFIED,
				// 308 303 307
				PERMANENT_REDIRECT, SEE_OTHER, TEMPORARY_REDIRECT:
			break;
		// 400 409 417 424 403 410
		case BAD_REQUEST, CONFLICT, EXPECTATION_FAILED, FAILED_DEPENDENCY, FORBIDDEN, GONE,
				// 431 423 405 404 413 402
				LENGTH_REQUIRED, LOCKED, METHOD_NOT_ALLOWED, NOT_ACCEPTABLE, NOT_FOUND,
				// 412 428 431 408
				PAYLOAD_TOO_LARGE, PAYMENT_REQUIRED, PROXY_AUTHENTICATION_REQUIRED, PRECONDITION_FAILED,
				// 428 416 431
				PRECONDITION_REQUIRED, REQUESTED_RANGE_NOT_SATISFIABLE, REQUEST_HEADER_FIELDS_TOO_LARGE,
				// 408 425 429 426 401
				REQUEST_TIMEOUT, TOO_EARLY, TOO_MANY_REQUESTS, UPGRADE_REQUIRED, UNAUTHORIZED,
				// 451 422 415 414
				UNAVAILABLE_FOR_LEGAL_REASONS, UNPROCESSABLE_ENTITY, UNSUPPORTED_MEDIA_TYPE, URI_TOO_LONG:
			page = "400";
			break;
		// 502 504 505 507 500
		case BAD_GATEWAY, GATEWAY_TIMEOUT, HTTP_VERSION_NOT_SUPPORTED, INSUFFICIENT_STORAGE, INTERNAL_SERVER_ERROR,
				// 508 511 510 501 503
				LOOP_DETECTED, NETWORK_AUTHENTICATION_REQUIRED, NOT_EXTENDED, NOT_IMPLEMENTED, SERVICE_UNAVAILABLE,
				// 506
				VARIANT_ALSO_NEGOTIATES:
			page = "500";
			break;

		default:
			break;

		}
		// You can add any logic here before returning the error page
		return page; // Thymeleaf template name (error.html)
	}

}
