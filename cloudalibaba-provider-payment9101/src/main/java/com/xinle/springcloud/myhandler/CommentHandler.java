package com.xinle.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class CommentHandler {

    public static String myHandler1(BlockException b){
        return "CommentHandler.......";
    }

}
