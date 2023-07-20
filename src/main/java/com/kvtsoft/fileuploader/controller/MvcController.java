package com.kvtsoft.fileuploader.controller;

import com.kvtsoft.fileuploader.util.MultipartToFile;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@Controller
public class MvcController {

    @RequestMapping("/homepage")
    public String homepage() {
        return "homepage";
    }

    @PostMapping("/upload-excel-file")
    public String uploadFile(Model model, MultipartFile file) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        File inputFile = MultipartToFile.convert(file);
        byte[] fileContent = FileUtils.readFileToByteArray(inputFile);
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        System.out.println("encodedString: " + encodedString);
        HttpEntity<String> requestEntity
                = new HttpEntity<>(encodedString, headers);

        String serverUrl = "http://localhost:8080/process-excel";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate
                .postForEntity(serverUrl, requestEntity, String.class);

        // process the file
        return "submission";
    }
}
