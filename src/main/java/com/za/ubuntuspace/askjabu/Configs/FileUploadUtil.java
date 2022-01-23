package com.za.ubuntuspace.askjabu.Configs;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

public class FileUploadUtil {

    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        try{
            InputStream inputStream = multipartFile.getInputStream();
            Path filePath = uploadPath.resolve(fileName);

            System.out.println(filePath);

            Files.copy(inputStream,filePath,StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException e){
            throw new IOException("Could Not Save Image File : "+fileName);
        }
    }
}
