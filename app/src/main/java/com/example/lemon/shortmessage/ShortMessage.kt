package com.example.lemon.shortmessage

import java.io.Serializable

/**
 * Created by lemon on 2017/12/24.
 */
class ShortMessage : Serializable {
    /**短信序号，如100*/
    var id: Int = 0

    /**联系人通信序号，如100，与同一个手机号互发的短信，其序号是相同的，可以认为这个id标志联系人*/
    var threadId: Int = 0
    /**发件人地址，即手机号，如：+86131xxxxxxx*/
    lateinit var address: String

    /**通讯录中联系人姓名，陌生人为null*/
    lateinit var person: String
    /**日期*/
    var date: Long = 0
    /**协议类型
     * OSMS_RPOTO：短信
     * 1MMS_PROTO彩信*/
    var protocol: Int = 0
    /**是否阅读 */
    var read: Boolean = false
    /**短信状态
     * -1 接收
     * 0 complete
     * 64 pending
     * 128 failed*/
    var status: Int = 0
    /**短信类型
     * 1：收到的短信
     * 2：发出的短信*/
    var type: Int = 0

    /*短信具体内容*/
    lateinit var body: String
    /**短信服务中心号码编号*/
    lateinit var serviceCenter: String
}