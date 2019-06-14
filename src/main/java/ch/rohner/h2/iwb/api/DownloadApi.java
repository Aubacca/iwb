package ch.rohner.h2.iwb.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("download")
public class DownloadApi {

    @GetMapping("file")
    public void downloadFile(@RequestParam("fileName") String fileName, HttpServletResponse httpServletResponse) throws IOException, URISyntaxException {
        System.out.println("DownloadApi.downloadFile>fileName " + fileName);
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        httpServletResponse.getOutputStream().write(getFileContent(fileName));
    }

    private byte[] getFileContent(String fileName) throws URISyntaxException, IOException {
        return Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(fileName).toURI()));
    }
}
