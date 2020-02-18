package service;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import constant.BOARD_TYPE;
import constant.USER_TYPE;
import domain.entity.HiaBoardEntity;
import domain.repo.BoardRepository;
import domain.repo.HiaAdminEntityRepository;
import domain.repo.HiaProfessorEntityRepository;
import domain.repo.HiaStudentEntityRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import request.BoardRequest;
import response.BoardPageResponse;
import response.BoardResponse;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ohseoklee on 2018-12-07.
 */

@Service
public class BoardService {

    @Resource
    private BoardRepository boardRepository;

    @Resource
    private HiaAdminEntityRepository adminRepository;

    @Resource
    private HiaProfessorEntityRepository professorRepository;

    @Resource
    private HiaStudentEntityRepository studentRepository;

    @Value("access_key")
    private String accessKey;

    @Value("secret_key")
    private String secretKey;

    @Transactional
    public BoardPageResponse getBoard(BOARD_TYPE type, int page) {
        PageRequest pageRequest = new PageRequest(page, 10);

        Page<HiaBoardEntity> boardEntities = boardRepository.findAllByType(type, pageRequest);
        BoardPageResponse boardPageResponse = new BoardPageResponse();

        boardPageResponse.init(boardEntities);
        List<BoardResponse> boardResponses = new ArrayList<>();

        boardEntities.forEach(hiaBoardEntity -> {
            BoardResponse boardResponse = new BoardResponse(hiaBoardEntity);

            boardResponse.setWriterName(getWriterName(hiaBoardEntity.getWriter(), hiaBoardEntity.getUserType()));

            boardResponses.add(boardResponse);
        });

        boardPageResponse.setContent(boardResponses);

        return boardPageResponse;
    }

    @Transactional
    public BoardResponse write(BoardRequest boardRequest) {
        HiaBoardEntity hiaBoardEntity = new HiaBoardEntity();

        hiaBoardEntity.setContent(boardRequest.getContent());
        hiaBoardEntity.setType(boardRequest.getType());
        hiaBoardEntity.setUserType(boardRequest.getUserType());
        hiaBoardEntity.setWriter(boardRequest.getWriter());
        hiaBoardEntity.setTitle(boardRequest.getTitle());

        BoardResponse boardResponse = toBoard(boardRepository.saveAndFlush(hiaBoardEntity));
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);

        AmazonSNSClient snsClient = new AmazonSNSClient(basicAWSCredentials);
        snsClient.setRegion(Region.getRegion(Regions.AP_NORTHEAST_2));

        PublishRequest publishRequest = null;
        try {
            publishRequest = new PublishRequest("arn:aws:sns:ap-northeast-2:113978175082:hereiam_push", new ObjectMapper().writeValueAsString(boardResponse));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        PublishResult publishResult = snsClient.publish(publishRequest);
        //print MessageId of message published to SNS topic
        System.out.println("MessageId - " + publishResult.getMessageId());

        return boardResponse;
    }

    public BoardResponse get(int id) {
        BoardResponse boardResponse = new BoardResponse(boardRepository.findOne(id));
        boardResponse.setWriterName(getWriterName(boardResponse.getWriter(), boardResponse.getUserType()));

        return boardResponse;
    }

    private BoardResponse toBoard(HiaBoardEntity hiaBoardEntity) {
        BoardResponse boardResponse = new BoardResponse(hiaBoardEntity);
        boardResponse.setWriterName(getWriterName(boardResponse.getWriter(), boardResponse.getUserType()));

        return boardResponse;
    }

    private String getWriterName(int writerId, USER_TYPE type) {
        String writerName = "";
        switch (type) {
            case STUDENT:
                if (studentRepository.findOne(writerId) != null)
                    writerName = studentRepository.findOne(writerId).getName();
                break;
            case PROFESSOR:
                if (professorRepository.findOne(writerId) != null)
                    writerName = professorRepository.findOne(writerId).getName();
                break;
            case ADMIN:
                if (adminRepository.findOne(writerId) != null)
                    writerName = adminRepository.findOne(writerId).getName();
                break;
        }

        return writerName;
    }
}
