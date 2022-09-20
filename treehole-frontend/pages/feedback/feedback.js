// pages/feedback/feedback.js
// Page({

//   /**
//    * 页面的初始数据
//    */
//   data: {

//   },
//   talks:function(e){
//     this.setData({
//        talks:e.detail.value
//     })           
// },
// oks:function(){
//   console.log(this.data.talks)
// },
// /*
// 输入文本框内容
// */
// getDataBindTap:function(e){
//   var result = e.detail.value;
//   console.log(result)
// },

//   /**
//    * 生命周期函数--监听页面加载
//    */
//   onLoad: function (options) {

//   },

//   /**
//    * 生命周期函数--监听页面初次渲染完成
//    */
//   onReady: function () {

//   },

//   /**
//    * 生命周期函数--监听页面显示
//    */
//   onShow: function () {

//   },

//   /**
//    * 生命周期函数--监听页面隐藏
//    */
//   onHide: function () {

//   },

//   /**
//    * 生命周期函数--监听页面卸载
//    */
//   onUnload: function () {

//   },

//   /**
//    * 页面相关事件处理函数--监听用户下拉动作
//    */
//   onPullDownRefresh: function () {

//   },

//   /**
//    * 页面上拉触底事件的处理函数
//    */
//   onReachBottom: function () {

//   },

//   /**
//    * 用户点击右上角分享
//    */
//   onShareAppMessage: function () {

//   }
// })
var util = require('../../utils/util.js');
var feedbackContent = ""
Page({
  data: {
    formData:""
  },
  input:function(e){
    feedbackContent = e.detail.value
  },
  formSubmit:function(e){
    var that = this;
    //var formData = that.data.value;
    //var url= that.data.addUrl;
    this.setData({
      formData: feedbackContent
    }),
    console.log("formdata:",this.data.formData);
    this.addFeedback(wx.getStorageSync("uid"),this.data.formData);
  },

  async addFeedback(uid, fContent) {
   const res = await wx.request({
    url:'http://121.199.40.49:8443/feedback/add',
    data:{uid:wx.getStorageSync('uid'),fContent:feedbackContent},
    method: 'POST',
    header: {
      "Content-Type": "application/x-www-form-urlencoded"
    },
    success: (result) => {
      wx.showModal({
        title: '提示',
        content: '反馈成功！'
      }),
    console.log(res);
    if(res.data.code == 50001 ||res.data.code ==10001){
      wx.showModal({
        title: '提示',
        content: '反馈失败！'
      })
    }
    console.log(res);
    },
    fail: (res) => {
      wx.showModal({
        title: '提示',
        content: '反馈失败！'
      })
      console.log(res)
    },
  })
  console.log(res);
},
onLoad: function (options) {
  
}
})