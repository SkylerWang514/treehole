// pages/my-home/my-home.js
import {
  request
} from "../../request/index.js"
var app = getApp();
var uavatar = "../../icons/toy2.png";
var uname = "匿名用户";
// var tid1 = 1;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    uname: wx.getStorageSync("uname"),
    avatar: wx.getStorageSync("avatar"),
    treeholeList: [],

    thumbFlagList:[],
    // thumbUrl1: 未点赞  thumbUrl：已经点赞
    thumbUrl1: "../../icons/like.png",
    thumbUrl: "../../icons/like_clicked.png",
    
  },
  onLoad: function (options) {
    this.getMyselfTreehole();
    //this.deleteTreeholeByTid();
    
  },

  //删除自己的树洞
  deleteMyTreehole(e) {
    //var that = this;
    console.log(e.currentTarget.dataset.tid);
    this.deleteTreeholeByTid(e.currentTarget.dataset.tid);
    console.log("删除成功");
    this.getMyselfTreehole();
    //that.onLoad();
  },

  async getMyselfTreehole(id) {
    const res = await request({
      url: "http://121.199.40.49:8443/homepage/getMyselfTreehole",
      data: {
        id: wx.getStorageSync("uid")
      },
      method: "GET"
    });
    console.log(res);
    if (res.data.code == 10001 || res.data.code == 10002 || res.data.code == 10003 || res.data.code == 10004) {
      return;
    }
    this.setData({
      treeholeList: res.data.data
    });
    console.log("data:"+this.data.treeholeList);
    this.judgeThumb();
  },

  async judgeThumb(){
    var temp = [];
    for (var index in this.data.treeholeList){
      const res = await request({url:"http://121.199.40.49:8443/publish/judgethumb",data:{id:wx.getStorageSync('uid'),tid:this.data.treeholeList[index].tid},method: "GET"});
      if (res.data.code == 10002 || res.data.code == 10004 ||  res.data.code == 40001 || res.data.code == 80001){
       console.log(res.data.code);
       return;
      }
      temp[index] = res.data.data;
    }
    this.setData({
      thumbFlagList: temp,
    })
},

  async deleteTreeholeByTid(tid) {
    const res = await request({
      url:"http://121.199.40.49:8443/feedback/deleteTreeholeByTid",
      data:{
        tid:tid,
       method: "GET"
    }
  });
    console.log(res);
  }
})