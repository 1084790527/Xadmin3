package com.yao;
/**
 * @author 妖妖
 * @date 11:35 2021/3/11
 */
import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.junit.platform.commons.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class DownLoad {
    private static Log log = LogFactory.getLog(DownLoad.class);

    //创建一个线程池对象
    static class DownLoadRunnable implements Runnable {

        private String url;
        private String fileName;
        private long start;//开始位置
        private long end;//结束位置

        public DownLoadRunnable(String url, String fileName, long start, long end) {
            this.url = url;
            this.fileName = fileName;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            boolean f = true;
            while (f){
                try {
//                    log.info("fileName : "+start+"   "+end);
                    URL httpUrl = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
                    connection.setRequestProperty("Accept-Encoding", "identity");
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.79 Safari/537.1");
                    connection.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
                    //设置下载位置  向服务器拿到指定的流信息
                    connection.setRequestProperty("Range", "bytes=" + start + "-" + end);
                    //创建一个文件  根据指定位置写入信息
                    RandomAccessFile accessFile = new RandomAccessFile(new File(fileName), "rws");
                    accessFile.seek(start);//设置读写的位置
                    InputStream inputStream = connection.getInputStream();
                    byte[] b = new byte[1024 * 4];//设置缓冲区的大小存
                    int len = 0;
                    while ((len = inputStream.read(b)) != -1) {
                        accessFile.write(b, 0, len);
                    }
                    if (accessFile != null) {
                        accessFile.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    f = false;
                } catch (MalformedURLException e) {
//                    e.printStackTrace();
                } catch (IOException e) {
//                    e.printStackTrace();
                }
            }
        }
    }
    public void downLoadFile(String url,String filePath,String fileName){
        ExecutorService threadPool = null;
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept-Encoding", "identity");
            connection.setReadTimeout(5000);
            connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.79 Safari/537.1");
            connection.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            int count = connection.getContentLength();//获取了图片的大小
//            System.out.println("count : "+count);
            //1024 字节 * 512 = 1kb * 512 = 0.5MB
            //524288 = 0.5MB     1048576 = 1MB
            int z = count/524288;
            int block = 0;
            if (count == -1){
                z = 1;
                block = -1;
            }else {
                block = count/z;
            }
            System.out.println("count : "+count);
            System.out.println("block : "+block);
            threadPool = Executors.newFixedThreadPool(500);
//            System.out.println(connection.getHeaderField("Content-Length"));
            String ext = "";
            try {
                String type = connection.getHeaderField("Content-Type");
                type = replacementType(type);
                MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
                MimeType jpeg = allTypes.forName(type);
                ext = jpeg.getExtension();
                if ("".equals(ext))
                    ext = url.substring(url.lastIndexOf("."));
            } catch (Exception e) {
                ext = ".jpg";
            }
//            for (Map.Entry<String, List<String>> entry : connection.getHeaderFields().entrySet()) {
//                System.out.println(entry.getKey()+"   "+ JSON.toJSONString(entry.getValue()));
//            }

            //算线程的启始位置
            if (StringUtils.isBlank(fileName))
                fileName = getFileName(url);
            else
                fileName += ext;
            File fileDownLoad = new File(filePath,fileName);
//            System.out.println(fileDownLoad.toPath());
            List<Future> futures = new ArrayList<>();
            for (int i=0;i<z;i++){
                long start = i*block;
                long end = (i+1)*block -1;
                if (i==z-1){
                    end = count;
                }
                DownLoadRunnable runnable = new DownLoadRunnable(url, fileDownLoad.getAbsolutePath(),start,end);
                //提交任务
                futures.add(threadPool.submit(runnable));
            }
            for (Future future : futures) {
                try {
                    future.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (threadPool != null){
                threadPool.shutdown();
                threadPool = null;
            }
        }
    }
    /**
     * 获取 URL后缀名
     */
    private String getFileName(String url){
        return url.substring(url.lastIndexOf("/")+1);
    }
    private String replacementType(String type){
        return type.replaceAll("image/jpg","image/jpeg");
    }
}
