// pages/command-me/command-me.js
var util = require('../command-me/util.js');
import {request} from "../../request/index.js"
Page({

  /**
   * 页面的初始数据
   */
  data: {
    // 获取到的我的评论集合
    myCommentList:[],
    thumbFlagList:[],
      // thumbUrl1: 未点赞  thumbUrl：已经点赞
      thumbUrl1: "../../icons/like.png",
      thumbUrl: "../../icons/like_clicked.png",
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
    this.getCommentMeList();
    
  },

  async getCommentMeList(){
    const res = await request({url:"http://121.199.40.49:8443/comment/getCommentMe",data:{uid:wx.getStorageSync("uid")},method: "GET"});
    if(res.data.code == 50001 || res.data.code == 10001){
      return;
    }
    this.setData({
      myCommentList:res.data.data
    });
    this.judgeThumb();
  },

  async judgeThumb(){
    var temp = [];
    var tstyle = []
    for (var index in this.data.myCommentList){
      const res = await request({url:"http://121.199.40.49:8443/publish/judgethumb",data:{id:wx.getStorageSync('uid'),tid:this.data.myCommentList[index].treeholeVO.tid},method: "GET"});
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

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.judgeThumb();
    this.getCommentMeList();
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    this.getCommentMeList();
    wx.stopPullDownRefresh();
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})