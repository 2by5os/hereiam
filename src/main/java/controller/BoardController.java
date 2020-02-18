package controller;

import constant.BOARD_TYPE;
import org.springframework.web.bind.annotation.*;
import request.BoardRequest;
import response.BoardPageResponse;
import response.BoardResponse;
import service.BoardService;

import javax.annotation.Resource;

/**
 * Created by ohseoklee on 2018-12-07.
 */

@RestController
@RequestMapping("/board")
public class BoardController {

    @Resource
    private BoardService boardService;

    @RequestMapping("/{id}")
    public BoardResponse getBoard(@PathVariable int id) {
        return boardService.get(id);
    }

    @RequestMapping("/{type}/{page}")
    public BoardPageResponse getBoards(@PathVariable String type, @PathVariable int page){

        return boardService.getBoard(BOARD_TYPE.valueOf(type.toUpperCase()), page);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public BoardResponse writeBoard(@RequestBody BoardRequest boardRequest){
        return boardService.get(boardService.write(boardRequest).getId());
    }
}
