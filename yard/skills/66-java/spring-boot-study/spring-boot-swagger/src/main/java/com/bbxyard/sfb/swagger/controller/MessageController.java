package com.bbxyard.sfb.swagger.controller;

import com.bbxyard.sfb.swagger.common.BaseResult;
import com.bbxyard.sfb.swagger.entity.Message;
import com.bbxyard.sfb.swagger.repository.MessageRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "消息", description = "消息操作 API", position = 1000, protocols = "http")
@RestController
@RequestMapping("/msg")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @ApiOperation(
            value = "消息列表",
            notes =  "完整的消息内容列表",
            produces = "application/json, application/xml",
            consumes = "application/json, application/xml",
            response = List.class
    )
    @GetMapping(value = "")
    public List<Message> listAll() {
        List<Message> list = this.messageRepository.findAll();
        return list;
    }

    @ApiOperation(value = "添加消息", notes = "根据参数创建消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "消息ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "text", value = "正文", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "summary", value = "摘要", required = false, dataType = "String", paramType = "query"),
    })
    @PostMapping(value = "")
    public Message create(Message message) {
        System.out.println("message === " + message.toString());
        message = this.messageRepository.save(message);
        return message;
    }

    @ApiOperation(value = "更新消息", notes = "根据参数修改消息")
    @ApiResponses({
            @ApiResponse(code = 100, message = "请求参数有误"),
            @ApiResponse(code = 101, message = "未授权"),
            @ApiResponse(code = 103, message = "禁止访问"),
            @ApiResponse(code = 104, message = "请求路径不存在"),
            @ApiResponse(code = 200, message = "服务器内部错误")
    })
    @PutMapping(value = "")
    public Message update(Message message) {
        Message res = this.messageRepository.update(message);
        return res;
    }


    @ApiOperation(value = "打补丁", notes = "修正文本内容")
    @PatchMapping(value = "/text")
    public BaseResult<Message> patch(Message msg) {
        Message res = this.messageRepository.updateText(msg);
        return BaseResult.successWithData(res);
    }

    @ApiOperation(value = "获取消息", notes = "根据id获取消息")
    @GetMapping(value = "/{id}")
    public Message get(@PathVariable Long id) {
        Message message = this.messageRepository.findMessage(id);
        return message;
    }

    @ApiOperation(value = "删除消息", notes = "根据id删除")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        this.messageRepository.deleteMessage(id);
    }
}
