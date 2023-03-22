package com.eighteen.userservice.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.eighteen.userservice.dto.MusicDto;
import com.eighteen.userservice.dto.request.RequestUpdateProfileDto;
import com.eighteen.userservice.dto.response.ResponseProfileDto;
import com.eighteen.userservice.entity.User;
import com.eighteen.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Autowired
    private AmazonS3 amazonS3;

    public ResponseProfileDto getProfile(String userId) {

        User user = userRepository.findByUserId(userId);
        ResponseProfileDto responseProfileDto = new ModelMapper().map(user, ResponseProfileDto.class);
        return responseProfileDto;
    }

    public String checkNickname(String userId, String nickname) {

        User user = userRepository.findByNickname(nickname);
        String res = "ok";
        if (user == null) {
            res = "no";
        }
        return res;
    }

    public String updateProfile(RequestUpdateProfileDto requestUpdateProfileDto) {

        User user = userRepository.findByUserId(requestUpdateProfileDto.getUserId());
        user.updateProfile(requestUpdateProfileDto);
        userRepository.save(user);
        return "ok";
    }

    public String updateImage(String userId, MultipartFile profileImage) throws IOException {

        User user = userRepository.findByUserId(userId);
        String s3FileName = userId;
        boolean isExistObject = amazonS3.doesObjectExist(bucket, s3FileName);
        if (isExistObject) {
            amazonS3.deleteObject(bucket, s3FileName);
        }
        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(profileImage.getInputStream().available());
        amazonS3.putObject(bucket, s3FileName, profileImage.getInputStream(), objMeta);
        amazonS3.setObjectAcl(bucket, s3FileName, CannedAccessControlList.PublicRead);
        return amazonS3.getUrl(bucket, s3FileName).toString();
    }
}
