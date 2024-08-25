package com.example.bfhl;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    @GetMapping
    public Map<String, Integer> getOperationCode() {
        Map<String, Integer> response = new HashMap<>();
        response.put("operation_code", 1);
        return response;
    }

    @PostMapping
    public Map<String, Object> processData(@RequestBody Map<String, List<String>> requestData) {
        Map<String, Object> response = new HashMap<>();

        String fullName = "john_doe";
        String dob = "17091999";
        String userId = fullName + "_" + dob;
        
        response.put("is_success", true);
        response.put("user_id", userId);
        response.put("email", "john@xyz.com");
        response.put("roll_number", "ABCD123");

        List<String> numbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        String highestLowercase = null;

        for (String item : requestData.get("data")) {
            if (item.matches("\\d+")) {
                numbers.add(item);
            } else if (item.matches("[a-zA-Z]")) {
                alphabets.add(item);
                if (item.matches("[a-z]")) {
                    if (highestLowercase == null || item.compareTo(highestLowercase) > 0) {
                        highestLowercase = item;
                    }
                }
            }
        }

        response.put("numbers", numbers);
        response.put("alphabets", alphabets);
        response.put("highest_lowercase_alphabet", highestLowercase == null ? Collections.emptyList() : Collections.singletonList(highestLowercase));

        return response;
    }
}
