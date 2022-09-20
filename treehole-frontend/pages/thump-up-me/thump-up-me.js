// pages/thump-up-me/thump-up-me.js

import {request} from "../../request/index.js"
Page({

  /**
   * 页面的初始数据
   */
  data: {
    myThumbList:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getThumbList();
  },

  async getThumbList(){
    const res = await request({url:"http://121.199.40.49:8443/comment/thumbupme",data:{uid:wx.getStorageSync("uid")},method: "GET"});
    if(res.data.code == 50001 || res.data.code == 10001){
      return;
    }
    this.setData({
      myThumbList:res.data.data
    });
  }
})