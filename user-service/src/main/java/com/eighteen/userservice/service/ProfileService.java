package com.eighteen.userservice.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.eighteen.userservice.dto.MusicDto;
import com.eighteen.userservice.dto.request.RequestUpdateProfileDto;
import com.eighteen.userservice.dto.response.ResponseProfileDto;
import com.eighteen.userservice.entity.Music;
import com.eighteen.userservice.entity.MyEighteen;
import com.eighteen.userservice.entity.User;
import com.eighteen.userservice.repository.MusicRepository;
import com.eighteen.userservice.repository.MyEighteenRepository;
import com.eighteen.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Autowired
    private AmazonS3 amazonS3;
    private final MusicRepository musicRepository;
    private final MyEighteenRepository myEighteenRepository;

    public ResponseProfileDto getProfile(String userId) {

        User user = userRepository.findByUserId(userId);
        ResponseProfileDto responseProfileDto = new ModelMapper().map(user, ResponseProfileDto.class);
        return responseProfileDto;
    }

    public String checkNickname(String nickname) {

        User user = userRepository.findByNickname(nickname);
        String res = "ok";
        if (user == null) {
            res = "no";
        }
        return res;
    }

    public String updateProfile(String userId, RequestUpdateProfileDto requestUpdateProfileDto, Optional<MultipartFile> image) throws IOException{

        User user = userRepository.findByUserId(userId);
        if (image.isPresent()) {
            MultipartFile profileImage = image.get();
            String s3FileName = UUID.randomUUID() + "-" + profileImage.getOriginalFilename();
            ObjectMetadata objMeta = new ObjectMetadata();
            objMeta.setContentLength(profileImage.getInputStream().available());
            amazonS3.putObject(bucket, s3FileName, profileImage.getInputStream(), objMeta);
            amazonS3.setObjectAcl(bucket, s3FileName, CannedAccessControlList.PublicRead);
            user.updateImage(amazonS3.getUrl(bucket, s3FileName).toString());
        }
        user.updateProfile(requestUpdateProfileDto);
        userRepository.save(user);
        return "ok";
    }

    public List<MusicDto> getHistory(String userId, Optional<List<Integer>> musicList) {

        User user = userRepository.findByUserId(userId);
        List<MusicDto> musicDtos = new ArrayList<>();
        if (musicList.isPresent()) {
            List<Integer> musics = musicList.get();
            for (Integer musicId : musics) {
                Music music = musicRepository.findByMusicId(musicId);
                MyEighteen myEighteen = myEighteenRepository.findByUserAndMusic(user, music);
                MusicDto musicDto = new MusicDto(music, Boolean.TRUE);
                if (myEighteen == null) {
                    musicDto.setIsEighteen(Boolean.FALSE);
                }
                musicDtos.add(musicDto);
            }
        }
        return musicDtos;
    }

    public void withdrawal(String userId) {

        User user = userRepository.findByUserId(userId);
        userRepository.delete(user);
    }
}
