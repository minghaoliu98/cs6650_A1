package com.example.demo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@SpringBootApplication
@RestController
public class SpringServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringServerApplication.class, args);
	}
	@PostMapping("/swipe/leftorright")
	public ResponseEntity<String> hello(@RequestBody String params) {
		JsonObject json = new Gson().fromJson(params, JsonObject.class);
		int status = 400;
		if (validation(json)) {
			status = 201;
		}
		return ResponseEntity.status(status).body("HTTP Status will be CREATED (CODE 201)\n");
	}

	public boolean validation(JsonObject json) {
		try {
			String swipe = json.get("swipe").getAsString();
			if (!swipe.equals("Left") && !swipe.equals("Right")) {
				return false;
			}
			if (json.get("swiper").getAsInt() < 1 || json.get("swiper").getAsInt() > 5000) {
				return false;
			}
			if (json.get("swipee").getAsInt() < 1 || json.get("swipee").getAsInt() > 1000000) {
				return false;
			}
			if (json.get("comment").getAsString().length() != 256) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}