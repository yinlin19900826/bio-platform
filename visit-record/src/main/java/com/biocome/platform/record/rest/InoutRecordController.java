package com.biocome.platform.record.rest;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.common.util.JsonUtils;
import com.biocome.platform.inter.basemanager.entity.InoutRecord;
import com.biocome.platform.inter.basemanager.vo.inoutRecord.InoutRecordBulkReq;
import com.biocome.platform.inter.basemanager.vo.inoutRecord.InoutRecordBulkResp;
import com.biocome.platform.inter.basemanager.vo.inoutRecord.InoutRecordForListResp;
import com.biocome.platform.inter.basemanager.vo.inoutRecord.InoutRecordReq;
import com.biocome.platform.record.biz.InoutRecordBiz;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: hxy
 * @create:2019/5/7 15:25
 */
@Controller
@RequestMapping("/inoutRecord")
@Api(value = "出入记录", tags = {"出入记录操作"})
public class InoutRecordController extends BaseController<InoutRecordBiz, InoutRecord> {
    Logger log = LoggerFactory.getLogger(InoutRecordController.class);

    @Autowired
    private DefaultMQProducer defaultMQProducer;
    @Value("${rocketmq.producer.topics}")
    private String topicid;
    @Value("${rocketmq.producer.tags}")
    private String tags;

    @ApiOperation("出入记录上传")
    @PostMapping("/upload/recordinfo")
    @ResponseBody
    public BaseRpcResponse inoutRecordUpload(@RequestBody InoutRecordReq req) {
        BaseRpcResponse resp = new BaseRpcResponse();
        try {
            if (req.getInfo() != null) {
                req.getInfo().setAccesskey(req.getAccesskey());
                String msg = JsonUtils.beanToJson(req.getInfo());

                Message sendMsg = new Message(topicid, tags, msg.getBytes());

                SendResult sendResult = defaultMQProducer.send(sendMsg);

                //baseBiz.addInoutRecord(record);
                return new BaseRpcResponse().success();
            } else {
                resp.setResult("0");
                resp.setErrorcode("100");
                resp.setMessage("参数格式错误");
                return resp;
            }
        } catch (Exception e) {
            log.info("------上报进出记录时发生异常------");
            log.info(e.getMessage());
            resp.setMessage("发生异常");
            resp.setResult("0");
            resp.setErrorcode("401");
            return resp;
        }
    }

    @ApiOperation("出入记录批量上传")
    @PostMapping("/upload/recordinfobulk")
    @ResponseBody
    public List<InoutRecordBulkResp> inoutRecordUploadBluk(@RequestBody InoutRecordBulkReq req) {
        InoutRecordBulkResp resp = new InoutRecordBulkResp();
        String accesskey = req.getAccesskey();
        List<InoutRecordBulkResp> list = new ArrayList<>();
        if (req.getList() != null && req.getList().size() > 0) {
            for (InoutRecord record : req.getList()) {
                record.setAccesskey(accesskey);
                try {
                    String msg = JsonUtils.beanToJson(record);

                    Message sendMsg = new Message(topicid, tags, msg.getBytes());

                    SendResult sendResult = defaultMQProducer.send(sendMsg);

                    //baseBiz.addInoutRecord(record);
                    resp.setId(record.getId());
                    resp.setResult("1");
                    resp.setErrorcode("");
                    resp.setMessage("成功");
                    list.add(resp);
                    continue;
                } catch (Exception e) {
                    log.info("------上报进出记录时发生异常------");
                    log.info(e.getMessage());
                    resp.setMessage("发生异常");
                    resp.setResult("0");
                    resp.setErrorcode("401");
                    resp.setId(record.getId());
                    list.add(resp);
                    continue;
                }
            }
        } else {
            resp.setResult("0");
            resp.setErrorcode("100");
            resp.setMessage("参数格式错误");
            list.add(resp);
        }
        return list;
    }

    @ApiOperation("获取出入记录")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "主键ID", paramType = "query"),
            @ApiImplicitParam(name = "opentype", value = "开门方式", paramType = "query"),
            @ApiImplicitParam(name = "cardno", value = "卡号", paramType = "query"),
            @ApiImplicitParam(name = "starttime", value = "开始时间", paramType = "query"),
            @ApiImplicitParam(name = "endtime", value = "结束时间", paramType = "query")
    })
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public TableResultResponse<InoutRecordForListResp> page(@RequestParam(defaultValue = "20") int pageSize,
                                                            @RequestParam(defaultValue = "1") int pageNum,
                                                            Integer id,
                                                            String opentype,
                                                            String cardno,
                                                            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date starttime,
                                                            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endtime,
                                                            String buildcode) {
        Page<InoutRecordForListResp> result = PageHelper.startPage(pageNum, pageSize);
        baseBiz.selectInoutRecordForList(id, opentype, cardno, starttime, endtime, buildcode);
        return new TableResultResponse<InoutRecordForListResp>(result.getTotal(), result.getResult());
    }
}