package com.my.stufy.utlis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@Component
public class FileHelper {


    @Autowired
    private HttpServletResponse response;

    //下载单个文件
    public boolean download(String filename, String savePath) {
        File dir = new File(savePath);

        if (!dir.exists()) {
            new IllegalArgumentException("{}目录不存在" + savePath);
        }
        InputStream in = null;
        ServletOutputStream outputStream = null;
        File file = new File(savePath + File.separator + filename);
        try {
            in = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int len = 0;
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.addHeader("Content-Disposition", "attachment;filename=" + filename);
            outputStream = response.getOutputStream();
            if (file.length() == 0) {
                outputStream.write(in.available());
            } else {
                while ((len = in.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, len);
                }
            }
        } catch (Exception e) {
            return false;
        } finally {
            if (in != null || outputStream != null) {
                try {
                    in.close();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * 通过zip压缩的方式下载批量文件
     */
    public boolean downloadByZip(String filename, String savePath) throws IOException {
        File dir = new File(savePath);
        if (!dir.exists()) {
            new IllegalArgumentException("{}目录不存在" + savePath);
        }
        DataOutputStream os = null;
        File file = new File(savePath + File.separator + filename);
        try {
            //设置返回头信息
            response.setCharacterEncoding("utf-8");
            response.setContentType(MediaType.MULTIPART_FORM_DATA_VALUE);
            response.addHeader("Content-Disposition","attachment;filename=" + "data.zip");
            ZipOutputStream zipos = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream()));
            zipos.setMethod(ZipOutputStream.DEFLATED);
            zipos.putNextEntry(new ZipEntry(filename));
            os = new DataOutputStream(zipos);
            InputStream is = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length = 0;
            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }
            is.close();
            zipos.closeEntry();
        } catch (Exception ex) {
            return false;
        } finally {
            os.flush();//必须强制刷新
        }
        return true;
    }

}
