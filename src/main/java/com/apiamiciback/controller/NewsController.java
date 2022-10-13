package com.apiamiciback.controller;

import com.apiamiciback.dto.NewsRequestDto;
import com.apiamiciback.model.News;
import com.apiamiciback.service.FileStorageService;
import com.apiamiciback.service.NewService;
import com.apiamiciback.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/news")
@Slf4j
public class NewsController {

    @Autowired
    private NewService newService;

    @Autowired
    FileStorageService storageService;

    @GetMapping("/getAllNews")
    public ResponseEntity<List<News>> getAllNews(){

        log.info("Call get all news.");
        return ResponseEntity.ok().body(newService.getAllNews());
    }

    @GetMapping("/getLastNews")
    public ResponseEntity<News>getLastNews()
    {
        log.info("Call getLastNews");
        return ResponseEntity.ok(newService.getLastNew());
    }
    @GetMapping(value = "/getNews/{id}", consumes = {"application/json"})
    public ResponseEntity<News> getNews(@PathVariable int id){

        log.info("Call get news id : ",id);
        return ResponseEntity.ok().body(newService.getNew(id));
    }
    @PostMapping(value = "/saveNews", consumes = {"multipart/form-data"})
    public ResponseEntity<?>saveNews(@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("file") MultipartFile file){
        log.info("Call save news for : {}.", title + ' ' + content + " "  + file.getOriginalFilename());

        String message = "";
        String url = "";
        try {
            url = storageService.save(file, file.getOriginalFilename());

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            log.info(message + "URL : " + url);

        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            log.error(message);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }

        NewsRequestDto newsRequestDto = NewsRequestDto.builder()
                .content(content)
                .title(title)
                .createdAt(new Date(System.currentTimeMillis()))
                .url(url)
                .build();
        log.info(newsRequestDto.getContent());


        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/news/saveNews").toUriString());
        return ResponseEntity.created(uri).body(newService.saveNews(newsRequestDto));
    }

    @PutMapping(value = "/updateNews/{id}",consumes = {"application/json"})
    public ResponseEntity<News>updateNews(@RequestBody  @Valid News news, @PathVariable int id){

        log.info("Call update news for : {}", news.getTitle());
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/news/updateNews").toUriString());
        return ResponseEntity.created(uri).body(newService.updateNews(news, id));
    }

    @GetMapping(value= "/files/{fileCode}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable("fileCode") String fileCode) {
        log.info("Je suis lé");
        Resource resource = null;
        try{
            resource = storageService.getFileAsResource(fileCode);
            log.info("ressoirce " + resource);
        }catch (IOException e) {
            log.error("error" + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        if (resource == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile multipartFile)
             {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();

                 String filecode = null;
                 try {
                     filecode = FileUploadUtil.saveFile(fileName, multipartFile);
                 } catch (IOException e) {
                     e.printStackTrace();
                 }

                 return  ResponseEntity.ok().body("/uploads/" + filecode);
    }

    @DeleteMapping("/deleteNews/{id}")
    public ResponseEntity<?>deleteBoolNew (@PathVariable int id){

        log.info("Delete news {}", id);

        newService.deleteNews(id);

        return ResponseEntity.ok().body( id + " News deleted");
    }
}
