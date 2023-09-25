package co.simplon.livravenir.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileStorage {

    private static final Map<String, String> TYPES;
    static {
	TYPES = new HashMap<>();
	TYPES.put(MediaType.IMAGE_JPEG_VALUE, "jpeg");
	TYPES.put(MediaType.IMAGE_PNG_VALUE, "png");
    }

    @Value("${livravenir.uploads.location}")
    private String uploadDir;

    private Path uploadPath;

    public FileStorage() {
	// TODO Auto-generated constructor stub
    }

    String store(MultipartFile file, String fileName) {
	/*
	 * String contentType = file.getContentType(); String fullName =
	 * String.format("%s.%s", fileName, TYPES.get(contentType));
	 */

	Path target = uploadPath.resolve(fileName);
	try (InputStream in = file.getInputStream()) {

	    Files.copy(in, target,
		    StandardCopyOption.REPLACE_EXISTING);
	    return fileName;
	} catch (IOException ex) {
	    throw new RuntimeException(ex);
	}

    }

    String replace(MultipartFile file, String fileName,
	    String originalName) {
	String newFullName = store(file, fileName);
	delete(originalName);
	return newFullName;
    }

    void delete(String fileName) {
	Path target = uploadPath.resolve(fileName);
	try {
	    Files.delete(target);
	} catch (IOException ex) {
	    throw new RuntimeException(ex);
	}

    }

}
