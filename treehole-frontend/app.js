// app.js

App({
  onLaunch() {
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },
  globalData: {
    userInfo: null
  },

   
  // 新的getUserProfile形式的获取用户信息
  newlogin(){
    const token = wx.getStorageSync('token')
    if (token) return Promise.resolve(true)
    return new Promise((resolve,rej)=>{
      wx.getUserProfile({
        lang: 'zh_CN',
        desc: '用户登录',
        success:async res => {
          wx.login({
            success: async (re) => {
              if (re.code){
               res.userInfo.code=re.code
               const data= await (res.userInfo)
               
               console.log(re.code,res.userInfo.nickName,res.userInfo.avatarUrl)
                wx.request({
                  url: 'http://121.199.40.49:8443/login/login',
                  header: { 
                    //请求头和ajax写法一样
                     "Content-Type": "application/x-www-form-urlencoded"
                    }, 
                  data: require( 'utils/util.js' ).json2Form( {code:re.code,name:res.userInfo.nickName,avatar:res.userInfo.avatarUrl}),
                  method:"POST",
                  success(resp){
                    console.log("success here")
                    if (!resp || resp.data.code == 10001 || resp.data.code == 80002) {
                      resolve(false)
                      return wx.showToast({
                        icon:'none',
                        title: resp?.resultMsg ?? '登录失败'
                      })
                    }
                    wx.showToast({
                      icon:'none',
                      title: '登录成功'
                    })
                    console.log(resp)
                    console.log("uid:"+resp.data.data.openid)
                    console.log("uname"+ res.userInfo.nickName)
                    console.log("avatar"+ res.userInfo.avatarUrl)
                    // 在缓存中存储uid
                    wx.setStorageSync("uid", resp.data.data.openid)
                    wx.setStorageSync("uname", res.userInfo.nickName)
                    wx.setStorageSync("avatar", res.userInfo.avatarUrl)
                    resolve(true)
                  }
                })
              }
            }
          })
        }
        ,fail(res){
          console.log(res)
          if (res.errMsg=="getUserProfile:fail auth deny"){
            wx.showToast({
              icon:'none',
              title: '拒绝授权'
            })
          }
        }
      })
    })
  },
})
