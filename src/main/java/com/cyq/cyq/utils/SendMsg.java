//package com.cyq.cyq.utils;
//
//import org.apache.commons.httpclient.Header;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.NameValuePair;
//import org.apache.commons.httpclient.methods.PostMethod;
//
//public class SendMsg {
//
//    public static void sendMsg()throws Exception
//    {
//
//        HttpClient client = new HttpClient();
//        PostMethod post = new PostMethod("http://gbk.api.smschinese.cn");
//        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
//        NameValuePair[] data ={ new NameValuePair("Uid", "1334538961@qq.com"),
//                new NameValuePair("Key", "d41d8cd98f00b204e980"),
//                new NameValuePair("smsMob","15878126562,13558333806"),
//                new NameValuePair("smsText","顺路帮验证码：88889999")};
//        post.setRequestBody(data);
//
//        client.executeMethod(post);
//        Header[] headers = post.getResponseHeaders();
//        int statusCode = post.getStatusCode();
//        System.out.println("statusCode:"+statusCode);
//        for(Header h : headers)
//        {
//            System.out.println(h.toString());
//        }
//        String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
//        System.out.println(result); //打印返回消息状态
//        post.releaseConnection();
//
//    }
//
//}
