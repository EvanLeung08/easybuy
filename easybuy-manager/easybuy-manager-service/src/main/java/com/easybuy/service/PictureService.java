package com.easybuy.service;

import org.springframework.web.multipart.MultipartFile;

import com.easybuy.pojo.PictureResult;

public interface PictureService {

	PictureResult uploadPic(MultipartFile picFile);

}
