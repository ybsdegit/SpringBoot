package com.ybs.controller;

import com.ybs.service.DevOpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DevOpsController
 * @Author Paulson
 * @Date 2020/12/5
 * @Description:
 */

@RestController
public class DevOpsController {
    @Autowired
    private DevOpsService devOpsService;

    @GetMapping("/devops")
    public String devops(@RequestParam String taskId) throws Exception {
        devOpsService.executeShellAndSendWebSocket(taskId);
        return "操作成功";
    }
}
