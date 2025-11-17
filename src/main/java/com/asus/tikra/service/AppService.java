package com.asus.tikra.service;

import com.asus.tikra.model.dto.app.AppQueryRequest;
import com.asus.tikra.model.entity.App;
import com.asus.tikra.model.entity.User;
import com.asus.tikra.model.vo.AppVO;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * 应用 服务层。
 *
 */
public interface AppService extends IService<App> {

    /**
     * 获取应用封装类
     *
     * @param app 应用实体对象
     * @return 应用视图对象
     */
    AppVO getAppVO(App app);

    /**
     * 构建应用查询包装器
     *
     * @param appQueryRequest 应用查询请求对象
     * @return 查询包装器
     */
    QueryWrapper getQueryWrapper(AppQueryRequest appQueryRequest);

    /**
     * 获取应用视图对象列表
     *
     * @param appList 应用实体对象列表
     * @return 应用视图对象列表
     */
    List<AppVO> getAppVOList(List<App> appList);

    /**
     * 通过对话生成代码
     *
     * @param appId     应用ID
     * @param message   用户消息
     * @param loginUser 当前登录用户
     * @return 响应消息流
     */
    Flux<String> chatToGenCode(Long appId, String message, User loginUser);

    /**
     * 部署应用
     *
     * @param appId     应用ID
     * @param loginUser 当前登录用户
     * @return 部署地址
     */
    String deployApp(Long appId, User loginUser);
}
