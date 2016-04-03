package com.easybuy.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.border.StrokeBorder;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.csource.fastdfs.test.UploadLocalFileSender;
import org.junit.Test;

import sun.print.resources.serviceui_pt_BR;

/**
 * 
 * <p>Title: FastDfsClient</p>
 * <p>Description: </p>
 * <p>Company: www.evanshare.com</p> 
 * @author	Evan
 * @date	2016年3月2日上午12:56:22
 * @version 1.0
 */
public class FastDfsClient {
	
	
	private TrackerClient trackerClient = null;
	//Create Tracker server
	private TrackerServer trackerServer = null;
	//Create Storage server
	private StorageServer storageServer = null;
	//Create Storage Client
	private StorageClient storageClient1 =null;
	
	public FastDfsClient(){
		
	}
	public FastDfsClient(String conf) throws FileNotFoundException, IOException, Exception{
		if(conf.contains("classpath:")){
			conf = conf.replace("classpath:", this.getClass().getResource("/").getPath());
		}
		ClientGlobal.init(conf);
		//Create Tracker Client
		 trackerClient = new TrackerClient();
		//Create Tracker server
		 trackerServer = trackerClient.getConnection();
		//Create Storage server
		 storageServer = null;
		//Create Storage Client
		 storageClient1 = new StorageClient(trackerServer,storageServer);
	}
	
	public String UploadFile(byte[] file_buff,String file_ext_name){
		String url =null;
		try {
			
		String[] infoStrs =	storageClient1.upload_file(file_buff, file_ext_name, null);
		 url = infoStrs[0]+"/"+infoStrs[1];
		
		
		System.out.println(url);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return url;
	}
	
	public static void main(String[] args) throws Exception {
		//FastDfsClient fastDfsClient = new FastDfsClient("classpath:resources/resource/client.conf");
		FastDfsClient fastDfsClient = new FastDfsClient("D:\\Users\\10856214\\workspace-template\\easybuy-manager\\easybuy-manager-web\\src\\main\\resources\\resource\\client.conf");
		byte[] fileByteArray = getByte("C:\\Users\\10856214\\Desktop\\activeMq1.png");
		fastDfsClient.UploadFile(fileByteArray, "jpg");
	}
	
	public static byte[] getByte(String filePath) throws IOException{
		File file  = new File(filePath);
		FileInputStream in  = new FileInputStream(file);
		ByteArrayOutputStream out = new ByteArrayOutputStream(10000);
	    byte[] buff = new byte[1000];
	    int n ;
		while((n=in.read(buff))!=-1){
			
			out.write(buff, 0, n);
		}
		in.close();
		out.close();
		
		byte[] fileByteArray = out.toByteArray();
		
		
		return fileByteArray;
	}
	

}
