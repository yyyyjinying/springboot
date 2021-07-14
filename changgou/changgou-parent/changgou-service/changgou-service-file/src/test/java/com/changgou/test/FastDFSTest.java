package com.changgou.test;


import com.changgou.util.FastDFSClient;
import lombok.extern.slf4j.Slf4j;
import org.csource.fastdfs.FileInfo;
import org.junit.Test;

import java.io.*;

@Slf4j
public class FastDFSTest {
    @Test
    public void getTrackerUrl(){
        String trackerUrl = FastDFSClient.getTrackerUrl();
        log.debug(trackerUrl);

    }

    @Test
    public void downLoad(){
// "data": "http://172.16.147.138:8080/group1/M00/00/00/rBCTimDtP9qASTObAACtGdmsI9o252.png"
        InputStream is = FastDFSClient.downFile("group1", "M00/00/00/rBCTimDtP9qASTObAACtGdmsI9o252.png");
        try {
            FileOutputStream os = new FileOutputStream(new File("/Users/yyyyjinying/desktop","aa.png"));

            int index = -1;
            while((index = is.read())!=-1){
                os.write(index);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getFile(){
        FileInfo file = FastDFSClient.getFile("group1", "M00/00/00/rBCTimDtP9qASTObAACtGdmsI9o252.png");
        log.debug(file.toString());
    }

    @Test
    public void deleteFile(){
        FastDFSClient.deleteFile("group1", "M00/00/00/rBCTimDtTP6ADFCpAACtGdmsI9o597.png");
    }



}
