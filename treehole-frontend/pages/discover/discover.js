// pages/discover/discover.js
import {request} from "../../request/index.js"
var max = 10
Page({

  /**
   * 页面的初始数据
   */
  data: {
    // 树洞集合
    treeholeList:[],
    // 点赞集合
    thumbFlagList:[],
    // thumbUrl1: 未点赞  thumbUrl：已经点赞
    thumbUrl1: "../../icons/like.png",
    thumbUrl: "../../icons/like_clicked.png",
    style:"news",
    // 风格样式：自己 他人
    styleList:[],
  },

  /* 后端需要的参数 */
  QueryParams: {
    id: wx.getStorageSync("uid"),
    //页数
    page: 0,
    //每一页显示的最多条数
    num: 5
  },

   //获取树洞列表
   async getTreeholeList(){
    this.QueryParams.page = this.QueryParams.page + 1;
    const res = await request({url:"http://121.199.40.49:8443/publish/find",data:this.QueryParams});
    console.log(this.QueryParams);
    if(res.data.code == 10002 || res.data.code == 10004 ||  res.data.code == 40001 ||  res.data.code == 80001){
      console.log("incorrect code:"+res.data.code);
      return;
    }
    console.log("分页测试：第"+this.QueryParams.page+"页，该页"+this.QueryParams.num+"条数据")
    var temp = this.data.treeholeList;
    temp.push.apply(temp,res.data.data);
   
    this.setData({
      treeholeList:temp
    })
    this.judgeThumb();
  },

  async judgeThumb(){
    var temp = [];
    var tstyle = []
    for (var index in this.data.treeholeList){
      const res = await request({url:"http://121.199.40.49:8443/publish/judgethumb",data:{id:wx.getStorageSync('uid'),tid:this.data.treeholeList[index].tid},method: "GET"});
      if (res.data.code == 10002 || res.data.code == 10004 ||  res.data.code == 40001 || res.data.code == 80001){
       console.log(res.data.code);
       return;
      }
      //样式设置判断
      if (wx.getStorageSync('uname') == this.data.treeholeList[index].uname){
        tstyle[index] = "personal-news";
      }
      else{
        tstyle[index] = "news";
      }
      //用来存取判断结果，true或false
      temp[index] = res.data.data;
    }
    this.setData({
      thumbFlagList: temp,
      styleList:tstyle
    })
  },

  async countNum(){
    const res = await request({url:"http://121.199.40.49:8443/publish/find/all",data:null});
    max = res.data.data;
  },

  styleset:function(e){
    if (wx.getStorageSync('uname') == this.data.treeholeList[e.currentTarget.dataset.index].uname){
      this.setData({
        style:"personal-news"
      })
    }
    else{
      this.setData({
        style:"news"
      })
    }
    
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
   this.getTreeholeList();
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

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    this.QueryParams.page = 0;
    this.setData({
      treeholeList:[]
    })
    this.getTreeholeList();
    //刷新完成后停止下拉刷新动效
    wx.stopPullDownRefresh();
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    this.countNum();
    console.log("max="+max)
    
    console.log(this.QueryParams.page);

    if (this.QueryParams.page * 5 >= max){
      console.log("没有更多数据！");
      return wx.showToast({
        icon:'none',
        title: '没有更多数据'
      })
    }
    this.onLoad();
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})