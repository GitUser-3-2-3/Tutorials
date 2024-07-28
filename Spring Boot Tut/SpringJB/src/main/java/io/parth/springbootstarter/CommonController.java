package io.parth.springbootstarter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

public class CommonController {

    @NonNull
    public static ResponseEntity<Map<String, String>> getMapPostEntity(Boolean isAdded) {
        Map<String, String> response = new HashMap<>();

        if (!isAdded) {
            response.put("message", "Couldn't Add");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            response.put("message", "Added Successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @NonNull
    public static ResponseEntity<Map<String, String>> getMapUpdateEntity(Boolean isUpdated) {
        Map<String, String> response = new HashMap<>();

        if (!isUpdated) {
            response.put("message", "Couldn't Update");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            response.put("message", "Updated Successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @NonNull
    public static ResponseEntity<Map<String, String>> getMapDeleteEntity(Boolean isDeleted) {
        Map<String, String> response = new HashMap<>();

        if (!isDeleted) {
            response.put("message", "Couldn't Delete");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            response.put("message", "Deleted Successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
