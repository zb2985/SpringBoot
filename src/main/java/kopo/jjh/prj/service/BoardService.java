package kopo.jjh.prj.service;

import kopo.jjh.prj.domain.entity.Board;
import kopo.jjh.prj.domain.repository.BoardRepository;
import kopo.jjh.prj.dto.BoardDto;
import kopo.jjh.prj.service.impl.Boardmapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private BoardRepository boardRepository;
    private static final int BLOCK_PAGE_NUM_COUNT = 11;
    private static final int PAGE_POST_COUNT = 10;
    private Boardmapper boardmapper;
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public List<BoardDto> Boardlist(Integer pageNum) {

      Page<Board> page = boardRepository.findAll(PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createdDate")));
        List<Board> boardList = page.getContent();
        List<BoardDto> boardDtoList = new ArrayList<>();
        for(Board board : boardList) {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .author(board.getAuthor())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .hitCnt(board.getHitCnt())
                    .createdDate(board.getCreatedDate())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }
    @Transactional
    public Long getBoardCount() {
        return boardRepository.count();
    }
    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

// 총 게시글 갯수
        Double postsTotalCount = Double.valueOf(this.getBoardCount());

// 총 게시글 기준으로 계산한 마지막 페이지 번호 계산 (올림으로 계산)
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

        Integer blockStartPageNum =
                (curPageNum <= BLOCK_PAGE_NUM_COUNT / 2)
                        ? 1
                        : curPageNum - BLOCK_PAGE_NUM_COUNT / 2;

// 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum =
                (totalLastPageNum > blockStartPageNum + BLOCK_PAGE_NUM_COUNT - 1 )
                        ? blockStartPageNum + BLOCK_PAGE_NUM_COUNT - 1
                        : totalLastPageNum;

// 페이지 번호 할당
        for (int val = blockStartPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            pageList[idx] = val;
        }

        return pageList;
    }

    @Transactional
    public BoardDto getPost(Long id) {
        Board board = boardRepository.findById(id).get();

        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .author(board.getAuthor())
                .title(board.getTitle())
                .content(board.getContent())
                .fileId(board.getFileId())
                .createdDate(board.getCreatedDate())
                .build();

        board.increaseViewCount();

        return boardDto;
    }

    public Page<Board> getPosts(Pageable pageable) {
        int page;
        if(pageable.getPageNumber() <= 0){
            page = 0;
        }else{
            page = pageable.getPageNumber()-1;
        }

        Pageable requestPageable = PageRequest.of(page, pageable.getPageSize());
        return boardRepository.findAll(requestPageable);
    }
    @Transactional
    public void deletePost(Long username_no) {
        boardRepository.deleteById(username_no);
    }
    public int boardListCnt() throws Exception {
        return boardmapper.boardListCnt();
    }



}