package com.hao.haoaiagent.app;

import cn.hutool.core.lang.UUID;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoveAppTest {

    @Resource
    private LoveApp loveApp;

    @Test
    void testChat() {
        String chatId = UUID.randomUUID().toString();
        //第1轮
        String message = "我的好朋友A是赵云";
        String answer = loveApp.doChat(message,chatId);
        //第2轮
        message = "我的好朋友B是张飞";
        answer = loveApp.doChat(message,chatId);
        Assertions.assertNotNull(answer);
        //第3轮
        message = "我的好朋友A是谁？帮我回忆一下";
        answer = loveApp.doChat(message,chatId);
        Assertions.assertNotNull(answer);

        //ceshi
    }

    @Test
    void testChat2() {
        String chatId = UUID.randomUUID().toString();
        //第1轮
        String message = "我的好朋友A是赵云";
        LoveApp.LoveReport report = loveApp.doChatWithReport(message, chatId);
        Assertions.assertNotNull(report);
        Assertions.assertNotNull(report.title());
        Assertions.assertNotNull(report.suggestions());
        //第2轮
        message = "我的好朋友B是张飞";
        report = loveApp.doChatWithReport(message, chatId);
        Assertions.assertNotNull(report);
        Assertions.assertNotNull(report.title());
        Assertions.assertNotNull(report.suggestions());
        //第3轮
        message = "我的好朋友A是谁？帮我回忆一下";
        report = loveApp.doChatWithReport(message, chatId);
        Assertions.assertNotNull(report);
        Assertions.assertNotNull(report.title());
        Assertions.assertNotNull(report.suggestions());
    }

    @Test
    void doChatWithRag() {
        String chatId = UUID.randomUUID().toString();
        String message = "读完《好好学习：个人知识管理精进指南》花了多长时间？书中提到哪些其他书籍";
        String answer = loveApp.doChatWithRag(message, chatId);
        Assertions.assertNotNull(answer);
    }
}