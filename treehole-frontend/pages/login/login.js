// pages/login/login.js
Page({

  handleGetUserInfo(e){
    console.log(e);

  },
  async log(){
    let that =this
    const res = await getApp().newlogin();
    if (!res) return;
    wx.navigateTo({
      url: '../discover/discover',
      fail(ex){
        // fail can not redirectTo a tabbar page
        console.log(ex)
        wx.switchTab({
          url: '../discover/discover',
        })
      }
    })
  },

  data: {

  },

  
})