// pages/post/post.js
import {request} from "../../request/index.js"
var Util = require( '../../utils/util.js' );
var content = ''
var check = 0
Page({
  data: {
    uname: wx.getStorageSync("uname"),
    avatar: wx.getStorageSync("avatar"),
  },

  //匿名勾选
  anonymousOrNot:function(e){
  if(e.detail.value==''){
      check=0
  }
  else{
      check=1
  }
  console.log("匿名勾选："+check);
  },
  //输入内容实时更新content
  input:function(e){
    content = e.detail.value
    },
    //发布按钮事件
    send:function(){
        var that = this;
        // var user_id = wx.getStorageSync('userid')
        console.log(content);
        if(content == ""){
          wx.showModal({
            title:'提示',
            content:'内容不能为空！'
          })
          return;
        }
        this.publishTreehole(wx.getStorageSync("uid"),content,check);
        check=0;
        
    },

    //发布动态 接口处理
    async publishTreehole(id,content,ano){
        const res = await request({
          url: 'http://121.199.40.49:8443/publish/publish',
          header: { 
            //请求头和ajax写法一样
             "Content-Type": "application/x-www-form-urlencoded"
            }, 
           data: Util.json2Form( {id:id,content:content,anonymous:ano}),
          // data:  {id:id,content:content,anonymous:ano},

          method: "POST",
          // timeout: 0,
          // success: (result) => {
          //     wx.showModal({
          //       title:'提示',
          //       content:'发布成功！',
          //       success:function(res){
          //         if(res.confirm){
          //           console.log("用户点击确定");
                    
          //            console.log(result);
          //           wx.switchTab({
          //             url: '/pages/discover/discover',
          //           })
          //         }
          //         else if (res.cancel) {    
          //           console.log('用户点击取消');    
          //       }    
          //       }
          //     })
          // },
          // fail: (res) => {
          //   wx.showModal({
          //       title:'提示',
          //       content:'发布失败！'
          //     })
              
          // },
        });
          console.log(res);
          if(res.data.code==0)
          {
            wx.showModal({
              title:'提示',
              content:'发布成功',
              success:function(res){
                if(res.confirm){
                    wx.switchTab({
                      url: '/pages/discover/discover',
                    })
                }
            
              }
            })
  
          }
          else if(res.data.code==50001){
            wx.showModal({
              title:'提示',
              content:'有敏感词',
            })
          }

    },
  onLoad: function (options) {
  },
 

})