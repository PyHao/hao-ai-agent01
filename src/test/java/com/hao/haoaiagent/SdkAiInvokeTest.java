package com.hao.haoaiagent;

import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.hao.haoaiagent.demo.invoke.SdkAiInvoke;
import com.hao.haoaiagent.demo.invoke.SpringAiAiInvoke;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SdkAiInvokeTest {
    @Autowired
    private SdkAiInvoke sdkAiInvoke;



    @Test
    void testCall() throws Exception{
        GenerationResult result = sdkAiInvoke.callWithMessage();

        System.out.println("====================思考过程====================");
        System.out.println(result.getOutput().getChoices().get(0).getMessage().getReasoningContent());
        System.out.println("\n====================完整回复====================");
        System.out.println(result.getOutput().getChoices().get(0).getMessage().getContent());
    }

//    @Test
//    void testAiAiInvoke() throws Exception{
//        SpringAiAiInvoke
//    }


}
