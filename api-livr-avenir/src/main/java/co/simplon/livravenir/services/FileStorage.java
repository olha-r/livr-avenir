package co.simplon.livravenir.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;

@Component
class FileStorage {

    private static final Map<String, String> TYPES;
    static {
	TYPES = new HashMap<>();
	TYPES.put(MediaType.IMAGE_JPEG_VALUE, "jpeg");
	TYPES.put(MediaType.IMAGE_PNG_VALUE, "png");
    }

    @Value("${livravenir.uploads.location}")
    private String uploadDir;

    private Path uploadPath;

    FileStorage() {
	//
    }

    @PostConstruct
    private void init() {
	this.uploadPath = Paths.get(uploadDir);
    }

    String store(MultipartFile file, String baseName) {
	String contentType = file.getContentType();
	String fullName = String.format("%s.%s", baseName,
		TYPES.get(contentType));
	Path target = uploadPath.resolve(fullName);
	try (InputStream in = file.getInputStream()) {
	    Files.copy(in, target,
		    StandardCopyOption.REPLACE_EXISTING);
	    return fullName;
	} catch (IOException ex) {
	    throw new RuntimeException(ex);
	}
    }

    void delete(String fullName) {
	Path target = uploadPath.resolve(fullName);
	try {
	    Files.delete(target);
	} catch (IOException ex) {
	    throw new RuntimeException(ex);
	}
    }

    String replace(MultipartFile file, String baseName,
	    String original) {
	String newFullName = store(file, baseName);
	delete(original);
	return newFullName;
    }
}
