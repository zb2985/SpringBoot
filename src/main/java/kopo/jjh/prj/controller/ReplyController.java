package kopo.jjh.prj.controller;

import kopo.jjh.prj.mapper.ReplyService;
import kopo.jjh.prj.mapper.ReplyVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/post/{bno}")
public class ReplyController {
    private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);

    @Inject
    private ReplyService service;

    @RequestMapping(value = "/replies", method = RequestMethod.GET)
    public ResponseEntity<String> register(ReplyVO reply) throws Exception {
        logger.debug("ReplyRegister>>{}",reply);

            service.register(reply);
            //제대로 등록되었으면 "ReplyRegisterOK" 문자열과 HTTP 상태 정상
            return new ResponseEntity<>("ReplyRegisterOK", HttpStatus.OK);

        }
    }
