package com.asus.tikra.service;

import com.asus.tikra.model.dto.chathistory.ChatHistoryQueryRequest;
import com.asus.tikra.model.entity.ChatHistory;
import com.asus.tikra.model.entity.User;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;

import java.time.LocalDateTime;

/**
 * 对话历史 服务层。
 *
 */
public interface ChatHistoryService extends IService<ChatHistory> {

    /**
     * 添加对话消息。
     *
     * @param appId       应用ID
     * @param message     消息内容
     * @param messageType 消息类型
     * @param userId      用户ID
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    boolean addChatMessage(Long appId, String message, String messageType, Long userId);

    /**
     * 根据应用ID删除对话历史。
     *
     * @param appId 应用ID
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    boolean deleteByAppId(Long appId);

    /**
     * 将聊天历史加载到内存中。
     *
     * @param appId       应用ID
     * @param chatMemory  聊天记忆对象
     * @param maxCount    最大加载数量
     * @return 实际加载的消息数量
     */
    int loadChatHistoryToMemory(Long appId, MessageWindowChatMemory chatMemory, int maxCount);

    /**
     * 获取查询包装类
     *
     * @param chatHistoryQueryRequest
     * @return
     */
    QueryWrapper getQueryWrapper(ChatHistoryQueryRequest chatHistoryQueryRequest);

    Page<ChatHistory> listAppChatHistoryByPage(Long appId, int pageSize,
                                               LocalDateTime lastCreateTime,
                                               User loginUser);
}
