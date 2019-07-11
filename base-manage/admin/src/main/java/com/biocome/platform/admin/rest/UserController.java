package com.biocome.platform.admin.rest;

import com.biocome.platform.admin.biz.MenuBiz;
import com.biocome.platform.admin.biz.UserBiz;
import com.biocome.platform.admin.rpc.service.PermissionService;
import com.biocome.platform.admin.vo.FrontUser;
import com.biocome.platform.admin.vo.MenuTree;
import com.biocome.platform.admin.vo.UserVo;
import com.biocome.platform.admin.entity.Menu;
import com.biocome.platform.admin.entity.User;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.rest.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-08 11:51
 */
@RestController
@RequestMapping("user")
@Api(value = "用户", tags = "用户管理")
public class UserController extends BaseController<UserBiz,User> {
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private MenuBiz menuBiz;

    @Autowired
    private UserBiz userBiz;

    @RequestMapping(value = "/front/info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUserInfo(String token) throws Exception {
        FrontUser userInfo = permissionService.getUserInfo(token);
        if(userInfo==null) {
            return ResponseEntity.status(401).body(false);
        } else {
            return ResponseEntity.ok(userInfo);
        }
    }

    @RequestMapping(value = "/front/menus", method = RequestMethod.GET)
    public @ResponseBody
    List<MenuTree> getMenusByUsername(String token) throws Exception {
        return permissionService.getMenusByUsername(token);
    }

    @RequestMapping(value = "/front/menu/all", method = RequestMethod.GET)
    public @ResponseBody
    List<Menu> getAllMenus() throws Exception {
        return menuBiz.selectListAll();
    }

    @ApiOperation("用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<UserVo> list(@RequestParam(defaultValue = "20") int pageSize, @RequestParam(defaultValue = "1") int pageNo) {
        return userBiz.selectUserListByCondition(pageSize, pageNo);
    }


    @ApiOperation("删除用户/禁用用户")
    @RequestMapping(value = "/disable/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse disable(@PathVariable int id) {
        userBiz.disable(id);
        return new ObjectRestResponse<>().success();
    }

    @ApiOperation("增加用户")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody User entity){
        return userBiz.addUser(entity);
    }

    @ApiOperation("修改用户")
    @RequestMapping(value = "update/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse<User> update(@RequestBody User entity){
        try {
            return userBiz.updateSelective(entity);
        }catch (Exception e){
            return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "更新用户信息失败，错误信息为：" + e.getMessage());
        }
    }

    @ApiOperation("用户详情")
    @RequestMapping(value = "get/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse<User> get(@PathVariable int id){
        try {
            return super.get(id);
        }catch (Exception e){
            return new ObjectRestResponse<>(CommonConstants.EX_OTHER_CODE, "获取用户信息失败，错误信息为：" + e.getMessage());
        }
    }
}
