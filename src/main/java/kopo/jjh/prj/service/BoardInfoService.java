package kopo.jjh.prj.service;

import kopo.jjh.prj.domain.entity.BoardInfo;
import kopo.jjh.prj.domain.repository.InfoRepository;
import kopo.jjh.prj.dto.BoardInfoDto;
import kopo.jjh.prj.service.impl.BoardInfomapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardInfoService {
    private InfoRepository infoRepository;
    private static final int BLOCK_PAGE_NUM_COUNT = 9;
    private static final int PAGE_POST_COUNT = 10;
    private BoardInfomapper boardmapper;



    public BoardInfoService(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    @Transactional
    public Long saveInfoPost(BoardInfoDto boardInfoDto) {
        return infoRepository.save(boardInfoDto.toEntity()).getId();
    }

    @Transactional
    public List<BoardInfoDto> BoardInfolist(Integer pageNum) {

        Page<BoardInfo> page = infoRepository.findAll(PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createdDate")));

        List<BoardInfo> boardInfoList = page.getContent();
        List<BoardInfoDto> boardInfoDtoList = new ArrayList<>();



        for(BoardInfo boardInfo : boardInfoList) {
            BoardInfoDto boardInfoDto = BoardInfoDto.builder()
                    .id(boardInfo.getId())
                    .author(boardInfo.getAuthor())
                    .title(boardInfo.getTitle())
                    .content(boardInfo.getContent())
                    .hitCnt(boardInfo.getHitCnt())
                    .createdDate(boardInfo.getCreatedDate())

                    .build();
            boardInfoDtoList.add(boardInfoDto);
        }
        return boardInfoDtoList;
    }
    @Transactional
    public Long getBoardInfoCount() {
        return infoRepository.count();
    }
    public Integer[] getPageInfoList(Integer curPageNum) {
        Integer[] pageInfoList = new Integer[BLOCK_PAGE_NUM_COUNT];

// ??? ????????? ??????
        Double postsTotalCount = Double.valueOf(this.getBoardInfoCount());

// ??? ????????? ???????????? ????????? ????????? ????????? ?????? ?????? (???????????? ??????)
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

        Integer blockStartPageNum =
                (curPageNum <= BLOCK_PAGE_NUM_COUNT / 2)
                        ? 1
                        : curPageNum - BLOCK_PAGE_NUM_COUNT / 2;

// ?????? ???????????? ???????????? ????????? ????????? ????????? ?????? ??????
        Integer blockLastPageNum =
                (totalLastPageNum > blockStartPageNum + BLOCK_PAGE_NUM_COUNT - 1 )
                        ? blockStartPageNum + BLOCK_PAGE_NUM_COUNT - 1
                        : totalLastPageNum;

// ????????? ?????? ??????
        for (int val = blockStartPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            pageInfoList[idx] = val;
        }

        return pageInfoList;
    }

    @Transactional
    public BoardInfoDto getInfoPost(Long id) {
        BoardInfo boardInfo = infoRepository.findById(id).get();

        BoardInfoDto boardInfoDto = BoardInfoDto.builder()
                .id(boardInfo.getId())
                .author(boardInfo.getAuthor())
                .title(boardInfo.getTitle())
                .content(boardInfo.getContent())
                .fileId(boardInfo.getFileId())
                .createdDate(boardInfo.getCreatedDate())

                .build();

        boardInfo.increaseViewCount();

        return boardInfoDto;
    }

    public Page<BoardInfo> getInfoPosts(Pageable pageable) {
        int page;
        if(pageable.getPageNumber() <= 0){
            page = 0;
        }else{
            page = pageable.getPageNumber()-1;
        }

        Pageable requestPageable = PageRequest.of(page, pageable.getPageSize());
        return infoRepository.findAll(requestPageable);
    }
    @Transactional
    public void deleteInfoPost(Long username_no) {
        infoRepository.deleteById(username_no);
    }
    public int boardInfoListCnt() throws Exception {
        return boardmapper.boardInfoListCnt();
    }

    @Transactional
    public List<BoardInfoDto> searchPosts(String keyword) {
        List<BoardInfo> boardinfo = infoRepository.findByTitleContaining(keyword);
        List<BoardInfoDto> boardInfoDtoList = new ArrayList<>();
        if (boardinfo.isEmpty()) return boardInfoDtoList;
        for (BoardInfo boardInfo : boardinfo) {
            boardInfoDtoList.add(this.convert(boardInfo));
        }
        return boardInfoDtoList;
    }

    private BoardInfoDto convert(BoardInfo boardInfo){
        return BoardInfoDto.builder()
                .id(boardInfo.getId())
                .author(boardInfo.getAuthor())
                .title(boardInfo.getTitle())
                .content(boardInfo.getContent())
                .hitCnt(boardInfo.getHitCnt())
                .createdDate(boardInfo.getCreatedDate())
                .build();

    }
}