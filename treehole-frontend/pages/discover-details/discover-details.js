// pages/discover-details/discover-details.js

import {request} from "../../request/index.js"
// 点赞标识
var tid = 1
var thumbUpFlag = false
//是否匿名
var check = false
// 评论内容
var comContent = ""

Page({
  // 页面初始数据
  data: {
    avatar:"../../icons/toy2.png",
    nickname:"匿名用户",
    // thumbUrl1: 未点赞  thumbUrl：已经点赞
    thumbUrl: "../../icons/like.png",
    tcontent:"学子三楼的地三鲜真好吃，就是有点油，吃多了就腻了。学子二楼饺子王里的三鲜馄饨也很好吃。可恶，好饿。呜呜呜呜呜呜，俺好想吃学子二楼的馄饨啊啊啊啊。",
    thumbnum:123,
    commentnum:22,
    commentList:[],
    ttime:"07-18 11:32",
    index:0,
    ano:false,
  },
    //匿名勾选
    anonymousOrNot:function(e){
      if(e.detail.value==''){
          check=0
      }
      else{
          check=1
      }
      },

    //获取评论列表
    async getCommentList(){
      const res = await request({url:"http://121.199.40.49:8443/publish/getcomment",data:{id:wx.getStorageSync("uid"),tid:tid},method: "GET"});
      if(res.data.code == 10002 || res.data.code == 10004 ||  res.data.code == 40001 ||  res.data.code == 80001){
        console.log(res.data.code);
        return;
      }
      this.setData({
        commentList:res.data.data,
      })
      if (this.data.commentList == null){
        return;
      }

      var arr = Object.keys(this.data.commentList);
      this.setData({
        commentnum:arr.length,
      })

   },

  thumbUp(){
    if(thumbUpFlag){
      this.setData({
        thumbUrl: "../../icons/like.png",
      })
      this.noThumbUp()
      thumbUpFlag = false
    }else{
      this.setData({
        thumbUrl: "../../icons/like_clicked.png",
      })
      this.thumbUp1()
      thumbUpFlag = true
    }
  },

  async thumbUp1(word){
    const res = await request({url:"http://121.199.40.49:8443/comment/thumb",data:{uid:wx.getStorageSync("uid"),tid:tid},method: "POST",header: {
      "Content-Type": "application/x-www-form-urlencoded"
      },});
    console.log(res);
    if(res.data.code == 50002 || res.data.code == 10003 || res.data.code == 10002 ||res.data.code == 80001|| res.data.code == 40001){
      return;
    }
    console.log(res);
    this.setData({
      thumbnum: parseInt(res.data.data)
    });
  },

  async noThumbUp(word){
    const res = await request({url:"http://121.199.40.49:8443/comment/noThumb",data:{uid:wx.getStorageSync("uid"),tid:tid},method: "POST",header: {
      "Content-Type": "application/x-www-form-urlencoded"
      },});
    console.log(res);
    if(res.data.code == 50002 || res.data.code == 10003 || res.data.code == 10002 ||res.data.code == 80001|| res.data.code == 40001){
      return;
    }
    console.log(res);
    this.setData({
      thumbnum: parseInt(res.data.data)
    });
  },

  /**
   * 生命周期函数--监听页面加载：页面加载时，参数从discover页传进来
   */
  onLoad: function (options) {
    this.setData({
      avatar:options.avatar,
      nickname:options.nickname,
      tcontent:options.tcontent,
      thumbnum:options.thumbnum,
      commentnum:options.commentnum,
      index:options.index,
      ttime:options.ttime,
      ano:options.ano,
    }),
    tid = options.tid;
    if(options.flag == 'true'){
      thumbUpFlag=true
      this.setData({
        thumbUrl: "../../icons/like_clicked.png",
      })
    }
    this.getCommentList();
  },
  
  QueryParams: {
    uid:wx.getStorageSync("uid"),
    tid:tid
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
    console.log("onUnload函数调用")
    var pages = getCurrentPages();
    var prevPage = pages[pages.length - 2]; //上一个页面
    if (thumbUpFlag == true){
      prevPage.setData({
        ['thumbFlagList[' + this.data.index + ']']:"true",
      })
    }
    else{
      prevPage.setData({
        ['thumbFlagList[' + this.data.index + ']']:"false",
      })
    }
    prevPage.setData({
      ['treeholeList[' + this.data.index + '].thumbNum']:this.data.thumbnum,
      ['treeholeList[' + this.data.index + '].commentNum']:this.data.commentnum,
      ['myCommentList[' + this.data.index + '].treeholeVO.thumbNum']:this.data.thumbnum,
      ['myCommentList[' + this.data.index + '].treeholeVO.commentNum']:this.data.commentnum,
    })
    },
    //input事件
    bindblur:function(e){
      comContent = e.detail.value;
    },

    //发布评论
    async publish(){
      const res = await request({url:"http://121.199.40.49:8443/publish/comment",data:{uid:wx.getStorageSync("uid"),tid:tid,content:comContent,anonymous:check},method: "POST",header: {
      "Content-Type": "application/x-www-form-urlencoded"
      },});
      console.log(res);
      if(res.data.code == 10002 || res.data.code == 10004 || res.data.code == 40001){
        wx.showModal({
          title:'提示',
          content:'发布失败！'
        })
        return;
      }
      else if(res.data.code==50001){
        wx.showModal({
          title:'提示',
          content:'有敏感词',
        })
      }
      else{
        wx.showModal({
          title:'提示',
          content:'发布成功！'
        })
        this.setData({
          comContent:""
        })
      }
      this.getCommentList();
      
    },
  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    this.getCommentList();
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