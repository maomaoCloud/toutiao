package com.tiaotiaopoker;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
/*
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources( TiaotiaoApiApplication.class );
    }
*/

}

/**
 * 一、用户报名流程
 *     A用户是本系统肯定存在的用户
 *     B用户不一定存在
 * B用户扫码的时候，选择对应的A用户，然后通过A用户来绑定信息：
 *          此时B用户一定是登录了的：然后绑定对应的信息
 *
 * 二、报名流程
 *     A用户是一定存在的，直接加载自己的用户信息
 *     B没有就不填写，有的话：
 *                  只用填写用户名和电话
 *                  B用户扫描的时候，回到一

 文档表述：
 1.如在报名时填写搭档姓名，则在签到墙直接展示A姓名搭B姓名，A为报名 者，B为搭档(下同)，如报名时未填写搭档姓名，则⽆无需带‘搭’字，
 2.未签到，头像处显示:未到场，扫码签到成功，头像替换成微信头像。
 3.A报名者:扫码直接弹出报名信息，点击签到成功
 4.B搭档:扫码弹出选框:未报名\搭档已帮我报名、点击搭档已帮我报名弹出“信息填写框”输入真实姓名和手机号码后弹出选择搭档，
   后台进⾏行行模糊匹配，将模糊符合条件的队伍A搭B列列出(除AB都已签到的队伍)，点击 符合的某⼀一队可签到成功，如⽆无符合队伍
    ，可点击查看所有报名信息，从中 选择。
 5.C未报名空降者:扫码弹出选框:未报名\搭档已帮我报名、点未报名，
   弹出:请填写报名信息，填写完成后提示:联系⼯工作⼈人员进⾏行行报名审核，审核 通过后⾃自动签到成功

 * */